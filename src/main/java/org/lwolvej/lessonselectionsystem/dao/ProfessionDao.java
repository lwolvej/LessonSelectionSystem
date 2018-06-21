package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.entity.Profession;

import java.util.List;

/**
 * 专业实体的dao层
 *
 * @author LwolveJ
 */
public interface ProfessionDao {

    /**
     * 获取所有专业的信息
     *
     * @return 返回所有专业信息的集合
     */
    List<Profession> selectAllProfession();

    /**
     * 根据学院id获取学院下的专业的信息
     *
     * @param departmentId 学院的id
     * @return 学院下专业信息的集合
     */
    List<Profession> selectProfessionById(Long departmentId);

    /**
     * 获取专业信息
     *
     * @param professionId 专业的id
     * @return 专业的信息
     */
    Profession selectProfession(Long professionId);
}
