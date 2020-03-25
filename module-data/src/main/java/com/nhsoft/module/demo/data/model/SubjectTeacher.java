package com.nhsoft.module.demo.data.model;

import com.nhsoft.provider.data.annotation.DataTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel
@DataTable("老师学科成绩")
@Data
@AllArgsConstructor
public class SubjectTeacher {

    @ApiModelProperty("老师姓名")
    private String teatcherName;

    @ApiModelProperty("学科名称")
    private String subjectName;

    @ApiModelProperty("平均分数")
    private Double avgScore;

    @ApiModelProperty("最高分")
    private BigDecimal maxScore;

    @ApiModelProperty("最低分")
    private BigDecimal minScore;

}
