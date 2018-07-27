package com.curriculumEvaluationSystem.bean;

public class Student {
    private Integer id;

    private String studentNumber;
    
    private String name;
    
    private String img;

    private String sex;

    private Integer age;

    private String dept;

    private String className;

    private String address;

    private String phone;

    private String other;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

	@Override
	public String toString() {
		return "Student [id=" + id + ", studentNumber=" + studentNumber + ", name=" + name + ", img=" + img + ", sex="
				+ sex + ", age=" + age + ", dept=" + dept + ", className=" + className + ", address=" + address
				+ ", phone=" + phone + ", other=" + other + "]";
	}

	

	
    
    
}