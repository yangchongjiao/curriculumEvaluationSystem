package com.curriculumEvaluationSystem.service;

import java.util.List;

import com.curriculumEvaluationSystem.bean.Admin;
import com.curriculumEvaluationSystem.bean.Student;

public interface StudentService {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    Student insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    List<Student> selectByParam(Student record);
    
    List<Student> getAll();

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
