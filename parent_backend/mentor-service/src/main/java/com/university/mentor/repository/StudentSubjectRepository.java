package com.university.mentor.repository;

import com.university.mentor.entity.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {

    // This fixes the "Cannot resolve method" error in assignSubjectToStudent
    boolean existsByStudent_RollNumberAndSubject_Id(String rollNumber, Long subjectId);

    // This fixes the "Cannot resolve method" error in saveAttendance
    Optional<StudentSubject> findByStudent_RollNumberAndSubject_Id(String rollNumber, Long subjectId);
}