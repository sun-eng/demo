package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SubjectYear {

    private String subjectName;

    private String studyYear;

    private Double avgScore;

    private BigDecimal maxScore;

    private BigDecimal minScore;

}
