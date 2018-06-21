package org.lwolvej.lessonselectionsystem.dao.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.constant.ScoreConstant;
import org.lwolvej.lessonselectionsystem.dao.ScoreDao;
import org.lwolvej.lessonselectionsystem.dto.AnalyseDTO;
import org.lwolvej.lessonselectionsystem.entity.*;
import org.lwolvej.lessonselectionsystem.exception.DaoException;
import org.lwolvej.lessonselectionsystem.util.DBUtil;

import java.sql.*;
import java.util.List;


/**
 * dao层实现类
 *
 * @author LwolveJ
 */
public class ScoreDaoImpl implements ScoreDao {

    @Override
    public Boolean insertScore(Integer scoreNumber, Long studentId, Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        int index = 0;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ScoreConstant.INSERT_SCORE);
            statement.setInt(1, scoreNumber);
            statement.setLong(2, studentId);
            statement.setLong(3, lessonId);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            DBUtil.freeConn(connection);
        }
        return index != 0;
    }

    @Override
    public Boolean deleteScore(Long scoreId) {
        Connection connection = null;
        PreparedStatement statement;
        int index = 0;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ScoreConstant.DELETE_SCORE);
            statement.setLong(1, scoreId);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            DBUtil.freeConn(connection);
        }
        return index != 0;
    }

    @Override
    public Boolean updateScore(Integer scoreNumber, Long studentId, Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        int index = 0;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ScoreConstant.UPDATE_SCORE);
            statement.setInt(1, scoreNumber);
            statement.setLong(2, studentId);
            statement.setLong(3, lessonId);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return index != 0;
    }

    @Override
    public List<Score> selectScoreByStudentId(Long studentId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Score> scores;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ScoreConstant.SELECT_SCORE_BY_STUDENT_ID);
            statement.setLong(1, studentId);
            resultSet = statement.executeQuery();
            scores = getListResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return scores;
    }

    @Override
    public List<Score> selectScoreByTeacherId(Long teacherId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Score> scores;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ScoreConstant.SELECT_SCORE_BY_TEACHER_ID);
            statement.setLong(1, teacherId);
            resultSet = statement.executeQuery();
            scores = getListResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return scores;
    }

    @Override
    public List<Score> selectScoreByLessonId(Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Score> scores;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ScoreConstant.SELECT_SCORE_BY_LESSON_ID);
            statement.setLong(1, lessonId);
            resultSet = statement.executeQuery();
            scores = getListResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return scores;
    }

    @Override
    public AnalyseDTO selectMaxScoreByLessonId(Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        AnalyseDTO dto = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ScoreConstant.SELECT_MAX_SCORE_BY_LESSON_ID);
            statement.setLong(1, lessonId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dto = new AnalyseDTO();
                dto.setLessonId(resultSet.getLong("lesson_id"));
                dto.setData(resultSet.getDouble("max_score_number"));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return dto;
    }

    @Override
    public AnalyseDTO selectMinScoreByLessonId(Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        AnalyseDTO dto = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ScoreConstant.SELECT_MIN_SCORE_BY_LESSON_ID);
            statement.setLong(1, lessonId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dto = new AnalyseDTO();
                dto.setLessonId(resultSet.getLong("lesson_id"));
                dto.setData(resultSet.getDouble("min_score_number"));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return dto;
    }

    @Override
    public AnalyseDTO selectAverageScoreByLessonId(Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        AnalyseDTO dto = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(ScoreConstant.SELECT_AVERAGE_SCORE_BY_LESSON_ID);
            statement.setLong(1, lessonId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dto = new AnalyseDTO();
                dto.setData(resultSet.getDouble("average_score_number"));
                dto.setLessonId(resultSet.getLong("lesson_id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return dto;
    }


    private List<Score> getListResult(ResultSet resultSet) throws SQLException {
        List<Score> scoreList = Lists.newArrayList();
        while (resultSet.next()) {
            Score score = new Score();
            score.setScoreId(resultSet.getLong("score_id"));
            score.setScoreNumber(resultSet.getInt("score_number"));
            Student student = new Student();
            student.setStudentId(resultSet.getLong("student_id"));
            student.setStudentName(resultSet.getString("student_name"));
            student.setStudentGender(resultSet.getBoolean("student_gender"));
            student.setStudentNumber(resultSet.getString("student_number"));
            Department department = new Department();
            department.setDepartmentId(resultSet.getLong("a_department_id"));
            department.setDepartmentName(resultSet.getString("a_department_name"));
            Profession profession = new Profession();
            profession.setProfessionId(resultSet.getLong("profession_id"));
            profession.setProfessionName(resultSet.getString("profession_name"));
            Classes classes = new Classes();
            classes.setClassesId(resultSet.getLong("classes_id"));
            classes.setClassesName(resultSet.getString("classes_name"));
            Grade grade = new Grade();
            grade.setGradeId(resultSet.getInt("grade_id"));
            grade.setGradeName(resultSet.getInt("grade_name"));
            student.setDepartment(department);
            student.setProfession(profession);
            student.setGrade(grade);
            student.setClasses(classes);
            Lesson lesson = new Lesson();
            lesson.setLessonId(resultSet.getLong("lesson_id"));
            lesson.setLessonName(resultSet.getString("lesson_name"));
            lesson.setLessonStatus(resultSet.getBoolean("lesson_status"));
            score.setStudent(student);
            score.setLesson(lesson);
            scoreList.add(score);
        }
        return scoreList;
    }
}
