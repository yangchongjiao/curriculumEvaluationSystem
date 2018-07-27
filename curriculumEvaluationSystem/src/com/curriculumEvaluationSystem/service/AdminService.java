package com.curriculumEvaluationSystem.service;

import java.util.List;

import com.curriculumEvaluationSystem.bean.Admin;

public interface AdminService {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    Admin insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    Admin selectByParam(Admin record);
    
    List<Admin> getUsers();

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}
