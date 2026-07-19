package com.university.mentor.repository;

import com.university.mentor.entity.StudentAttendance;
import com.university.mentor.entity.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {

    // Now we only look for the mapping.
    // This ensures there is only ONE row per student per subject.
    Optional<StudentAttendance> findByStudentSubject(StudentSubject studentSubject);

    List<StudentAttendance> findByStudentSubject_Student_RollNumber(String rollNumber);
}