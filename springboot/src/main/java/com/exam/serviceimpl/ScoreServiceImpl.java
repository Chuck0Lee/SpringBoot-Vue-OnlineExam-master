package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;
import com.exam.entity.StudentScore;
import com.exam.mapper.ScoreMapper;
import com.exam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public int add(Score score) {
        return scoreMapper.add(score);
    }

    @Override
    public int add2(Score score) {
        return scoreMapper.add2(score);
    }

    @Override
    public List<Score> findAll() {
        return scoreMapper.findAll();
    }

    @Override
    public IPage<Score> findById(Page<Score> page, Integer studentId) {
        return scoreMapper.findById(page, studentId);
    }

    @Override
    public IPage<StudentScore> findByType(Page<StudentScore> page, Integer studentId) {
        return scoreMapper.findByType(page, studentId);
    }

    @Override
    public List<Score> findById(Integer studentId) {
        return scoreMapper.findByid(studentId);
    }

    @Override
    public List<StudentScore> findBytype(Integer studentId) {
        return scoreMapper.findBytype(studentId);
    }

    @Override
    public List<Score> findByExamCode(Integer examCode) {
        return scoreMapper.findByExamCode(examCode);
    }

    @Override
    public List<StudentScore> findscore(){ return scoreMapper.findscore(); }

    @Override
    public int updateScore(Integer score,Integer examCode,Integer studentId){
        return scoreMapper.updateScore(score,examCode,studentId);
    }
}
