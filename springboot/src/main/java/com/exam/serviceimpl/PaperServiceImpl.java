package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.PaperManage;
import com.exam.entity.RightNum;
import com.exam.mapper.PaperMapper;
import com.exam.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;
    @Override
    public List<PaperManage> findAll() {
        return paperMapper.findAll();
    }

    @Override
    public List<PaperManage> findById(Integer paperId) {
        return paperMapper.findById(paperId);
    }

    @Override
    public int add(PaperManage paperManage) {
        return paperMapper.add(paperManage);
    }

    @Override
    public int addRightNum(Integer paperId,Integer questionId){
        return paperMapper.addRightNum(paperId,questionId);
    }
    @Override
    public int addRightNum2(Integer paperId,Integer questionId){
        return paperMapper.addRightNum2(paperId,questionId);
    }

    @Override
    public int addRightNum3(Integer paperId,Integer questionId){
        return paperMapper.addRightNum3(paperId,questionId);
    }

    @Override
    public int addNum(Integer paperId){
        return paperMapper.addNum(paperId);
    }

    @Override
    public IPage<RightNum> rightNum(Page page, Integer paperId){
        return paperMapper.rightNum(page,paperId);
    }


}
