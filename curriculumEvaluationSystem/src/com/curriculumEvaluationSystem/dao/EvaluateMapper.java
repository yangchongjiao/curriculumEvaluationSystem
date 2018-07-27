package com.curriculumEvaluationSystem.dao;

import java.util.List;
import java.util.Map;

import com.curriculumEvaluationSystem.bean.Evaluate;

public interface EvaluateMapper {
    
    int insert(Evaluate record);

    int insertSelective(Evaluate record);
    
    int deleteByPrimaryKey(Integer id);
    
    @SuppressWarnings("rawtypes")
	Map selectByPrimaryKey(Integer id);
    
    @SuppressWarnings("rawtypes")
	List<Map> selectByParam(Evaluate record);
    
    @SuppressWarnings("rawtypes")
	List<Map> selectAll();

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
    
}