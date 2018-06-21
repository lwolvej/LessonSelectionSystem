package org.lwolvej.lessonselectionsystem.service.impl;

import org.lwolvej.lessonselectionsystem.dao.ClassesDao;
import org.lwolvej.lessonselectionsystem.entity.Classes;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.service.ClassesService;
import org.lwolvej.lessonselectionsystem.util.ObjectUtil;

import java.util.List;

public class ClassesServiceImpl implements ClassesService {

    private ClassesDao classesDao = Factory.getInstance().create(ClassesDao.class);

    @Override
    public List<Classes> queryClassById(String departmentId, String professionId, String gradeId) {
        Long newId1 = ObjectUtil.StringToLong(departmentId);
        Long newId2 = ObjectUtil.StringToLong(professionId);
        Long newId3 = ObjectUtil.StringToLong(gradeId);
        return classesDao.selectClassesById(newId1, newId2, newId3);
    }
}
