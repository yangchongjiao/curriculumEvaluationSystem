package com.curriculumEvaluationSystem.bean;

import java.util.List;
import java.util.Map;

public class Wenjuan {
    private Integer id;

    private Integer classId;
    
    private Integer studentId;

    private String wenjuanTitle;
    
    private String evaluateContnet;
    
    private List<Question> questions;

    private List<Map> evaluates;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getWenjuanTitle() {
		return wenjuanTitle;
	}

	public void setWenjuanTitle(String wenjuanTitle) {
		this.wenjuanTitle = wenjuanTitle;
	}

	public String getEvaluateContnet() {
		return evaluateContnet;
	}

	public void setEvaluateContnet(String evaluateContnet) {
		this.evaluateContnet = evaluateContnet;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Map> getEvaluates() {
		return evaluates;
	}

	public void setEvaluates(List<Map> evaluates) {
		this.evaluates = evaluates;
	}

	@Override
	public String toString() {
		return "Wenjuan [id=" + id + ", classId=" + classId + ", studentId=" + studentId + ", wenjuanTitle="
				+ wenjuanTitle + ", evaluateContnet=" + evaluateContnet + "]";
	}

	
    
    

    }