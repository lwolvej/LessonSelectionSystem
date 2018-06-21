package org.lwolvej.lessonselectionsystem.dao.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.constant.GradeConstant;
import org.lwolvej.lessonselectionsystem.dao.GradeDao;
import org.lwolvej.lessonselectionsystem.entity.Grade;
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
public class GradeDaoImpl implements GradeDao {

    @Override
    public List<Grade> selectAllGrade() {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Grade> grades = Lists.newArrayList();
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(GradeConstant.SELECT_ALL_GRADE);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Grade grade = new Grade();
                grade.setGradeName(resultSet.getInt("grade_name"));
                grade.setGradeId(resultSet.getInt("grade_id"));
                grades.add(grade);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return grades;
    }

    @Override
    public Grade selectGradeById(Long gradeId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Grade grade = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(GradeConstant.SELECT_GRADE_BY_ID);
            statement.setLong(1, gradeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                grade = new Grade();
                grade.setGradeId(resultSet.getInt("grade_id"));
                grade.setGradeName(resultSet.getInt("grade_name"));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return grade;
    }
}
