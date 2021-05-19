package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.ExamManage;
import com.exam.serviceimpl.ExamManageServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExamManageController {

    @Autowired
    private ExamManageServiceImpl examManageService;

    @GetMapping("/examss/{grade}/{major}")
    public ApiResult findBymajor(@PathVariable("grade") Integer grade, @PathVariable("major") String major){
        System.out.println("不分页查询所有试卷--------");
        ApiResult apiResult;
        List<ExamManage> all = examManageService.findBymajor(grade,major);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }


    @GetMapping("/subjects/{grade}/{major}")
    public ApiResult findBysubjects(@PathVariable("grade") Integer grade, @PathVariable("major") String major){
        System.out.println("不分页查询所有课程--------");
        ApiResult apiResult;
        List<ExamManage> all = examManageService.findBysubjects(grade,major);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @GetMapping("/subjectPs/{grade}/{major}")
    public ApiResult findBysubjectPs(@PathVariable("grade") Integer grade, @PathVariable("major") String major){
        System.out.println("不分页查询所有课程--------");
        ApiResult apiResult;
        List<ExamManage> all = examManageService.findBysubjectPs(grade,major);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @GetMapping("/exams")
    public ApiResult findall(){
        System.out.println("不分页查询所有试卷");
        ApiResult apiResult;
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", examManageService.findAll());
        return apiResult;
    }

    @GetMapping("/exams/{page}/{size}/{grade}/{major}/{subject}")
    public ApiResult findBygrade(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("grade") Integer grade, @PathVariable("major") String major,@PathVariable("subject") String subject){
        System.out.println("分页查询所有试卷");
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findBygrade(examManage,grade,major,subject);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @GetMapping("/exam/{page}/{size}/{grade}/{major}/{subject}")
    public ApiResult findBygenre(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("grade") Integer grade, @PathVariable("major") String major,@PathVariable("subject") String subject){
        System.out.println("分页查询所有试卷+++++++++++++");
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findBygenre(examManage,grade,major,subject);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @GetMapping("/subject/{page}/{size}/{grade}/{major}")
    public ApiResult findBysubject(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("grade") Integer grade, @PathVariable("major") String major){
        System.out.println("分页查询所有课程");
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findBysubject(examManage,grade,major);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @GetMapping("/subjectP/{page}/{size}/{grade}/{major}")
    public ApiResult findBysubjectP(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("grade") Integer grade, @PathVariable("major") String major){
        System.out.println("分页查询所有课程");
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findBysubjectP(examManage,grade,major);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @GetMapping("/exams/{page}/{size}/{teacherId}")
    public ApiResult findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("teacherId") Integer teacherId){
        System.out.println("分页查询所有试卷--------");
        ApiResult apiResult;
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findAll(examManage,teacherId);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @GetMapping("/exam/{examCode}")
    public ApiResult findById(@PathVariable("examCode") Integer examCode){
        System.out.println("根据ID查找");
        ExamManage res = examManageService.findById(examCode);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"考试编号不存在",null);
        }
        return ApiResultHandler.buildApiResult(200,"请求成功！",res);
    }

    @DeleteMapping("/exam/{examCode}")
    public ApiResult deleteById(@PathVariable("examCode") Integer examCode){
        int res = examManageService.delete(examCode);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }

    @PutMapping("/exam")
    public ApiResult update(@RequestBody ExamManage exammanage){
        int res = examManageService.update(exammanage);
//        if (res == 0) {
//            return ApiResultHandler.buildApiResult(20000,"请求参数错误");
//        }
        System.out.print("更新操作执行---");
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @PostMapping("/exam")
    public ApiResult add(@RequestBody ExamManage exammanage){
        int res = examManageService.add(exammanage);
        System.out.println(exammanage);
        if ( res ==1) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        } else {
            return  ApiResultHandler.buildApiResult(400,"添加失败",res);
        }
    }

    @GetMapping("/examManagePaperId")
    public ApiResult findOnlyPaperId() {
        ExamManage res = examManageService.findOnlyPaperId();
        if (res != null) {
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"请求失败",res);
    }


}
