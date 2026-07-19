package com.university.mentor.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rollNumber;      // 🔥 IMPORTANT (for parent mapping)

    private String studentName;

//    private String subject;

    private Double cgpa;            // 🔥 Added

    private int backlog;            // 🔥 Added

    private String remarks;

    private String mentorUsername;  // who uploaded
}
