package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.JudgeError;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;
import com.exam.entity.MutilError;
import org.apache.ibatis.annotations.*;

import java.util.List;

//判断题

@Mapper
public interface JudgeQuestionMapper {

    @Select("select * from judge_question where questionId in (select questionId from paper_manage where questionType = 3 and paperId = #{paperId})")
    List<JudgeQuestion> findByIdAndType(Integer paperId);

    @Select("select * from judge_question")
    IPage<JudgeQuestion> findAll(Page page);

    /**
     * 查询最后一条记录的questionId
     * @return JudgeQuestion
     */
    @Select("select questionId from judge_question order by questionId desc limit 1")
    JudgeQuestion findOnlyQuestionId();

    @Insert("insert into judge_question(subject,question,answer,analysis,level,section) values " +
            "(#{subject},#{question},#{answer},#{analysis},#{level},#{section})")
    int add(JudgeQuestion judgeQuestion);

    @Select("select questionId from judge_question  where subject=#{subject} and section = #{section}  order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(@Param("subject") String subject, @Param("pageNo") Integer pageNo,@Param("section") String section);

    int AddJudge(@Param("Judgelist") List<JudgeQuestion> Judgelist);

    @Delete("delete from judge_question where questionId = #{questionId}")
    int deleteJudge(@Param("questionId") Integer questionId);

    @Select("select * from judge_question where questionId = #{questionId}")
    JudgeQuestion selectbyId(Integer questionId);

    @Update("update judge_question set subject=#{subject},question=#{question},"+"section=#{section},score=#{score},"+"level=#{level} where questionId = #{questionId}")
    int update(JudgeQuestion judgeQuestion);

    @Select("select count(*) from judge_question where section = #{section} and subject = #{subject}")
    int judgenum(@Param("section") String section,@Param("subject") String subject);

    @Insert("Insert into error_question(questionId,studentId,rights,type)"+"values(#{questionId},#{studentId},#{rights},3)")
    int judgeError(@Param("questionId") Integer questionId,@Param("studentId") Integer studentId,@Param("rights") String rights);

    @Select("select judge_question.questionId,judge_question.question,judge_question.answer,rights from error_question join judge_question on " +
            "error_question.questionId = judge_question.questionId where studentId = #{studentId} and type = 3 ")
    IPage<JudgeError> judgeErrors(Page<?> page, @Param("studentId") Integer studentId);

    @Delete("delete from error_question where questionId = #{questionId} and type = 3")
    int deleteError(@Param("questionId") Integer questionId);
}
