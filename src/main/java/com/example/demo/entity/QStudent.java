package com.example.demo.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QStudent {

    private String subjectName;

    private String studyYear;

    private BigDecimal score;

    public QStudent(String subjectName, String studyYear, BigDecimal score) {
        this.subjectName = subjectName;
        this.studyYear = studyYear;
        this.score = score;
    }
}
