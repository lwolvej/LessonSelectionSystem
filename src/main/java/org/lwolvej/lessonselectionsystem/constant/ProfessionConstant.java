package org.lwolvej.lessonselectionsystem.constant;

/**
 * profession实体的dao层实现
 *
 * @author LwolveJ
 */
public final class ProfessionConstant {

    public static final String SELECT_ALL_PROFESSION = "SELECT * FROM profession";

    public static final String SELECT_PROFESSION_BY_ID = "SELECT * FROM profession WHERE department_id = ?";

    public static final String SELECT_PROFESSION = "SELECT * FROM profession WHERE profession_id = ?";
}
