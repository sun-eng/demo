package com.example.demo.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QStubjectYear {

    private String subjectName;

    private String studyYear;

    private Double avgScore;

    private BigDecimal maxScore;

    private BigDecimal minScore;

    public QStubjectYear(String subjectName, String studyYear, Double avgScore, BigDecimal maxScore, BigDecimal minScore) {
        this.subjectName = subjectName;
        this.studyYear = studyYear;
        this.avgScore = avgScore;
        this.maxScore = maxScore;
        this.minScore = minScore;
    }
}
