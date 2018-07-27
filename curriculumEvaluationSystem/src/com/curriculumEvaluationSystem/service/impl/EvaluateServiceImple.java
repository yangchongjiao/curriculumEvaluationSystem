package com.curriculumEvaluationSystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curriculumEvaluationSystem.bean.Evaluate;
import com.curriculumEvaluationSystem.dao.EvaluateMapper;
import com.curriculumEvaluationSystem.service.EvaluateService;
@Component
public class EvaluateServiceImple implements EvaluateService {

	@Autowired
	EvaluateMapper evaluateMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		int a=evaluateMapper.deleteByPrimaryKey(id);
		return a;
	}

	@Override
	public int insert(Evaluate record) {
		// TODO Auto-generated method stub
		int a=evaluateMapper.insert(record);
		return a;
	}

	@Override
	public Evaluate insertSelective(Evaluate record) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		int a=evaluateMapper.insertSelective(record);
		return record;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return evaluateMapper.selectByPrimaryKey(id);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> selectByParam(Evaluate record) {
		// TODO Auto-generated method stub
		return evaluateMapper.selectByParam(record);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getAll() {
		// TODO Auto-generated method stub
		return evaluateMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(Evaluate record) {
		// TODO Auto-generated method stub
		int a=evaluateMapper.updateByPrimaryKeySelective(record);
		return a;
	}

	@Override
	public int updateByPrimaryKey(Evaluate record) {
		// TODO Auto-generated method stub
		int a=evaluateMapper.updateByPrimaryKey(record);
		return a;
	}


	

}
