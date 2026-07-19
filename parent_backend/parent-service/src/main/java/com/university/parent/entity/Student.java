package com.university.parent.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This must be unique to be used as a referenced column
    @Column(unique = true, nullable = false)
    private String rollNumber;

    private String name;
}