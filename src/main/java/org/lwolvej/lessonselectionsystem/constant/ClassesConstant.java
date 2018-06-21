package org.lwolvej.lessonselectionsystem.constant;

/**
 * classes实体dao层SQL语句
 *
 * @author LwolveJ
 */
public final class ClassesConstant {

    public static final String SELECT_ALL_CLASSES = "SELECT * FROM classes";

    public static final String SELECT_CLASSES_BY_ID = "SELECT * FROM classes " +
            " WHERE department_id = ? " +
            " AND profession_id = ? " +
            " AND grade_id = ? ";

    public static final String SELECT_CLASSES = "SELECT * FROM classes WHERE classes_id = ?";
}
