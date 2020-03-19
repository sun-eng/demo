package com.example.demo.controller;

import com.example.demo.entity.QStubjectYear;
import com.example.demo.entity.QSubjectTeacher;
import com.example.demo.entity.QTeacherYear;
import com.example.demo.exception.DemoException;
import com.example.demo.service.TeacherService;
import com.example.demo.util.CommonPage;
import com.nhsoft.provider.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "老师查询")
@RestController
@RequestMapping("/tea")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "教导主任查询每学年学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByYear")
    public Response<CommonPage<QStubjectYear>> findScoreByYear(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws DemoException {
        CommonPage<QStubjectYear> page = teacherService.findScoreByYear(teaNo, pageSize, pageNum);
        return Response.data(page);
    }

    @ApiOperation(value = "教导主任查询教师-学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByTea")
    public Response<CommonPage<QSubjectTeacher>> findScoreByTea(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws DemoException {
        CommonPage<QSubjectTeacher> page = teacherService.findScoreByTea(teaNo, pageSize, pageNum);
        return Response.data(page);
    }

    @ApiOperation(value = "查询教师本人每学年，学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByTeaNo")
    public Response<CommonPage<QTeacherYear>> findScoreByTeaNo(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws DemoException {
        CommonPage<QTeacherYear> page = teacherService.findScoreByTeaNo(teaNo, pageSize, pageNum);
        return Response.data(page);
    }

}
