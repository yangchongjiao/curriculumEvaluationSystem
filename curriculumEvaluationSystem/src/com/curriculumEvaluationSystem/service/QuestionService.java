package com.curriculumEvaluationSystem.service;

import java.util.List;

import com.curriculumEvaluationSystem.bean.Question;

public interface QuestionService {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    Question insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    List<Question> selectByParam(Question record);
    
    List<Question> getAll();

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
}
