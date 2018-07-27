package com.curriculumEvaluationSystem.dao;

import java.util.List;
import java.util.Map;

import com.curriculumEvaluationSystem.bean.Wenjuan;

public interface WenjuanMapper {
    
    int insert(Wenjuan record);

    int insertSelective(Wenjuan record);

    //List<Wenjuan> selectByExample(WenjuanExample example);
    int deleteByPrimaryKey(Integer id);
    
    @SuppressWarnings("rawtypes")
	Map selectByPrimaryKey(Integer id);
    
    @SuppressWarnings("rawtypes")
	List<Map> selectByParam(Wenjuan record);
    
    @SuppressWarnings("rawtypes")
	List<Map> selectAll();

    int updateByPrimaryKeySelective(Wenjuan record);

    int updateByPrimaryKey(Wenjuan record);
    
}