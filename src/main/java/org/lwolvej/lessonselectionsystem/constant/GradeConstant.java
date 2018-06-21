package org.lwolvej.lessonselectionsystem.constant;

/**
 * grade实体dao层SQL语句
 *
 * @author LwolveJ
 */
public final class GradeConstant {

    public static final String SELECT_ALL_GRADE = "SELECT * FROM grade";

    public static final String SELECT_GRADE_BY_ID = "SELECT * FROM grade WHERE grade_id = ?";
}
