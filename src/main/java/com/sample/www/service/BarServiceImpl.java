package com.sample.www.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class BarServiceImpl implements BarService {

	@Override
	public String getData() {

		return getClass().toString();
	}

}
