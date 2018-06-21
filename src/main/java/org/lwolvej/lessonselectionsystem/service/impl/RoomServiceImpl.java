package org.lwolvej.lessonselectionsystem.service.impl;

import org.lwolvej.lessonselectionsystem.dao.RoomDao;
import org.lwolvej.lessonselectionsystem.entity.Room;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.service.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    private RoomDao roomDao = Factory.getInstance().create(RoomDao.class);

    @Override
    public List<Room> queryAllRoom() {
        return roomDao.selectAllRoom();
    }
}
