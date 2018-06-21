package org.lwolvej.lessonselectionsystem.service.impl;

import org.lwolvej.lessonselectionsystem.dao.*;
import org.lwolvej.lessonselectionsystem.dto.StudentDTO;
import org.lwolvej.lessonselectionsystem.entity.*;
import org.lwolvej.lessonselectionsystem.service.StudentService;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.util.MD5Util;
import org.lwolvej.lessonselectionsystem.util.ObjectUtil;

import java.util.List;

/**
 * 服务层实现类
 *
 * @author LwolveJ
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = Factory.getInstance().create(StudentDao.class);

    private DepartmentDao departmentDao = Factory.getInstance().create(DepartmentDao.class);

    private ProfessionDao professionDao = Factory.getInstance().create(ProfessionDao.class);

    private ClassesDao classesDao = Factory.getInstance().create(ClassesDao.class);

    private GradeDao gradeDao = Factory.getInstance().create(GradeDao.class);

    private LessonDao lessonDao = Factory.getInstance().create(LessonDao.class);

    @Override
    public Student studentLogin(String studentId, String studentPassword) {
        Long studentIdLong = ObjectUtil.StringToLong(studentId);
        //判断输入的id是否符合规范
        if (studentIdLong != null) {
            String newPwd = ObjectUtil.stringCheck(studentPassword);
            //判断密码是否为空或者null
            if (newPwd != null) {
                Student student = studentDao.selectStudentById(studentIdLong);
                if (student != null) {
                    //判断两者密码是否一致
                    if (MD5Util.checkPassword(newPwd, student.getStudentPwd().trim())) {
                        return student;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Long addNewStudent(StudentDTO studentDTO) {
        //判断前端数据是否为空
        if (studentDTO != null) {
            Long departmentId = studentDTO.getDepartmentId();
            //判断其中有无空的数据
            if (departmentId != null) {
                Department department = departmentDao.selectDepartmentById(departmentId);
                //判断学院是否存在
                if (department != null) {
                    Long professionId = studentDTO.getProfessionId();
                    //判断有无空的数据
                    if (professionId != null) {
                        Profession profession = professionDao.selectProfession(professionId);
                        //判断专业是否存在
                        if (profession != null) {
                            Long classesId = studentDTO.getClassesId();
                            //判断有无空的数据
                            if (classesId != null) {
                                //判断班级是否存在
                                Classes classes = classesDao.selectClasses(classesId);
                                if (classes != null) {
                                    //判断有无空的数据
                                    Long gradeId = studentDTO.getGradeId();
                                    if (gradeId != null) {
                                        Grade grade = gradeDao.selectGradeById(gradeId);
                                        //判断年级是否存在
                                        if (grade != null) {
                                            String newPwd = MD5Util.getMD5(studentDTO.getStudentPwd());
                                            return studentDao.insertStudent(studentDTO.getStudentName(), newPwd,
                                                    studentDTO.getStudentGender(), studentDTO.getStudentNumber(),
                                                    studentDTO.getDepartmentId(), studentDTO.getProfessionId(),
                                                    studentDTO.getClassesId(), studentDTO.getGradeId());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Student> queryStudentByLessonId(String lessonId) {
        Long newId = ObjectUtil.StringToLong(lessonId);
        if (newId != null) {
            Lesson lesson = lessonDao.selectLessonById(newId);
            if (lesson != null) {
                List<Student> students = studentDao.selectStudentByLessonId(newId);
                if (students != null && students.size() != 0) {
                    return students;
                }
            }
        }
        return null;
    }

    @Override
    public List<Student> queryAllStudent() {
        return studentDao.selectAllStudent();
    }
}
