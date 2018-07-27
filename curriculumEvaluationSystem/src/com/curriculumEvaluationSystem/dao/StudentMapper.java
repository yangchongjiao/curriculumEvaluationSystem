package com.curriculumEvaluationSystem.dao;

import java.util.List;

import com.curriculumEvaluationSystem.bean.Student;

public interface StudentMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);
    
    List<Student> selectByParam(Student record);
    
    List<Student> selectAll();

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}