package org.lwolvej.lessonselectionsystem.dao.impl;

import org.lwolvej.lessonselectionsystem.constant.TeacherConstant;
import org.lwolvej.lessonselectionsystem.dao.TeacherDao;
import org.lwolvej.lessonselectionsystem.entity.Department;
import org.lwolvej.lessonselectionsystem.entity.Teacher;
import org.lwolvej.lessonselectionsystem.exception.DaoException;
import org.lwolvej.lessonselectionsystem.util.DBUtil;

import java.sql.*;

/**
 * dao层实现类
 *
 * @author LwolveJ
 */
public class TeacherDaoImpl implements TeacherDao {

    /**
     * 根据教师id获取教师实体
     *
     * @param teacherId 教师id
     * @return 教师实体
     */
    public Teacher selectTeacherById(Long teacherId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Teacher teacher = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(TeacherConstant.SELECT_TEACHER_BY_ID);
            statement.setLong(1, teacherId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                teacher = new Teacher();
                teacher.setTeacherId(resultSet.getLong("teacher_id"));
                teacher.setTeacherName(resultSet.getString("teacher_name"));
                teacher.setTeacherPwd(resultSet.getString("teacher_pwd"));
                teacher.setTeacherGender(resultSet.getBoolean("teacher_gender"));
                teacher.setTeacherSite(resultSet.getString("teacher_site"));
                teacher.setTeacherNumber(resultSet.getString("teacher_number"));
                Department department = new Department();
                department.setDepartmentId(resultSet.getLong("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                teacher.setDepartment(department);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return teacher;
    }

    @Override
    public Long insertTeacher(String teacherName, String teacherPwd, Boolean teacherGender,
                              String teacherNumber, String teacherSite, Long departmentId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Long index = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(TeacherConstant.INSERT_TEACHER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, teacherName);
            statement.setString(2, teacherPwd);
            statement.setBoolean(3, teacherGender);
            statement.setString(4, teacherNumber);
            statement.setString(5, teacherSite);
            statement.setLong(6, departmentId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                index = (long) resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return index;
    }
}
