package org.lwolvej.lessonselectionsystem.service.impl;

import org.lwolvej.lessonselectionsystem.dao.ClockDao;
import org.lwolvej.lessonselectionsystem.entity.Clock;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.service.ClockService;

import java.util.List;

public class ClockServiceImpl implements ClockService {

    private ClockDao clockDao = Factory.getInstance().create(ClockDao.class);

    @Override
    public List<Clock> queryAllClock() {
        return clockDao.selectAllClock();
    }
}
