package com.curriculumEvaluationSystem.dao;

import java.util.List;
import java.util.Map;

import com.curriculumEvaluationSystem.bean.Course;

public interface CourseMapper {
    
    int deleteByPrimaryKey(Integer classId);

    int insert(Course record);

    int insertSelective(Course record);

    @SuppressWarnings("rawtypes")
	Map selectByPrimaryKey(Integer id);
    
    @SuppressWarnings("rawtypes")
	List<Map> selectByParam(Course record);
    
    @SuppressWarnings("rawtypes")
	List<Map> selectAll();

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}