package org.lwolvej.lessonselectionsystem.entity;

import lombok.Data;

/**
 * 课程安排实体
 *
 * @author LwolveJ
 */
@Data
public class Manage {

    private Long manageId; //安排的id

    private Room room; //安排的教室

    private Clock clock; //安排的时间

    private Lesson lesson; //安排的课程

    private Grade grade; //安排的年级

    private Teacher teacher; //安排的教师
}
