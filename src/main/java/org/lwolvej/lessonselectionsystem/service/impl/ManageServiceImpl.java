package org.lwolvej.lessonselectionsystem.service.impl;

import com.google.common.collect.Lists;
import org.lwolvej.lessonselectionsystem.dao.LessonDao;
import org.lwolvej.lessonselectionsystem.dao.ManageDao;
import org.lwolvej.lessonselectionsystem.dao.ScoreDao;
import org.lwolvej.lessonselectionsystem.dao.TeacherDao;
import org.lwolvej.lessonselectionsystem.dto.ManageDTO;
import org.lwolvej.lessonselectionsystem.entity.Lesson;
import org.lwolvej.lessonselectionsystem.entity.Manage;
import org.lwolvej.lessonselectionsystem.entity.Teacher;
import org.lwolvej.lessonselectionsystem.service.ManageService;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.util.ObjectUtil;

import java.util.List;

/**
 * 服务层实现类
 *
 * @author LwolveJ
 */
public class ManageServiceImpl implements ManageService {

    private LessonDao lessonDao = Factory.getInstance().create(LessonDao.class);

    private ScoreDao scoreDao = Factory.getInstance().create(ScoreDao.class);

    private ManageDao manageDao = Factory.getInstance().create(ManageDao.class);

    private TeacherDao teacherDao = Factory.getInstance().create(TeacherDao.class);

    @Override
    public List<ManageDTO> queryTeacherLesson(String teacherId) {
        Long newId = ObjectUtil.StringToLong(teacherId);
        if (newId != null) {
            Teacher teacher = teacherDao.selectTeacherById(newId);
            if (teacher != null) {
                List<Manage> manages = manageDao.selectByTeacherId(newId);
                if (manages != null && manages.size() != 0) {
                    return getResult(manages, teacher);
                }
            }
        }
        return null;
    }

    @Override
    public Boolean changeManage(ManageDTO manageDTO, String manageId) {
        return null;
    }

    private List<ManageDTO> getResult(List<Manage> manages, Teacher teacher) {
        List<ManageDTO> data = Lists.newArrayList();
        manages.forEach(obj -> {
            Lesson lesson = lessonDao.selectLessonById(obj.getLesson().getLessonId());
            if (lesson.getLessonStatus()) {
                ManageDTO dto = new ManageDTO();
                dto.setClockId(obj.getClock().getClockId());
                dto.setClockName(obj.getClock().getClockName());
                dto.setGradeId((long) obj.getGrade().getGradeId());
                dto.setGradeName(obj.getGrade().getGradeName());
                dto.setLessonId(lesson.getLessonId());
                dto.setLessonName(lesson.getLessonName());
                dto.setRoomId(obj.getRoom().getRoomId());
                dto.setRoomName(obj.getRoom().getRoomName());
                dto.setTeacherId(teacher.getTeacherId());
                dto.setTeacherName(teacher.getTeacherName());
                data.add(dto);
            }
        });
        return data;
    }
}
