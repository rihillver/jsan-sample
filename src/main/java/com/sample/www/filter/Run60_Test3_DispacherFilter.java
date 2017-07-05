package com.sample.www.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.jsan.mvc.filter.DispatcherFilter;

/**
 * 通过 test3-jsanmvc.properties.properties 配置使用 StrictSimpleRestMappingAdapter 。
 *
 */

@WebFilter(
		initParams = {
				@WebInitParam(name = "debug", value = "true"), 
				@WebInitParam(name = "contextPath", value = "/jsan-sample"), 
				@WebInitParam(name = "viewPath", value = "/WEB-INF/template"), 
				@WebInitParam(name = "executeTime", value = "et"), 
				// @WebInitParam(name = "mappingSuffix", value = ",.html"), // 缺省为 ""，即无后缀
				@WebInitParam(name = "configFile", value = "/test3-jsanmvc.properties")
				}, 
		urlPatterns = "/test3/*")
public class Run60_Test3_DispacherFilter extends DispatcherFilter {

}
