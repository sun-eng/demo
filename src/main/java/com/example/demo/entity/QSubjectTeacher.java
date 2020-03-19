package com.example.demo.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QSubjectTeacher {

    private String teatcherName;

    private String subjectName;

    private Double avgScore;

    private BigDecimal maxScore;

    private BigDecimal minScore;

    public QSubjectTeacher(String teatcherName, String subjectName, Double avgScore, BigDecimal maxScore, BigDecimal minScore) {
        this.teatcherName = teatcherName;
        this.subjectName = subjectName;
        this.avgScore = avgScore;
        this.maxScore = maxScore;
        this.minScore = minScore;
    }
}
