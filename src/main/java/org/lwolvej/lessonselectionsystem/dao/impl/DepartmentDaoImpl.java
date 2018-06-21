package org.lwolvej.lessonselectionsystem.dao.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.constant.DepartmentConstant;
import org.lwolvej.lessonselectionsystem.dao.DepartmentDao;
import org.lwolvej.lessonselectionsystem.entity.Department;
import org.lwolvej.lessonselectionsystem.exception.DaoException;
import org.lwolvej.lessonselectionsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * dao层实现类
 *
 * @author LwolveJ
 */
public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public List<Department> selectAllDepartment() {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Department> departments = Lists.newArrayList();
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(DepartmentConstant.SELECT_ALL_DEPARTMENT);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getLong("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return departments;
    }

    @Override
    public Department selectDepartmentById(Long departmentId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Department department = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(DepartmentConstant.SELECT_DEPARTMENT_BY_ID);
            statement.setLong(1, departmentId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                department = new Department();
                department.setDepartmentId(resultSet.getLong("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return department;
    }
}
