package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.entity.Department;

import java.util.List;

/**
 * dao层接口
 *
 * @author LwolveJ
 */
public interface DepartmentDao {

    /**
     * 获取所有学院的信息
     *
     * @return 所有学院信息的集合
     */
    List<Department> selectAllDepartment();

    /**
     * 获取学院信息
     *
     * @param departmentId 学院id
     * @return 学院信息
     */
    Department selectDepartmentById(Long departmentId);
}
