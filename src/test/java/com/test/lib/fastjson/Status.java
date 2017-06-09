package com.test.lib.fastjson;

public class Status {

	int number;
	int grade;
	String classTeacher;
	double score;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getClassTeacher() {
		return classTeacher;
	}

	public void setClassTeacher(String classTeacher) {
		this.classTeacher = classTeacher;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Status [number=" + number + ", grade=" + grade + ", classTeacher=" + classTeacher + ", score=" + score
				+ "]";
	}

}
