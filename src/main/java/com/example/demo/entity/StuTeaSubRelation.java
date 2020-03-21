package com.example.demo.entity;


import com.example.demo.util.DateTimeGenerator;
import lombok.Data;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity(name = "relation")
public class StuTeaSubRelation {

    @Id
    private Long id;

    private Long stuId;

    private Long teaId;

    private Long subId;

    private BigDecimal score;

    private String stuYear;

    @GeneratorType(when = GenerationTime.INSERT, type = DateTimeGenerator.class)
    private Date gmtCreate;

    @GeneratorType(when = GenerationTime.ALWAYS, type = DateTimeGenerator.class)
    private Date gmtModified;

}
