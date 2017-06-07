package com.test.fastjson;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
//import com.alibaba.fastjson.annotation.JSONType;

//@JSONType(orders={"id","age","name","fatherName","sex","salary","student","birth","status"})
public class User extends Person {

	@JSONField(ordinal = 1)
	int id;
	@JSONField(ordinal = 2)
	int age;
	@JSONField(ordinal = 3)
	String name;
	String fatherName;
	Boolean sex;
	Double salary;

	boolean student;

	@JSONField(format = "yyyy年MM月dd日")
	Date birth;

	Status status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public boolean isStudent() {
		return student;
	}

	public void setStudent(boolean student) {
		this.student = student;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", name=" + name + ", fatherName=" + fatherName + ", sex=" + sex
				+ ", salary=" + salary + ", student=" + student + ", birth=" + birth + ", status=" + status
				+ ", country=" + country + ", code=" + code + "]";
	}

}
