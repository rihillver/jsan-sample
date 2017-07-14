package com.sample.www.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.jsan.mvc.filter.DispatcherFilter;

@WebFilter(
		initParams = {
				@WebInitParam(name = "debug", value = "true"), 
				@WebInitParam(name = "contextPath", value = "/jsan-sample"), 
				@WebInitParam(name = "executeTime", value = "etime"), 
				@WebInitParam(name = "methodKey", value = "med"), // 缺省为"mod" 
				@WebInitParam(name = "mappingSuffix", value = ",.html,.do,.action"),
				}, 
		urlPatterns = "/test0/*")
public class Run30_Test0_DispacherFilter extends DispatcherFilter {

}
