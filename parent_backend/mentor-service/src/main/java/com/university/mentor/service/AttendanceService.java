package com.university.mentor.service;

import com.university.mentor.entity.*;
import com.university.mentor.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final StudentSubjectRepository studentSubjectRepository;
    private final StudentAttendanceRepository attendanceRepository;

    // Inside AttendanceService.java


    public void saveAttendance(String rollNumber, String studentName, String subjectName,
                               Integer totalClasses, Integer absences, Integer present,
                               Double percent, LocalDate recordDate) {

        // 1. Find or create Subject
        Subject subject = subjectRepository.findBySubjectName(subjectName)
                .orElseGet(() -> subjectRepository.save(Subject.builder().subjectName(subjectName).build()));

        // 2. Find or create Student
        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseGet(() -> studentRepository.save(Student.builder().rollNumber(rollNumber).name(studentName).build()));

        // 3. Find or create Mapping
        StudentSubject studentSubject = studentSubjectRepository
                .findByStudent_RollNumberAndSubject_Id(rollNumber, subject.getId())
                .orElseGet(() -> studentSubjectRepository.save(StudentSubject.builder().student(student).subject(subject).build()));

        // 4. OVERRIDE LOGIC
        // We search ONLY by studentSubject.
        // If a record exists from Feb 20, and we upload Feb 25, it finds the Feb 20 row and UPDATES it.
        StudentAttendance attendance = attendanceRepository.findByStudentSubject(studentSubject)
                .orElse(new StudentAttendance());

        attendance.setStudentSubject(studentSubject);
        attendance.setRollNumber(rollNumber);
        attendance.setSubjectName(subjectName);
        attendance.setTotalClasses(totalClasses);
        attendance.setAbsences(absences);
        attendance.setPresent(present);
        attendance.setAttendancePercent(percent);

        // This now acts as a "Last Updated On" date
        attendance.setRecordDate(recordDate);

        attendanceRepository.save(attendance);
    }



    public String assignSubjectToStudent(Long subjectId, String rollNumber) {

        // 1. Fetch Subject & Student (to ensure they exist)
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // 2. Prevent duplicate assignment using the new Repository method
        boolean exists = studentSubjectRepository.existsByStudent_RollNumberAndSubject_Id(rollNumber, subjectId);

        if (exists) {
            return "Subject already mapped to this student";
        }

        // 3. Create the Mapping Object
        StudentSubject mapping = StudentSubject.builder()
                .student(student)   // Pass the whole object
                .subject(subject)   // Pass the whole object
                .build();

        // 4. Save to the StudentSubject table
        studentSubjectRepository.save(mapping);

        return "Subject '" + subject.getSubjectName() + "' assigned to " + student.getName();
    }
}