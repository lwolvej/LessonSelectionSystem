package org.lwolvej.lessonselectionsystem.service.impl;

import org.lwolvej.lessonselectionsystem.dao.ProfessionDao;
import org.lwolvej.lessonselectionsystem.entity.Profession;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.service.ProfessionService;
import org.lwolvej.lessonselectionsystem.util.ObjectUtil;

import java.util.List;

public class ProfessionServiceImpl implements ProfessionService {

    private ProfessionDao professionDao = Factory.getInstance().create(ProfessionDao.class);

    @Override
    public List<Profession> queryProfessionByDepartmentId(String departmentId) {
        Long newId = ObjectUtil.StringToLong(departmentId);
        if (newId != null) {
            List<Profession> professionList = professionDao.selectProfessionById(newId);
            if (professionList != null && professionList.size() != 0) {
                return professionList;
            }
        }
        return null;
    }
}
