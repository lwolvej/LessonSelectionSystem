package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.entity.Clock;

import java.util.List;

public interface ClockDao {

    List<Clock> selectAllClock();
}
