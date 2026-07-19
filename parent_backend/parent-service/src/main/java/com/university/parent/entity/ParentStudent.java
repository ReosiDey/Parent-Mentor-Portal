package com.university.parent.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parent_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The Parent ID (FK)
    private Long parentId;

    // The Student Roll Number (FK)
    private String studentRollNumber;

    // THE EXTRA FIELD YOU WANT
    private String parentName;
}