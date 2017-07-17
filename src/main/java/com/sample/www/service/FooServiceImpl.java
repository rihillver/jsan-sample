package com.sample.www.service;

import org.springframework.stereotype.Service;

@Service
public class FooServiceImpl implements FooService {

	@Override
	public String getData() {

		return getClass().toString();
	}

}
