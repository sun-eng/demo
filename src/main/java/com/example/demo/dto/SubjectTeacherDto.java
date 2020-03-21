package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SubjectTeacherDto {

    private String teatcherName;

    private String subjectName;

    private Double avgScore;

    private BigDecimal maxScore;

    private BigDecimal minScore;

}
