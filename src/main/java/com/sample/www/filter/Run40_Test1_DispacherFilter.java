package com.sample.www.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.jsan.mvc.filter.DispatcherFilter;

@WebFilter(
		initParams = {
				@WebInitParam(name = "debug", value = "true"), 
				@WebInitParam(name = "executeTime", value = "etime"), 
				@WebInitParam(name = "mappingSuffix", value = ",.html,.do,.action"),
				@WebInitParam(name = "methodDelimiter", value = "-")
				}, 
		urlPatterns = "/test1/*")
public class Run40_Test1_DispacherFilter extends DispatcherFilter {

}
