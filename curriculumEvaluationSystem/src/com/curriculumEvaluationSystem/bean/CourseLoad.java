package com.curriculumEvaluationSystem.bean;

public class CourseLoad {
    private Integer id;

    private String img;
    
    private String classname;

    private Integer teacherId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

	@Override
	public String toString() {
		return "CourseLoad [id=" + id + ", img=" + img + ", classname=" + classname + ", teacherId=" + teacherId + "]";
	}
    
    
}