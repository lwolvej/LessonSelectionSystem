package org.lwolvej.lessonselectionsystem.service;

import org.lwolvej.lessonselectionsystem.entity.Profession;

import java.util.List;

public interface ProfessionService {

    List<Profession> queryProfessionByDepartmentId(String departmentId);
}
