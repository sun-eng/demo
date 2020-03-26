package com.nhsoft.module.demo.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SubjectTeacherDTO {

    private String teacherName;

    private String subjectName;

    private Double avgScore;

    private BigDecimal maxScore;

    private BigDecimal minScore;

}
