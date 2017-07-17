package com.sample.www.dao;

import org.springframework.stereotype.Repository;

@Repository
public class FooDao {

	public String getData() {

		return getClass().toString();
	}
}
