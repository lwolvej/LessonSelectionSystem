package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.entity.Room;

import java.util.List;

public interface RoomDao {

    List<Room> selectAllRoom();
}
