package com.sample.www.service.impl;

import org.springframework.stereotype.Service;

import com.sample.www.service.FooService;

@Service("impl.fooServiceImpl")
public class FooServiceImpl implements FooService {

	@Override
	public String getData() {

		return getClass().toString();
	}

}
