package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.*;
import com.exam.serviceimpl.JudgeQuestionServiceImpl;
import com.exam.serviceimpl.PaperServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.util.ImportJudge_Util;
import com.exam.util.ImportMulti_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class JudgeQuestionController {

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    private PaperServiceImpl paperService;

    @PostMapping("/judgeQuestion")
    public ApiResult add(@RequestBody JudgeQuestion judgeQuestion) {
        int res = judgeQuestionService.add(judgeQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    @GetMapping("/judgeQuestionId")
    public ApiResult findOnlyQuestionId() {
        JudgeQuestion res = judgeQuestionService.findOnlyQuestionId();
        return  ApiResultHandler.buildApiResult(200,"查询成功",res);
    }


    @PostMapping("/importJudge")
    public ApiResult AddJudge(MultipartFile file){
        // 1.自定义一个工具类拿到要解析的文件并解析成要存储的数据
        List<JudgeQuestion> list = ImportJudge_Util.excel2Employee2(file);
        // 2.遍历输出你解析的数据格式是否正确
        System.out.println(list);
        for (JudgeQuestion judgeQuestion : list) {
            System.out.println(judgeQuestion.toString());
        }

//         3.进行数据库添加操作
        if (judgeQuestionService.AddJudeg(list) == 1) {
            System.out.println("数据库++++++++++");
            return ApiResultHandler.buildApiResult(200,"上传成功",null);

        }
        return ApiResultHandler.buildApiResult(400,"上传失败",null);

    }

    @PostMapping("/importJudge2")
    public ApiResult add2(@RequestParam(value="paperId",required=false) Integer paperId,MultipartFile file) {
        // 1.自定义一个工具类拿到要解析的文件并解析成要存储的数据
        List<JudgeQuestion> list = ImportJudge_Util.excel2Employee2(file);
        // 2.遍历输出你解析的数据格式是否正确
//        System.out.println(list);
        for (JudgeQuestion judgeQuestion : list) {
            System.out.println(judgeQuestion.toString());

        }

        // 插入题目之前的最大ID
        Integer Id1 = judgeQuestionService.findOnlyQuestionId().getQuestionId();
//         3.进行数据库添加操作
        if (judgeQuestionService.AddJudeg(list) == 1) {
//            System.out.println("---------++++++++++++++"+list.getQuestionId());
//            paperService.add(paperId,1,multiQuestionService.findOnlyQuestionId());
            System.out.println("数据库++++++++++");

            return ApiResultHandler.buildApiResult(200, "上传成功", null);

        }

        // 插入题目之后的最大ID
        Integer Id2 = judgeQuestionService.findOnlyQuestionId().getQuestionId();

        for (Integer i = Id1+1;i<=Id2;i++){
            PaperManage paperManage = new PaperManage(paperId, 2,i);
            paperService.add(paperManage);
        }
        return ApiResultHandler.buildApiResult(400, "上传失败", null);

    }

    @DeleteMapping("/deleteJudge/{questionId}")
    public ApiResult deleteJudge(@PathVariable("questionId") Integer questionId){
        int res = judgeQuestionService.deleteJudge(questionId);
        System.out.println("==========================="+questionId);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }

    @GetMapping("/selectJudgeById/{questionId}")
    public ApiResult selectbyId(@PathVariable("questionId") Integer questionId){
        System.out.println("根据ID查找");
        JudgeQuestion res = judgeQuestionService.selectbyId(questionId);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"题目编号不存在",null);
        }
        return ApiResultHandler.buildApiResult(200,"请求成功！",res);
    }

    @PutMapping("/judge")
    public ApiResult update(@RequestBody JudgeQuestion judgeQuestion){
        int res = judgeQuestionService.update(judgeQuestion);
//        if (res == 0) {
//            return ApiResultHandler.buildApiResult(20000,"请求参数错误");
//        }
        System.out.print("更新操作执行---");
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @GetMapping("/judgenum/{section}/{subject}")
    public ApiResult judgenum(@PathVariable("section") String section,@PathVariable("subject") String subject){
        int res = judgeQuestionService.judgenum(section,subject);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    @GetMapping("/judgeerror/{questionId}/{studentId}/{rights}")
    public ApiResult mutilError(@PathVariable("questionId") Integer questionId,@PathVariable("studentId") Integer studentId,@PathVariable("rights") String rights){
        int res = judgeQuestionService.judgeError(questionId,studentId,rights);
        return ApiResultHandler.buildApiResult(200,"插入成功",res);
    }

    @GetMapping("/judgeerrors/{page}/{size}/{studentId}")
    public ApiResult judgeErrors(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("studentId") Integer studentId){
        Page<JudgeError> judgeErrorPage = new Page<>(page,size);
        IPage<JudgeError> judgeErrorIPage = judgeQuestionService.judgeErrors(judgeErrorPage,studentId);
        System.out.println("==================="+judgeErrorIPage.getRecords());
        return ApiResultHandler.buildApiResult(200,"查询该学生判断题错题",judgeErrorIPage);
    }

    @DeleteMapping("/deleteErrorJudge/{questionId}")
    public ApiResult deleteError(@PathVariable("questionId") Integer questionId){
        int res = judgeQuestionService.deleteError(questionId);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }
}
