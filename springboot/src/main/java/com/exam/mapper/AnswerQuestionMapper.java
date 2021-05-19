package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.answerQuestion;
import com.exam.entity.examAnswer;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @Classname AnswerQuestionMapper
 * @Description TODO
 * @Author liqichang
 * @Date 2021/5/10 10:35
 */
public interface AnswerQuestionMapper {
    @Select("select * from answer_question where questionId in (select questionId from paper_manage where questionType = 4 and paperId = #{paperId})")
    List<answerQuestion> findByIdAndType(Integer paperId);

    @Insert("insert into answer_score(examCode,questionId,studentId,answers)"+"values(#{examCode},#{questionId},#{studentId},#{topic4Answer})")
    int add(@Param("examCode") Integer examCode,@Param("questionId") Integer questionId, @Param("studentId") Integer studentId, @Param("topic4Answer") String topic4Answer);

    @Select("select answerId,answer_score.studentId,student.studentName,answer_question.question,answer_question.answer,answer_score.answers,answer_score.score from answer_score join student on student.studentId=answer_score.studentId join answer_question on answer_question.questionId=answer_score.questionId where examCode = #{examCode}")
    IPage<examAnswer> findAll(Page page, @Param("examCode") Integer examCode);

    @Update("update answer_score set score = #{score}")
    int update(@Param("score") Integer score);

    @Delete("delete from answer_score where answerId = #{answerId}")
    int deleteAnswer(@Param("answerId") Integer answerId);

    @Options(useGeneratedKeys = true,keyProperty ="questionId" )
    @Insert("insert into answer_question(subject,question,answer,analysis,section) values " +
            "(#{subject,},#{question},#{answer},#{analysis},#{section})")
    int add2(answerQuestion answerQuestion);

    @Select("select questionId from answer_question order by questionId desc limit 1")
    answerQuestion findOnlyQuestionId();
}
