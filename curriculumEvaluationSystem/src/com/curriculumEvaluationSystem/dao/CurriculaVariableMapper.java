package com.curriculumEvaluationSystem.dao;

import java.util.List;
import java.util.Map;

import com.curriculumEvaluationSystem.bean.CurriculaVariable;

public interface CurriculaVariableMapper {
    

    int insert(CurriculaVariable record);

    int insertSelective(CurriculaVariable record);
    
    int deleteByPrimaryKey(Integer id);
    
    @SuppressWarnings("rawtypes")
	Map selectByPrimaryKey(CurriculaVariable record);
    
    @SuppressWarnings("rawtypes")
	List<Map> selectByParam(CurriculaVariable record);
    
    @SuppressWarnings("rawtypes")
	List<Map> selectAll();

    int updateByPrimaryKeySelective(Map parm);

    int updateByPrimaryKey(Map parm);
    
}