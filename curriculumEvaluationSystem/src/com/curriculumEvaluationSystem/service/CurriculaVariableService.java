package com.curriculumEvaluationSystem.service;

import java.util.List;
import java.util.Map;

import com.curriculumEvaluationSystem.bean.CurriculaVariable;

public interface CurriculaVariableService {
    int deleteByPrimaryKey(Integer id);

    int insert(CurriculaVariable record);

    CurriculaVariable insertSelective(CurriculaVariable record);

    @SuppressWarnings("rawtypes")
	Map selectByPrimaryKey(CurriculaVariable record);

    @SuppressWarnings("rawtypes")
	List<Map> selectByParam(CurriculaVariable record);
    
    @SuppressWarnings("rawtypes")
	List<Map> getAll();

    int updateByPrimaryKeySelective(CurriculaVariable record, CurriculaVariable old);

    int updateByPrimaryKey(CurriculaVariable record, CurriculaVariable old);
}
