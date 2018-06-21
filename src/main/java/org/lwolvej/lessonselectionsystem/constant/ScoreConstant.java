package org.lwolvej.lessonselectionsystem.constant;

/**
 * 成绩相关的SQL语句
 *
 * @author LwolveJ
 */
public final class ScoreConstant {

    public static final String INSERT_SCORE = "INSERT INTO score(score_number, student_id, lesson_id) VALUES(?,?,?)";

    public static final String DELETE_SCORE = "DELETE FROM score WHERE score_id = ?";

    public static final String UPDATE_SCORE = "UPDATE score SET score_number = ? WHERE student_id = ? AND lesson_id = ?";

    public static final String SELECT_SCORE_BY_STUDENT_ID = "SELECT a.score_id, a.score_number, b.student_id, b.student_name, " +
            " b.student_gender, b.student_number, c.department_id AS a_department_id, c.department_name AS a_department_name, " +
            " d.profession_id, d.profession_name, e.grade_id, e.grade_name, " +
            " f.classes_id, f.classes_name, g.lesson_id, g.lesson_name, g.lesson_status " +
            " FROM score a " +
            " LEFT JOIN student b ON b.student_id = a.student_id " +
            " LEFT JOIN department c ON c.department_id = b.department_id " +
            " LEFT JOIN profession d ON d.profession_id = b.profession_id " +
            " LEFT JOIN grade e ON e.grade_id = b.grade_id " +
            " LEFT JOIN classes f ON f.classes_id = b.classes_id " +
            " LEFT JOIN lesson g ON g.lesson_id = a.lesson_id " +
            " WHERE a.student_id = ?";

    public static final String SELECT_SCORE_BY_TEACHER_ID = "";

    public static final String SELECT_SCORE_BY_LESSON_ID = "SELECT a.score_id, a.score_number, b.student_id, b.student_name, " +
            " b.student_gender, b.student_number, c.department_id AS a_department_id, c.department_name AS a_department_name, " +
            " d.profession_id, d.profession_name, e.grade_id, e.grade_name, " +
            " f.classes_id, f.classes_name, g.lesson_id, g.lesson_name, g.lesson_status " +
            " FROM score a " +
            " LEFT JOIN student b ON b.student_id = a.student_id " +
            " LEFT JOIN department c ON c.department_id = b.department_id " +
            " LEFT JOIN profession d ON d.profession_id = b.profession_id " +
            " LEFT JOIN grade e ON e.grade_id = b.grade_id " +
            " LEFT JOIN classes f ON f.classes_id = b.classes_id " +
            " LEFT JOIN lesson g ON g.lesson_id = a.lesson_id " +
            " WHERE a.lesson_id = ?";

    public static final String SELECT_MAX_SCORE_BY_LESSON_ID = "SELECT * FROM max_score WHERE lesson_id = ?";

    public static final String SELECT_MIN_SCORE_BY_LESSON_ID = "SELECT * FROM min_score WHERE lesson_id = ?";

    public static final String SELECT_AVERAGE_SCORE_BY_LESSON_ID = "SELECT * FROM average_score WHERE lesson_id = ?";
}

