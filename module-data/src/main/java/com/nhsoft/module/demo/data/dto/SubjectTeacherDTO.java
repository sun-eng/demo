package com.nhsoft.module.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectTeacherDTO {

    private String teatcherName;

    private String subjectName;

    private Double avgScore;

    private BigDecimal maxScore;

    private BigDecimal minScore;

}
