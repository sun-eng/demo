package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class StubjectYearDto {

    private String subjectName;

    private String studyYear;

    private Double avgScore;

    private BigDecimal maxScore;

    private BigDecimal minScore;

}
