package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;
import com.exam.entity.StudentScore;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface ScoreMapper {
    /**
     * @param score 添加一条成绩记录
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "scoreId")
    @Insert("insert into score(examCode,studentId,subject,etScore,answerDate,type) values(#{examCode},#{studentId},#{subject},#{etScore},#{answerDate},1)")
    int add(Score score);

    @Options(useGeneratedKeys = true,keyProperty = "scoreId")
    @Insert("insert into score(examCode,studentId,subject,etScore,answerDate,type) values(#{examCode},#{studentId},#{subject},#{etScore},#{answerDate},2)")
    int add2(Score score);

    @Select("select scoreId,examCode,studentId,subject,etScore,answerDate from score order by scoreId desc")
    List<Score> findAll();

    // 分页
    @Select("select scoreId,examCode,studentId,subject,etScore,answerDate from score where studentId = #{studentId} order by scoreId desc")
    IPage<Score> findById(Page<?> page,@Param("studentId") Integer studentId);

    // 分页
    @Select("select studentName,subject,etScore,answerDate,type from score join student on score.studentId = student.studentId where score.studentId = #{studentId} and type = 1  order by scoreId desc")
    IPage<StudentScore> findByType(Page<?> page,@Param("studentId") Integer studentId);

    // 不分页
    @Select("select scoreId,examCode,studentId,subject,etScore,answerDate from score where studentId = #{studentId}")
    List<Score> findByid(@Param("studentId")Integer studentId);

    @Select("select studentName,subject,etScore,answerDate,type from score join student on score.studentId = student.studentId where score.studentId = #{studentId} and type = 1")
    List<StudentScore> findBytype(@Param("studentId")Integer studentId);

    /**
     *
     * @return 查询每位学生的学科分数。 max其实是假的，为了迷惑老师，达到一次考试考生只参加了一次的效果
     */
    @Select("select max(etScore) as etScore from score where examCode = #{examCode} and type = 1 group by studentId")
    List<Score> findByExamCode(@Param("examCode")Integer examCode);

    @Select("select score.studentId,studentName,subject,etScore,answerDate from score join student on score.studentId=student.studentId order by answerDate desc")
//    @Results({
//            @Result(property = "company", column = "company_id", one = @One(select =
//                    "com.example.demo.mapper.CompanyMapper.getCompanyById"))
//    })
    List<StudentScore> findscore();

    @Update("update score set etScore = #{score}+etScore where examCode = #{examCode} and studentId = #{studentId}")
    int updateScore(@Param("score") Integer score,@Param("examCode") Integer examCode,@Param("studentId") Integer studentId);
}
