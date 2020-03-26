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
import java.util.Date;

@ApiModel
@Entity(name = "student")
@Data
@DataTable("学生")
@DynamicUpdate
public class Student {

    @ApiModelProperty(value = "ID", example = "0")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("学号")
    private String stuNo;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("创建时间")
    @GeneratorType(when = GenerationTime.INSERT, type = DateTimeGenerator.class)
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @GeneratorType(when = GenerationTime.ALWAYS, type = DateTimeGenerator.class)
    private Date gmtModified;

}
