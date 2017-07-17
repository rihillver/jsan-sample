package com.sample.www.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jsan.mvc.filter.AbstractRequestLogging;

@WebFilter(urlPatterns = "/*")
public class Run10_RequestLoggingFilter extends AbstractRequestLogging {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void beforeRequest(HttpServletRequest request) {

		//System.out.println(getDateString() + " [" + getUserId(request) + "] (" + request.getRemoteAddr() + ") " + request.getRequestURI() + getQueryString(request));

		logger.info("[{}] ({}) {}{}", getUserId(request), request.getRemoteAddr(), request.getRequestURI(),
				getQueryString(request));
	}

	@Override
	protected void afterRequest(HttpServletRequest request) {

	}

	protected String getUserId(HttpServletRequest request) {

//		String userId = null;
//
//		HttpSession session = request.getSession(false);
//		if (session != null) {
//			Object obj = session.getAttribute("uid");
//			if (obj != null) {
//				userId = (String) obj;
//			}
//		}
//
//		return userId;
		
		return null;
	}

	protected final ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	protected String getDateString() {

		return simpleDateFormatThreadLocal.get().format(new Date());
	}

	protected String getQueryString(HttpServletRequest request) {

		String str = request.getQueryString();
		if (str != null) {
			return "?" + str;
		} else {
			return "";
		}
	}
}
