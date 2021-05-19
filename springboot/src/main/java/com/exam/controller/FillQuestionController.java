package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.*;
import com.exam.serviceimpl.FillQuestionServiceImpl;
import com.exam.serviceimpl.PaperServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.util.ImportFill_Util;
import com.exam.util.ImportJudge_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FillQuestionController {

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;

    @Autowired
    private PaperServiceImpl paperService;

    @PostMapping("/fillQuestion")
    public ApiResult add(@RequestBody FillQuestion fillQuestion) {
        int res = fillQuestionService.add(fillQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    @GetMapping("/fillQuestionIds")
    public ApiResult findOnlyQuestionId() {
        FillQuestion res = fillQuestionService.findOnlyQuestionId();
        System.out.println("+++++++++++++++++"+res);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    @PostMapping("/importFill")
    public ApiResult AddFill(MultipartFile file){
        // 1.自定义一个工具类拿到要解析的文件并解析成要存储的数据
        List<FillQuestion> list = ImportFill_Util.excel2Employee3(file);
        // 2.遍历输出你解析的数据格式是否正确
        System.out.println(list);
        for (FillQuestion fillQuestion : list) {
            System.out.println(fillQuestion.toString());
        }

//         3.进行数据库添加操作
        if (fillQuestionService.AddFill(list) == 1) {
            System.out.println("数据库++++++++++");
            return ApiResultHandler.buildApiResult(200,"上传成功",null);

        }
        return ApiResultHandler.buildApiResult(400,"上传失败",null);

    }

    @PostMapping("/importFill2")
    public ApiResult add2(@RequestParam(value="paperId",required=false) Integer paperId,MultipartFile file) {
        // 1.自定义一个工具类拿到要解析的文件并解析成要存储的数据
        List<FillQuestion> list = ImportFill_Util.excel2Employee3(file);
        // 2.遍历输出你解析的数据格式是否正确
//        System.out.println(list);
        for (FillQuestion fillQuestion : list) {
            System.out.println(fillQuestion.toString());

        }

        // 插入题目之前的最大ID
        Integer Id1 = fillQuestionService.findOnlyQuestionId().getQuestionId();
//         3.进行数据库添加操作
        if (fillQuestionService.AddFill(list) == 1) {
//            System.out.println("---------++++++++++++++"+list.getQuestionId());
//            paperService.add(paperId,1,multiQuestionService.findOnlyQuestionId());
            System.out.println("数据库++++++++++");

            return ApiResultHandler.buildApiResult(200, "上传成功", null);

        }

        // 插入题目之后的最大ID
        Integer Id2 = fillQuestionService.findOnlyQuestionId().getQuestionId();

        for (Integer i = Id1+1;i<=Id2;i++){
            PaperManage paperManage = new PaperManage(paperId, 3,i);
            paperService.add(paperManage);
        }
        return ApiResultHandler.buildApiResult(400, "上传失败", null);

    }

    @DeleteMapping("/deleteFill/{questionId}")
    public ApiResult deleteFill(@PathVariable("questionId") Integer questionId){
        int res = fillQuestionService.deleteFill(questionId);
//        System.out.println("==========================="+questionId);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }

    @GetMapping("/selectFillById/{questionId}")
    public ApiResult selectbyId(@PathVariable("questionId") Integer questionId){
        System.out.println("根据ID查找");
        FillQuestion res = fillQuestionService.selectbyId(questionId);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"题目编号不存在",null);
        }
        return ApiResultHandler.buildApiResult(200,"请求成功！",res);
    }

    @PutMapping("/fill")
    public ApiResult update(@RequestBody FillQuestion fillQuestion){
        int res = fillQuestionService.update(fillQuestion);
//        if (res == 0) {
//            return ApiResultHandler.buildApiResult(20000,"请求参数错误");
//        }
        System.out.print("更新操作执行---");
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @GetMapping("/fillnum/{section}/{subject}")
    public ApiResult judgenum(@PathVariable("section") String section,@PathVariable("subject") String subject){
        int res = fillQuestionService.fillnum(section,subject);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    @GetMapping("/fillerror/{questionId}/{studentId}")
    public ApiResult fillError(@PathVariable("questionId") Integer questionId,@PathVariable("studentId") Integer studentId){
        int res = fillQuestionService.fillError(questionId,studentId);
        return ApiResultHandler.buildApiResult(200,"插入成功",res);
    }

    @GetMapping("/fillerrors/{page}/{size}/{studentId}")
    public ApiResult judgeErrors(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("studentId") Integer studentId){
        Page<FillError> fillErrorPage = new Page<>(page,size);
        IPage<FillError> fillErrorIPage= fillQuestionService.fillErrors(fillErrorPage,studentId);
        return ApiResultHandler.buildApiResult(200,"查询该学生判断题错题",fillErrorIPage);
    }

    @DeleteMapping("/deleteErrorFill/{questionId}")
    public ApiResult deleteError(@PathVariable("questionId") Integer questionId){
        int res = fillQuestionService.deleteError(questionId);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }
}
