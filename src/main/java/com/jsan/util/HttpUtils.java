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
 * Http 请求的工具类（仅支持 GET 请求）。<br>
 * <br>
 *
 * 本类主要通过 HttpURLConnection 简易实现，需要更复杂更专业的应用则建议使用 HttpComponents 组件。
 * 
 */

public class HttpUtils {

	private static final int CONNECT_TIMEOUT = 10000; // 连接超时：10秒

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
		HttpURLConnection connection = null;
		InputStream inputStream = null;

		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(CONNECT_TIMEOUT);
			connection.setRequestProperty("User-Agent", USER_AGENT); // 设置 User-Agent，避免部分网站禁止网络爬虫抓取网页内容
			connection.connect();

			if (charset == null) {
				String contentType = connection.getContentType();
				if (contentType != null) {
					String temp = contentType.replaceAll(".*charset\\s*=\\s*([\\w-]+).*", "$1");
					if (!temp.equals(contentType) && temp.length() > 0) {
						charset = temp;
					}
				}
			}

			if (connection.getResponseCode() == 200) {
				inputStream = connection.getInputStream();
				str = streamToString(inputStream, charset);
			}
		} catch (IOException e) {
			// logging...
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// logging...
					e.printStackTrace();
				}
			}
			if (connection != null) {
				connection.disconnect();
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

		URL url = null;

		try {
			url = new URL(urlstr);
		} catch (MalformedURLException e) {
			// logging...
			e.printStackTrace();
		}

		return getString(url, charset);
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

		HttpURLConnection connection = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(CONNECT_TIMEOUT);
			connection.setRequestProperty("User-Agent", USER_AGENT); // 设置 User-Agent，避免部分网站禁止网络爬虫抓取网页内容
			connection.connect();

			if (connection.getResponseCode() == 200) {

				inputStream = connection.getInputStream();
				outputStream = new FileOutputStream(file);

				if (convertStream(inputStream, outputStream)) {
					return file;
				} else {
					return null;
				}
			} else {
				return null;
			}

		} catch (IOException e) {
			// logging...
			e.printStackTrace();
			return null;
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// logging...
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// logging...
					e.printStackTrace();
				}
			}
			if (connection != null) {
				connection.disconnect();
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

		URL url = null;

		try {
			url = new URL(urlstr);
		} catch (MalformedURLException e) {
			// logging...
			e.printStackTrace();
		}

		return getFile(url, file);
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
	 * @param inputStream
	 * @param outputStream
	 * @return
	 */
	private static boolean convertStream(InputStream inputStream, OutputStream outputStream) {

		boolean b = false;

		if (inputStream != null && outputStream != null) {
			byte[] buffer = new byte[1024 * 4];
			int len = 0;
			try {
				while ((len = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, len);
				}
				b = true;
			} catch (Exception e) {
				// logging...
				e.printStackTrace();
			}
		}

		return b;
	}

	/**
	 * 从输入流中获取数据（指定字符编码）。
	 * 
	 * @param inputStream
	 * @param charset
	 * @return
	 */
	private static String streamToString(InputStream inputStream, String charset) {

		String str = null;
		ByteArrayOutputStream outputStream = null;

		if (inputStream != null) {
			try {
				// result = new byte[inputStream.available()]; // 注意：当从网络 URL 中获取输入流时不能使用 inputStream.available()
				// inputStream.read(result);

				outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024 * 4];
				int len = 0;
				while ((len = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, len);
				}
				byte[] result = outputStream.toByteArray();

				if (charset == null) {
					str = new String(result);
				} else {
					str = new String(result, charset);
				}
			} catch (Exception e) {
				// logging...
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						// logging...
						e.printStackTrace();
					}
				}
			}
		}

		return str;
	}

	// public static void main(String[] args) throws UnsupportedEncodingException {
	//
	// System.out.println(getString("http://scm.taixingmart.com/app/query.do?key="+URLEncoder.encode("可乐", "utf-8")));
	// System.out.println(getFile("http://s0.hao123img.com/res/r/image/2015-02-10/043d904b7b7b21559175358c34a7a294.jpg", "d:/test/abc.jpg"));
	//
	// }

}
