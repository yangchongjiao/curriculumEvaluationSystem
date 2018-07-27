package com.curriculumEvaluationSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curriculumEvaluationSystem.bean.Question;
import com.curriculumEvaluationSystem.dao.QuestionMapper;
import com.curriculumEvaluationSystem.service.QuestionService;
@Component
public class QuestionServiceImple implements QuestionService {

	@Autowired
	QuestionMapper questionMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		int a=questionMapper.deleteByPrimaryKey(id);
		return a;
	}

	@Override
	public int insert(Question record) {
		// TODO Auto-generated method stub
		int a=questionMapper.insert(record);
		return a;
	}

	@Override
	public Question insertSelective(Question record) {
		// TODO Auto-generated method stub
		int a=questionMapper.insertSelective(record);
		return record;
	}

	@Override
	public Question selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return questionMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<Question> selectByParam(Question record) {
		// TODO Auto-generated method stub
		return questionMapper.selectByParam(record);
	}
	
	@Override
	public List<Question> getAll() {
		// TODO Auto-generated method stub
		return questionMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(Question record) {
		// TODO Auto-generated method stub
		int a=questionMapper.updateByPrimaryKeySelective(record);
		return a;
	}

	@Override
	public int updateByPrimaryKey(Question record) {
		// TODO Auto-generated method stub
		int a=questionMapper.updateByPrimaryKey(record);
		return a;
	}


	

}
