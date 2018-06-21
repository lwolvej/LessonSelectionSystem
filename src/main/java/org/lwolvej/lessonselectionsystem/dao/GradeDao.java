package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.entity.Grade;

import java.util.List;

/**
 * dao层接口
 *
 * @author LwolveJ
 */
public interface GradeDao {

    /**
     * 获取所有的年级实体
     *
     * @return 返回年级实体的集合
     */
    List<Grade> selectAllGrade();

    /**
     * 通过年级的id获取年级实体
     *
     * @param gradeId 年级的id
     * @return 年级的实体
     */
    Grade selectGradeById(Long gradeId);
}
