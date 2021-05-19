package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.MultiQuestion;
import com.exam.entity.MutilError;
import com.exam.entity.StudentScore;
import org.apache.ibatis.annotations.*;

import java.util.List;

//选择题
@Mapper
public interface MultiQuestionMapper {
    /**
     * select * from multiquestions where questionId in (
     * 	select questionId from papermanage where questionType = 1 and paperId = 1001
     * )
     */
    @Select("select * from multi_question where questionId in (select questionId from paper_manage where questionType = 1 and paperId = #{paperId})")
    List<MultiQuestion> findByIdAndType(Integer PaperId);

    @Select("select * from multi_question")
    IPage<MultiQuestion> findAll(Page page);

    /**
     * 查询最后一条记录的questionId
     * @return MultiQuestion
     */
    @Select("select questionId from multi_question order by questionId desc limit 1")
    MultiQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true,keyProperty = "questionId")
    @Insert("insert into multi_question(subject,question,answerA,answerB,answerC,answerD,rightAnswer,analysis,section,level) " +
            "values(#{subject},#{question},#{answerA},#{answerB},#{answerC},#{answerD},#{rightAnswer},#{analysis},#{section},#{level})")
    int add(MultiQuestion multiQuestion);

    @Select("select questionId from multi_question  where subject =#{subject} and section = #{section} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(@Param("subject") String subject, @Param("pageNo") Integer pageNo,@Param("section") String section);

//    @Options(useGeneratedKeys = true,keyProperty = "questionId",keyColumn = "questionId")

//    @Insert("insert into multi_question(subject,question,answerA,answerB,answerC,answerD,rightAnswer,analysis,section,level) " +
//            "values(#{subject},#{question},#{answerA},#{answerB},#{answerC},#{answerD},#{rightAnswer},#{analysis},#{section},#{level})")
//    int add2(@Param("Multilist") List<MultiQuestion> Multilist);

//    List<MultiQuestion> add2(List<MultiQuestion> Multilist);

    int add2(@Param("Multilist") List<MultiQuestion> Multilist);

    @Delete("delete from multi_question where questionId = #{questionId}")
    int deleteMutil(@Param("questionId") Integer questionId);


    @Select("select * from multi_question where questionId = #{questionId}")
    MultiQuestion selectbyId(Integer questionId);

    @Update("update multi_question set subject=#{subject},question=#{question},"+"section=#{section},score=#{score},"+"level=#{level} where questionId = #{questionId}")
    int update(MultiQuestion multiQuestion);

    @Select("select count(*) from multi_question where section = #{section} and subject = #{subject}")
    int mutilnum(@Param("section") String section,@Param("subject") String subject);

    @Insert("Insert into error_question(questionId,studentId,rights,type)"+"values(#{questionId},#{studentId},#{rights},1)")
    int mutilError(@Param("questionId") Integer questionId,@Param("studentId") Integer studentId,@Param("rights") String rights);

    @Select("select multi_question.questionId,multi_question.question,multi_question.answerA,multi_question.answerB,multi_question.answerC,multi_question.answerD,multi_question.rightAnswer,rights from error_question join multi_question on " +
            "error_question.questionId = multi_question.questionId where studentId = #{studentId} and type = 1 ")
    IPage<MutilError> mutilErrors(Page<?> page, @Param("studentId") Integer studentId);

    @Delete("delete from error_question where questionId = #{questionId} and type = 1")
    int deleteError(@Param("questionId") Integer questionId);


}
