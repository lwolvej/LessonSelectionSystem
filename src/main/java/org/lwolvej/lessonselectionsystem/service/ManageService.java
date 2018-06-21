package org.lwolvej.lessonselectionsystem.service;

import org.lwolvej.lessonselectionsystem.dto.ManageDTO;

import java.util.List;

/**
 * 服务层接口
 *
 * @author LwolveJ
 */
public interface ManageService {

    /**
     *根据教师的id查找教师的所有课程安排，除去没有通过的课程
     * @param teacherId 教师的id
     * @return 查找到的集合
     */
    List<ManageDTO> queryTeacherLesson(String teacherId);

    /**
     *修改一个课程
     * @param manageDTO 数据
     * @param manageId id
     * @return 是否修改成功
     */
    Boolean changeManage(ManageDTO manageDTO, String manageId);
}
