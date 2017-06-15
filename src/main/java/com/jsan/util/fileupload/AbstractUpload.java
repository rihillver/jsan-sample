package com.jsan.util.fileupload;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

public abstract class AbstractUpload {

	protected long sizeMax = -1;
	protected long fileSizeMax = -1;
	protected String headerEncoding;

	// --------------------------------------------------

	protected HttpServletRequest request;
	protected String characterEncoding; // 字符编码，获取表单文本域时用
	protected int fileMax; // 最大上传文件数
	protected boolean allowEmptyFile; // 是否允许上传空文件
	protected Set<String> allowTypes; // 允许上传的文件类型，例如.jpg、.rar等，""表示无扩展名的文件
	protected String savePath; // 保存路径，默认Web根目录
	protected String saveDirectory; // 保存目录，相对于savePath下的目录
	protected String[] fileNames;

	// ----------------------

	protected String destPath;
	protected NamingAdapter namingAdapter;
	protected Map<String, String[]> parameterMap;

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
			parameterMap = new LinkedHashMap<>();
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
	
	public static void main(String[] args) {
		
		FileUpload fileUpload = new FileUpload(null);
		
		fileUpload.handleFormField("a","1");
		fileUpload.handleFormField("a","2");
		fileUpload.handleFormField("a","3");
		fileUpload.handleFormField("b","11");
		fileUpload.handleFormField("c","22");
		fileUpload.handleFormField("c","33");
		fileUpload.handleFormField("a","4");
		
		for(Map.Entry<String, String[]> entry:fileUpload.parameterMap.entrySet()){
			
			System.out.println(entry.getKey() +" - "+Arrays.toString(entry.getValue()));
			
		}
		
		
		
	}
	
}
