package com.sample.www.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.jsan.mvc.filter.DispatcherFilter;

/**
 * 使用 SimpleRestMappingAdapter。
 *
 */

@WebFilter(
		initParams = {
				@WebInitParam(name = "debug", value = "true"), 
				@WebInitParam(name = "cacheable", value = "true"), 
				@WebInitParam(name = "viewPath", value = ""), // 此处强烈建议设置
				@WebInitParam(name = "executeTime", value = "etime"), 
				@WebInitParam(name = "mappingSuffix", value = ",.html"),
				@WebInitParam(name = "methodDelimiter", value = "/")
				}, 
		urlPatterns = "/test2/*")
public class Run50_Test2_DispacherFilter extends DispatcherFilter {

}
