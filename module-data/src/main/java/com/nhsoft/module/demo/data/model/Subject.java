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
@DataTable("课程")
@Entity(name = "subject")
@Data
@DynamicUpdate
public class Subject {

    @ApiModelProperty(value = "ID", example = "0")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("课程编号")
    private String subNo;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("老师id")
    private Long teaId;

    @ApiModelProperty("创建时间")
    @GeneratorType(when = GenerationTime.INSERT, type = DateTimeGenerator.class)
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @GeneratorType(when = GenerationTime.ALWAYS, type = DateTimeGenerator.class)
    private Date gmtModified;

    @ApiModelProperty("关联关系表")
    @OneToMany
    @JoinColumn(name = "subId")
    private List<StuTeaSubRelation> stuTeaSubRelations;

    @ApiModelProperty("关联老师表")
    @OneToMany
    @JoinColumn(name = "subId")
    private List<Teacher> teachers;

}
