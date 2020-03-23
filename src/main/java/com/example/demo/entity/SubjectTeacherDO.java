package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SubjectTeacherDO {

    private String teatcherName;

    private String subjectName;

    private Double avgScore;

    private BigDecimal maxScore;

    private BigDecimal minScore;

}
