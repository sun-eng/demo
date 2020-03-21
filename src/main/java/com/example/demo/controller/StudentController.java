package com.example.demo.controller;


import com.example.demo.dto.StudentDto;
import com.example.demo.entity.StuTeaSubRelation;
import com.example.demo.exception.DemoException;
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
        try {
            List<StudentDto> list = studentService.findAllByStuNo(stuNo, pageSize, pageNum);
            int total = studentService.sumResultByStuNo(stuNo);
            CommonPage<StudentDto> page = CommonPage.restPage(list, pageNum, pageSize, total);
            return Response.data(page);
        } catch (DemoException e) {
            return Response.error(500, e.getMessage());
        }
    }

    @RequestMapping("/findAll")
    public Response findAll() {
        try {
            List<StuTeaSubRelation> page = studentService.findAll();
            return Response.data(page);
        } catch (DemoException e) {
            return Response.error(500, e.getMessage());
        }

    }
}
