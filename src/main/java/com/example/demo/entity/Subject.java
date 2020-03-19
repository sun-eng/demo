package com.example.demo.entity;


import com.example.demo.util.DateTimeGenerator;
import lombok.Data;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "subject")
@Data
public class Subject {

    @Id
    private Long id;

    private String subNo;

    private String name;

    private String teaId;

    @GeneratorType(when = GenerationTime.INSERT, type = DateTimeGenerator.class)
    private Date gmtCreate;

    @GeneratorType(when = GenerationTime.ALWAYS, type = DateTimeGenerator.class)
    private Date gmtModified;

    @OneToMany
    @JoinColumn(name = "subId")
    private List<Relation> relation;

    @OneToMany
    @JoinColumn(name = "subId")
    private List<Teacher> teacher;

}
