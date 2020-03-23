package com.example.demo.controller;

import com.example.demo.dto.SubjectTeacherDTO;
import com.example.demo.dto.SubjectYearDTO;
import com.example.demo.dto.TeacherYearDTO;
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

import java.util.List;

@Api(tags = "老师查询")
@RestController
@RequestMapping("/tea")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "教导主任查询每学年学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByYear")
    public Response findScoreByYear(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<SubjectYearDTO> list = teacherService.findScoreByYear(teaNo, pageSize, pageNum);
        int total = teacherService.sumResultByYear(teaNo);
        CommonPage<SubjectYearDTO> page = CommonPage.restPage(list, pageNum, pageSize, total);
        return Response.data(page);
    }

    @ApiOperation(value = "教导主任查询教师-学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByTea")
    public Response findScoreByTea(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<SubjectTeacherDTO> list = teacherService.findScoreByTea(teaNo, pageSize, pageNum);
        int total = teacherService.sumResultByTea(teaNo);
        CommonPage<SubjectTeacherDTO> page = CommonPage.restPage(list, pageNum, pageSize, total);
        return Response.data(page);
    }

    @ApiOperation(value = "查询教师本人每学年，学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByTeaNo")
    public Response findScoreByTeaNo(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<TeacherYearDTO> list = teacherService.findScoreByTeaNo(teaNo, pageSize, pageNum);
        int total = teacherService.sumResultByTeaNo(teaNo);
        CommonPage<TeacherYearDTO> page = CommonPage.restPage(list, pageNum, pageSize, total);
        return Response.data(page);
    }

}
