package com.example.demo.entity;


import com.example.demo.util.DateTimeGenerator;
import lombok.Data;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;


@Entity(name = "teacher")
@Data
public class Teacher {

    @Id
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
