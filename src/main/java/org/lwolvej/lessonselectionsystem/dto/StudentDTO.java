package org.lwolvej.lessonselectionsystem.dto;

import lombok.Data;

/**
 * 传输类
 *
 * @author LwolveJ
 */
@Data
public class StudentDTO {

    private String studentName;

    private String studentPwd;

    private Boolean studentGender;

    private String studentNumber;

    private Long departmentId;

    private Long professionId;

    private Long classesId;

    private Long gradeId;
}
