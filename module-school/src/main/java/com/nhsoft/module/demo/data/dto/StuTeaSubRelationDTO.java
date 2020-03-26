package com.nhsoft.module.demo.data.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class StuTeaSubRelationDTO {

    private Long id;

    private Long stuId;

    private Long teaId;

    private Long subId;

    private BigDecimal score;

    private String stuYear;

    private Date gmtCreate;

    private Date gmtModified;

}
