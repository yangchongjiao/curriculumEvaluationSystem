package com.curriculumEvaluationSystem.service;

import java.util.List;
import java.util.Map;

import com.curriculumEvaluationSystem.bean.Wenjuan;

public interface WenjuanService {
    int deleteByPrimaryKey(Integer id);

    int insert(Wenjuan record);

    Wenjuan insertSelective(Wenjuan record);

    @SuppressWarnings("rawtypes")
	Map selectByPrimaryKey(Integer id);

    @SuppressWarnings("rawtypes")
	List<Map> selectByParam(Wenjuan record);
    
    @SuppressWarnings("rawtypes")
	List<Map> getAll();

    int updateByPrimaryKeySelective(Wenjuan record);

    int updateByPrimaryKey(Wenjuan record);
}
