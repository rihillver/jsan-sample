package com.jsan.util.upload;

/**
 * 上传文件信息 Bean 。
 *
 */

public class FileInfo {

	private String sourceName;
	private String sourceFullName;
	private String sourcePath;

	private String destName;
	private String destFullName;
	private String destPath;

	private String rootPath;
	private String relativePath;

	private String fieldName;
	private String contentType;
	private String type;

	private long size;
	private byte[] bytes;

	private int width;
	private int height;

	/**
	 * 返回客户端源文件名（不含扩展名）
	 * 
	 * @return
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * 设置客户端源文件名（不含扩展名）
	 * 
	 * @param sourceName
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * 返回客户端源文件全名
	 * 
	 * @return
	 */
	public String getSourceFullName() {
		return sourceFullName;
	}

	/**
	 * 设置客户端源文件全名
	 * 
	 * @param sourceFullName
	 */
	public void setSourceFullName(String sourceFullName) {
		this.sourceFullName = sourceFullName;
	}

	/**
	 * 返回客户端源文件路径。
	 * 
	 * @return
	 */
	public String getSourcePath() {
		return sourcePath;
	}

	/**
	 * 设置客户端源文件路径。
	 * 
	 * @param sourcePath
	 */
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	/**
	 * 返回目标文件名（不含扩展名）。
	 * 
	 * @return
	 */
	public String getDestName() {
		return destName;
	}

	/**
	 * 设置目标文件名（不含扩展名）。
	 * 
	 * @param destName
	 */
	public void setDestName(String destName) {
		this.destName = destName;
	}

	/**
	 * 返回目标文件全名。
	 * 
	 * @return
	 */
	public String getDestFullName() {
		return destFullName;
	}

	/**
	 * 设置目标文件全名。
	 * 
	 * @param destFullName
	 */
	public void setDestFullName(String destFullName) {
		this.destFullName = destFullName;
	}

	/**
	 * 返回目标文件路径。
	 * 
	 * @return
	 */
	public String getDestPath() {
		return destPath;
	}

	/**
	 * 设置目标文件路径。
	 * 
	 * @param destPath
	 */
	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	/**
	 * 返回目标文件根路径。
	 * 
	 * @return
	 */
	public String getRootPath() {
		return rootPath;
	}

	/**
	 * 设置目标文件根路径。
	 * 
	 * @param rootPath
	 */
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	/**
	 * 返回目标文件相对路径。
	 * 
	 * @return
	 */
	public String getRelativePath() {
		return relativePath;
	}

	/**
	 * 设置目标文件相对路径。
	 * 
	 * @param relativePath
	 */
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	/**
	 * 返回文件表单字段名。
	 * 
	 * @return
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * 设置文件表单字段名。
	 * 
	 * @param fieldName
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * 返回文件内容类型。
	 * 
	 * @return
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * 设置文件内容类型。
	 * 
	 * @param contentType
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * 返回文件类型。
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置文件类型。
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 返回文件大小（Byte）。
	 * 
	 * @return
	 */
	public long getSize() {
		return size;
	}

	/**
	 * 设置文件大小（Byte）。
	 * 
	 * @param size
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * 返回文件（Byte 数组）。
	 * 
	 * @return
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * 设置文件（Byte 数组）。
	 * 
	 * @param bytes
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * 返回宽度（仅图片）。
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 设置宽度（仅图片）。
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 返回高度（仅图片）。
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 设置高度（仅图片）。
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "FileInfo [sourceName=" + sourceName + ", sourceFullName=" + sourceFullName + ", sourcePath=" + sourcePath + ", destName=" + destName + ", destFullName=" + destFullName + ", destPath=" + destPath + ", rootPath=" + rootPath + ", relativePath=" + relativePath + ", fieldName=" + fieldName + ", contentType=" + contentType + ", type=" + type + ", bytes=..., size=" + size + ", width=" + width + ", height=" + height + "]";
	}

}
