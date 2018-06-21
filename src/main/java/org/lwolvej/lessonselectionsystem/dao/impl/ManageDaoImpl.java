package org.lwolvej.lessonselectionsystem.dao.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.constant.ManageConstant;
import org.lwolvej.lessonselectionsystem.dao.ManageDao;
import org.lwolvej.lessonselectionsystem.entity.*;
import org.lwolvej.lessonselectionsystem.exception.DaoException;
import org.lwolvej.lessonselectionsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * dao层实现类
 *
 * @author LwolveJ
 */
public class ManageDaoImpl implements ManageDao {

    @Override
    public Boolean insertManage(Long roomId, Long clockId, Long lessonId, Long gradeId, Long teacherId) {
        Connection connection = null;
        PreparedStatement statement;
        int index = 0;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ManageConstant.INSERT_MANAGE);
            statement.setLong(1, roomId);
            statement.setLong(2, clockId);
            statement.setLong(3, lessonId);
            statement.setLong(4, gradeId);
            statement.setLong(5, teacherId);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return index != 0;
    }

    @Override
    public Boolean deleteManage(Long manageId) {
        Connection connection = null;
        PreparedStatement statement;
        int index = 0;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ManageConstant.DELETE_MANAGE);
            statement.setLong(1, manageId);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return index != 0;
    }

    @Override
    public Boolean deleteManageByLessonId(Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        int index = 0;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ManageConstant.DELETE_MANAGE_BY_LESSON_ID);
            statement.setLong(1, lessonId);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return index != 0;
    }

    @Override
    public List<Manage> selectByGradeId(Long gradeId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Manage> manageList;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ManageConstant.SELECT_BY_GRADE_ID);
            statement.setLong(1, gradeId);
            resultSet = statement.executeQuery();
            manageList = getResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return manageList;
    }

    @Override
    public List<Manage> selectByTeacherId(Long teacherId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Manage> manageList;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ManageConstant.SELECT_BY_TEACHER_ID);
            statement.setLong(1, teacherId);
            resultSet = statement.executeQuery();
            manageList = getResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return manageList;
    }

    @Override
    public Boolean updateManage(Long manageId, Long roomId, Long clockId, Long lessonId, Long gradeId, Long teacherId) {
        Connection connection = null;
        PreparedStatement statement;
        int index = 0;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ManageConstant.UPDATE_MANAGE);
            statement.setLong(1, roomId);
            statement.setLong(2, clockId);
            statement.setLong(3, lessonId);
            statement.setLong(4, gradeId);
            statement.setLong(5, teacherId);
            statement.setLong(6, manageId);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return index != 0;
    }


    @Override
    public List<Manage> selectByRoomId(Long roomId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Manage> manageList;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ManageConstant.SELECT_BY_ROOM_ID);
            statement.setLong(1, roomId);
            resultSet = statement.executeQuery();
            manageList = getResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return manageList;
    }

    @Override
    public Manage selectByLessonId(Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Manage manage = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ManageConstant.SELECT_BY_LESSON_ID);
            statement.setLong(1, lessonId);
            resultSet = statement.executeQuery();
            manage = getOneResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return manage;
    }

    private Manage getOneResult(ResultSet resultSet) throws SQLException {
        Manage manage = null;
        while (resultSet.next()) {
            manage = new Manage();
            //设置安排的id
            manage.setManageId(resultSet.getLong("manage_id"));
            //设置安排的教室
            Room room = new Room();
            room.setRoomId(resultSet.getLong("room_id"));
            room.setRoomName(resultSet.getString("room_name"));
            //设置安排的时间
            Clock clock = new Clock();
            clock.setClockId(resultSet.getLong("clock_id"));
            clock.setClockName(resultSet.getString("clock_name"));
            //设置安排的课程
            Lesson lesson = new Lesson();
            lesson.setLessonId(resultSet.getLong("lesson_id"));
            lesson.setLessonName(resultSet.getString("lesson_name"));
            lesson.setLessonStatus(resultSet.getBoolean("lesson_status"));
            //设置安排的年级
            Grade grade = new Grade();
            grade.setGradeId(resultSet.getInt("grade_id"));
            grade.setGradeName(resultSet.getInt("grade_name"));
            //设置安排的教师
            Teacher teacher = new Teacher();
            teacher.setTeacherId(resultSet.getLong("teacher_id"));
            teacher.setTeacherName(resultSet.getString("teacher_name"));
            teacher.setTeacherGender(resultSet.getBoolean("teacher_gender"));
            teacher.setTeacherNumber(resultSet.getString("teacher_number"));
            teacher.setTeacherSite(resultSet.getString("teacher_site"));
            //设置教师所属的学院
            Department department = new Department();
            department.setDepartmentId(resultSet.getLong("department_id"));
            department.setDepartmentName(resultSet.getString("department_name"));
            //组装
            teacher.setDepartment(department);
            manage.setRoom(room);
            manage.setClock(clock);
            manage.setGrade(grade);
            manage.setLesson(lesson);
            manage.setTeacher(teacher);
        }
        return manage;
    }

    private List<Manage> getResult(ResultSet resultSet) throws SQLException {
        List<Manage> manages = Lists.newArrayList();
        while (resultSet.next()) {
            Manage manage = new Manage();
            //设置安排的id
            manage.setManageId(resultSet.getLong("manage_id"));
            //设置安排的教室
            Room room = new Room();
            room.setRoomId(resultSet.getLong("room_id"));
            room.setRoomName(resultSet.getString("room_name"));
            //设置安排的时间
            Clock clock = new Clock();
            clock.setClockId(resultSet.getLong("clock_id"));
            clock.setClockName(resultSet.getString("clock_name"));
            //设置安排的课程
            Lesson lesson = new Lesson();
            lesson.setLessonId(resultSet.getLong("lesson_id"));
            lesson.setLessonName(resultSet.getString("lesson_name"));
            lesson.setLessonStatus(resultSet.getBoolean("lesson_status"));
            //设置安排的年级
            Grade grade = new Grade();
            grade.setGradeId(resultSet.getInt("grade_id"));
            grade.setGradeName(resultSet.getInt("grade_name"));
            //设置安排的教师
            Teacher teacher = new Teacher();
            teacher.setTeacherId(resultSet.getLong("teacher_id"));
            teacher.setTeacherName(resultSet.getString("teacher_name"));
            teacher.setTeacherGender(resultSet.getBoolean("teacher_gender"));
            teacher.setTeacherNumber(resultSet.getString("teacher_number"));
            teacher.setTeacherSite(resultSet.getString("teacher_site"));
            //设置教师所属的学院
            Department department = new Department();
            department.setDepartmentId(resultSet.getLong("department_id"));
            department.setDepartmentName(resultSet.getString("department_name"));
            //组装
            teacher.setDepartment(department);
            manage.setRoom(room);
            manage.setClock(clock);
            manage.setGrade(grade);
            manage.setLesson(lesson);
            manage.setTeacher(teacher);
            manages.add(manage);
        }
        return manages;
    }
}
