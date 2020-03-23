package com.example.demo.controller;


import com.example.demo.dto.SubjectScoreDTO;
import com.example.demo.entity.StuTeaSubRelationDO;
import com.example.demo.service.StudentService;
import com.example.demo.util.CommonPage;
import com.nhsoft.provider.common.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "学生查询成绩")
    @RequestMapping("/find")
    public Response find(@ApiParam("学号") @RequestParam("stuNo") String stuNo,
                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<SubjectScoreDTO> subjectScoreDTOs = studentService.findAllByStuNo(stuNo, pageSize, pageNum);
        int total = studentService.sumResultByStuNo(stuNo);
        CommonPage<SubjectScoreDTO> page = CommonPage.restPage(subjectScoreDTOs, pageNum, pageSize, total);
        return Response.data(page);

    }

    @RequestMapping("/findAll")
    public Response findAll() {
        List<StuTeaSubRelationDO> stuTeaSubRelationDOs = studentService.findAll();
        return Response.data(stuTeaSubRelationDOs);
    }
}
