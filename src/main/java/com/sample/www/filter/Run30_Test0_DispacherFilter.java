package com.sample.www.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.jsan.mvc.filter.DispatcherFilter;

@WebFilter(
		initParams = {
				@WebInitParam(name = "debug", value = "true"), 
				@WebInitParam(name = "executeTime", value = "etime"), 
				@WebInitParam(name = "mappingSuffix", value = ",.html,.do,.action"),
				}, 
		urlPatterns = {"/test/*", "/test0/*"})
public class Run30_Test0_DispacherFilter extends DispatcherFilter {

}
