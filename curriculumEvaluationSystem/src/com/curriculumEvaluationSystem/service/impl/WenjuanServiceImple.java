package com.curriculumEvaluationSystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curriculumEvaluationSystem.bean.Wenjuan;
import com.curriculumEvaluationSystem.dao.WenjuanMapper;
import com.curriculumEvaluationSystem.service.WenjuanService;
@Component
public class WenjuanServiceImple implements WenjuanService {

	@Autowired
	WenjuanMapper wenjuanMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		int a=wenjuanMapper.deleteByPrimaryKey(id);
		return a;
	}

	@Override
	public int insert(Wenjuan record) {
		// TODO Auto-generated method stub
		int a=wenjuanMapper.insert(record);
		return a;
	}

	@Override
	public Wenjuan insertSelective(Wenjuan record) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		int a=wenjuanMapper.insertSelective(record);
		return record;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return wenjuanMapper.selectByPrimaryKey(id);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> selectByParam(Wenjuan record) {
		// TODO Auto-generated method stub
		return wenjuanMapper.selectByParam(record);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getAll() {
		// TODO Auto-generated method stub
		return wenjuanMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(Wenjuan record) {
		// TODO Auto-generated method stub
		int a=wenjuanMapper.updateByPrimaryKeySelective(record);
		return a;
	}

	@Override
	public int updateByPrimaryKey(Wenjuan record) {
		// TODO Auto-generated method stub
		int a=wenjuanMapper.updateByPrimaryKey(record);
		return a;
	}


	

}
