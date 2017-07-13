package com.sample.www.dao;

import org.springframework.stereotype.Repository;

import com.jsan.spring.ContextUtils;
import com.sample.www.bean.Person;

@Repository
public class PersonDao {

	public int addPerson(Person person) {

		System.out.println(person + " --> add...");
		return 1;
	}

	public boolean deletePerson(Person person) {

		System.out.println(person + " --> delete...");
		return true;
	}

	public Person findPerson(int id) {

		System.out.println(id + " --> findPerson...");
		return ContextUtils.getBean(Person.class);
	}
}
