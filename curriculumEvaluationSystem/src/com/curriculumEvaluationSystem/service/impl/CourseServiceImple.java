package com.curriculumEvaluationSystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curriculumEvaluationSystem.bean.Course;
import com.curriculumEvaluationSystem.dao.CourseMapper;
import com.curriculumEvaluationSystem.service.CourseService;
@Component
public class CourseServiceImple implements CourseService {

	@Autowired
	CourseMapper courseMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		int a=courseMapper.deleteByPrimaryKey(id);
		return a;
	}

	@Override
	public int insert(Course record) {
		// TODO Auto-generated method stub
		int a=courseMapper.insert(record);
		return a;
	}

	@Override
	public Course insertSelective(Course record) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		int a=courseMapper.insertSelective(record);
		return record;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return courseMapper.selectByPrimaryKey(id);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> selectByParam(Course record) {
		// TODO Auto-generated method stub
		return courseMapper.selectByParam(record);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getAll() {
		// TODO Auto-generated method stub
		return courseMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(Course record) {
		// TODO Auto-generated method stub
		int a=courseMapper.updateByPrimaryKeySelective(record);
		return a;
	}

	@Override
	public int updateByPrimaryKey(Course record) {
		// TODO Auto-generated method stub
		int a=courseMapper.updateByPrimaryKey(record);
		return a;
	}


	

}
