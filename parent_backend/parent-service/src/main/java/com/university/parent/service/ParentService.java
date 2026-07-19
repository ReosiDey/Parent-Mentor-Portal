package com.university.parent.service;

import com.university.parent.client.MentorClient;
import com.university.parent.entity.Parent;
import com.university.parent.entity.ParentStudent;
import com.university.parent.entity.Student;
import com.university.parent.repository.ParentRepository;
import com.university.parent.repository.ParentStudentRepository;
import com.university.parent.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParentService {

    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final ParentStudentRepository parentStudentRepository; // Added this
    private final MentorClient mentorClient;

    public Parent getParentProfile(String username) {
        return parentRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Parent profile not found for: " + username));
    }

    @Transactional
    public String assignStudentToParent(String parentUsername, String rollNumber) {
        Parent parent = parentRepository.findByUsername(parentUsername)
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() -> new RuntimeException("Student with roll number " + rollNumber + " not found"));

        // Check against the new Mapping Repository
        if (parentStudentRepository.existsByParentIdAndStudentRollNumber(parent.getId(), student.getRollNumber())) {
            return "Student already assigned to this parent";
        }

        // Create the new mapping entry
        ParentStudent mapping = ParentStudent.builder()
                .parentId(parent.getId())
                .studentRollNumber(student.getRollNumber())
                .parentName(parent.getParentName()) // Storing parent name in mapping as requested
                .build();

        parentStudentRepository.save(mapping);
        return "Student assigned successfully";
    }

    /**
     * GET ATTENDANCE REPORT
     * Now uses the Mapping table to find student roll numbers
     */
    public List<Object> getDetailedAttendanceReport(String username) {
        Parent parent = getParentProfile(username);

        // Fetch mappings to get roll numbers
        List<ParentStudent> mappings = parentStudentRepository.findByParentId(parent.getId());

        return mappings.stream()
                .flatMap(mapping -> {
                    try {
                        // Call Mentor Service using rollNumber from mapping
                        List<Object> attendance = mentorClient.getStudentAttendance(mapping.getStudentRollNumber());
                        return attendance.stream();
                    } catch (Exception e) {
                        log.error("Failed to fetch attendance for roll number: {}", mapping.getStudentRollNumber());
                        return Stream.empty();
                    }
                })
                .toList();
    }

    /**
     * GET STUDENT PROGRESS
     */
    public List<Object> getStudentsProgress(String username) {
        Parent parent = getParentProfile(username);
        List<ParentStudent> mappings = parentStudentRepository.findByParentId(parent.getId());

        return mappings.stream()
                .map(mapping -> {
                    try {
                        return mentorClient.getStudentProgress(mapping.getStudentRollNumber());
                    } catch (Exception e) {
                        log.error("Failed to fetch progress for roll number: {}", mapping.getStudentRollNumber());
                        return null;
                    }
                })
                .filter(java.util.Objects::nonNull)
                .toList();
    }

    public Parent createParent(Parent parent) {
        return parentRepository.save(parent);
    }
}