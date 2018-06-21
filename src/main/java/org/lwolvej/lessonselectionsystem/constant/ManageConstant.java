package org.lwolvej.lessonselectionsystem.constant;

/**
 * 安排相关的SQL语句
 *
 * @author LwolveJ
 */
public final class ManageConstant {

    public static final String INSERT_MANAGE = "INSERT INTO manage(room_id, clock_id, lesson_id, grade_id, teacher_id) VALUES (?,?,?,?,?)";

    public static final String DELETE_MANAGE = "DELETE FROM manage WHERE manage_id = ?";

    public static final String DELETE_MANAGE_BY_LESSON_ID = "DELETE FROM manage WHERE lesson_id = ?";

    public static final String SELECT_BY_GRADE_ID = "SELECT a.manage_id, b.room_id, b.room_name, " +
            " c.clock_id, c.clock_name, d.lesson_id, d.lesson_name, d.lesson_status, e.grade_id, e.grade_name, " +
            " f.teacher_id, f.teacher_name, f.teacher_gender, f.teacher_number, f.teacher_site, " +
            " g.department_id, g.department_name " +
            " FROM manage a " +
            " LEFT JOIN room b ON b.room_id = a.room_id " +
            " LEFT JOIN clock c ON c.clock_id = a.clock_id " +
            " LEFT JOIN lesson d ON d.lesson_id = a.lesson_id " +
            " LEFT JOIN grade e ON e.grade_id = a.grade_id " +
            " LEFT JOIN teacher f ON f.teacher_id = a.teacher_id " +
            " LEFT JOIN department g ON g.department_id = f.department_id " +
            " WHERE a.grade_id = ?";

    public static final String SELECT_BY_TEACHER_ID = "SELECT a.manage_id, b.room_id, b.room_name, " +
            " c.clock_id, c.clock_name, d.lesson_id, d.lesson_name, d.lesson_status, e.grade_id, e.grade_name, " +
            " f.teacher_id, f.teacher_name, f.teacher_gender, f.teacher_number, f.teacher_site, " +
            " g.department_id, g.department_name " +
            " FROM manage a " +
            " LEFT JOIN room b ON b.room_id = a.room_id " +
            " LEFT JOIN clock c ON c.clock_id = a.clock_id " +
            " LEFT JOIN lesson d ON d.lesson_id = a.lesson_id " +
            " LEFT JOIN grade e ON e.grade_id = a.grade_id " +
            " LEFT JOIN teacher f ON f.teacher_id = a.teacher_id " +
            " LEFT JOIN department g ON g.department_id = f.department_id " +
            " WHERE a.teacher_id = ?";

    public static final String SELECT_BY_ROOM_ID = "SELECT a.manage_id, b.room_id, b.room_name, " +
            " c.clock_id, c.clock_name, d.lesson_id, d.lesson_name, d.lesson_status, e.grade_id, e.grade_name, " +
            " f.teacher_id, f.teacher_name, f.teacher_gender, f.teacher_number, f.teacher_site, " +
            " g.department_id, g.department_name " +
            " FROM manage a " +
            " LEFT JOIN room b ON b.room_id = a.room_id " +
            " LEFT JOIN clock c ON c.clock_id = a.clock_id " +
            " LEFT JOIN lesson d ON d.lesson_id = a.lesson_id " +
            " LEFT JOIN grade e ON e.grade_id = a.grade_id " +
            " LEFT JOIN teacher f ON f.teacher_id = a.teacher_id " +
            " LEFT JOIN department g ON g.department_id = f.department_id " +
            " WHERE a.room_id = ?";

    public static final String SELECT_BY_LESSON_ID = "SELECT a.manage_id, b.room_id, b.room_name, " +
            " c.clock_id, c.clock_name, d.lesson_id, d.lesson_name, d.lesson_status, e.grade_id, e.grade_name, " +
            " f.teacher_id, f.teacher_name, f.teacher_gender, f.teacher_number, f.teacher_site, " +
            " g.department_id, g.department_name " +
            " FROM manage a " +
            " LEFT JOIN room b ON b.room_id = a.room_id " +
            " LEFT JOIN clock c ON c.clock_id = a.clock_id " +
            " LEFT JOIN lesson d ON d.lesson_id = a.lesson_id " +
            " LEFT JOIN grade e ON e.grade_id = a.grade_id " +
            " LEFT JOIN teacher f ON f.teacher_id = a.teacher_id " +
            " LEFT JOIN department g ON g.department_id = f.department_id " +
            " WHERE a.lesson_id = ?";

    public static final String UPDATE_MANAGE = "UPDATE manage SET room_id = ?, clock_id = ?, " +
            " lesson_id = ?, grade_id = ?, teacher_id = ? WHERE manage_id = ?";
}
