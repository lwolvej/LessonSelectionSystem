package org.lwolvej.lessonselectionsystem.dto;

import lombok.Data;

/**
 * 课程表传输实体
 *
 * @author LwolveJ
 */
@Data
public class TimeTableDTO {

    private Long lessonId;

    private String lessonName;

    private Long teacherId;

    private String teacherName;

    private Long roomId;

    private String roomName;

    private Long clockId;

    private String clockName;
}
