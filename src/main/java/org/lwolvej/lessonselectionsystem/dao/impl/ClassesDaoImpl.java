package org.lwolvej.lessonselectionsystem.dao.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.constant.ClassesConstant;
import org.lwolvej.lessonselectionsystem.dao.ClassesDao;
import org.lwolvej.lessonselectionsystem.entity.Classes;
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
public class ClassesDaoImpl implements ClassesDao {

    @Override
    public List<Classes> selectAllClasses() {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Classes> classesList = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ClassesConstant.SELECT_ALL_CLASSES);
            resultSet = statement.executeQuery();
            classesList = getResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return classesList;
    }

    @Override
    public List<Classes> selectClassesById(Long departmentId, Long professionId, Long gradeId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Classes> classesList = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ClassesConstant.SELECT_CLASSES_BY_ID);
            statement.setLong(1, departmentId);
            statement.setLong(2, professionId);
            statement.setLong(3, gradeId);
            resultSet = statement.executeQuery();
            classesList = getResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return classesList;
    }

    @Override
    public Classes selectClasses(Long classesId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Classes classes = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ClassesConstant.SELECT_CLASSES);
            statement.setLong(1, classesId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                classes = new Classes();
                classes.setClassesId(resultSet.getLong("classes_id"));
                classes.setClassesName(resultSet.getString("classes_name"));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return classes;
    }

    private List<Classes> getResult(ResultSet resultSet) throws SQLException {
        List<Classes> classesList = Lists.newArrayList();
        while (resultSet.next()) {
            Classes classes = new Classes();
            classes.setClassesId(resultSet.getLong("classes_id"));
            classes.setClassesName(resultSet.getString("classes_name"));
            classesList.add(classes);
        }
        return classesList;
    }
}
