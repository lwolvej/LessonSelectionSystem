package org.lwolvej.lessonselectionsystem.service.impl;

import org.lwolvej.lessonselectionsystem.dao.GradeDao;
import org.lwolvej.lessonselectionsystem.entity.Grade;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.service.GradeService;

import java.util.List;

public class GradeServiceImpl implements GradeService {

    private GradeDao gradeDao = Factory.getInstance().create(GradeDao.class);

    @Override
    public List<Grade> queryAllGrade() {
        return gradeDao.selectAllGrade();
    }
}
