package org.lwolvej.lessonselectionsystem.dao;

import org.lwolvej.lessonselectionsystem.dto.AnalyseDTO;
import org.lwolvej.lessonselectionsystem.entity.Score;

import java.util.List;

/**
 * score的dao层
 *
 * @author LwolveJ
 */
public interface ScoreDao {

    /**
     * 新插入一个成绩
     *
     * @param scoreNumber 成绩
     * @param studentId   成绩所属学生
     * @param lessonId    成绩所属课程
     * @return 是否插入成功
     */
    Boolean insertScore(Integer scoreNumber, Long studentId, Long lessonId);

    /**
     * 删除一个成绩
     *
     * @param scoreId 成绩的id
     * @return 是否删除成功
     */
    Boolean deleteScore(Long scoreId);

    /**
     * 更新成绩
     *
     * @param scoreNumber 成绩
     * @param studentId   学生的id
     * @param lessonId    课程的id
     * @return 是否更新成功
     */
    Boolean updateScore(Integer scoreNumber, Long studentId, Long lessonId);

    /**
     * 根据学生的id查询成绩
     *
     * @param studentId 学生的id
     * @return 成绩的集合
     */
    List<Score> selectScoreByStudentId(Long studentId);

    /**
     * 根据教师的id查询成绩
     *
     * @param teacherId 教师的id
     * @return 成绩的集合
     */
    List<Score> selectScoreByTeacherId(Long teacherId);

    /**
     * 根据课程的id查询成绩
     *
     * @param lessonId 课程的id
     * @return 课程的集合
     */
    List<Score> selectScoreByLessonId(Long lessonId);

    /**
     * 最高的成绩
     *
     * @param lessonId 课程的id
     * @return 最高的成绩
     */
    AnalyseDTO selectMaxScoreByLessonId(Long lessonId);

    /**
     * 查询最低的成绩
     *
     * @param lessonId 课程的id
     * @return 最低的成绩
     */
    AnalyseDTO selectMinScoreByLessonId(Long lessonId);

    /**
     * 平均成绩
     *
     * @param lessonId 课程的id
     * @return 平均成绩
     */
    AnalyseDTO selectAverageScoreByLessonId(Long lessonId);
}
