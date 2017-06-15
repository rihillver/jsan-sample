package com.jsan.util.fileupload;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;

/**
 * 使用 Apache Commons FileUpload 组件上传文件的工具类。 <br>
 * <br>
 * 
 * 依赖： <br>
 * 1、commons-fileupload-1.3.1.jar <br>
 * 2、common-io.jar
 *
 */

public class UploadUtils {

	/**
	 * 返回 getDiskFileItemFactory 实例。
	 * 
	 * @return
	 */
	public static DiskFileItemFactory getDiskFileItemFactory() {

		return getDiskFileItemFactory(-1, (File)null);
	}

	public static DiskFileItemFactory getDiskFileItemFactory(int sizeThreshold, String repositoryPath) {
		
		return getDiskFileItemFactory(sizeThreshold, repositoryPath, null);
	}

	/**
	 * 返回 getDiskFileItemFactory 实例。
	 * 
	 * @param sizeThreshold
	 *            内存缓冲区大小（Byte）
	 * @param repository
	 *            临时文件夹
	 * @return
	 */
	public static DiskFileItemFactory getDiskFileItemFactory(int sizeThreshold, File repository) {

		return getDiskFileItemFactory(sizeThreshold, repository, null);
	}

	public static DiskFileItemFactory getDiskFileItemFactory(int sizeThreshold, String repositoryPath, ServletContext context) {
		
		return getDiskFileItemFactory(sizeThreshold, new File(repositoryPath), context);
	}

	/**
	 * 返回 getDiskFileItemFactory 实例（如果设置了 ServletContext ，则将自动清除临时文件，建议在文件处理完的时候调用 FileItem 的 delete() 方法能更及时删除临时文件，并可忽略自动清除临时文件的设置）。
	 * 
	 * @param sizeThreshold
	 *            内存缓冲值（Byte）
	 * @param repository
	 *            临时文件夹
	 * @param context
	 *            Servlet 上下文
	 * @return
	 */
	public static DiskFileItemFactory getDiskFileItemFactory(int sizeThreshold, File repository, ServletContext context) {

		DiskFileItemFactory factory = new DiskFileItemFactory();

		if (sizeThreshold > -1) {
			factory.setSizeThreshold(sizeThreshold); // DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD = 10240 （未设置时的默认值）
		}

		if (repository != null) {
			if(!repository.exists()){
				repository.mkdirs();
			}
			factory.setRepository(repository); // new File(System.getProperty("java.io.tmpdir")) （未设置时默认值）
		}

		if (context != null) {
			FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(context);
			factory.setFileCleaningTracker(fileCleaningTracker);
		}

		return factory;
	}
	
	/**
	 * 返回 ServletFileUpload 实例。 <br>
	 * 
	 * 注： <br>
	 * 1、如果设置了单个文件大小限制，那么任意一个文件超过此大小，则整个请求都将抛出异常上传失败 <br>
	 * 2、如果设置了整个请求上传数据限制，那么总上传数据（含文本表单数据）超过此大小，则整个请求都将抛出异常上传失败
	 * 
	 * @return
	 */
	public static ServletFileUpload getServletFileUpload() {

		return getServletFileUpload(-1, -1);
	}

	/**
	 * 返回 ServletFileUpload 实例。 <br>
	 * 
	 * 注： <br>
	 * 1、如果设置了单个文件大小限制，那么任意一个文件超过此大小，则整个请求都将抛出异常上传失败 <br>
	 * 2、如果设置了整个请求上传数据限制，那么总上传数据（含文本表单数据）超过此大小，则整个请求都将抛出异常上传失败
	 * 
	 * @param fileSizeMax
	 *            单个上传文件的最大尺寸限制
	 * @param sizeMax
	 *            整个请求上传数据的最大尺寸限制（含文本表单数据）
	 * @return
	 */
	public static ServletFileUpload getServletFileUpload(long fileSizeMax, long sizeMax) {

		return getServletFileUpload(fileSizeMax, sizeMax, null);
	}

	/**
	 * 返回 ServletFileUpload 实例。 <br>
	 * 
	 * 注： <br>
	 * 1、如果设置了单个文件大小限制，那么任意一个文件超过此大小，则整个请求都将抛出异常上传失败 <br>
	 * 2、如果设置了整个请求上传数据限制，那么总上传数据（含文本表单数据）超过此大小，则整个请求都将抛出异常上传失败
	 * 
	 * @param fileSizeMax
	 *            单个上传文件的最大尺寸限制
	 * @param sizeMax
	 *            整个请求上传数据的最大尺寸限制（含文本表单数据）
	 * @param headerEncoding
	 *            请求头字符编码
	 * @return
	 */
	public static ServletFileUpload getServletFileUpload(long fileSizeMax, long sizeMax, String headerEncoding) {

		return getServletFileUpload(fileSizeMax, sizeMax, headerEncoding, null);
	}

	/**
	 * 返回 ServletFileUpload 实例。 <br>
	 * 
	 * 注： <br>
	 * 1、如果设置了单个文件大小限制，那么任意一个文件超过此大小，则整个请求都将抛出异常上传失败 <br>
	 * 2、如果设置了整个请求上传数据限制，那么总上传数据（含文本表单数据）超过此大小，则整个请求都将抛出异常上传失败
	 * 
	 * @param fileSizeMax
	 *            单个上传文件的最大尺寸限制
	 * @param sizeMax
	 *            整个请求上传数据的最大尺寸限制（含文本表单数据）
	 * @param headerEncoding
	 *            请求头字符编码
	 * @param fileItemFactory
	 *            FileItemFactory 实例
	 * @return
	 */
	public static ServletFileUpload getServletFileUpload(long fileSizeMax, long sizeMax, String headerEncoding, FileItemFactory fileItemFactory) {

		ServletFileUpload servletFileUpload = new ServletFileUpload();
		servletFileUpload.setFileItemFactory(fileItemFactory);
		servletFileUpload.setHeaderEncoding(headerEncoding);
		servletFileUpload.setFileSizeMax(fileSizeMax);
		servletFileUpload.setSizeMax(sizeMax);

		return servletFileUpload;
	}

	/**
	 * 返回文件全名（通过文件路径提取文件全名）。
	 * 
	 * @param path
	 * @return
	 */
	public static String handleFileFullName(String path) {

		String str = null;
		if (path != null) {
			str = path.replaceAll(".*[/|\\\\]", "");
		}
		return str;
	}

	/**
	 * 返回文件全名（通过文件全名提取不含扩展名的文件名）。
	 * 
	 * @param fileName
	 * @return
	 */
	public static String handleFileName(String fileName) {

		String str = null;
		if (fileName != null && fileName.contains(".")) {
			str = fileName.substring(0, fileName.lastIndexOf('.'));
		}
		return str;
	}

	/**
	 * 返回文件类型（通过文件全名提取文件类型，及文件后缀，若无文件后缀则返回 ""）。
	 * 
	 * @param sourceFileName
	 * @return
	 */
	public static String handleFileType(String sourceFileName) {

		if (sourceFileName != null && sourceFileName.contains(".")) {
			return sourceFileName.replaceAll(".*\\.", "");
		} else {
			return "";
		}
	}

	/**
	 * 返回经过转换成小写后的文件类型数组。
	 * 
	 * @param fileType
	 * @return
	 */
	public static String[] handleFileTypeToLowerCase(String[] fileType) {

		if (fileType == null) {
			return null;
		} else {
			for (int i = 0; i < fileType.length; i++) {
				fileType[i] = fileType[i].toLowerCase(); // 转小写
			}
			return fileType;
		}
	}
	
	// =================================================================
	
	public static String getWebRootPath(){
		
		return null;
	}

}
