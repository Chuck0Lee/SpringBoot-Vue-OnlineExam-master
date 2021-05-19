package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.PaperManage;
import com.exam.service.PaperService;
import com.exam.serviceimpl.FillQuestionServiceImpl;
import com.exam.serviceimpl.JudgeQuestionServiceImpl;
import com.exam.serviceimpl.MultiQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    FillQuestionServiceImpl fillQuestionService;

    @Autowired
    JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    PaperService paperService;



    @PostMapping("/item")
    public ApiResult ItemController(@RequestBody Item items) {
        // 选择题
        Integer changeNumber = items.getChangeNumber();
        // 填空题
        Integer fillNumber = items.getFillNumber();
        // 判断题
        Integer judgeNumber = items.getJudgeNumber();
        //出卷id
        Integer paperId = items.getPaperId();

        String section = items.getSection();

        // 选择题数据库获取
        List<Integer>  changeNumbers = multiQuestionService.findBySubject(items.getSubject(), changeNumber,section);
        System.out.println(changeNumbers.size());
        System.out.println(items.getSubject()+"------------------------------");
        if(changeNumbers.isEmpty()){
            return ApiResultHandler.buildApiResult(400,"选择题数据库获取失败",null);
        }
        for (Integer number : changeNumbers) {
            PaperManage paperManage = new PaperManage(paperId, 1,number);
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"选择题组卷保存失败",null);
        }

        // 填空题
        List<Integer> fills = fillQuestionService.findBySubject(items.getSubject(), fillNumber,section);
        if(fills.isEmpty())
            return ApiResultHandler.buildApiResult(400,"填空题数据库获取失败",null);
        for (Integer fillNum : fills) {
            PaperManage paperManage = new PaperManage(paperId,2,fillNum);
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"填空题题组卷保存失败",null);
        }
        // 判断题
        List<Integer> judges = judgeQuestionService.findBySubject(items.getSubject(), judgeNumber,section);
        if(judges.isEmpty())
            return ApiResultHandler.buildApiResult(400,"判断题数据库获取失败",null);
        for (Integer judge : judges) {
            System.out.println(judge);
            PaperManage paperManage = new PaperManage(paperId,3,judge);
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"判断题题组卷保存失败",null);
        }

            /**
            * @Description: 检测数据库中题目数量是否足够
            * @Author: liqichang
            * @Date: 2021/4/28 20:39
            */

            if (changeNumbers.size() < changeNumber||judges.size()<judgeNumber||fills.size()<fillNumber) {
                return ApiResultHandler.buildApiResult(400, "题库内数量不足", null);
            }


          return ApiResultHandler.buildApiResult(200,"试卷组卷成功",null);
    }
}
