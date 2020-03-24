package com.example.demo.controller;


import com.example.demo.dto.StuTeaSubRelationDTO;
import com.example.demo.dto.SubjectScoreDTO;
import com.example.demo.entity.StuTeaSubRelation;
import com.example.demo.entity.Student;
import com.example.demo.entity.SubjectScore;
import com.example.demo.service.StudentService;
import com.example.demo.util.CommonPage;
import com.nhsoft.provider.common.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "学生查询成绩")
    @RequestMapping("/find")
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

    @RequestMapping("/findAll")
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

    @RequestMapping("/save")
    public Response save(Student student){
        studentService.saveStudent(student);
        return Response.empty();
    }

    @RequestMapping("/update")
    public Response update(Student student){
        studentService.updateStudent(student);
        return Response.empty();
    }
}
