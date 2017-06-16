package com.jsan.util.fileupload;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;

/**
 * 文件上传工具类（基于 Apache Commons FileUpload）。
 *
 */

public class UploadUtils {

	/**
	 * 返回 getDiskFileItemFactory 实例。
	 * 
	 * @return
	 */
	public static DiskFileItemFactory getFileItemFactory() {

		return getFileItemFactory(-1, (File)null);
	}

	public static DiskFileItemFactory getFileItemFactory(int sizeThreshold, String repositoryPath) {
		
		return getFileItemFactory(sizeThreshold, repositoryPath, null);
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
	public static DiskFileItemFactory getFileItemFactory(int sizeThreshold, File repository) {

		return getFileItemFactory(sizeThreshold, repository, null);
	}

	public static DiskFileItemFactory getFileItemFactory(int sizeThreshold, String repositoryPath, ServletContext context) {
		
		return getFileItemFactory(sizeThreshold, new File(repositoryPath), context);
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
	public static DiskFileItemFactory getFileItemFactory(int sizeThreshold, File repository, ServletContext context) {

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

	
	// =================================================================

	public static String getWebRootPath() {

		URL url = Object.class.getResource("/");
		File file = new File(url.getPath());
		try {
			return file.getParentFile().getParentFile().getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String extractFileName(String path) {

		int index = path.lastIndexOf('/');
		if (index > -1) {
			path = path.substring(index + 1);
		}

		index = path.lastIndexOf('\\');
		if (index > -1) {
			path = path.substring(index + 1);
		}

		return path;
	}

	public static String extractFileNameWithoutExt(String fileName) {

		int index = fileName.lastIndexOf('.');
		if (index > -1) {
			return fileName.substring(0, index);
		} else {
			return "";
		}
	}

	public static String extractFileType(String fileName) {

		int index = fileName.lastIndexOf('.');
		if (index > -1) {
			return fileName.substring(index + 1);
		} else {
			return "";
		}
	}

	public static void main(String[] args) {
		
		String str = "d:/adafasd/sdfsdf\\\\sdfjsdfsd.exe";

		System.out.println(extractFileName(str));
		System.out.println(extractFileNameWithoutExt(str));
		System.out.println(extractFileType(str));
		
		System.out.println(getWebRootPath());
	}
}
