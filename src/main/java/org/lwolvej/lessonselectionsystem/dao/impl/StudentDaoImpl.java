package org.lwolvej.lessonselectionsystem.dao.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.constant.StudentConstant;
import org.lwolvej.lessonselectionsystem.dao.StudentDao;
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
public class StudentDaoImpl implements StudentDao {


    /**
     * 通过student的id获取student实体
     *
     * @param studentId student的id
     * @return student实体
     */
    public Student selectStudentById(Long studentId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Student student = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(StudentConstant.SELECT_STUDENT_BY_ID);
            statement.setLong(1, studentId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                student = new Student();
                //学生相关
                student.setStudentId(resultSet.getLong("student_id"));
                student.setStudentName(resultSet.getString("student_name"));
                student.setStudentGender(resultSet.getBoolean("student_gender"));
                student.setStudentPwd(resultSet.getString("student_pwd"));
                student.setStudentNumber(resultSet.getString("student_number"));
                //学院相关
                Department department = new Department();
                department.setDepartmentId(resultSet.getLong("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                //专业相关
                Profession profession = new Profession();
                profession.setProfessionId(resultSet.getLong("profession_id"));
                profession.setProfessionName(resultSet.getString("profession_name"));
                //年级相关
                Grade grade = new Grade();
                grade.setGradeId(resultSet.getInt("grade_id"));
                grade.setGradeName(resultSet.getInt("grade_name"));
                //班级相关
                Classes classes = new Classes();
                classes.setClassesId(resultSet.getLong("classes_id"));
                classes.setClassesName(resultSet.getString("classes_name"));
                student.setDepartment(department);
                student.setProfession(profession);
                student.setGrade(grade);
                student.setClasses(classes);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return student;
    }


    @Override
    public Long insertStudent(String studentName, String studentPwd, Boolean studentGender, String studentNumber,
                              Long departmentId, Long profession_id, Long classesId, Long gradeId) {

        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        Long index = null;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(StudentConstant.INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, studentName);
            statement.setString(2, studentPwd);
            statement.setBoolean(3, studentGender);
            statement.setString(4, studentNumber);
            statement.setLong(5, departmentId);
            statement.setLong(6, profession_id);
            statement.setLong(7, classesId);
            statement.setLong(8, gradeId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                index = (long) resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return index;
    }


    @Override
    public List<Student> selectAllStudent() {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Student> students;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(StudentConstant.SELECT_ALL_STUDENT);
            resultSet = statement.executeQuery();
            students = getResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return students;
    }

    @Override
    public List<Student> selectStudentByLessonId(Long lessonId) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        List<Student> students;
        try {
            connection = DBUtil.getConn();
            statement = connection.prepareStatement(StudentConstant.SELECT_STUDENT_BY_LESSON_ID);
            statement.setLong(1, lessonId);
            resultSet = statement.executeQuery();
            students = getResult(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            DBUtil.freeConn(connection);
        }
        return students;
    }

    private List<Student> getResult(ResultSet resultSet) throws SQLException {
        List<Student> students = Lists.newArrayList();

        while (resultSet.next()) {
            Student student = new Student();
            //学生相关
            student.setStudentId(resultSet.getLong("student_id"));
            student.setStudentName(resultSet.getString("student_name"));
            student.setStudentGender(resultSet.getBoolean("student_gender"));
            student.setStudentPwd(resultSet.getString("student_pwd"));
            student.setStudentNumber(resultSet.getString("student_number"));
            //学院相关
            Department department = new Department();
            department.setDepartmentId(resultSet.getLong("department_id"));
            department.setDepartmentName(resultSet.getString("department_name"));
            //专业相关
            Profession profession = new Profession();
            profession.setProfessionId(resultSet.getLong("profession_id"));
            profession.setProfessionName(resultSet.getString("profession_name"));
            //年级相关
            Grade grade = new Grade();
            grade.setGradeId(resultSet.getInt("grade_id"));
            grade.setGradeName(resultSet.getInt("grade_name"));
            //班级相关
            Classes classes = new Classes();
            classes.setClassesId(resultSet.getLong("classes_id"));
            classes.setClassesName(resultSet.getString("classes_name"));
            student.setDepartment(department);
            student.setProfession(profession);
            student.setGrade(grade);
            student.setClasses(classes);

            students.add(student);
        }
        return students;
    }
}
