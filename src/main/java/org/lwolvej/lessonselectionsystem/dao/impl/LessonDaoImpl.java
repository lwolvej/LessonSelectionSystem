package org.lwolvej.lessonselectionsystem.dao.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.constant.LessonConstant;
import org.lwolvej.lessonselectionsystem.dao.LessonDao;
import org.lwolvej.lessonselectionsystem.entity.Lesson;
import org.lwolvej.lessonselectionsystem.exception.DaoException;
import org.lwolvej.lessonselectionsystem.util.DBUtil;

import java.sql.*;
import java.util.List;

/**
 * dao层实现类
 *
 * @author LwolveJ
 */
public class LessonDaoImpl implements LessonDao {

    @Override
    public Lesson selectLessonById(Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Lesson lesson = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(LessonConstant.SELECT_LESSON_BY_ID);
            statement.setLong(1, lessonId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                lesson = new Lesson();
                lesson.setLessonId(resultSet.getLong("lesson_id"));
                lesson.setLessonName(resultSet.getString("lesson_name"));
                lesson.setLessonStatus(resultSet.getBoolean("lesson_status"));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return lesson;
    }

    @Override
    public List<Lesson> selectLessonByStatus(Boolean status) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Lesson> lessonList = Lists.newArrayList();
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(LessonConstant.SELECT_LESSON_BY_STATUS);
            statement.setBoolean(1, status);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setLessonId(resultSet.getLong("lesson_id"));
                lesson.setLessonName(resultSet.getString("lesson_name"));
                lesson.setLessonStatus(resultSet.getBoolean("lesson_status"));
                lessonList.add(lesson);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return lessonList;
    }

    @Override
    public Long insertLesson(String lessonName, Boolean lessonStatus, Long teacherId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        long index = 0;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(LessonConstant.INSERT_LESSON, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, lessonName);
            statement.setBoolean(2, lessonStatus);
            statement.setLong(3, teacherId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                index = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return index;
    }

    @Override
    public Boolean updateLessonInfo(Long lessonId, String lessonName, Boolean lessonStatus) {
        Connection connection = null;
        PreparedStatement statement;
        int index = 0;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(LessonConstant.UPDATE_LESSON_INFO);
            statement.setString(1, lessonName);
            statement.setBoolean(2, lessonStatus);
            statement.setLong(3, lessonId);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return index != 0;
    }

    @Override
    public Boolean deleteLesson(Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        int index = 0;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(LessonConstant.DELETE_LESSON);
            statement.setLong(1, lessonId);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return index != 0;
    }
}
