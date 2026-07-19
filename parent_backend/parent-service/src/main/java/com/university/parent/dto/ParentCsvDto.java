package com.university.parent.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentCsvDto {

    @CsvBindByName(column = "Roll Number") // Matches CSV header
    private String rollNumber;

    @CsvBindByName(column = "Full Name") // Matches CSV header
    private String studentName;

    @CsvBindByName(column = "Parent Name") // Matches CSV header
    private String parentName;

    @CsvBindByName(column = "Phone Number") // Matches CSV header
    private String phoneNumber;
}