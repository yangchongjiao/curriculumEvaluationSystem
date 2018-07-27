package com.curriculumEvaluationSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curriculumEvaluationSystem.bean.Admin;
import com.curriculumEvaluationSystem.dao.AdminMapper;
import com.curriculumEvaluationSystem.service.AdminService;
@Component
public class AdminServiceImple implements AdminService {

	@Autowired
	AdminMapper adminMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		int a=adminMapper.deleteByPrimaryKey(id);
		return a;
	}

	@Override
	public int insert(Admin record) {
		// TODO Auto-generated method stub
		int a=adminMapper.insert(record);
		return a;
	}

	@Override
	public Admin insertSelective(Admin record) {
		// TODO Auto-generated method stub
		int a=adminMapper.insertSelective(record);
		return record;
	}

	@Override
	public Admin selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return adminMapper.selectByPrimaryKey(id);
	}


	@Override
	public Admin selectByParam(Admin record) {
		// TODO Auto-generated method stub
		return adminMapper.selectByParam(record);
	}
	
	@Override
	public List<Admin> getUsers() {
		// TODO Auto-generated method stub
		return adminMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKeySelective(Admin record) {
		// TODO Auto-generated method stub
		int a=adminMapper.updateByPrimaryKeySelective(record);
		return a;
	}

	@Override
	public int updateByPrimaryKey(Admin record) {
		// TODO Auto-generated method stub
		int a=adminMapper.updateByPrimaryKey(record);
		return a;
	}

	public AdminMapper getAdminMapper() {
		return adminMapper;
	}
	@Autowired
	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}

	

}
