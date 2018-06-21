package org.lwolvej.lessonselectionsystem.constant;

/**
 * 教师相关的SQL语句
 *
 * @author LwolveJ
 */
public final class TeacherConstant {

    public static final String SELECT_TEACHER_BY_ID = "SELECT a.teacher_id, a.teacher_name, " +
            " a.teacher_pwd, a.teacher_gender, a.teacher_number, a.teacher_site, " +
            " b.department_id, b.department_name " +
            " FROM teacher a " +
            " LEFT JOIN department b ON b.department_id = a.department_id " +
            " WHERE a.teacher_id = ?";

    public static final String INSERT_TEACHER = "INSERT INTO teacher(teacher_name, teacher_pwd, teacher_gender, " +
            " teacher_number, teacher_site, department_id) VALUES (?, ?, ?, ?, ?, ?)";
}
