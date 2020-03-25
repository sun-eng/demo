package com.nhsoft.module.demo.data.model;

import com.nhsoft.provider.data.annotation.DataTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel
@DataTable("学科-年份成绩")
@Data
@AllArgsConstructor
public class SubjectYear {

    @ApiModelProperty("学科名称")
    private String subjectName;

    @ApiModelProperty("学年")
    private String studyYear;

    @ApiModelProperty("平均成绩")
    private Double avgScore;

    @ApiModelProperty("最高成绩")
    private BigDecimal maxScore;

    @ApiModelProperty("最低成绩")
    private BigDecimal minScore;

}
