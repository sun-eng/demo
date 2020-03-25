package com.nhsoft.module.demo.data.model;


import com.nhsoft.module.demo.data.util.DateTimeGenerator;
import com.nhsoft.provider.data.annotation.DataTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ApiModel
@DataTable("老师")
@Entity(name = "teacher")
@Data
@DynamicUpdate
public class Teacher {

    @ApiModelProperty(value = "ID", example = "0")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("老师编号")
    private String teaNo;

    @ApiModelProperty("老师姓名")
    private String name;

    @ApiModelProperty("老师性别")
    private String sex;

    @ApiModelProperty("年纪")
    private String age;

    @ApiModelProperty("学科id")
    private Long subId;

    @ApiModelProperty("是否教导主任，1是，0不是")
    private String isAdmin;

    @ApiModelProperty("创建时间")
    @GeneratorType(when = GenerationTime.INSERT, type = DateTimeGenerator.class)
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @GeneratorType(when = GenerationTime.ALWAYS, type = DateTimeGenerator.class)
    private Date gmtModified;

    @ApiModelProperty("关联关系表")
    @OneToMany
    @JoinColumn(name = "teaId")
    private List<StuTeaSubRelation> stuTeaSubRelations;
}
