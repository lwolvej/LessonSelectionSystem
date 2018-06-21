package org.lwolvej.lessonselectionsystem.dto;

import lombok.Data;

/**
 * 传输类
 *
 * @author LwolveJ
 */
@Data
public class LessonDTO {

    private String lessonName;

    private Long roomId;

    private Long clockId;

    private Long gradeId;

    private Long teacherId;
}
