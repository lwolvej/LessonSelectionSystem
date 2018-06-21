package org.lwolvej.lessonselectionsystem.constant;

/**
 * lesson实体dao层SQL语句
 *
 * @author LwolveJ
 */
public final class LessonConstant {

    public static final String SELECT_LESSON_BY_ID = "SELECT * FROM lesson WHERE lesson_id = ?";

    public static final String SELECT_LESSON_BY_STATUS = "SELECT * FROM lesson WHERE lesson_status = ?";

    public static final String INSERT_LESSON = "INSERT INTO lesson(lesson_name, lesson_status, teacher_id) VALUES(?,?,?)";

    public static final String UPDATE_LESSON_INFO = "UPDATE lesson SET lesson_name = ?, lesson_status = ? WHERE lesson_id = ?";

    public static final String DELETE_LESSON = "DELETE FROM lesson WHERE lesson_id = ?";
}
