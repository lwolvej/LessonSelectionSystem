package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.entity.Classes;

import java.util.List;

/**
 * dao层接口
 *
 * @author LwolveJ
 */
public interface ClassesDao {

    /**
     * 查询所有班级
     *
     * @return 返回查询到的班级的集合
     */
    List<Classes> selectAllClasses();

    /**
     * 根据id查询班级
     *
     * @param departmentId 学院的id
     * @param professionId 专业的id
     * @param gradeId      年级的id
     * @return 班级的集合
     */
    List<Classes> selectClassesById(Long departmentId, Long professionId, Long gradeId);

    /**
     * 获取班级信息
     *
     * @param classesId 班级的id
     * @return 班级的信息
     */
    Classes selectClasses(Long classesId);
}
