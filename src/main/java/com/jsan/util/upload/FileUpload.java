package com.jsan.util.upload;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传简易封装工具类。 <br>
 * <br>
 * 
 * 参数： <br>
 * fileItemFactory ： FileItemFactory 实例（设置内存缓冲大小及临时文件夹等） <br>
 * headerEncoding ： 请求头字符编码，用于解决文件名乱码问题（默认 null） <br>
 * fileSizeMax ： 单个上传文件的最大尺寸限制（默认 0，即无限制） <br>
 * sizeMax ： 整个请求上传数据的最大尺寸限制（含文本表单数据）（默认 0，即无限制） <br>
 * fileMax ： 最大允许上传的文件数（默认 0，即无限制） <br>
 * fileType ： 允许上传的文件类型，大小写不限，"" 表示无后缀文件（默认 null，即无限制） <br>
 * characterEncoding ： 文本表单字符编码，用于解决文本表单乱码（默认 null） <br>
 * rootPath ： 文件存储根路径（根文件夹）（默认为 web 应用根目录，即 request.getServletContext().getRealPath("")） <br>
 * relativePath ： 文件存储相对路径，即相对于根路径下的目录路径（子文件夹），与 rootPath 区分主要是便于在数据库中存储（默认为 ""） <br>
 * names ： 文件命名，优先于 named 接口，一般建议根据给出命名的数量同时指定 fileMax 数量，避免数组越界（默认 null） <br>
 * named ： 通过 named 接口自定义文件命名 <br>
 * <br>
 * 
 * 注意：允许上传文件大小为 0 的空文件。
 *
 */

public class FileUpload {

	protected HttpServletRequest request;

	protected FileItemFactory fileItemFactory;
	protected String headerEncoding;
	protected long fileSizeMax;
	protected long sizeMax;

	protected int fileMax;
	protected String[] fileType;
	protected String characterEncoding;

	protected String rootPath;
	protected String relativePath;

	protected String[] names;
	protected Named named;

	protected Map<String, FileInfo> fileInfoMap;

	protected int fileCount;
	protected String savePath;
	protected List<String> fileTypeList;

	protected Vector<String> vector;
	protected Map<String, List<String>> map;

	public FileUpload(HttpServletRequest request) {

		this.request = request;
	}

	/**
	 * 执行文件上传，返回成功执行数。 <br>
	 * <br>
	 * 
	 * 注：当该方法抛出异常时一般为以下原因 <br>
	 * 1、非 multipart 表单 <br>
	 * 2、单个文件超出大小限制 <br>
	 * 3、整个请求超出给定限制
	 * 
	 * @return
	 * @throws Exception
	 */
	public int process() throws Exception {

		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new Exception("the request doesn't contains multipart content.");
		}

		ServletFileUpload servletFileUpload = new ServletFileUpload();
		servletFileUpload.setHeaderEncoding(headerEncoding);
		if (fileSizeMax > 0) {
			servletFileUpload.setFileSizeMax(fileSizeMax); // 未设置时默认值为 -1，即不限制大小
		}
		if (sizeMax > 0) {
			servletFileUpload.setSizeMax(sizeMax); // 未设置时默认值为 -1，即不限制大小
		}

		if (rootPath == null) { // 如果 rootPath 为 null，则文件保存路径为 web 应用的根路径
			rootPath = request.getServletContext().getRealPath("");
		}
		if (relativePath == null) {
			relativePath = "";
		}
		File savePathFile = new File(rootPath, relativePath);
		if (!savePathFile.exists()) { // 文件目录若不存在则创建
			savePathFile.mkdirs();
		}
		savePath = savePathFile.getPath();

		if (fileType != null) {
			fileTypeList = Arrays.asList(handleFileTypeToLowerCase(fileType));
		}

		map = new HashMap<String, List<String>>();
		vector = new Vector<String>();
		fileInfoMap = new LinkedHashMap<String, FileInfo>();

		return handleUpload(servletFileUpload);
	}

	/**
	 * 处理具体文件上传操作，返回成功执行数。
	 * 
	 * @param servletFileUpload
	 * @return
	 * @throws Exception
	 */
	protected int handleUpload(ServletFileUpload servletFileUpload) throws Exception {

		if (fileItemFactory == null) { // 非 Streaming 方式必须使用 FileItemFactory
			fileItemFactory = new DiskFileItemFactory();
		}
		servletFileUpload.setFileItemFactory(fileItemFactory);

		List<FileItem> lists = servletFileUpload.parseRequest(request);

		if (lists == null) {
			return 0;
		} else {

			for (FileItem fileItem : lists) {

				if (fileItem.isFormField()) {

					try {
						handleFormField(fileItem.getFieldName(), characterEncoding == null ? fileItem.getString() : fileItem.getString(characterEncoding));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

				} else {

					if (fileMax > 0 && fileCount >= fileMax) { // 判断最多上传文件数量
						continue;
					}

					if (fileItem.getName().isEmpty()) { // 空文件表单
						continue;
					}

					// if (fileItem.getSize() == 0) { // 判断文件大小，空文件不能上传
					// continue;
					// }

					String sourcePath = fileItem.getName(); // 早期 IE 浏览器可能含客户端绝对路径
					String sourceFullName = handleFileFullName(sourcePath);
					String sourceName = handleFileName(sourceFullName);
					String type = handleFileType(sourceFullName).toLowerCase(); // 小写

					if (fileTypeList != null && !fileTypeList.contains(type)) { // 判断文件类型
						continue;
					}

					String destFullName = sourceFullName;

					if (names != null || named != null) { // 重命名
						if (names == null) {
							destFullName = named.getName(fileItem.getFieldName(), destFullName, fileCount);
						} else {
							destFullName = names[fileCount];
						}
						if (type.length() > 0) {
							destFullName = destFullName + "." + type;
						}
					}

					String destName = handleFileName(destFullName);

					File file = new File(savePath, destFullName);

					try {
						fileItem.write(file);

						FileInfo fileInfo = new FileInfo();
						fileInfo.setSourceName(sourceName);
						fileInfo.setSourceFullName(sourceFullName);
						fileInfo.setSourcePath(sourcePath);

						fileInfo.setDestName(destName);
						fileInfo.setDestFullName(destFullName);
						fileInfo.setDestPath(file.getPath());

						fileInfo.setRootPath(rootPath);
						fileInfo.setRelativePath(relativePath);

						fileInfo.setFieldName(fileItem.getFieldName());
						fileInfo.setContentType(fileItem.getContentType());
						fileInfo.setType(type);
						fileInfo.setSize(fileItem.getSize());

						fileInfoMap.put(fileItem.getFieldName(), fileInfo);

						if (!fileItem.isInMemory()) {
							fileItem.delete(); // 删除临时文件
						}

						fileCount++;

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			return fileCount;
		}
	}

	/**
	 * 设置文本表单数据。
	 * 
	 * @param key
	 * @param value
	 */
	protected void handleFormField(String key, String value) {

		if (map.get(key) == null) {
			List<String> list = new ArrayList<String>();
			list.add(value);
			map.put(key, list);
			vector.add(key);
		} else {
			List<String> list = map.get(key);
			list.add(value);
		}
	}

	/**
	 * 返回文件全名（通过文件路径提取文件全名）。
	 * 
	 * @param path
	 * @return
	 */
	protected String handleFileFullName(String path) {

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
	protected String handleFileName(String fileName) {

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
	protected String handleFileType(String sourceFileName) {

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
	protected String[] handleFileTypeToLowerCase(String[] fileType) {

		if (fileType == null) {
			return null;
		} else {
			for (int i = 0; i < fileType.length; i++) {
				fileType[i] = fileType[i].toLowerCase(); // 转小写
			}
			return fileType;
		}
	}

	/**
	 * 返回请求参数值。
	 * 
	 * @param name
	 * @return
	 */
	public String getParameter(String name) {

		String str = null;

		if (map.get(name) != null) {
			str = map.get(name).get(0);
		}
		return str;
	}

	/**
	 * 返回请求参数的 Map 值。
	 * 
	 * @return
	 */
	public Map<String, String[]> getParameterMap() {

		Map<String, String[]> parameterMap = new HashMap<String, String[]>();

		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			parameterMap.put(entry.getKey(), getParameterValues(entry.getKey()));
		}

		return parameterMap;
	}

	/**
	 * 返回请求参数名（枚举）。
	 * 
	 * @return
	 */
	public Enumeration<String> getParameterNames() {

		return vector.elements();
	}

	/**
	 * 返回请求参数值（数组）。
	 * 
	 * @param name
	 * @return
	 */
	public String[] getParameterValues(String name) {

		String[] strs = null;
		List<String> list = map.get(name);

		if (list != null) {
			int size = list.size();
			strs = new String[size];
			for (int i = 0; i < size; i++) {
				strs[i] = list.get(i);
			}
		}

		return strs;
	}

	/**
	 * 返回 FileItemFactory 。
	 * 
	 * @return
	 */
	public FileItemFactory getFileItemFactory() {

		return fileItemFactory;
	}

	/**
	 * 设置 FileItemFactory 。
	 * 
	 * @param fileItemFactory
	 */
	public void setFileItemFactory(FileItemFactory fileItemFactory) {

		this.fileItemFactory = fileItemFactory;
	}

	/**
	 * 返回请求头字符编码。
	 * 
	 * @return
	 */
	public String getHeaderEncoding() {

		return headerEncoding;
	}

	/**
	 * 设置请求头字符编码。
	 * 
	 * @param headerEncoding
	 */
	public void setHeaderEncoding(String headerEncoding) {

		this.headerEncoding = headerEncoding;
	}

	/**
	 * 返回单个上传文件的最大尺寸限制。
	 * 
	 * @return
	 */
	public long getFileSizeMax() {

		return fileSizeMax;
	}

	/**
	 * 设置单个上传文件的最大尺寸限制。
	 * 
	 * @param fileSizeMax
	 */
	public void setFileSizeMax(long fileSizeMax) {

		this.fileSizeMax = fileSizeMax;
	}

	/**
	 * 返回整个请求上传数据的最大尺寸限制（含文本表单数据）。
	 * 
	 * @return
	 */
	public long getSizeMax() {

		return sizeMax;
	}

	/**
	 * 设置整个请求上传数据的最大尺寸限制（含文本表单数据）。
	 * 
	 * @param sizeMax
	 */
	public void setSizeMax(long sizeMax) {

		this.sizeMax = sizeMax;
	}

	/**
	 * 返回最大允许上传的文件数。
	 * 
	 * @return
	 */
	public int getFileMax() {

		return fileMax;
	}

	/**
	 * 设置最大允许上传的文件数。
	 * 
	 * @param fileMax
	 */
	public void setFileMax(int fileMax) {

		this.fileMax = fileMax;
	}

	/**
	 * 返回允许上传的文件类型。
	 * 
	 * @return
	 */
	public String[] getFileType() {

		return fileType;
	}

	/**
	 * 设置允许上传的文件类型，例如："jpg"、"rar"、""(无后缀文件)。
	 * 
	 * @param fileType
	 */
	public void setFileType(String... fileType) {

		this.fileType = fileType;
	}

	/**
	 * 返回文本表单字符编码。
	 * 
	 * @return
	 */
	public String getCharacterEncoding() {
		return characterEncoding;
	}

	/**
	 * 设置文本表单字符编码。
	 * 
	 * @param characterEncoding
	 */
	public void setCharacterEncoding(String characterEncoding) {

		this.characterEncoding = characterEncoding;
	}

	/**
	 * 返回文件名。
	 * 
	 * @return
	 */
	public String[] getNames() {

		return names;
	}

	/**
	 * 设置文件名。
	 * 
	 * @param names
	 */
	public void setNames(String... names) {

		this.names = names;
	}

	/**
	 * 返回命名接口的实例。
	 * 
	 * @return
	 */
	public Named getNamed() {

		return named;
	}

	/**
	 * 设置命名接口的实例。
	 * 
	 * @param named
	 */
	public void setNamed(Named named) {

		this.named = named;
	}

	/**
	 * 返回 FileInfo 的 Map 结果集。
	 * 
	 * @return
	 */
	public Map<String, FileInfo> getFileInfoMap() {

		return fileInfoMap;
	}

	/**
	 * 返回文件存储根路径。
	 * 
	 * @return
	 */
	public String getRootPath() {

		return rootPath;
	}

	/**
	 * 设置文件存储根路径。
	 * 
	 * @param rootPath
	 */
	public void setRootPath(String rootPath) {

		this.rootPath = rootPath;
	}

	/**
	 * 返回文件存储相对路径。
	 * 
	 * @return
	 */
	public String getRelativePath() {

		return relativePath;
	}

	/**
	 * 设置文件存储相对路径。
	 * 
	 * @param relativePath
	 */
	public void setRelativePath(String relativePath) {

		this.relativePath = relativePath;
	}

}
