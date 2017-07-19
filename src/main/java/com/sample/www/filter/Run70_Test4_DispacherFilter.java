package com.sample.www.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.jsan.spring.DispatcherFilter;


@WebFilter(
		initParams = {
				@WebInitParam(name = "contextPath", value = "/jsan-sample"), 
				@WebInitParam(name = "executeTime", value = "etime"), 
				@WebInitParam(name = "viewPath", value = ""), // 此处强烈建议设置
				@WebInitParam(name = "methodDelimiter", value = "/")
				}, 
		urlPatterns = {"/test4/*","/test5/*"})
public class Run70_Test4_DispacherFilter extends DispatcherFilter {

}
