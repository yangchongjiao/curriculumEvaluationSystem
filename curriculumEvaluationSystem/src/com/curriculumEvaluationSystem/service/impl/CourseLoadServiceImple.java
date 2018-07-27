package com.curriculumEvaluationSystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curriculumEvaluationSystem.bean.CourseLoad;
import com.curriculumEvaluationSystem.dao.CourseLoadMapper;
import com.curriculumEvaluationSystem.service.CourseLoadService;
@Component
public class CourseLoadServiceImple implements CourseLoadService {

	@Autowired
	CourseLoadMapper courseLoadMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		int a=courseLoadMapper.deleteByPrimaryKey(id);
		return a;
	}

	@Override
	public int insert(CourseLoad record) {
		// TODO Auto-generated method stub
		int a=courseLoadMapper.insert(record);
		return a;
	}

	@Override
	public CourseLoad insertSelective(CourseLoad record) {
		// TODO Auto-generated method stub
		int a=courseLoadMapper.insertSelective(record);
		return record;
	}

	@Override
	public Map selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return courseLoadMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<Map> selectByParam(CourseLoad record) {
		// TODO Auto-generated method stub
		return courseLoadMapper.selectByParam(record);
	}
	
	@Override
	public List<Map> getAll() {
		// TODO Auto-generated method stub
		return courseLoadMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(CourseLoad record) {
		// TODO Auto-generated method stub
		int a=courseLoadMapper.updateByPrimaryKeySelective(record);
		return a;
	}

	@Override
	public int updateByPrimaryKey(CourseLoad record) {
		// TODO Auto-generated method stub
		int a=courseLoadMapper.updateByPrimaryKey(record);
		return a;
	}


	

}
