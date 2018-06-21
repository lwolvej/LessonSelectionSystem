package org.lwolvej.lessonselectionsystem.service;

import org.lwolvej.lessonselectionsystem.dto.LessonDTO;
import org.lwolvej.lessonselectionsystem.dto.ManageDTO;
import org.lwolvej.lessonselectionsystem.dto.TimeTableDTO;
import org.lwolvej.lessonselectionsystem.entity.Lesson;

import java.util.List;

/**
 * 课程的service层，主要实现课程表，新增修改删除课程，课程审批的功能
 *
 * @author LwolveJ
 */
public interface LessonService {


    /**
     * 学生查询课程表
     *
     * @param studentId 学生id
     * @return 课程表查询结果
     */
    List<TimeTableDTO> queryTimeTable(String studentId);

    /**
     * 查找所有还没有被通过的课程
     *
     * @return 查找到的数据集
     */
    List<ManageDTO> queryLessonNotApprove();

    /**
     * 新添加一门课程
     *
     * @param data 课程数据
     * @return 是否添加成功
     */
    Boolean addNewLesson(LessonDTO data);

    /**
     * 修改课程的状态
     *
     * @param data 需要修改的课程id
     * @return 是否修改成功
     */
    Boolean changeLessonStatus(String data);

    /**
     * 删除课程
     *
     * @param data 需要删除的课程id
     * @return 是否删除成功
     */
    Boolean removeLesson(String data);

    /**
     * 学生选课
     * @param studentId 学生的id
     * @param lessonIdList 课程id的集合
     * @return 是否全部选课成功
     */
    Boolean studentSelectLesson(String studentId, List<String> lessonIdList);

    Lesson queryOneLesson(String lessonId);

    List<ManageDTO> queryLessonCanSelect(String studentId);
}

