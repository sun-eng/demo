package com.example.demo.entity;


import com.example.demo.util.DateTimeGenerator;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity(name = "teacher")
@Data
@DynamicUpdate
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String teaNo;

    private String name;

    private String sex;

    private String age;

    private Long subId;

    private String isAdmin;

    @GeneratorType(when = GenerationTime.INSERT, type = DateTimeGenerator.class)
    private Date gmtCreate;

    @GeneratorType(when = GenerationTime.ALWAYS, type = DateTimeGenerator.class)
    private Date gmtModified;

    @OneToMany
    @JoinColumn(name = "teaId")
    private List<StuTeaSubRelation> stuTeaSubRelations;
}
