package org.lwolvej.lessonselectionsystem.service;

import org.lwolvej.lessonselectionsystem.dto.AnalyseDTO;
import org.lwolvej.lessonselectionsystem.dto.ScoreDTO;
import org.lwolvej.lessonselectionsystem.entity.Score;

import java.util.List;

/**
 * 分数的服务层
 *
 * @author LwolveJ
 */
public interface ScoreService {

    /**
     * 站在学生的角度查询成绩
     *
     * @param studentId 学生的id
     * @return 学生的各科成绩
     */
    List<Score> queryScoreByStudent(String studentId);

    /**
     * 站在学校的角度查询成绩
     *
     * @param lessonId 课程的id
     * @return 选择该课程的学生各科成绩
     */
    List<Score> queryScoreByTeacher(String lessonId);

    /**
     * 前端传来成绩数据接收，并处理，将其读入数据库
     *
     * @param data 成绩数据
     * @return 是否插入成功
     */
    Boolean addScore(List<ScoreDTO> data);

    /**
     * 前端传来修改成绩的信息，处理
     *
     * @param data 成绩修改的相关信息
     * @return 是否修改成功
     */
    Boolean changeScore(ScoreDTO data);

    List<AnalyseDTO> queryScoreAnalysis(String lessonId);


    /**
     * 站在学校的角度，如果某个课程被移除，那么该课程下的所有成绩也会被消除
     *
     * @param lessonId 课程id
     * @return 课程的结果
     */
    @Deprecated
    //补充：成绩信息应该永远被保存，以便学生检查，也就是课程的状态粒度需要重新细分
    Boolean removeScore(String lessonId);
}
