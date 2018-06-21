package org.lwolvej.lessonselectionsystem.dao.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.constant.ClockConstant;
import org.lwolvej.lessonselectionsystem.dao.ClockDao;
import org.lwolvej.lessonselectionsystem.entity.Clock;
import org.lwolvej.lessonselectionsystem.exception.DaoException;
import org.lwolvej.lessonselectionsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClockDaoImpl implements ClockDao {

    @Override
    public List<Clock> selectAllClock() {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Clock> clocks = Lists.newArrayList();
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ClockConstant.SELECT_ALL_CLOCK);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Clock clock = new Clock();
                clock.setClockId(resultSet.getLong("clock_id"));
                clock.setClockName(resultSet.getString("clock_name"));
                clocks.add(clock);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return clocks;
    }
}
