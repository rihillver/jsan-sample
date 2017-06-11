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
//import java.io.OutputStreamWriter;
//import java.io.UnsupportedEncodingException;

/**
 * 数据流转换工具类。
 * <p>
 * 更专业的可参 Apache Commons IO。
 *
 */

public class StreamUtils {

	/**
	 * 输入流转为输出流。
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @return
	 */
	public static boolean convertStream(InputStream inputStream, OutputStream outputStream) {

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
	 * 从输入流中获取数据。
	 * 
	 * @param inputStream
	 * @return
	 */
	public static byte[] readStreamToByte(InputStream inputStream) {

		byte[] result = null;
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
				result = outputStream.toByteArray();

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

		return result;
	}

	/**
	 * 从输入流中获取数据。
	 * 
	 * @param inputStream
	 * @return
	 */
	public static String readStreamToString(InputStream inputStream) {

		return readStreamToString(inputStream, null);
	}

	/**
	 * 从输入流中获取数据（指定字符编码）。
	 * 
	 * @param inputStream
	 * @param charset
	 * @return
	 */
	public static String readStreamToString(InputStream inputStream, String charset) {

		String str = null;

		byte[] result = readStreamToByte(inputStream);

		if (result != null) {
			if (charset == null) {
				str = new String(result);
			} else {
				try {
					str = new String(result, charset);
				} catch (Exception e) {
					// logging...
					e.printStackTrace();
				}
			}
		}

		return str;
	}

	/**
	 * 从文件获取数据。
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] readFileToByte(File file) {

		byte[] result = null;

		if (file != null) {
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(file);
				result = readStreamToByte(inputStream);
			} catch (Exception e) {
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
			}
		}

		return result;
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

		String str = null;

		if (file != null) {
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(file);
				str = readStreamToString(inputStream, charset);
			} catch (Exception e) {
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
			}
		}

		return str;
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
	 * @param outputStream
	 * @param bytes
	 * @return
	 */
	public static boolean writeStreamFromByte(OutputStream outputStream, byte[] bytes) {

		boolean b = false;

		if (outputStream != null && bytes != null) {
			try {
				outputStream.write(bytes);
				b = true;
			} catch (IOException e) {
				// logging...
				e.printStackTrace();
			}
		}

		return b;
	}

	/**
	 * 将数据写入输出流。
	 * 
	 * @param outputStream
	 * @param str
	 * @return
	 */
	public static boolean writeStreamFromString(OutputStream outputStream, String str) {

		return writeStreamFromString(outputStream, str, null);
	}

	/**
	 * 将数据写入输出流（指定字符编码）。
	 * 
	 * @param outputStream
	 * @param str
	 * @param charset
	 * @return
	 */
	public static boolean writeStreamFromString(OutputStream outputStream, String str, String charset) {

		byte[] bytes = null;

		if (str != null) {
			if (charset == null) {
				bytes = str.getBytes();
			} else {
				try {
					bytes = str.getBytes(charset);
				} catch (Exception e) {
					// logging...
					e.printStackTrace();
				}
			}
		}

		return writeStreamFromByte(outputStream, bytes);
	}

	/**
	 * 将数据写入文件。
	 * 
	 * @param file
	 * @param bytes
	 * @return
	 */
	public static boolean writeFileFromByte(File file, byte[] bytes) {

		boolean b = false;

		if (file != null) {
			OutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream(file);
				b = writeStreamFromByte(outputStream, bytes);
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

		return b;
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
					// logging...
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

	// /**
	// * 将数据追加写入输出流。
	// *
	// * @param outputStream
	// * @param str
	// * @return
	// */
	// public static boolean appendStreamFromString(OutputStream outputStream, String str) {
	//
	// return appendStreamFromString(outputStream, str, null);
	// }

	// /**
	// * 将数据追加写入输出流（指定字符编码）。
	// *
	// * @param outputStream
	// * @param str
	// * @param charset
	// * @return
	// */
	// public static boolean appendStreamFromString(OutputStream outputStream, String str, String charset) {
	//
	// boolean b = false;
	// OutputStreamWriter outputStreamWriter = null;
	//
	// if (charset == null) {
	// outputStreamWriter = new OutputStreamWriter(outputStream);
	// } else {
	// try {
	// outputStreamWriter = new OutputStreamWriter(outputStream, charset);
	// } catch (UnsupportedEncodingException e) {
	// // logging...
	// e.printStackTrace();
	// }
	// }
	//
	// try {
	// outputStreamWriter.write(str);
	// b = true;
	// } catch (IOException e) {
	// // logging...
	// e.printStackTrace();
	// }
	//
	// try {
	// if (outputStreamWriter != null) {
	// outputStreamWriter.close();
	// }
	// } catch (IOException e) {
	// // logging...
	// e.printStackTrace();
	// }
	//
	// return b;
	// }

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

		boolean b = false;

		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file, true); // 写入文件末尾处
			b = writeStreamFromString(outputStream, str, charset);
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

		return b;
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

		boolean b = false;

		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(obj);
			objectOutputStream.reset();
			b = true;
		} catch (Exception e) {
			// logging
			e.printStackTrace();
		} finally {
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					// logging
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// logging
					e.printStackTrace();
				}
			}
		}

		return b;
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

		Object obj = null;

		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			objectInputStream = new ObjectInputStream(fileInputStream);
			obj = (Object) objectInputStream.readObject();
		} catch (Exception e) {
			// logging...
			e.printStackTrace();
		} finally {
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					// logging...
					e.printStackTrace();
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// logging...
					e.printStackTrace();
				}
			}
		}

		return obj;
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
	 * 简易判断文件编码，默认为 GBK，仅判断 UTF-8，虽然不准确，但一般中文系统除了 UTF-8 之外多数就是 GBK 了，所以在此仅作最简单初级的判断。<br>
	 * <br>
	 * 
	 * 若要实现更复杂的文件编码检测，可使用 cpdetector 进行探测。
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileCharset(File file) {

		String charset = "GBK";
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] b = new byte[3];
			inputStream.read(b);
			if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
				charset = "UTF-8";
			}
		} catch (Exception e) {
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
		}

		return charset;
	}

	/**
	 * 简易判断文件编码，默认为 GBK，仅判断 UTF-8，虽然不准确，但一般中文系统除了 UTF-8 之外多数就是 GBK 了，所以在此仅作最简单初级的判断。<br>
	 * <br>
	 * 
	 * 若要实现更复杂的文件编码检测，可使用 cpdetector 进行探测。
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileCharset(String filePath) {

		File file = new File(filePath);
		return getFileCharset(file);
	}

}