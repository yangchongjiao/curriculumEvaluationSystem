package com.curriculumEvaluationSystem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curriculumEvaluationSystem.bean.CurriculaVariable;
import com.curriculumEvaluationSystem.dao.CurriculaVariableMapper;
import com.curriculumEvaluationSystem.service.CurriculaVariableService;
@Component
public class CurriculaVariableServiceImple implements CurriculaVariableService {

	@Autowired
	CurriculaVariableMapper curriculaVariableMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		int a=curriculaVariableMapper.deleteByPrimaryKey(id);
		return a;
	}

	@Override
	public int insert(CurriculaVariable record) {
		// TODO Auto-generated method stub
		int a=curriculaVariableMapper.insert(record);
		return a;
	}

	@Override
	public CurriculaVariable insertSelective(CurriculaVariable record) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		int a=curriculaVariableMapper.insertSelective(record);
		return record;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map selectByPrimaryKey(CurriculaVariable record) {
		// TODO Auto-generated method stub
		return curriculaVariableMapper.selectByPrimaryKey(record);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> selectByParam(CurriculaVariable record) {
		// TODO Auto-generated method stub
		return curriculaVariableMapper.selectByParam(record);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getAll() {
		// TODO Auto-generated method stub
		return curriculaVariableMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(CurriculaVariable record, CurriculaVariable old) {
		// TODO Auto-generated method stub
		
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("studentId", record.getStudentId());
		paraMap.put("classId", record.getClassId());
		paraMap.put("oldStudentId", old.getStudentId());
		paraMap.put("oldClassId", old.getClassId());
		int a=curriculaVariableMapper.updateByPrimaryKeySelective(paraMap);
		return a;
	}

	@Override
	public int updateByPrimaryKey(CurriculaVariable record, CurriculaVariable old) {
		// TODO Auto-generated method stub
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("studentId", record.getStudentId());
		paraMap.put("classId", record.getClassId());
		paraMap.put("oldStudentId", old.getStudentId());
		paraMap.put("oldClassId", old.getClassId());
		int a=curriculaVariableMapper.updateByPrimaryKey(paraMap);
		return a;
	}


	

}
