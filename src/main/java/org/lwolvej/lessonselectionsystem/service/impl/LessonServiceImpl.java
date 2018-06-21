package org.lwolvej.lessonselectionsystem.service.impl;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.lwolvej.lessonselectionsystem.dao.*;
import org.lwolvej.lessonselectionsystem.dto.LessonDTO;
import org.lwolvej.lessonselectionsystem.dto.ManageDTO;
import org.lwolvej.lessonselectionsystem.dto.TimeTableDTO;
import org.lwolvej.lessonselectionsystem.entity.*;
import org.lwolvej.lessonselectionsystem.service.LessonService;
import org.lwolvej.lessonselectionsystem.factory.Factory;
import org.lwolvej.lessonselectionsystem.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 服务层实现类
 *
 * @author LwolveJ
 */
public class LessonServiceImpl implements LessonService {

    private LessonDao lessonDao = Factory.getInstance().create(LessonDao.class);

    private ManageDao manageDao = Factory.getInstance().create(ManageDao.class);

    private ScoreDao scoreDao = Factory.getInstance().create(ScoreDao.class);

    private StudentDao studentDao = Factory.getInstance().create(StudentDao.class);

    @Override
    public List<TimeTableDTO> queryTimeTable(String studentId) {
        //check if it is null or empty
        Long newId = ObjectUtil.StringToLong(studentId);
        if (newId != null) {
            Student student = studentDao.selectStudentById(newId);
            //检查学生id是否存在
            if (student != null) {
                List<Score> scores = scoreDao.selectScoreByStudentId(newId);
                //check if it is null or empty
                if (scores != null && scores.size() != 0) {
                    List<TimeTableDTO> list = Lists.newArrayList();
                    //构建映射
                    final Map<Long, Score> map = Maps.newHashMap();
                    //过滤掉不想要的元素
                    scores = scores.stream()
                            .filter(obj -> obj.getScoreNumber().equals(-1))
                            .collect(Collectors.toList());
                    //可能将所有元素都过滤掉了，所以进行判断
                    if (scores != null && scores.size() != 0) {
                        //填充映射
                        scores.forEach(obj -> map.put(obj.getLesson().getLessonId(), obj));
                        //获取该年级的安排集合
                        List<Manage> manages = manageDao.selectByGradeId(
                                (long) student.getGrade().getGradeId()
                        );
                        //对集合进行判断
                        if (manages != null && manages.size() != 0) {
                            //装填数据
                            manages.forEach(obj -> {
                                Score score = map.get(obj.getLesson().getLessonId());
                                //如果能够通过lesson_id在score集合中获取值也就是存在这门课程
                                if (score != null) {
                                    TimeTableDTO tableDTO = new TimeTableDTO();
                                    tableDTO.setLessonId(obj.getLesson().getLessonId());
                                    tableDTO.setLessonName(obj.getLesson().getLessonName());
                                    tableDTO.setClockId(obj.getClock().getClockId());
                                    tableDTO.setClockName(obj.getClock().getClockName());
                                    tableDTO.setRoomId(obj.getRoom().getRoomId());
                                    tableDTO.setRoomName(obj.getRoom().getRoomName());
                                    tableDTO.setTeacherId(obj.getTeacher().getTeacherId());
                                    tableDTO.setTeacherName(obj.getTeacher().getTeacherName());
                                    list.add(tableDTO);
                                }
                            });
                            return list;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 1.确定传输的数据是否在数据库的各个表中都存在。
     * 2.其次查询安排表，确定在同一时间内，同一个教室只能有一个课程
     * 3.接着查询教师安排表，确定这个时间内教师没有课程
     * 4.如果满足以上条件，返回true，否则false
     *
     * @param data 课程数据
     * @return 是否添加成功
     */
    @Override
    public Boolean addNewLesson(LessonDTO data) {
        //确定data不为空
        if (data != null) {
            boolean index = true;
            //获取这个教师的所有课程
            List<Manage> manages = manageDao.selectByTeacherId(data.getTeacherId());
            //判断教师查询是否为空，为空说明没有课程，不为空则判断在这个时间点有没有课程
            if (manages != null) {
                //确定这个教师在这个时间内没有空
                for (Manage manage : manages) {
                    if ((manage.getClock().getClockId()).equals(data.getClockId())) {
                        index = false;
                    }
                    if (!index) break;
                }
            }
            //如果失败，则不需要接下面继续判断，直接断定失败
            if (index) {
                List<Manage> manages1 = manageDao.selectByRoomId(data.getRoomId());
                //判断相同时间下有没有教室与其相同，相同则失败
                if (manages1 != null) {
                    for (Manage manage : manages1) {
                        if ((manage.getClock().getClockId()).equals(data.getClockId())) {
                            index = false;
                        }
                    }
                }
                //表示两次判断都成功，则开始插入
                if (index) {
                    Long lessonId = lessonDao.insertLesson(data.getLessonName(), false, data.getTeacherId());
                    return manageDao.insertManage(
                            data.getRoomId(), data.getClockId(),
                            lessonId, data.getGradeId(), data.getTeacherId()
                    );
                }
            }
        }
        return false;
    }

    @Override
    public Boolean changeLessonStatus(String data) {
        Long id = ObjectUtil.StringToLong(data);
        //判断前端数据不为空
        if (id != null) {
            Lesson lesson = lessonDao.selectLessonById(id);
            //判断课程存在
            if (lesson != null) {
                //如果课程已经审批通过则不需要再次去审批，直接结束
                if (!lesson.getLessonStatus()) {
                    lesson.setLessonStatus(true);
                    lessonDao.updateLessonInfo(lesson.getLessonId(), lesson.getLessonName(), true);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean removeLesson(String data) {
        Long id = ObjectUtil.StringToLong(data);
        if (id != null) {
            Lesson lesson = lessonDao.selectLessonById(id);
            //判断是否存在
            if (lesson != null) {
                Boolean l = lessonDao.deleteLesson(id);
                Boolean j = manageDao.deleteManageByLessonId(id);
                return l && j;
            }
        }
        return false;
    }

    /**
     * 学生选课：首先确定学生在数据库中的确存在，然后遍历lessonId确定每个安排。
     * 接着判断已有安排内不冲突，随后查询学生已有安排(在score表中使用学生id来查找，获得所有lessonId，接着遍历查找所有安排)
     * 然后比较已有安排和新加入（已经过滤一次）的安排是否有冲突，有冲突则跳过。
     * 最后数据插入score表。
     * 判断冲突的方式：不能够同一时间上不同的课。
     *
     * @param studentId    学生的id
     * @param lessonIdList 课程id的集合
     * @return 是否选课成功:null:信息无效，false：存在有课程冲突，true：全部成功
     */
    @Override
    public Boolean studentSelectLesson(String studentId, List<String> lessonIdList) {
        Long newId = ObjectUtil.StringToLong(studentId);
        if (newId != null) {
            Student student = studentDao.selectStudentById(newId);
            if (student != null) {
                //判断插入是否完整
                Boolean index = true;
                //新的安排的集合
                Map<Long, Manage> manageMap = Maps.newHashMap();
                //获取所有新的集合,并过滤
                for (String id : lessonIdList) {
                    Long lessonId = ObjectUtil.StringToLong(id);
                    Manage manage = manageDao.selectByLessonId(lessonId);
                    //如果map中存在这个时间的key，则表示不符合规则，剔除。同时标记插入不完整
                    if (!manageMap.containsKey(manage.getClock().getClockId())) {
                        manageMap.put(manage.getClock().getClockId(), manage);
                    } else {
                        index = false;
                    }
                }
                //获取旧集合
                List<Score> scoreList = scoreDao.selectScoreByStudentId(newId);
                //if有旧课程,else没有
                if (scoreList != null && scoreList.size() != 0) {
                    //最终插入的集合
                    List<Long> list = new ArrayList<>();
                    //开始和旧的数据进行判断
                    for (Score src : scoreList) {
                        Manage manage = manageDao.selectByLessonId(src.getLesson().getLessonId());
                        //if map中有, else map中没有
                        if (manageMap.containsKey(manage.getClock().getClockId())) {
                            index = false;
                        } else {
                            list.add(manage.getLesson().getLessonId());
                        }
                    }
                    list.forEach(o -> scoreDao.insertScore(-1, newId, o));
                } else {
                    manageMap.forEach((key, value) -> scoreDao.insertScore(-1, newId, key));
                }
                //有课程冲突返回false，没有则返回true
                return index;
            }
        }
        return null;
    }

    @Override
    public List<ManageDTO> queryLessonNotApprove() {
        List<Lesson> lessons = lessonDao.selectLessonByStatus(false);
        if (lessons != null && lessons.size() != 0) {
            return getResult(lessons);
        }
        return null;
    }

    @Override
    public Lesson queryOneLesson(String lessonId) {
        Long newId = ObjectUtil.StringToLong(lessonId);
        return lessonDao.selectLessonById(newId);
    }

    @Override
    public List<ManageDTO> queryLessonCanSelect(String studentId) {
        Long newId = ObjectUtil.StringToLong(studentId);
        if (newId != null) {
            List<Score> scores = scoreDao.selectScoreByStudentId(newId);
            List<Lesson> lessons = lessonDao.selectLessonByStatus(true);
            if (lessons != null && lessons.size() != 0) {
                List<ManageDTO> data = getResult(lessons);
                if (scores != null && scores.size() != 0) {
                    for (Score score : scores) {
                        data = data.stream()
                                .filter(obj -> !obj.getLessonId().equals(score.getLesson().getLessonId()))
                                .collect(Collectors.toList());
                    }
                }
                return data;
            }
        }
        return null;
    }

    private List<ManageDTO> getResult(List<Lesson> lessons) {
        List<ManageDTO> data = Lists.newArrayList();
        for (Lesson lesson : lessons) {
            Manage manage = manageDao.selectByLessonId(lesson.getLessonId());
            ManageDTO dto = new ManageDTO();
            dto.setLessonId(lesson.getLessonId());
            dto.setLessonName(lesson.getLessonName());
            dto.setRoomId(manage.getRoom().getRoomId());
            dto.setRoomName(manage.getRoom().getRoomName());
            dto.setClockId(manage.getClock().getClockId());
            dto.setClockName(manage.getClock().getClockName());
            dto.setTeacherId(manage.getTeacher().getTeacherId());
            dto.setTeacherName(manage.getTeacher().getTeacherName());
            dto.setGradeId((long) manage.getGrade().getGradeId());
            dto.setGradeName(manage.getGrade().getGradeName());
            data.add(dto);
        }
        return data;
    }
}
