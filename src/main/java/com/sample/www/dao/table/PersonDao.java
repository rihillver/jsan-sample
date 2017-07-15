package com.sample.www.dao.table;

import org.springframework.stereotype.Repository;

import com.jsan.spring.ContextUtils;
import com.sample.www.bean.Person;

@Repository
public class PersonDao {

	public int addPerson(Person person) {

		System.out.println(person + " -->111 add...");
		return 1;
	}

	public boolean deletePerson(Person person) {

		System.out.println(person + " -->111 delete...");
		return true;
	}

	public Person findPerson(int id) {

		System.out.println(id + " -->111 findPerson...");
		return ContextUtils.getBean(Person.class);
	}

	@Override
	public String toString() {
		return getClass().toString();
	}

}
