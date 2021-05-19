package com.exam.serviceimpl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.MultiQuestion;
import com.exam.entity.MutilError;
import com.exam.mapper.MultiQuestionMapper;
import com.exam.service.MultiQuestionService;
import com.exam.util.ApiResultHandler;
import com.exam.util.ImportMulti_Util;
import com.exam.vo.ImportMultiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Queue;

@Service
public class MultiQuestionServiceImpl implements MultiQuestionService {

    @Resource
    private MultiQuestionMapper multiQuestionMapper;



    @Override
    public List<MultiQuestion> findByIdAndType(Integer PaperId) {
        return multiQuestionMapper.findByIdAndType(PaperId);
    }

    @Override
    public IPage<MultiQuestion> findAll(Page<MultiQuestion> page) {
        return multiQuestionMapper.findAll(page);
    }

    @Override
    public MultiQuestion findOnlyQuestionId() {
        return multiQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public int add(MultiQuestion multiQuestion) {
        return multiQuestionMapper.add(multiQuestion);
    }

    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo,String section) {
        return multiQuestionMapper.findBySubject(subject,pageNo,section);
    }

    @Override
    public int add2(List<MultiQuestion> multilist) {
//        System.out.println("------++++++++"+multilist.getQuestionId());
        return multiQuestionMapper.add2(multilist);
    }

    @Override
    public int deleteMutil(Integer questionId){
        return multiQuestionMapper.deleteMutil(questionId);
    }

    @Override
    public MultiQuestion selectbyId(Integer questionId){
        return multiQuestionMapper.selectbyId(questionId);
    }

    @Override
    public int update(MultiQuestion multiQuestion){
        return multiQuestionMapper.update(multiQuestion);
    }

    @Override
    public int mutilnum(String section,String subject){
        return multiQuestionMapper.mutilnum(section,subject);
    }

    @Override
    public int mutilError(Integer questionId,Integer studentId,String rights){
        return multiQuestionMapper.mutilError(questionId,studentId,rights);
    }

    @Override
    public IPage<MutilError> mutilErrors(Page<MutilError> page,Integer studentId){
        return multiQuestionMapper.mutilErrors(page,studentId);
    }

    @Override
    public int deleteError(Integer questionId){
        return multiQuestionMapper.deleteError(questionId);
    }


}
