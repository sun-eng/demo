package com.nhsoft.module.demo.data.model;

import com.nhsoft.provider.data.annotation.DataTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel
@DataTable("每年学科分数")
@Data
@AllArgsConstructor
public class SubjectScore {

    @ApiModelProperty("学科名称")
    private String subjectName;

    @ApiModelProperty("学年")
    private String studyYear;

    @ApiModelProperty("学分")
    private BigDecimal score;

}
