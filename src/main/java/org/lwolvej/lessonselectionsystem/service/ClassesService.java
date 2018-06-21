package org.lwolvej.lessonselectionsystem.service;

import org.lwolvej.lessonselectionsystem.entity.Classes;

import java.util.List;

public interface ClassesService {

    List<Classes> queryClassById(String departmentId, String professionId, String gradeId);
}
