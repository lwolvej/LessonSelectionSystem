package org.lwolvej.lessonselectionsystem.service.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.dao.*;
import org.lwolvej.lessonselectionsystem.dto.AnalyseDTO;
import org.lwolvej.lessonselectionsystem.dto.ScoreDTO;
import org.lwolvej.lessonselectionsystem.entity.Lesson;
import org.lwolvej.lessonselectionsystem.entity.Score;
import org.lwolvej.lessonselectionsystem.entity.Student;
import org.lwolvej.lessonselectionsystem.entity.Teacher;
import org.lwolvej.lessonselectionsystem.service.ScoreService;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.util.ObjectUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务层实现类
 *
 * @author LwolveJ
 */
public class ScoreServiceImpl implements ScoreService {

    private LessonDao lessonDao = Factory.getInstance().create(LessonDao.class);

    private ScoreDao scoreDao = Factory.getInstance().create(ScoreDao.class);

    private StudentDao studentDao = Factory.getInstance().create(StudentDao.class);

    private TeacherDao teacherDao = Factory.getInstance().create(TeacherDao.class);


    @Override
    public List<Score> queryScoreByStudent(String studentId) {
        Long newId = ObjectUtil.StringToLong(studentId);
        //判断id是否为空
        if (newId != null) {
            Student student = studentDao.selectStudentById(newId);
            //判断该id下的student是否存在
            if (student != null) {
                //查找到有之后查询score表
                List<Score> scores = scoreDao.selectScoreByStudentId(newId);
                //如果查找到的数据为空，则直接返回null
                if (scores != null && scores.size() != 0) {
                    scores = scores.stream()
                            .filter(obj -> obj.getScoreNumber() != -1)
                            .collect(Collectors.toList());
                    return scores;
                }
            }
        }
        return null;
    }

    @Override
    public List<Score> queryScoreByTeacher(String lessonId) {
        Long newId = ObjectUtil.StringToLong(lessonId);
        //判断teacherId不为空
        if (newId != null) {
            Lesson lesson = lessonDao.selectLessonById(newId);
            //判断teacherId是否正确
            if (lesson != null) {
                List<Score> scores = scoreDao.selectScoreByLessonId(newId);
                //如果查找到数据为null或者空，则直接返回null
                if (scores != null && scores.size() != 0) {
                    return scores;
                }
            }
        }
        return null;
    }

    @Override
    public Boolean addScore(List<ScoreDTO> data) {
        for (ScoreDTO obj : data) {
            Long lessonId = ObjectUtil.StringToLong(obj.getLessonId());
            //判断lessonId不为空，否则快速失败
            if (lessonId != null) {
                Lesson lesson = lessonDao.selectLessonById(lessonId);
                //判断lesson不为空，否则快速失败
                if (lesson != null) {
                    Integer number = ObjectUtil.StringToInteger(obj.getScoreNumber());
                    Long studentId = ObjectUtil.StringToLong(obj.getStudentId());
                    return scoreDao.insertScore(number, studentId, lessonId);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean changeScore(ScoreDTO data) {
        return scoreDao.updateScore(ObjectUtil.StringToInteger(data.getScoreNumber()),
                ObjectUtil.StringToLong(data.getStudentId()),
                ObjectUtil.StringToLong(data.getLessonId()));
    }

    @Override
    public List<AnalyseDTO> queryScoreAnalysis(String lessonId) {
        Long newId = ObjectUtil.StringToLong(lessonId);
        AnalyseDTO max = scoreDao.selectMaxScoreByLessonId(newId);
        AnalyseDTO min = scoreDao.selectMinScoreByLessonId(newId);
        AnalyseDTO avg = scoreDao.selectAverageScoreByLessonId(newId);
        List<AnalyseDTO> data = Lists.newArrayList();
        data.add(max);
        data.add(min);
        data.add(avg);
        return data;
    }

    @Override
    public Boolean removeScore(String lessonId) {
        return null;
    }
}
