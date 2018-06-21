package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.entity.Manage;

import java.util.List;

/**
 * dao层接口
 *
 * @author LwolveJ
 */
public interface ManageDao {

    /**
     * 插入一个安排
     *
     * @param roomId    教室的id
     * @param clockId   时间的id
     * @param lessonId  课程的id
     * @param gradeId   年级的id
     * @param teacherId 教师的id
     * @return 是否插入的状态
     */
    Boolean insertManage(Long roomId, Long clockId, Long lessonId, Long gradeId, Long teacherId);

    /**
     * 删除一个安排
     *
     * @param manageId 安排的id
     * @return 是否删除的状态
     */
    Boolean deleteManage(Long manageId);

    /**
     * 通过lessonId删除一个安排
     *
     * @param lessonId 课程id
     * @return 是否删除成功
     */
    Boolean deleteManageByLessonId(Long lessonId);

    /**
     * 通过年级的id查询所有安排
     *
     * @param gradeId 年级的id
     * @return 相同年级的安排的集合
     */
    List<Manage> selectByGradeId(Long gradeId);

    /**
     * 通过教师的id查询所有安排
     *
     * @param teacherId 教师的id
     * @return 相同教师的安排的集合
     */
    List<Manage> selectByTeacherId(Long teacherId);

    /**
     * 通过教室的id查询所有安排
     *
     * @param roomId 教室的id
     * @return 相同时间下所有的安排
     */
    List<Manage> selectByRoomId(Long roomId);

    Manage selectByLessonId(Long lessonId);

    /**
     * 根据安排的id更新安排的信息
     *
     * @param manageId  安排的id
     * @param roomId    教室的id
     * @param clockId   时间的id
     * @param lessonId  课程的id
     * @param gradeId   年级的id
     * @param teacherId 教师的id
     * @return 是否更新成功
     */
    Boolean updateManage(Long manageId, Long roomId, Long clockId, Long lessonId, Long gradeId, Long teacherId);
}
