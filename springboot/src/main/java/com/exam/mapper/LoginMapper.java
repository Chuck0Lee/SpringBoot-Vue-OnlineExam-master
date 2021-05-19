package com.exam.mapper;

import com.exam.entity.Admin;
import com.exam.entity.MultiQuestion;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
* @Description: 用户登陆查询各用户类型的数据库
* @Author: liqichang
* @Date: 2021/5/6 16:05
*/
@Mapper
public interface LoginMapper {

    //在管理员中查找
    @Select("select adminId,adminName,sex,tel,email,cardId,role from admin where adminId = #{username} and pwd = #{password}")
    public Admin adminLogin(@Param("username")Integer username, @Param("password")String password);

    //在教师表中查找
    @Select("select teacherId,teacherName,institute,sex,tel,email,cardId," +
            "type,role from teacher where teacherId = #{username} and pwd = #{password}")
    public Teacher teacherLogin(@Param("username")Integer username, @Param("password")String password);

    //在学生表中查找
    @Select("select studentId,studentName,grade,major,clazz,institute,tel," +
            "email,cardId,sex,role from student where studentId = #{username} and pwd = #{password}")
    public Student studentLogin(@Param("username")Integer username, @Param("password")String password);

    int add2(@Param("Multilist") List<MultiQuestion> Multilist);
}
