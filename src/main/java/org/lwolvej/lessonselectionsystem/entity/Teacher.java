package org.lwolvej.lessonselectionsystem.entity;

import lombok.Data;

/**
 * 教室实体类
 *
 * @author LwolveJ
 */
@Data
public class Teacher {

    private Long teacherId; //教师id

    private String teacherName; //教师姓名

    private String teacherPwd; //教师密码

    private Boolean teacherGender; //教师性别

    private String teacherNumber; //教师联系电话

    private String teacherSite; //教师办公室地点

    private Department department; //教师所属学院
}
