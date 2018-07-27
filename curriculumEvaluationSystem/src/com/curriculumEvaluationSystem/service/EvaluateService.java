package com.curriculumEvaluationSystem.service;

import java.util.List;
import java.util.Map;

import com.curriculumEvaluationSystem.bean.Evaluate;

public interface EvaluateService {
    int deleteByPrimaryKey(Integer id);

    int insert(Evaluate record);

    Evaluate insertSelective(Evaluate record);

    @SuppressWarnings("rawtypes")
	Map selectByPrimaryKey(Integer id);

    @SuppressWarnings("rawtypes")
	List<Map> selectByParam(Evaluate record);
    
    @SuppressWarnings("rawtypes")
	List<Map> getAll();

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
}
