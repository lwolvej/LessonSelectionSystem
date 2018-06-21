package org.lwolvej.lessonselectionsystem.service;

import org.lwolvej.lessonselectionsystem.dto.TeacherDTO;
import org.lwolvej.lessonselectionsystem.entity.Teacher;

/**
 * 服务层接口
 *
 * @author LwolveJ
 */
public interface TeacherService {

    /**
     * 教师登录
     *
     * @param teacherId  教师的id
     * @param teacherPwd 教师的密码
     * @return 教师实体
     */
    Teacher teacherLogin(String teacherId, String teacherPwd);

    /**
     * 新增一个教师
     *
     * @param teacherDTO 前端传输的教室实体
     * @return 0:传输的实体有问题,1:没有选择教师所属的学院,2:教师所属的学院不存在,其他：新插入的教师id
     */
    Long addNewTeacher(TeacherDTO teacherDTO);
}
