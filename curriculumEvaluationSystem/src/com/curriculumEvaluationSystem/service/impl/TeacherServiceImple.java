package com.curriculumEvaluationSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curriculumEvaluationSystem.bean.Teacher;
import com.curriculumEvaluationSystem.dao.TeacherMapper;
import com.curriculumEvaluationSystem.service.TeacherService;
@Component
public class TeacherServiceImple implements TeacherService {

	@Autowired
	TeacherMapper teacherMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		int a=teacherMapper.deleteByPrimaryKey(id);
		return a;
	}

	@Override
	public int insert(Teacher record) {
		// TODO Auto-generated method stub
		int a=teacherMapper.insert(record);
		return a;
	}

	@Override
	public Teacher insertSelective(Teacher record) {
		// TODO Auto-generated method stub
		int a=teacherMapper.insertSelective(record);
		return record;
	}

	@Override
	public Teacher selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return teacherMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<Teacher> selectByParam(Teacher record) {
		// TODO Auto-generated method stub
		return teacherMapper.selectByParam(record);
	}
	
	@Override
	public List<Teacher> getAll() {
		// TODO Auto-generated method stub
		return teacherMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(Teacher record) {
		// TODO Auto-generated method stub
		int a=teacherMapper.updateByPrimaryKeySelective(record);
		return a;
	}

	@Override
	public int updateByPrimaryKey(Teacher record) {
		// TODO Auto-generated method stub
		int a=teacherMapper.updateByPrimaryKey(record);
		return a;
	}


	

}
