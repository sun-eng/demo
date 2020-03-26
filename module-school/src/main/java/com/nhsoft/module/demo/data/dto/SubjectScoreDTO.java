package com.nhsoft.module.demo.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SubjectScoreDTO {

    private String subjectName;

    private String studyYear;

    private BigDecimal score;

}
