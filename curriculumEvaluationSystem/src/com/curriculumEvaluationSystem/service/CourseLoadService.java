package com.curriculumEvaluationSystem.service;

import java.util.List;
import java.util.Map;

import com.curriculumEvaluationSystem.bean.CourseLoad;

public interface CourseLoadService {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseLoad record);

    CourseLoad insertSelective(CourseLoad record);

    @SuppressWarnings("rawtypes")
	Map selectByPrimaryKey(Integer id);

    @SuppressWarnings("rawtypes")
	List<Map> selectByParam(CourseLoad record);
    
    @SuppressWarnings("rawtypes")
	List<Map> getAll();

    int updateByPrimaryKeySelective(CourseLoad record);

    int updateByPrimaryKey(CourseLoad record);
}
