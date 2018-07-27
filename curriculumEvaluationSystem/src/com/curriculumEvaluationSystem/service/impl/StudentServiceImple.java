package com.curriculumEvaluationSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curriculumEvaluationSystem.bean.Admin;
import com.curriculumEvaluationSystem.bean.Student;
import com.curriculumEvaluationSystem.dao.AdminMapper;
import com.curriculumEvaluationSystem.dao.StudentMapper;
import com.curriculumEvaluationSystem.service.AdminService;
import com.curriculumEvaluationSystem.service.StudentService;
@Component
public class StudentServiceImple implements StudentService {

	@Autowired
	StudentMapper studentMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		int a=studentMapper.deleteByPrimaryKey(id);
		return a;
	}

	@Override
	public int insert(Student record) {
		// TODO Auto-generated method stub
		int a=studentMapper.insert(record);
		return a;
	}

	@Override
	public Student insertSelective(Student record) {
		// TODO Auto-generated method stub
		int a=studentMapper.insertSelective(record);
		return record;
	}

	@Override
	public Student selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return studentMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<Student> selectByParam(Student record) {
		// TODO Auto-generated method stub
		return studentMapper.selectByParam(record);
	}
	
	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return studentMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(Student record) {
		// TODO Auto-generated method stub
		int a=studentMapper.updateByPrimaryKeySelective(record);
		return a;
	}

	@Override
	public int updateByPrimaryKey(Student record) {
		// TODO Auto-generated method stub
		int a=studentMapper.updateByPrimaryKey(record);
		return a;
	}


	

}
