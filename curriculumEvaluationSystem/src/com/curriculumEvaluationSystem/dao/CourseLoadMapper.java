package com.curriculumEvaluationSystem.dao;

import java.util.List;
import java.util.Map;

import com.curriculumEvaluationSystem.bean.CourseLoad;

public interface CourseLoadMapper {
    
    
    int deleteByPrimaryKey(Integer id);

    int insert(CourseLoad record);

    int insertSelective(CourseLoad record);

    @SuppressWarnings("rawtypes")
	Map selectByPrimaryKey(Integer id);
    
    @SuppressWarnings("rawtypes")
	List<Map> selectByParam(CourseLoad record);
    
    @SuppressWarnings("rawtypes")
	List<Map> selectAll();

    int updateByPrimaryKeySelective(CourseLoad record);

    int updateByPrimaryKey(CourseLoad record);

    
}