package com.curriculumEvaluationSystem.bean;

public class Evaluate {
    private Integer id;

    private Integer wentiId;

    private Integer studentId;
    
    private Integer classId;

    private Integer grade;

    

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWentiId() {
        return wentiId;
    }

    public void setWentiId(Integer wentiId) {
        this.wentiId = wentiId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

	@Override
	public String toString() {
		return "Evaluate [id=" + id + ", wentiId=" + wentiId + ", studentId=" + studentId + ", classId=" + classId
				+ ", grade=" + grade + "]";
	}

	
    
    
}