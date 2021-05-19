package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.*;
import com.exam.serviceimpl.*;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PaperController {

    @Autowired
    private PaperServiceImpl paperService;

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;

    @Autowired
    private AnswerQuestionServicelmpl answerQuestionService;
    @GetMapping("/papers")
    public ApiResult<PaperManage> findAll() {
       ApiResult res =  ApiResultHandler.buildApiResult(200,"请求成功",paperService.findAll());
       return  res;
    }

    @GetMapping("/paper/{paperId}")
    public Map<Integer, List<?>> findById(@PathVariable("paperId") Integer paperId) {
        List<MultiQuestion> multiQuestionRes = multiQuestionService.findByIdAndType(paperId);   //选择题题库 1
        List<FillQuestion> fillQuestionsRes = fillQuestionService.findByIdAndType(paperId);     //填空题题库 2
        List<JudgeQuestion> judgeQuestionRes = judgeQuestionService.findByIdAndType(paperId);   //判断题题库 3
        List<answerQuestion> answerQuestionRes = answerQuestionService.findByIdAndType(paperId);  //简答题题库 4
        Map<Integer, List<?>> map = new HashMap<>();
        map.put(1,multiQuestionRes);
        map.put(2,fillQuestionsRes);
        map.put(3,judgeQuestionRes);
        map.put(4,answerQuestionRes);
        return  map;
    }

    @PostMapping("/paperManage")
    public ApiResult add(@RequestBody PaperManage paperManage) {
        int res = paperService.add(paperManage);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    @PutMapping("/addRightNum/{paperId}/{questionId}")
    public ApiResult addRightNum(@PathVariable("paperId") Integer paperId,@PathVariable("questionId") Integer questionId){
        int res = paperService.addRightNum(paperId,questionId);
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @PutMapping("/addRightNum2/{paperId}/{questionId}")
    public ApiResult addRightNum2(@PathVariable("paperId") Integer paperId,@PathVariable("questionId") Integer questionId){
        int res = paperService.addRightNum2(paperId,questionId);
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @PutMapping("/addRightNum3/{paperId}/{questionId}")
    public ApiResult addRightNum3(@PathVariable("paperId") Integer paperId,@PathVariable("questionId") Integer questionId){
        int res = paperService.addRightNum3(paperId,questionId);
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @GetMapping("/addNum/{paperId}")
    public ApiResult addNum(@PathVariable("paperId") Integer paperId){
        int res = paperService.addNum(paperId);
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @GetMapping("/nums/{page}/{size}/{paperId}")
    public ApiResult rightNum(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("paperId") Integer paperId){
        System.out.println("分页查询所有试卷--------");
        ApiResult apiResult;
        Page<RightNum> rightNum = new Page<>(page,size);
        IPage<RightNum> res = paperService.rightNum(rightNum,paperId);
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", res);
        return apiResult;
    }
}
