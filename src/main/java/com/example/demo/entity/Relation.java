package com.example.demo.entity;


import com.example.demo.util.DateTimeGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "关系类")
@Data
@Entity(name = "relation")
public class Relation {

    @ApiModelProperty(value = "ID", example = "0")
    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty("学号")
    private String stuNo;

    @ApiModelProperty("老师编号")
    private String teaNo;

    @ApiModelProperty("学科编号")
    private String subNo;

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
