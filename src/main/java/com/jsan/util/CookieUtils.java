package com.jsan.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 操作工具类。<br>
 * <br>
 * 
 * 注：<br>
 * 1、不指定 maxAge 的时候默认为 -1 ，即浏览器关闭后 Cookie 随即失效。<br>
 * 2、path 为 null 的时候 Cookie 的路径默认在其请求的路径下。
 *
 */

public class CookieUtils {

	/**
	 * 添加 Cookie（path 为根路径 "/"）。
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {

		addCookie(response, name, value, null, "/", null);
	}

	/**
	 * 添加 Cookie（path 为根路径 "/"）。
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, Integer maxAge) {

		addCookie(response, name, value, maxAge, "/", null);
	}

	/**
	 * 添加 Cookie 。
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param path
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, Integer maxAge, String path) {

		addCookie(response, name, value, maxAge, path, null);
	}

	/**
	 * 添加 Cookie 。
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param path
	 * @param domain
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain) {

		if (name != null) {
			try {
				name = URLEncoder.encode(name, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// logging...
				e.printStackTrace();
			}
		}

		if (value != null) {
			try {
				value = URLEncoder.encode(value, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// logging...
				e.printStackTrace();
			}
		}

		Cookie cookie = new Cookie(name, value);

		if (maxAge != null) {
			cookie.setMaxAge(maxAge);
		}
		if (path != null) {
			cookie.setPath(path);
		}
		if (domain != null) {
			cookie.setDomain(domain);
		}

		response.addCookie(cookie);
	}

	/**
	 * 删除 Cookie（path 为根路径 "/"）。
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	public static void removeCookie(HttpServletResponse response, String name) {

		addCookie(response, name, null, 0, "/", null);
	}

	/**
	 * 删除 Cookie 。
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param path
	 */
	public static void removeCookie(HttpServletResponse response, String name, String path) {

		addCookie(response, name, null, 0, path, null);
	}

	/**
	 * 删除 Cookie 。
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param path
	 * @param domain
	 */
	public static void removeCookie(HttpServletResponse response, String name, String path, String domain) {

		addCookie(response, name, null, 0, path, domain);
	}

	/**
	 * 返回 Cookie 。
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {

		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();

		if (cookies != null && name != null) {
			try {
				name = URLEncoder.encode(name, "utf-8");
			} catch (Exception e) {
				// logging...
				e.printStackTrace();
			}

			for (Cookie itemCookie : cookies) {
				if (name.equals(itemCookie.getName())) {
					cookie = itemCookie;
				}
			}
		}

		return cookie;
	}

	/**
	 * 返回 Cookie 值。
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {

		String value = null;
		Cookie cookie = getCookie(request, name);

		if (cookie != null) {
			value = cookie.getValue();
			try {
				value = URLDecoder.decode(value, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// logging...
				e.printStackTrace();
			}
		}

		return value;
	}

	/**
	 * 返回 Cookie 键值 Map 。
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getCookieMap(HttpServletRequest request) {

		Map<String, String> cookieMap = null;
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			cookieMap = new HashMap<String, String>();
			for (Cookie cookie : cookies) {
				try {
					cookieMap.put(URLDecoder.decode(cookie.getName(), "utf-8"), URLDecoder.decode(cookie.getValue(), "utf-8"));
				} catch (UnsupportedEncodingException e) {
					// logging...
					e.printStackTrace();
				}
			}
		}

		return cookieMap;
	}

}
