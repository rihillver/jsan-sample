package com.sample.www.service.impl;

import org.springframework.stereotype.Service;

import com.sample.www.service.BarService;

@Service("impl.barServiceImpl")
public class BarServiceImpl implements BarService {

	@Override
	public String getData() {

		return getClass().toString();
	}

}
