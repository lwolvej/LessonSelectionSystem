package org.lwolvej.lessonselectionsystem.entity;

import lombok.Data;

/**
 * 分数实体
 *
 * @author LwolveJ
 */
@Data
public class Score {

    private Long scoreId;

    private Integer scoreNumber;

    private Student student;

    private Lesson lesson;
}
