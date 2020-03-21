package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class StudentDto {

    private String subjectName;

    private String studyYear;

    private BigDecimal score;

}
