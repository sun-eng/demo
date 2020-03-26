package com.nhsoft.module.demo.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SubjectYearDTO {

    private String subjectName;

    private String studyYear;

    private Double avgScore;

    private BigDecimal maxScore;

    private BigDecimal minScore;

}
