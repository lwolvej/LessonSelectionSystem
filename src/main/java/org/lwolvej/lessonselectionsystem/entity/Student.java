package org.lwolvej.lessonselectionsystem.entity;

import lombok.Data;

/**
 * 学生实体类
 *
 * @author LwolveJ
 */
@Data
public class Student {

    private Long studentId; //学生id

    private String studentName; //学生姓名

    private String studentPwd; //学生密码

    private Boolean studentGender; //学生性别

    private String studentNumber; //学生联系电话

    private Department department; //学生所属学院

    private Profession profession; //学生所属专业

    private Grade grade; //学生所属年级

    private Classes classes; //学生所属班级
}
