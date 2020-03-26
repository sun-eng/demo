package com.nhsoft.module.demo.data.model;


import com.nhsoft.module.demo.data.util.DateTimeGenerator;
import com.nhsoft.provider.data.annotation.DataTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel
@DataTable("老师学生课程关系")
@Data
@Entity(name = "stu_tea_sub_relation")
@DynamicUpdate
public class StuTeaSubRelation {

    @ApiModelProperty(value = "ID", example = "0")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("学生id")
    private Long stuId;

    @ApiModelProperty("老师id")
    private Long teaId;

    @ApiModelProperty("学科id")
    private Long subId;

    @ApiModelProperty("分数")
    private BigDecimal score;

    @ApiModelProperty("学年")
    private String stuYear;

    @ApiModelProperty("创建时间")
    @GeneratorType(when = GenerationTime.INSERT, type = DateTimeGenerator.class)
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @GeneratorType(when = GenerationTime.ALWAYS, type = DateTimeGenerator.class)
    private Date gmtModified;

}
