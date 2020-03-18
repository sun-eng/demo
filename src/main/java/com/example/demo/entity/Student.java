package com.example.demo.entity;


import com.example.demo.util.DateTimeGenerator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@ApiModel(value = "学生类")
@Entity(name = "student")
@Data
public class Student {

    @ApiModelProperty(value = "ID", example = "0")
    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty("学号")
    private String stuNo;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年纪")
    private String age;

    @ApiModelProperty("创建时间")
    @GeneratorType(when = GenerationTime.INSERT, type = DateTimeGenerator.class)
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @GeneratorType(when = GenerationTime.ALWAYS, type = DateTimeGenerator.class)
    private Date gmtModified;

}
