package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.serviceimpl.AnswerServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.vo.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;


@RestController
public class AnswerController {

    @Autowired
    private AnswerServiceImpl answerService;


    /**
    * @Description: 查找所有试题
    * @Author: liqichang
    * @Date: 2021/4/28 15:55
    */
    @GetMapping("/answers/{page}/{size}")
    public ApiResult findAllQuestion(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
       Page<AnswerVO> answerVOPage = new Page<>(page,size);
       IPage<AnswerVO> answerVOIPage = answerService.findAll(answerVOPage);
       return ApiResultHandler.buildApiResult(200,"查询所有题库",answerVOIPage);

    }

//    @DeleteMapping("/deleteQuestion/{questionId}/{typess}")
//    public ApiResult deletequetion(@PathVariable("questionId") Integer questionId,@PathVariable("typess") String typess){
//        System.out.println(questionId);
//        System.out.println(typess);
//        return ApiResultHandler.buildApiResult(200,"删除成功",null);
//    }
}
