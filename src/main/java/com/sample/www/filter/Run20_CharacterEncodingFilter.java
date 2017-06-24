package com.sample.www.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.jsan.mvc.filter.CharacterEncodingFilter;

@WebFilter(
		initParams = {
				@WebInitParam(name = "encoding", value = "utf-8"), 
				@WebInitParam(name = "forceEncoding", value = "true"), //强制编码
				// @WebInitParam(name = "fromEncoding", value = "ISO-8859-1"), // 整站utf-8编码的情况下不用配置
				// @WebInitParam(name = "toEncoding", value = "utf-8"), // 整站utf-8编码的情况下不用配置
				@WebInitParam(name = "trim", value = "true") // 去除请求参数首尾空白
				}, 
		urlPatterns = "/*"
		)
public class Run20_CharacterEncodingFilter extends CharacterEncodingFilter {

}
