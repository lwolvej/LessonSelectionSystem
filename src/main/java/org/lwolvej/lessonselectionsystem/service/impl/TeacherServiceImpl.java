package org.lwolvej.lessonselectionsystem.service.impl;

import org.lwolvej.lessonselectionsystem.dao.DepartmentDao;
import org.lwolvej.lessonselectionsystem.dao.TeacherDao;
import org.lwolvej.lessonselectionsystem.dto.TeacherDTO;
import org.lwolvej.lessonselectionsystem.entity.Department;
import org.lwolvej.lessonselectionsystem.entity.Teacher;
import org.lwolvej.lessonselectionsystem.service.TeacherService;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.util.MD5Util;
import org.lwolvej.lessonselectionsystem.util.ObjectUtil;

/**
 * 服务层实现类
 *
 * @author LwolveJ
 */
public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao = Factory.getInstance().create(TeacherDao.class);

    private DepartmentDao departmentDao = Factory.getInstance().create(DepartmentDao.class);

    @Override
    public Teacher teacherLogin(String teacherId, String teacherPwd) {
        Long newId = ObjectUtil.StringToLong(teacherId);
        //判断输入的密码是否符合规范
        if (newId != null) {
            //判断输入的密码是否为空或者null
            String newPwd = ObjectUtil.stringCheck(teacherPwd);
            if (newPwd != null) {
                Teacher teacher = teacherDao.selectTeacherById(newId);
                //判断教师是否为空
                if(teacher != null) {
                    //判断密码是否正确
                    if (MD5Util.checkPassword(newPwd, teacher.getTeacherPwd().trim())) {
                        return teacher;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 粒度细分
     *
     * @param teacherDTO 前端传输的教室实体
     * @return 0:传输的实体有问题,1:没有选择教师所属的学院,2:教师所属的学院不存在,其他：新插入的教师id
     */
    @Override
    public Long addNewTeacher(TeacherDTO teacherDTO) {
        if (teacherDTO != null) {
            Long departmentId = teacherDTO.getDepartmentId();
            if (departmentId != null) {
                Department department = departmentDao.selectDepartmentById(departmentId);
                if (department != null) {
                    String pwd = MD5Util.getMD5(teacherDTO.getTeacherPwd());
                    return teacherDao.insertTeacher(teacherDTO.getTeacherName(), pwd,
                            teacherDTO.getTeacherGender(), teacherDTO.getTeacherNumber(), teacherDTO.getTeacherSite(),
                            teacherDTO.getDepartmentId());
                } else {
                    return (long) 2;
                }
            } else {
                return (long) 1;
            }
        }
        return (long) 0;
    }
}
