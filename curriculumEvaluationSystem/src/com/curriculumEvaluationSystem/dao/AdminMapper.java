package com.curriculumEvaluationSystem.dao;

import java.util.List;

import com.curriculumEvaluationSystem.bean.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    Admin selectByParam(Admin record);
    
    List<Admin> selectAll();

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}