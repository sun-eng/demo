package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SubjectScore {

    private String subjectName;

    private String studyYear;

    private BigDecimal score;

}
