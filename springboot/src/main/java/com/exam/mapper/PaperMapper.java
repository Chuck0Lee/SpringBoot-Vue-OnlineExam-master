package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.PaperManage;
import com.exam.entity.RightNum;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaperMapper {
    @Select("select paperId, questionType,questionId from paper_manage")
    List<PaperManage> findAll();

    @Select("select paperId, questionType,questionId from paper_manage where paperId = #{paperId}")
    List<PaperManage> findById(Integer paperId);

    @Insert("insert into paper_manage(paperId,questionType,questionId) values " +
            "(#{paperId},#{questionType},#{questionId})")
    int add(PaperManage paperManage);

    @Update("update paper_manage set rightnum = rightnum+1 where paperId = #{paperId} and questionId = #{questionId} and questionType = 1")
    int addRightNum(@Param("paperId")Integer paperId,@Param("questionId")Integer questionId);

    @Update("update paper_manage set rightnum = rightnum+1 where paperId = #{paperId} and questionId = #{questionId} and questionType = 3")
    int addRightNum2(@Param("paperId") Integer paperId,@Param("questionId")Integer questionId);

    @Update("update paper_manage set rightnum = rightnum+1 where paperId = #{paperId} and questionId = #{questionId} and questionType = 2")
    int addRightNum3(@Param("paperId") Integer paperId,@Param("questionId")Integer questionId);

    @Update("update paper_manage set num = num+1 where paperId = #{paperId}")
    int addNum(@Param("paperId") Integer paperId);

    @Select("select rightnum,num,multi_question.question,rightnum/num as rightss  from paper_manage join multi_question on multi_question.questionId = paper_manage.questionId where questionType=1 and paperId = #{paperId} " +
            "union select rightnum,num,fill_question.question,rightnum/num as rightss from paper_manage join fill_question on fill_question.questionId = paper_manage.questionId where questionType=2 and paperId = #{paperId} " +
            "union select rightnum,num,judge_question.question,rightnum/num as rightss from paper_manage join judge_question on judge_question.questionId = paper_manage.questionId where questionType=3 and paperId = #{paperId}")
    IPage<RightNum> rightNum(Page page,@Param("paperId") Integer paperId);

//    @Select("select rightnum,num,multi_question.question from paper_manage join multi_question on multi_question.questionId = paper_manage.questionId where questionType=1 and paperId = #{paperId} ")
//    IPage<RightNum> rightNum(Page page,@Param("paperId") Integer paperId);
}
