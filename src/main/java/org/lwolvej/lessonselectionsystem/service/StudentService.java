package org.lwolvej.lessonselectionsystem.service;

import org.lwolvej.lessonselectionsystem.dto.StudentDTO;
import org.lwolvej.lessonselectionsystem.entity.Student;

import java.util.List;

/**
 * 服务层接口
 *
 * @author LwolveJ
 */
public interface StudentService {

    /**
     * 学生登录
     *
     * @param studentId       学生的id
     * @param studentPassword 学生的密码
     * @return 学生实体
     */
    Student studentLogin(String studentId, String studentPassword);

    /**
     * 新插入一个学生
     *
     * @param studentDTO 前端传输的学生实体
     * @return 新插入的学生获取的id
     */
    Long addNewStudent(StudentDTO studentDTO);

    /**
     * 查询该门课程下的所有学生
     *
     * @param lessonId 课程号
     * @return 学生的集合
     */
    List<Student> queryStudentByLessonId(String lessonId);

    /**
     * 查询所有的学生
     *
     * @return 所有学生的集合
     */
    List<Student> queryAllStudent();
}
