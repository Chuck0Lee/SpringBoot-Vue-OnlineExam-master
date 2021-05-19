package com.exam.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.*;
import com.exam.mapper.MultiQuestionMapper;
import com.exam.service.MultiQuestionService;
import com.exam.serviceimpl.MultiQuestionServiceImpl;
import com.exam.serviceimpl.PaperServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.util.ImportMulti_Util;
import com.exam.vo.AnswerVO;
import com.exam.vo.ImportMultiVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class MultiQuestionController {

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    private PaperServiceImpl paperService;


    @GetMapping("/multiQuestionId")
    public ApiResult findOnlyQuestion() {
        MultiQuestion res = multiQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }

    @PostMapping("/MultiQuestion")
    public ApiResult add(@RequestBody MultiQuestion multiQuestion) {
        int res = multiQuestionService.add(multiQuestion);
        if (res != 0) {

            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "添加失败", res);
    }


    @PostMapping("/importMulti2")
    public ApiResult add2(@RequestParam(value="paperId",required=false) Integer paperId,MultipartFile file) {
        // 1.自定义一个工具类拿到要解析的文件并解析成要存储的数据
        List<MultiQuestion> list = ImportMulti_Util.excel2Employee(file);
        // 2.遍历输出你解析的数据格式是否正确
//        System.out.println(list);
        for (MultiQuestion multiquestion : list) {
            System.out.println(multiquestion.toString());

        }

        // 插入题目之前的最大ID
        Integer Id1 = multiQuestionService.findOnlyQuestionId().getQuestionId();
        System.out.println("++++-----++++++----"+Id1);
//         3.进行数据库添加操作
        if (multiQuestionService.add2(list) == 1) {
//            System.out.println("---------++++++++++++++"+list.getQuestionId());
//            paperService.add(paperId,1,multiQuestionService.findOnlyQuestionId());
            System.out.println("数据库++++++++++");

            return ApiResultHandler.buildApiResult(200, "上传成功", null);

        }

       // 插入题目之后的最大ID
        Integer Id2 = multiQuestionService.findOnlyQuestionId().getQuestionId();

        for (Integer i = Id1+1;i<=Id2;i++){
            PaperManage paperManage = new PaperManage(paperId, 1,i);
            paperService.add(paperManage);
        }
        System.out.println("+++----++++---+++"+Id2);
        return ApiResultHandler.buildApiResult(400, "上传失败", null);

    }

    @PostMapping("/importMulti")
    public ApiResult add2(MultipartFile file) {
        // 1.自定义一个工具类拿到要解析的文件并解析成要存储的数据
        List<MultiQuestion> list = ImportMulti_Util.excel2Employee(file);
        // 2.遍历输出你解析的数据格式是否正确
//        System.out.println(list);
        for (MultiQuestion multiquestion : list) {
            System.out.println(multiquestion.toString());

        }

        // 插入题目之前的最大ID
        Integer Id1 = multiQuestionService.findOnlyQuestionId().getQuestionId();
        System.out.println("++++-----++++++----"+Id1);
//         3.进行数据库添加操作
        if (multiQuestionService.add2(list) == 1) {
//            System.out.println("---------++++++++++++++"+list.getQuestionId());
//            paperService.add(paperId,1,multiQuestionService.findOnlyQuestionId());
            System.out.println("数据库++++++++++");

            return ApiResultHandler.buildApiResult(200, "上传成功", null);

        }

        return ApiResultHandler.buildApiResult(400, "上传失败", null);

    }

    @DeleteMapping("/deleteMutil/{questionId}")
    public ApiResult deleteMutil(@PathVariable("questionId") Integer questionId) {
        int res = multiQuestionService.deleteMutil(questionId);
//        System.out.println("==========================="+questionId);
        return ApiResultHandler.buildApiResult(200, "删除成功", res);
    }

    @GetMapping("/selectMutilById/{questionId}")
    public ApiResult selectbyId(@PathVariable("questionId") Integer questionId){
        System.out.println("根据ID查找");
        MultiQuestion res = multiQuestionService.selectbyId(questionId);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"题目编号不存在",null);
        }
        return ApiResultHandler.buildApiResult(200,"请求成功！",res);
    }

    @PutMapping("/mutil")
    public ApiResult update(@RequestBody MultiQuestion multiQuestion){
        int res = multiQuestionService.update(multiQuestion);
//        if (res == 0) {
//            return ApiResultHandler.buildApiResult(20000,"请求参数错误");
//        }
        System.out.print("更新操作执行---");
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }

    @GetMapping("/mutilnum/{section}/{subject}")
    public ApiResult mutilnum(@PathVariable("section") String section,@PathVariable("subject") String subject){
        int res = multiQuestionService.mutilnum(section,subject);
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    @GetMapping("/mutilerror/{questionId}/{studentId}/{rights}")
    public ApiResult mutilError(@PathVariable("questionId") Integer questionId,@PathVariable("studentId") Integer studentId,@PathVariable("rights") String rights){
            int res = multiQuestionService.mutilError(questionId,studentId,rights);
            return ApiResultHandler.buildApiResult(200,"插入成功",res);
    }

    @GetMapping("/mutilerrors/{page}/{size}/{studentId}")
    public ApiResult mutilErrors(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("studentId") Integer studentId){
        Page<MutilError> mutilErrorPage = new Page<>(page,size);
        IPage<MutilError> mutilErrorIPage = multiQuestionService.mutilErrors(mutilErrorPage,studentId);
        return ApiResultHandler.buildApiResult(200,"查询该学生选择题错题",mutilErrorIPage);

    }

    @DeleteMapping("/deleteErrorMulti/{questionId}")
    public ApiResult deleteError(@PathVariable("questionId") Integer questionId){
        int res = multiQuestionService.deleteError(questionId);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }






//    @PostMapping("/importMulti")
//    public ApiResult importMulitQuestion(@RequestParam("file") MultipartFile file ) throws Exception {
//
}
