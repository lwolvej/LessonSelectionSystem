package org.lwolvej.lessonselectionsystem.constant;

/**
 * 学生相关的SQL语句
 *
 * @author LwolveJ
 */
public final class StudentConstant {

    public static final String SELECT_STUDENT_BY_ID = "SELECT a.student_id, a.student_name, a.student_gender, " +
            " a.student_pwd, a.student_number, b.department_id, b.department_name, " +
            " c.profession_id, c.profession_name, d.grade_id, d.grade_name, " +
            " e.classes_id, e.classes_name " +
            " FROM student a " +
            " LEFT JOIN department b ON b.department_id = a.department_id " +
            " LEFT JOIN profession c ON c.profession_id = a.profession_id " +
            " LEFT JOIN grade d ON d.grade_id = a.grade_id " +
            " LEFT JOIN classes e ON e.classes_id = a.classes_id " +
            " WHERE a.student_id = ?";

    public static final String INSERT_STUDENT = "INSERT INTO student(student_name, student_pwd, student_gender, " +
            " student_number, department_id, profession_id, classes_id, grade_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


    public static final String SELECT_ALL_STUDENT = "SELECT a.student_id, a.student_name, a.student_gender, " +
            " a.student_pwd, a.student_number, b.department_id, b.department_name, " +
            " c.profession_id, c.profession_name, d.grade_id, d.grade_name, " +
            " e.classes_id, e.classes_name " +
            " FROM student a " +
            " LEFT JOIN department b ON b.department_id = a.department_id " +
            " LEFT JOIN profession c ON c.profession_id = a.profession_id " +
            " LEFT JOIN grade d ON d.grade_id = a.grade_id " +
            " LEFT JOIN classes e ON e.classes_id = a.classes_id ";

    public static final String SELECT_STUDENT_BY_LESSON_ID = "SELECT a.student_id, a.student_name, a.student_gender, " +
            " a.student_pwd, a.student_number, b.department_id, b.department_name, " +
            " c.profession_id, c.profession_name, d.grade_id, d.grade_name, " +
            " e.classes_id, e.classes_name " +
            " FROM student a " +
            " LEFT JOIN department b ON b.department_id = a.department_id " +
            " LEFT JOIN profession c ON c.profession_id = a.profession_id " +
            " LEFT JOIN grade d ON d.grade_id = a.grade_id " +
            " LEFT JOIN classes e ON e.classes_id = a.classes_id " +
            " WHERE a.student_id IN ( " +
            "  SELECT f.student_id FROM score f WHERE f.lesson_id = ?)";
}
