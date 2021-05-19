package com.exam.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSON;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.Score;
import com.exam.entity.StudentScore;
import com.exam.serviceimpl.ScoreServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @GetMapping("/scores")
    public ApiResult findAll() {
        List<Score> res = scoreService.findAll();
        return ApiResultHandler.buildApiResult(200,"查询所有学生成绩",res);
    }
//    分页
    @GetMapping("/score/{page}/{size}/{studentId}")
    public ApiResult findById(@PathVariable Integer page, @PathVariable Integer size, @PathVariable Integer studentId) {
        System.out.println("分页查询成绩");
        Page<Score> scorePage = new Page<>(page, size);
        IPage<Score> res = scoreService.findById(scorePage, studentId);
        return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
    }

    @GetMapping("/scores/{page}/{size}/{studentId}")
    public ApiResult findByType(@PathVariable Integer page, @PathVariable Integer size, @PathVariable Integer studentId) {
        System.out.println("分页查询成绩");
        Page<StudentScore> scorePage = new Page<>(page, size);
        IPage<StudentScore> res = scoreService.findByType(scorePage, studentId);
        return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
    }

//    不分页
    @GetMapping("/score/{studentId}")
    public ApiResult findById(@PathVariable("studentId") Integer studentId) {
        System.out.println("不分页查询");
        List<Score> res = scoreService.findById(studentId);
        if (!res.isEmpty()) {
            return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "ID不存在", res);
        }
    }

    //    不分页
    @GetMapping("/scoress/{studentId}")
    public ApiResult findBytype(@PathVariable("studentId") Integer studentId) {
        System.out.println("不分页查询");
        List<StudentScore> res = scoreService.findBytype(studentId);
        if (!res.isEmpty()) {
            return ApiResultHandler.buildApiResult(200, "根据ID查询成绩", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "ID不存在", res);
        }
    }

    @PostMapping("/score")
    public ApiResult add(@RequestBody Score score) {
        int res = scoreService.add(score);
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400,"成绩添加失败",res);
        }else {
            return ApiResultHandler.buildApiResult(200,"成绩添加成功",res);
        }
    }

    @PostMapping("/scoress")
    public ApiResult add2(@RequestBody Score score) {
        int res = scoreService.add2(score);
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400,"成绩添加失败",res);
        }else {
            return ApiResultHandler.buildApiResult(200,"成绩添加成功",res);
        }
    }

    @GetMapping("/scores/{examCode}")
    public ApiResult findByExamCode(@PathVariable("examCode") Integer examCode) {
        List<Score> scores = scoreService.findByExamCode(examCode);
        return ApiResultHandler.buildApiResult(200,"查询成功",scores);
    }

    @RequestMapping("/scores/export")
    public ApiResult findall (@RequestBody Map<String, Object> columnMap,
                          HttpServletResponse response) throws IOException {

            List<StudentScore> res2 = scoreService.findscore();
            ExcelWriter writer = ExcelUtil.getWriter();
            //第一行内容
            writer.addHeaderAlias("studentId", "学号");
            writer.addHeaderAlias("studentName", "姓名");
            writer.addHeaderAlias("subject", "课程名称");
            writer.addHeaderAlias("etScore", "成绩");
            writer.addHeaderAlias("answerDate", "考试日期");
            System.out.println(res2);
            List<Map<String, Object>> maps = new ArrayList<>();
            if (CollUtil.isNotEmpty(res2)) {
                writer.write(res2, true); //写入到excel中
            }
            writer.setColumnWidth(0, 20); //第1列20px宽
            writer.setColumnWidth(1, 15); //第2列15px 宽
            writer.setColumnWidth(2, 20); //第3列20px宽
            writer.setColumnWidth(3, 10); //第4列10px 宽
            writer.setColumnWidth(4, 20); //第5列20px 宽
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=test.xls");
            ServletOutputStream out = response.getOutputStream();
            writer.flush(out, true);
            writer.close();
            IoUtil.close(out);
            return null;
    }

    @PutMapping("/updatescore/{score}/{examCode}/{studentId}")
    public ApiResult updateScore(@PathVariable("score") Integer score,@PathVariable("examCode") Integer examCode,@PathVariable("studentId") Integer studentId){
        int res = scoreService.updateScore(score,examCode,studentId);
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }


}
