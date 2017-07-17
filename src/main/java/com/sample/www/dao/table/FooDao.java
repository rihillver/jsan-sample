package com.sample.www.dao.table;

import org.springframework.stereotype.Repository;

@Repository
public class FooDao {

	public String getData() {

		return getClass().toString();
	}
}
