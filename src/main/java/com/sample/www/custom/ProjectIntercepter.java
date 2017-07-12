package com.sample.www.custom;

import com.jsan.mvc.intercept.Interceptor;
import com.jsan.mvc.intercept.Invocation;

public class ProjectIntercepter implements Interceptor {

	@Override
	public void before(Invocation inv) {

		System.out.println("##### " + inv.getMethod());
	}

	@Override
	public void after(Invocation inv) {
	}

	@Override
	public void afterReturning(Invocation inv, Object result) {
	}

	@Override
	public void afterThrowing(Invocation inv, Exception e) {
	}

}
