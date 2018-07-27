package com.curriculumEvaluationSystem.service;

import java.util.List;

import com.curriculumEvaluationSystem.bean.Teacher;

public interface TeacherService {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    Teacher insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    List<Teacher> selectByParam(Teacher record);
    
    List<Teacher> getAll();

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}
