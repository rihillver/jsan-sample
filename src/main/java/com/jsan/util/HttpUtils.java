package com.jsan.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 简易的 Http 请求工具类。
 * <ul>
 * <li>主要通过 HttpURLConnection 简易实现（仅支持 GET 请求）。</li>
 * <li>更专业的可参 Apache HttpComponents。</li>
 * </ul>
 * 
 */

public class HttpUtils {

	private static final int CONNECT_TIMEOUT = 10000; // 默认连接超时为10秒

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36";

	/**
	 * 返回 URL 请求的字符串形式（自动分析字符编码）。
	 * 
	 * @param url
	 * @return
	 */
	public static String getString(URL url) {

		return getString(url, null);
	}

	/**
	 * 返回 URL 请求的字符串形式（指定字符编码）。
	 * 
	 * @param url
	 * @param charset
	 * @return
	 */
	public static String getString(URL url, String charset) {

		String str = null;
		HttpURLConnection conn = null;
		InputStream in = null;

		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setRequestProperty("User-Agent", USER_AGENT); // 设置User-Agent，避免部分网站禁止网络爬虫抓取网页内容
			conn.connect();

			if (charset == null) {
				String contentType = conn.getContentType();
				if (contentType != null) {
					String temp = contentType.replaceAll(".*charset\\s*=\\s*([\\w-]+).*", "$1");
					if (!temp.equals(contentType) && temp.length() > 0) {
						charset = temp;
					}
				}
			}

			if (conn.getResponseCode() == 200) {
				in = conn.getInputStream();
				str = streamToString(in, charset);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return str;
	}

	/**
	 * 返回 URL 请求的字符串形式（自动分析字符编码）。
	 * 
	 * @param urlstr
	 * @return
	 */
	public static String getString(String urlstr) {

		return getString(urlstr, null);
	}

	/**
	 * 返回 URL 请求的字符串形式（指定字符编码）。
	 * 
	 * @param urlstr
	 * @param charset
	 * @return
	 */
	public static String getString(String urlstr, String charset) {

		try {
			return getString(new URL(urlstr), charset);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回 URL 请求的文件形式。
	 * 
	 * @param url
	 * @param file
	 * @return
	 */
	public static File getFile(URL url, File file) {

		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}

		HttpURLConnection conn = null;
		InputStream in = null;
		OutputStream out = null;

		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(CONNECT_TIMEOUT);
			conn.setRequestProperty("User-Agent", USER_AGENT); // 设置User-Agent，避免部分网站禁止网络爬虫抓取网页内容
			conn.connect();

			if (conn.getResponseCode() == 200) {

				in = conn.getInputStream();
				out = new FileOutputStream(file);

				if (convertStream(in, out)) {
					return file;
				} else {
					return null;
				}
			} else {
				return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	/**
	 * 返回 URL 请求的文件形式。
	 * 
	 * @param url
	 * @param filePath
	 * @return
	 */
	public static File getFile(URL url, String filePath) {

		File file = new File(filePath);
		return getFile(url, file);
	}

	/**
	 * 返回 URL 请求的文件形式。
	 * 
	 * @param urlstr
	 * @param file
	 * @return
	 */
	public static File getFile(String urlstr, File file) {

		try {
			return getFile(new URL(urlstr), file);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回 URL 请求的文件形式。
	 * 
	 * @param urlstr
	 * @param filePath
	 * @return
	 */
	public static File getFile(String urlstr, String filePath) {

		File file = new File(filePath);
		return getFile(urlstr, file);
	}

	/**
	 * 输入流转为输出流。
	 * 
	 * @param in
	 * @param out
	 * @return
	 */
	private static boolean convertStream(InputStream in, OutputStream out) {

		if (in != null && out != null) {
			byte[] buffer = new byte[1024 * 4];
			int len = 0;
			try {
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				return true;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			return false;
		}

	}

	/**
	 * 从输入流中获取数据（指定字符编码）。
	 * 
	 * @param in
	 * @param charset
	 * @return
	 */
	private static String streamToString(InputStream in, String charset) {

		String str = null;
		ByteArrayOutputStream out = null;

		if (in != null) {
			try {

				// 当从网络URL中获取输入流时不能使用 in.available()
				// result = new byte[in.available()];
				// in.read(result);

				out = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024 * 4];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				byte[] result = out.toByteArray();

				if (charset == null) {
					str = new String(result);
				} else {
					str = new String(result, charset);
				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return str;
	}

}
