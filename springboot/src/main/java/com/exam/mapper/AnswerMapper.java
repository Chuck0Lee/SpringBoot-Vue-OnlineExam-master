package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.vo.AnswerVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface AnswerMapper {
    @Select("select questionId, question, subject, score, section,level, \"选择题\" as typess from multi_question " +
            "union select  questionId, question, subject, score, section,level, \"判断题\" as typess  from judge_question " +
            "union select  questionId, question, subject, score, section,level, \"填空题\" as typess from fill_question order by questionId desc")
    IPage<AnswerVO> findAll(Page page);


//    @Delete("IF (#{typess}='选择题') BEGIN delete * from multi_question where questionId = #{questionId}")
//    @Delete("delete from multi_question where questionId = #{questionId}")
//    int deleteQuestion(@Param("questionId") Integer questionId , @Param("typess") Integer typess);
}
