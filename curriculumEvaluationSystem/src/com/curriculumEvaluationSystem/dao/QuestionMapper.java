package com.curriculumEvaluationSystem.dao;

import java.util.List;

import com.curriculumEvaluationSystem.bean.Question;

public interface QuestionMapper {
    

    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);
    
    List<Question> selectByParam(Question record);
    
    List<Question> selectAll();

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
}