package com.jsan.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 数据流转换工具类。
 * <p>
 * 更专业的可参 Apache Commons IO （IOUtils.class）。
 *
 */

public class StreamUtils {

	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4; // 默认缓冲区大小

	/**
	 * 输入流转为输出流。
	 * 
	 * @param in
	 * @param out
	 * @return
	 */
	public static boolean convertStream(InputStream in, OutputStream out) {

		return convertStream(in, out, DEFAULT_BUFFER_SIZE);
	}

	/**
	 * 输入流转为输出流（指定缓冲区大小）。
	 * 
	 * @param in
	 * @param out
	 * @param bufferSize
	 * @return
	 */
	public static boolean convertStream(InputStream in, OutputStream out, int bufferSize) {

		if (in != null && out != null) {
			byte[] buffer = new byte[bufferSize];
			int len;
			try {
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				return true;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return false;
	}

	/**
	 * 从输入流中获取数据。
	 * 
	 * @param in
	 * @return
	 */
	public static byte[] readStreamToByte(InputStream in) {

		ByteArrayOutputStream out = null;

		if (in != null) {
			try {
				out = new ByteArrayOutputStream();
				if (convertStream(in, out)) {
					return out.toByteArray();
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

		return null;
	}

	/**
	 * 从输入流中获取数据。
	 * 
	 * @param in
	 * @return
	 */
	public static String readStreamToString(InputStream in) {

		return readStreamToString(in, null);
	}

	/**
	 * 从输入流中获取数据（指定字符编码）。
	 * 
	 * @param in
	 * @param charset
	 * @return
	 */
	public static String readStreamToString(InputStream in, String charset) {

		byte[] result = readStreamToByte(in);

		if (result != null) {
			if (charset == null) {
				return new String(result);
			} else {
				try {
					return new String(result, charset);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}

		return null;
	}

	/**
	 * 从文件获取数据。
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] readFileToByte(File file) {

		if (file != null) {
			InputStream in = null;
			try {
				in = new FileInputStream(file);
				return readStreamToByte(in);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}

	/**
	 * 从文件获取数据。
	 * 
	 * @param file
	 * @return
	 */
	public static String readFileToString(File file) {

		return readFileToString(file, null);
	}

	/**
	 * 从文件获取数据（指定字符编码）。
	 * 
	 * @param file
	 * @param charset
	 * @return
	 */
	public static String readFileToString(File file, String charset) {

		if (file != null) {
			InputStream in = null;
			try {
				in = new FileInputStream(file);
				return readStreamToString(in, charset);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}

	/**
	 * 从文件路径获取数据。
	 * 
	 * @param filePath
	 * @return
	 */
	public static byte[] readFileToByte(String filePath) {

		File file = new File(filePath);
		return readFileToByte(file);
	}

	/**
	 * 从文件路径获取数据。
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFileToString(String filePath) {

		File file = new File(filePath);
		return readFileToString(file);
	}

	/**
	 * 从文件路径获取数据（指定字符编码）。
	 * 
	 * @param filePath
	 * @param charset
	 * @return
	 */
	public static String readFileToString(String filePath, String charset) {

		File file = new File(filePath);
		return readFileToString(file, charset);
	}

	/**
	 * 将数据写入输出流。
	 * 
	 * @param out
	 * @param bytes
	 * @return
	 */
	public static boolean writeStreamFromByte(OutputStream out, byte[] bytes) {

		if (out != null && bytes != null) {
			try {
				out.write(bytes);
				return true;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return false;
	}

	/**
	 * 将数据写入输出流。
	 * 
	 * @param out
	 * @param str
	 * @return
	 */
	public static boolean writeStreamFromString(OutputStream out, String str) {

		return writeStreamFromString(out, str, null);
	}

	/**
	 * 将数据写入输出流（指定字符编码）。
	 * 
	 * @param out
	 * @param str
	 * @param charset
	 * @return
	 */
	public static boolean writeStreamFromString(OutputStream out, String str, String charset) {

		byte[] bytes = null;

		if (str != null) {
			if (charset == null) {
				bytes = str.getBytes();
			} else {
				try {
					bytes = str.getBytes(charset);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}

		return writeStreamFromByte(out, bytes);
	}

	/**
	 * 将数据写入文件。
	 * 
	 * @param file
	 * @param bytes
	 * @return
	 */
	public static boolean writeFileFromByte(File file, byte[] bytes) {

		if (file != null) {
			OutputStream out = null;
			try {
				out = new FileOutputStream(file);
				return writeStreamFromByte(out, bytes);
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

		return false;
	}

	/**
	 * 将数据写入文件。
	 * 
	 * @param file
	 * @param str
	 * @return
	 */
	public static boolean writeFileFromString(File file, String str) {

		return writeFileFromString(file, str, null);
	}

	/**
	 * 将数据写入文件（指定字符编码）。
	 * 
	 * @param file
	 * @param str
	 * @param charset
	 * @return
	 */
	public static boolean writeFileFromString(File file, String str, String charset) {

		byte[] bytes = null;

		if (str != null) {
			if (charset == null) {
				bytes = str.getBytes();
			} else {
				try {
					bytes = str.getBytes(charset);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return writeFileFromByte(file, bytes);
	}

	/**
	 * 将数据写入文件路径。
	 * 
	 * @param filePath
	 * @param bytes
	 * @return
	 */
	public static boolean writeFileFromByte(String filePath, byte[] bytes) {

		File file = new File(filePath);
		return writeFileFromByte(file, bytes);
	}

	/**
	 * 将数据写入文件路径。
	 * 
	 * @param filePath
	 * @param str
	 * @return
	 */
	public static boolean writeFileFromString(String filePath, String str) {

		File file = new File(filePath);
		return writeFileFromString(file, str);
	}

	/**
	 * 将数据写入文件路径（指定字符编码）。
	 * 
	 * @param filePath
	 * @param str
	 * @param charset
	 * @return
	 */
	public static boolean writeFileFromString(String filePath, String str, String charset) {

		File file = new File(filePath);
		return writeFileFromString(file, str, charset);
	}

	/**
	 * 将数据追加写入文件。
	 * 
	 * @param file
	 * @param str
	 * @return
	 */
	public static boolean appendFileFromString(File file, String str) {

		return appendFileFromString(file, str, null);
	}

	/**
	 * 将数据追加写入文件（指定字符编码）。
	 * 
	 * @param file
	 * @param str
	 * @param charset
	 * @return
	 */
	public static boolean appendFileFromString(File file, String str, String charset) {

		OutputStream out = null;
		try {
			out = new FileOutputStream(file, true); // 写入文件末尾处
			return writeStreamFromString(out, str, charset);
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

	/**
	 * 将数据追加写入文件路径。
	 * 
	 * @param filePath
	 * @param str
	 * @return
	 */
	public static boolean appendFileFromString(String filePath, String str) {

		return appendFileFromString(filePath, str, null);
	}

	/**
	 * 将数据追加写入文件路径（指定字符编码）。
	 * 
	 * @param filePath
	 * @param str
	 * @param charset
	 * @return
	 */
	public static boolean appendFileFromString(String filePath, String str, String charset) {

		File file = new File(filePath);
		return appendFileFromString(file, str, charset);
	}

	/**
	 * 将对象存储到文件（进行序列化与反序列化时，对象必须实现 Serializable 接口）。
	 * 
	 * @param file
	 * @param obj
	 * @return
	 */
	public static boolean writeObject(File file, Object obj) {

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.reset();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将对象存储到文件（进行序列化与反序列化时，对象必须实现 Serializable 接口）。
	 * 
	 * @param filePath
	 * @param obj
	 * @return
	 */
	public static boolean writeObject(String filePath, Object obj) {

		File file = new File(filePath);
		return writeObject(file, obj);
	}

	/**
	 * 读取文件并转化到对象（进行序列化与反序列化时，对象必须实现 Serializable 接口）。
	 * 
	 * @param file
	 * @return
	 */
	public static Object readObject(File file) {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			return ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 读取文件并转化到对象（进行序列化与反序列化时，对象必须实现 Serializable 接口）。
	 * 
	 * @param filePath
	 * @return
	 */
	public static Object readObject(String filePath) {

		File file = new File(filePath);
		return readObject(file);
	}

	/**
	 * 简易判断文件编码，默认为 GBK，仅判断 UTF-8，虽然不准确，但一般中文系统除了 UTF-8 之外多数就是 GBK
	 * 了，所以在此仅作最简单初级的判断。
	 * <p>
	 * 若要实现更专业的文件编码检测，可使用 cpdetector 进行探测。
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileCharset(File file) {

		String charset = "GBK";
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] b = new byte[3];
			in.read(b);
			if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
				charset = "UTF-8";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return charset;
	}

	/**
	 * 简易判断文件编码，默认为 GBK，仅判断 UTF-8，虽然不准确，但一般中文系统除了 UTF-8 之外多数就是 GBK
	 * 了，所以在此仅作最简单初级的判断。
	 * <p>
	 * 若要实现更专业的文件编码检测，可使用 cpdetector 进行探测。
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileCharset(String filePath) {

		File file = new File(filePath);
		return getFileCharset(file);
	}

}