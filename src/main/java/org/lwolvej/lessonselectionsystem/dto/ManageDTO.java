package org.lwolvej.lessonselectionsystem.dto;

import lombok.Data;

/**
 * 传输类
 *
 * @author LwolveJ
 */
@Data
public class ManageDTO {

    private Long roomId;

    private String roomName;

    private Long clockId;

    private String clockName;

    private Long lessonId;

    private String lessonName;

    private Long gradeId;

    private Integer gradeName;

    private Long teacherId;

    private String teacherName;
}
