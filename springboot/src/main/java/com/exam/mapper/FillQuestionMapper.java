package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillError;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeError;
import com.exam.entity.JudgeQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

//填空题
@Mapper
public interface FillQuestionMapper {

    @Select("select * from fill_question where questionId in (select questionId from paper_manage where questionType = 2 and paperId = #{paperId})")
    List<FillQuestion> findByIdAndType(Integer paperId);

    @Select("select * from fill_question")
    IPage<FillQuestion> findAll(Page page);

    /**
     * 查询最后一条questionId
     * @return FillQuestion
     */
    @Select("select questionId from fill_question order by questionId desc limit 1")
    FillQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true,keyProperty ="questionId" )
    @Insert("insert into fill_question(subject,question,answer,analysis,level,section) values " +
            "(#{subject,},#{question},#{answer},#{analysis},#{level},#{section})")
    int add(FillQuestion fillQuestion);

    @Select("select questionId from fill_question where subject = #{subject} and section = #{section} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(@Param("subject") String subject, @Param("pageNo") Integer pageNo,@Param("section") String section);

    int AddFill(@Param("Filllist") List<FillQuestion> Filllist);

    @Delete("delete from fill_question where questionId = #{questionId}")
    int deleteFill(@Param("questionId") Integer questionId);

    @Select("select * from fill_question where questionId = #{questionId}")
    FillQuestion selectbyId(Integer questionId);

    @Update("update fill_question set subject=#{subject},question=#{question},"+"section=#{section},score=#{score},"+"level=#{level} where questionId = #{questionId}")
    int update(FillQuestion fillQuestion);

    @Select("select count(*) from fill_question where section = #{section} and subject = #{subject}")
    int fillnum(@Param("section") String section,@Param("subject") String subject);

    @Insert("Insert into error_question(questionId,studentId,rights,type)"+"values(#{questionId},#{studentId},'无',2)")
    int fillError(@Param("questionId") Integer questionId,@Param("studentId") Integer studentId);

    @Select("select fill_question.questionId,fill_question.question,fill_question.answer,rights from error_question join fill_question on " +
            "error_question.questionId = fill_question.questionId where studentId = #{studentId} and type = 2 ")
    IPage<FillError> fillErrors(Page<?> page, @Param("studentId") Integer studentId);

    @Delete("delete from error_question where questionId = #{questionId} and type = 2")
    int deleteError(@Param("questionId") Integer questionId);
}
