package org.lwolvej.lessonselectionsystem.dao.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.constant.RoomConstant;
import org.lwolvej.lessonselectionsystem.dao.RoomDao;
import org.lwolvej.lessonselectionsystem.entity.Room;
import org.lwolvej.lessonselectionsystem.exception.DaoException;
import org.lwolvej.lessonselectionsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    @Override
    public List<Room> selectAllRoom() {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Room> rooms = Lists.newArrayList();
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(RoomConstant.SELECT_ALL_ROOM);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomId(resultSet.getLong("room_id"));
                room.setRoomName(resultSet.getString("room_name"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return rooms;
    }
}
