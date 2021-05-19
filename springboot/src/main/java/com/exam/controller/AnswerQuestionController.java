package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.*;
import com.exam.serviceimpl.AnswerQuestionServicelmpl;
import com.exam.serviceimpl.PaperServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname AnswerQuestionController
 * @description: TODO
 * @author: liqichang
 * @time: 2021/5/10 10:42
 */

@RestController
public class AnswerQuestionController {
    @Autowired
    private AnswerQuestionServicelmpl answerQuestionService;

    @Autowired
    private PaperServiceImpl paperService;

    @PostMapping("/answerAdd/{examCode}/{questionId}/{studentId}/{topic4Answer}")
    public ApiResult add(@PathVariable("examCode") Integer examCode,@PathVariable("questionId") Integer questionId,@PathVariable("studentId") Integer studentId,@PathVariable("topic4Answer") String topic4Answer){
        int res = answerQuestionService.add(examCode,questionId,studentId,topic4Answer);
        return ApiResultHandler.buildApiResult(200,"插入成功",res);
    }

    @GetMapping("/answers/{page}/{size}/{examCode}")
    public ApiResult findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("examCode") Integer examCode){
        ApiResult apiResult;
        Page<examAnswer> examanswer = new Page<>(page,size);
        IPage<examAnswer> all = answerQuestionService.findAll(examanswer,examCode);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        return apiResult;
    }

    @PutMapping("/answerScore/{score}")
    public ApiResult update(@PathVariable("score") Integer score){
        int res = answerQuestionService.update(score);
        return ApiResultHandler.buildApiResult(200,"修改成功",res);
    }

    @DeleteMapping("/deleteAnswer/{answerId}")
    public ApiResult deleteAnswer(@PathVariable("answerId") Integer answerId){
        int res = answerQuestionService.deleteAnswer(answerId);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }

    @PostMapping("/answerQuestion")
    public ApiResult add2(@RequestBody answerQuestion answerQuestion) {
        int res = answerQuestionService.add2(answerQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    @GetMapping("/answerQuestionId")
    public ApiResult findOnlyQuestionId() {
        answerQuestion res = answerQuestionService.findOnlyQuestionId();
//        System.out.println("+++++++++++++++++"+res);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }


}
