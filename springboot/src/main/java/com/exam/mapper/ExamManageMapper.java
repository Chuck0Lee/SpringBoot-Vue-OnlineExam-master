package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ExamManageMapper {
    @Select("select * from exam_manage")
    List<ExamManage> findall();

    @Select("select * from exam_manage where teacherId = #{teacherId}")
    IPage<ExamManage> findAll(Page page,@Param("teacherId") Integer teacherId);

    @Select("select * from exam_manage where grade = #{grade} and major = #{major} and genre = '考试'")
    List<ExamManage> findBymajor(@Param("grade") Integer grade,@Param("major") String major);

    @Select("select source from exam_manage where grade = #{grade} and major = #{major} and genre = '考试' group by source")
    List<ExamManage> findBysubjects(@Param("grade") Integer grade,@Param("major") String major);

    @Select("select source from exam_manage where grade = #{grade} and major = #{major} and genre = '练习' group by source")
    List<ExamManage> findBysubjectPs(@Param("grade") Integer grade,@Param("major") String major);

    //查找此学生专业、年级及科目的考试
    @Select("select * from exam_manage where grade = #{grade} and major = #{major} and genre = '考试' and source = #{subject}")
    IPage<ExamManage> findBygrade(Page page,@Param("grade") Integer grade,@Param("major") String major,@Param("subject") String subject);

    //查找此学生专业、年级及科目的练习
    @Select("select * from exam_manage where grade = #{grade} and major = #{major} and genre = '练习'and source = #{subject}")
    IPage<ExamManage> findBygenre(Page page,@Param("grade") Integer grade,@Param("major") String major,@Param("subject") String subject);

    @Select("select source from exam_manage where grade = #{grade} and major = #{major} and genre = '考试' group by source")
    IPage<ExamManage> findBysubject(Page page,@Param("grade") Integer grade,@Param("major") String major);

    @Select("select source from exam_manage where grade = #{grade} and major = #{major} and genre = '练习' group by source")
    IPage<ExamManage> findBysubjectP(Page page,@Param("grade") Integer grade,@Param("major") String major);

    @Select("select * from exam_manage where examCode = #{examCode}")
    ExamManage findById(Integer examCode);

    @Delete("delete from exam_manage where examCode = #{examCode}")
    int delete(Integer examCode);

    @Update("update exam_manage set description = #{description},source = #{source},paperId = #{paperId}," +
            "examDate = #{examDate},totalTime = #{totalTime},grade = #{grade},term = #{term}," +
            "major = #{major},institute = #{institute},totalScore = #{totalScore}," +
            "type = #{type},tips = #{tips} where examCode = #{examCode}")
    int update(ExamManage exammanage);

    @Options(useGeneratedKeys = true,keyProperty = "examCode")
    @Insert("insert into exam_manage(genre,description,source,paperId,examDate,totalTime,grade,term,major,institute,totalScore,type,tips,teacherId)" +
            " values(#{genre},#{description},#{source},#{paperId},#{examDate},#{totalTime},#{grade},#{term},#{major},#{institute},#{totalScore},#{type},#{tips},#{teacherId})")
    int add(ExamManage exammanage);

    /**
     * 查询最后一条记录的paperId,返回给前端达到自增效果
     * @return paperId
     */
    @Select("select paperId from exam_manage order by paperId desc limit 1")
    ExamManage findOnlyPaperId();
}
