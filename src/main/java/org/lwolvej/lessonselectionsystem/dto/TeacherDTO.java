package org.lwolvej.lessonselectionsystem.dto;

import lombok.Data;

/**
 * 传输类
 *
 * @author LwolveJ
 */
@Data
public class TeacherDTO {

    private String teacherName;

    private String teacherPwd;

    private Boolean teacherGender;

    private String teacherNumber;

    private String teacherSite;

    private Long departmentId;
}
