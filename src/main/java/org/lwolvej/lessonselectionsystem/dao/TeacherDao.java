package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.entity.Teacher;

/**
 * dao层接口
 *
 * @author LwolveJ
 */
public interface TeacherDao {

    /**
     * 根据教师id获取教师实体
     *
     * @param teacherId 教师id
     * @return 教师实体
     */
    Teacher selectTeacherById(Long teacherId);

    /**
     * 插入一个新的教师
     *
     * @param teacherName   教师姓名
     * @param teacherPwd    教师密码
     * @param teacherGender 教师性别
     * @param teacherNumber 教师联系电话
     * @param teacherSite   教师办公室
     * @param departmentId  教师所属学院
     * @return 教师的id
     */
    Long insertTeacher(String teacherName, String teacherPwd, Boolean teacherGender,
                       String teacherNumber, String teacherSite, Long departmentId);
}
