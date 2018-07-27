package com.curriculumEvaluationSystem.dao;

import java.util.List;

import com.curriculumEvaluationSystem.bean.Teacher;

public interface TeacherMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);
    
    List<Teacher> selectByParam(Teacher record);
    
    List<Teacher> selectAll();

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}