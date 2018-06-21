package org.lwolvej.lessonselectionsystem.constant;

/**
 * department实体dao层SQL语句
 *
 * @author LwolveJ
 */
public final class DepartmentConstant {

    public static final String SELECT_ALL_DEPARTMENT = "SELECT * FROM department";

    public static final String SELECT_DEPARTMENT_BY_ID = "SELECT * FROM department WHERE department_id = ?";
}
