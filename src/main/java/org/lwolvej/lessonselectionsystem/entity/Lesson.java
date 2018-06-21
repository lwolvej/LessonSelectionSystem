package org.lwolvej.lessonselectionsystem.entity;

import lombok.Data;

/**
 * 课程实体类
 *
 * @author LwolveJ
 */
@Data
public class Lesson {

    private Long lessonId;

    private String lessonName;

    private Boolean lessonStatus;
}
