package org.lwolvej.lessonselectionsystem.service.impl;

import org.lwolvej.lessonselectionsystem.dao.DepartmentDao;
import org.lwolvej.lessonselectionsystem.entity.Department;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao = Factory.getInstance().create(DepartmentDao.class);

    @Override
    public List<Department> queryAllDepartment() {
        return departmentDao.selectAllDepartment();
    }
}
