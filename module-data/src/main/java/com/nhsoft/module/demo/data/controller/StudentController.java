package com.nhsoft.module.demo.data.controller;


import com.nhsoft.module.demo.data.dto.StuTeaSubRelationDTO;
import com.nhsoft.module.demo.data.dto.SubjectScoreDTO;
import com.nhsoft.module.demo.data.model.StuTeaSubRelation;
import com.nhsoft.module.demo.data.model.Student;
import com.nhsoft.module.demo.data.model.SubjectScore;
import com.nhsoft.module.demo.data.service.StudentService;
import com.nhsoft.module.demo.data.util.CommonPage;
import com.nhsoft.provider.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "学生查询")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "学生查询成绩")
    @RequestMapping("nhsoft.demo.stu.find")
    public Response find(@ApiParam("学号") @RequestParam("stuNo") String stuNo,
                         @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                         @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<SubjectScore> subjectScores = studentService.findAllByStuNo(stuNo, offset, limit);
        List<SubjectScoreDTO> subjectScoreDTOs = new ArrayList<>();
        if (subjectScores != null && !subjectScores.isEmpty()) {
            for (SubjectScore subjectScore : subjectScores) {
                SubjectScoreDTO subjectScoreDTO = new SubjectScoreDTO();
                BeanUtils.copyProperties(subjectScore, subjectScoreDTO);
                subjectScoreDTOs.add(subjectScoreDTO);
            }
        }
        int total = studentService.sumResultByStuNo(stuNo);
        CommonPage<SubjectScoreDTO> page = CommonPage.restPage(subjectScoreDTOs, offset, limit, total);
        return Response.data(page);

    }

    @ApiOperation(value = "查询所有学生成绩")
    @RequestMapping("nhsoft.demo.stu.findAll")
    public Response findAll() {
        List<StuTeaSubRelation> stuTeaSubRelations = studentService.findAll();
        List<StuTeaSubRelationDTO> stuTeaSubRelationDTOs = new ArrayList<>();
        if (stuTeaSubRelations != null && !stuTeaSubRelations.isEmpty()) {
            for (StuTeaSubRelation stuTeaSubRelation : stuTeaSubRelations) {
                StuTeaSubRelationDTO stuTeaSubRelationDTO = new StuTeaSubRelationDTO();
                BeanUtils.copyProperties(stuTeaSubRelation, stuTeaSubRelationDTO);
                stuTeaSubRelationDTOs.add(stuTeaSubRelationDTO);
            }
        }
        return Response.data(stuTeaSubRelationDTOs);
    }

    @ApiOperation(value = "保存学生成绩")
    @RequestMapping("nhsoft.demo.stu.save")
    public Response save(Student student) {
        studentService.saveStudent(student);
        return Response.empty();
    }

    @ApiOperation(value = "更新学生成绩")
    @RequestMapping("nhsoft.demo.stu.update")
    public Response update(Student student) {
        studentService.updateStudent(student);
        return Response.empty();
    }
}
