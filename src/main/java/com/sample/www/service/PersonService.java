package com.sample.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsan.spring.ContextUtils;
import com.sample.www.bean.Person;
import com.sample.www.dao.PersonDao;


@Service
public class PersonService {

	@Autowired
	private PersonDao personDao;

	public void doSomething(Person person) {

		personDao.addPerson(person);
		personDao.deletePerson(person);

		personDao.findPerson(1);
	}
	
	public Person getPerson(){
		
		return ContextUtils.getBean(Person.class);
	}
}
