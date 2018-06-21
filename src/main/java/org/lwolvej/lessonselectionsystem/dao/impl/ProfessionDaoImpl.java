package org.lwolvej.lessonselectionsystem.dao.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.constant.ProfessionConstant;
import org.lwolvej.lessonselectionsystem.dao.ProfessionDao;
import org.lwolvej.lessonselectionsystem.entity.Profession;
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
public class ProfessionDaoImpl implements ProfessionDao {

    @Override
    public List<Profession> selectAllProfession() {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Profession> professions;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ProfessionConstant.SELECT_ALL_PROFESSION);
            resultSet = statement.executeQuery();
            professions = getResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return professions;
    }

    @Override
    public List<Profession> selectProfessionById(Long departmentId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Profession> professions;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ProfessionConstant.SELECT_PROFESSION_BY_ID);
            statement.setLong(1, departmentId);
            resultSet = statement.executeQuery();
            professions = getResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return professions;
    }

    @Override
    public Profession selectProfession(Long professionId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Profession profession = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ProfessionConstant.SELECT_PROFESSION);
            statement.setLong(1, professionId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                profession = new Profession();
                profession.setProfessionId(resultSet.getLong("profession_id"));
                profession.setProfessionName(resultSet.getString("profession_name"));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return profession;
    }

    private List<Profession> getResult(ResultSet resultSet) throws SQLException {
        List<Profession> professions = Lists.newArrayList();
        while (resultSet.next()) {
            Profession profession = new Profession();
            profession.setProfessionId(resultSet.getLong("profession_id"));
            profession.setProfessionName(resultSet.getString("profession_name"));
            professions.add(profession);
        }
        return professions;
    }
}
