package com.curriculumEvaluationSystem.service;

import java.util.List;
import java.util.Map;

import com.curriculumEvaluationSystem.bean.Course;

public interface CourseService {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    Course insertSelective(Course record);

    @SuppressWarnings("rawtypes")
	Map selectByPrimaryKey(Integer id);

    @SuppressWarnings("rawtypes")
	List<Map> selectByParam(Course record);
    
    @SuppressWarnings("rawtypes")
	List<Map> getAll();

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}
