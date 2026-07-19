package com.university.parent.repository;

import com.university.parent.entity.ParentStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParentStudentRepository extends JpaRepository<ParentStudent, Long> {

    // Find all students linked to a specific parent ID
    List<ParentStudent> findByParentId(Long parentId);

    // Check if a specific mapping already exists to prevent duplicates
    boolean existsByParentIdAndStudentRollNumber(Long parentId, String studentRollNumber);

    // Find the parent name associated with a specific student
    List<ParentStudent> findByStudentRollNumber(String studentRollNumber);
}