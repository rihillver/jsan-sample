package com.sample.www.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsan.spring.ContextUtils;
import com.sample.www.ProjectUtils;
import com.sample.www.bean.Person;
import com.sample.www.dao.PersonDao;

import root.test4.aop.Index.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(ProjectUtils.SPRING_CONFIG_FILE)
@Service
public class PersonService {

	@Autowired
	private PersonDao personDao;

	@Autowired
	private com.sample.www.dao.table.PersonDao personDao2;

	@Test
	public void test() {

		System.out.println(ContextUtils.getBean("user", User.class));
		System.out.println(ContextUtils.getBean("user1", User.class));

		doSomething(getPerson());
		doSomething1(getPerson());

		System.out.println(ContextUtils.getBean(PersonDao.class));
		System.out.println(ContextUtils.getBean(com.sample.www.dao.table.PersonDao.class));
	}

	public void doSomething(Person person) {

		personDao.addPerson(person);
		personDao.deletePerson(person);

		personDao.findPerson(1);
	}

	public void doSomething1(Person person) {

		personDao2.addPerson(person);
		personDao2.deletePerson(person);

		personDao2.findPerson(1);
	}

	public Person getPerson() {

		return ContextUtils.getBean(Person.class);
	}
}
