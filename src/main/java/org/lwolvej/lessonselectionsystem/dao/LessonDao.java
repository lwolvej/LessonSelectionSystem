package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.entity.Lesson;

import java.util.List;

/**
 * dao层接口
 *
 * @author LwolveJ
 */
public interface LessonDao {

    /**
     * 通过课程号来检索课程
     *
     * @param lessonId 课程号
     * @return 课程实体
     */
    Lesson selectLessonById(Long lessonId);


    /**
     * 通过状态来查找课程
     *
     * @param status 状态
     * @return 课程数据集
     */
    List<Lesson> selectLessonByStatus(Boolean status);

    /**
     * 新插入一个课程
     *
     * @param lessonName   课程名称
     * @param lessonStatus 课程状态
     * @param teacherId    教师id
     * @return 插入的课程号
     */
    Long insertLesson(String lessonName, Boolean lessonStatus, Long teacherId);

    /**
     * 更新课程信息：更新课程名称，更新课程申请状态
     *
     * @param lessonId     课程号
     * @param lessonName   课程名称
     * @param lessonStatus 课程状态
     * @return 是否更新成功
     */
    Boolean updateLessonInfo(Long lessonId, String lessonName, Boolean lessonStatus);

    /**
     * 删除一个课程
     *
     * @param lessonId 课程号
     * @return 是否删除成功
     */
    Boolean deleteLesson(Long lessonId);
}
