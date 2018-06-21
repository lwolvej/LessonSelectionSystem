package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.entity.Student;

import java.util.List;

/**
 * dao层接口
 *
 * @author LwolveJ
 */
public interface StudentDao {

    /**
     * 通过student的id获取student实体
     *
     * @param studentId student的id
     * @return student实体
     */
    Student selectStudentById(Long studentId);

    /**
     * 新插入一个学生
     *
     * @param studentName   学生姓名
     * @param studentPwd    学生密码
     * @param studentGender 学生性别
     * @param studentNumber 学生电话
     * @param departmentId  学生所属学院
     * @param profession_id 学院所属专业
     * @param classesId     学生所属班级
     * @param gradeId       学生所属年级
     * @return 插入成功返回的学生学号
     */
    Long insertStudent(String studentName, String studentPwd, Boolean studentGender, String studentNumber,
                       Long departmentId, Long profession_id, Long classesId, Long gradeId);


    /**
     * 获取所有学生
     *
     * @return 学生集合
     */
    List<Student> selectAllStudent();


    /**
     * 获取某个课程下的学生集合
     *
     * @param lessonId 课程的id
     * @return 学生集合
     */
    List<Student> selectStudentByLessonId(Long lessonId);
}
