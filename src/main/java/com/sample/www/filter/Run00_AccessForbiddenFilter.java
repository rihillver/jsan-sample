package com.sample.www.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.jsan.mvc.filter.AccessForbiddenFilter;

@WebFilter(
		initParams = {
				@WebInitParam(name = "statusCode", value = "404"), // 默认状态码为403
				}, 
		urlPatterns = "*.jsp" // 禁止直接访问jsp文件，当使用jsp作为视图时，建议所有请求都由控制器转发到jsp文件
		
		// 同时也可以禁止指定目录的访问，但仅限于Servlet容器（比如Tomcat），需要注意的是，如果是集成Apache或Nginx等情况，则这里禁止的目录里面的文件可能会由Apache或Nginx先处理，因此这种情况需要在Apache或Nginx这些服务器上同样相应的配置禁止访问的目录
		// urlPatterns = {"*.jsp", "/settings/*", "/upload/*"} 
		)
public class Run00_AccessForbiddenFilter extends AccessForbiddenFilter {

}
