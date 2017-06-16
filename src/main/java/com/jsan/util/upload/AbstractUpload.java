package com.jsan.util.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

public abstract class AbstractUpload {

	protected long sizeMax = -1;
	protected long fileSizeMax = -1;
	protected String headerEncoding;

	// --------------------------------------------------

	protected int fileMax = -1; // 最大上传文件数
	protected String characterEncoding; // 字符编码，获取表单文本域时用
	protected boolean allowEmptyFile; // 是否允许上传空文件
	protected Set<String> allowFileTypes; // 允许上传的文件类型，例如.jpg、.rar等，""表示无扩展名的文件
	protected boolean fileExtToUppercase; // 文件扩展名是否大写
	protected String savePath; // 保存路径，默认Web根目录
	protected String saveDirectory; // 保存目录，相对于savePath下的目录
	protected String[] fileNames;
	protected NamingAdapter namingAdapter;

	// ----------------------
	
	protected HttpServletRequest request;

	protected String destPath;
	protected List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
	protected Map<String, String[]> parameterMap = new LinkedHashMap<String, String[]>();

	// ----------------------

	public AbstractUpload(HttpServletRequest request) {

		this.request = request;
	}

	public int doUpload() throws Exception {

		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new Exception("the request doesn't contains multipart content");
		}

		ServletFileUpload upload = new ServletFileUpload();

		if (fileSizeMax > -1) {
			upload.setFileSizeMax(fileSizeMax); // 未设置时默认值为 -1，即不限制大小
		}
		if (sizeMax > -1) {
			upload.setSizeMax(sizeMax); // 未设置时默认值为 -1，即不限制大小
		}
		if (headerEncoding != null) {
			upload.setHeaderEncoding(headerEncoding);
		}

		if (savePath == null) {
			savePath = UploadUtils.getWebRootPath(); // web根目录
		}

		File destFile = saveDirectory == null ? new File(savePath) : new File(savePath, saveDirectory);
		if (!destFile.exists()) { // 文件目录若不存在则创建
			destFile.mkdirs();
		}

		destPath = destFile.getCanonicalPath();

		return executeUpload(upload);
	}

	protected abstract int executeUpload(ServletFileUpload upload) throws Exception;

	protected void handleFormField(String key, String value) {

		if (parameterMap == null) {
			parameterMap = new LinkedHashMap<String,String[]>();
		}

		if (parameterMap.containsKey(key)) {
			String[] values = parameterMap.get(key);
			int len = values.length;
			String[] newValues = new String[len + 1];
			System.arraycopy(values, 0, newValues, 0, len);
			newValues[len] = value;
			parameterMap.put(key, newValues);
		} else {
			String[] values = new String[1];
			values[0] = value;
			parameterMap.put(key, values);
		}
	}

	protected String handleFileName(String fieldName, String primitiveNameWithoutExt, int fileCount) {

		if (fileNames != null || namingAdapter != null) { // 重命名
			if (fileNames != null) {
				return fileNames[fileCount];
			} else {
				return namingAdapter.getName(fieldName, primitiveNameWithoutExt, fileCount);
			}
		}

		return primitiveNameWithoutExt;
	}

	public static void main(String[] args) {

		FileUpload fileUpload = new FileUpload(null);

		fileUpload.handleFormField("a", "1");
		fileUpload.handleFormField("a", "2");
		fileUpload.handleFormField("a", "3");
		fileUpload.handleFormField("b", "11");
		fileUpload.handleFormField("c", "22");
		fileUpload.handleFormField("c", "33");
		fileUpload.handleFormField("a", "4");

		for (Map.Entry<String, String[]> entry : fileUpload.parameterMap.entrySet()) {

			System.out.println(entry.getKey() + " - " + Arrays.toString(entry.getValue()));

		}

	}
	
	//------------------------------------

	public long getSizeMax() {
		return sizeMax;
	}

	public void setSizeMax(long sizeMax) {
		this.sizeMax = sizeMax;
	}

	public long getFileSizeMax() {
		return fileSizeMax;
	}

	public void setFileSizeMax(long fileSizeMax) {
		this.fileSizeMax = fileSizeMax;
	}

	public String getHeaderEncoding() {
		return headerEncoding;
	}

	public void setHeaderEncoding(String headerEncoding) {
		this.headerEncoding = headerEncoding;
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public int getFileMax() {
		return fileMax;
	}

	public void setFileMax(int fileMax) {
		this.fileMax = fileMax;
	}

	public boolean isAllowEmptyFile() {
		return allowEmptyFile;
	}

	public void setAllowEmptyFile(boolean allowEmptyFile) {
		this.allowEmptyFile = allowEmptyFile;
	}

	public Set<String> getAllowFileTypes() {
		return allowFileTypes;
	}

	/**
	 * 设置允许文件类型。
	 * <p>
	 * 内部将文件类型一律转换成小写。
	 * 
	 * @param allowFileTypes
	 */
	public void setAllowFileTypes(Set<String> allowFileTypes) {

		this.allowFileTypes = new HashSet<String>();
		for (String fileType : allowFileTypes) {
			this.allowFileTypes.add(fileType.toLowerCase());
		}
	}

	public boolean isFileExtToUppercase() {
		return fileExtToUppercase;
	}

	public void setFileExtToUppercase(boolean fileExtToUppercase) {
		this.fileExtToUppercase = fileExtToUppercase;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSaveDirectory() {
		return saveDirectory;
	}

	public void setSaveDirectory(String saveDirectory) {
		this.saveDirectory = saveDirectory;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	public NamingAdapter getNamingAdapter() {
		return namingAdapter;
	}

	public void setNamingAdapter(NamingAdapter namingAdapter) {
		this.namingAdapter = namingAdapter;
	}
	
	
	/**
	 * 设置允许文件类型（数组形式）。
	 * <p>
	 * 内部将文件类型一律转换成小写。
	 * 
	 * @param allowFileTypes
	 */
	public void setAllowFileTypes(String... allowFileTypes) {

		this.allowFileTypes = new HashSet<String>();
		for (String type : allowFileTypes) {
			this.allowFileTypes.add(type.toLowerCase());
		}
	}

	/**
	 * 返回 request。
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	
	/**
	 * 返回请求参数值。
	 * 
	 * @param name
	 * @return
	 */
	public String getParameter(String name) {

		String[] values = parameterMap.get(name);

		if (values != null) {
			return values[0];
		} else {
			return null;
		}
	}

	/**
	 * 返回请求参数值（数组）。
	 * 
	 * @param name
	 * @return
	 */
	public String[] getParameterValues(String name) {

		return parameterMap.get(name);
	}

	/**
	 * 返回请求参数的 Map 值。
	 * 
	 * @return
	 */
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	/**
	 * 返回 FileInfo 的 List。
	 * 
	 * @return
	 */
	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}
	
	
	
}
