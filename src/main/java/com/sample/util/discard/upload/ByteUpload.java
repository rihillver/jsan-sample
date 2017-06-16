package com.jsan.util.upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

/**
 * 文件上传简易封装工具类（Streaming 模式），将上传文件转换为 Byte 数组形式。
 *
 */

public class ByteUpload extends FileUpload {

	public ByteUpload(HttpServletRequest request) {

		super(request);
	}

	@Override
	protected int handleUpload(ServletFileUpload servletFileUpload) throws Exception {

		FileItemIterator fileItemIterator = servletFileUpload.getItemIterator(request);

		while (fileItemIterator.hasNext()) {

			FileItemStream fileItemStream = fileItemIterator.next();
			InputStream inputStream = fileItemStream.openStream();

			if (fileItemStream.isFormField()) {

				try {
					handleFormField(fileItemStream.getFieldName(), characterEncoding == null ? Streams.asString(inputStream) : Streams.asString(inputStream, characterEncoding));
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {

				if (fileMax > 0 && fileCount >= fileMax) { // 判断最多上传文件数量
					continue;
				}

				if (fileItemStream.getName().isEmpty()) { // 空文件表单
					continue;
				}

				String sourcePath = fileItemStream.getName(); // 早期 IE 浏览器可能含客户端绝对路径
				String sourceFullName = handleFileFullName(sourcePath);
				String sourceName = handleFileName(sourceFullName);
				String type = handleFileType(sourceFullName).toLowerCase(); // 小写

				if (fileTypeList != null && !fileTypeList.contains(type)) { // 判断文件类型
					continue;
				}

				String destFullName = sourceFullName;

				if (names != null || named != null) { // 重命名
					if (names == null) {
						destFullName = named.getName(fileItemStream.getFieldName(), destFullName, fileCount);
					} else {
						destFullName = names[fileCount];
					}
					if (type.length() > 0) {
						destFullName = destFullName + "." + type;
					}
				}

				String destName = handleFileName(destFullName);

				File file = new File(savePath, destFullName);

				ByteArrayOutputStream outputStream = null;

				try {

					outputStream = new ByteArrayOutputStream();

					byte[] buffer = new byte[1024 * 4];
					int len = 0;
					while ((len = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, len);
					}

					byte[] bytes = outputStream.toByteArray();

					long fileSize = bytes.length;

					FileInfo fileInfo = new FileInfo();
					fileInfo.setSourceName(sourceName);
					fileInfo.setSourceFullName(sourceFullName);
					fileInfo.setSourcePath(sourcePath);

					fileInfo.setDestName(destName);
					fileInfo.setDestFullName(destFullName);
					fileInfo.setDestPath(file.getPath());

					fileInfo.setRootPath(rootPath);
					fileInfo.setRelativePath(relativePath);

					fileInfo.setFieldName(fileItemStream.getFieldName());
					fileInfo.setContentType(fileItemStream.getContentType());
					fileInfo.setType(type);
					fileInfo.setSize(fileSize);

					fileInfo.setBytes(bytes);

					fileInfoMap.put(fileItemStream.getFieldName(), fileInfo);

					fileCount++;

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (outputStream != null) {
						try {
							outputStream.close();
						} catch (IOException e) {
							// e.printStackTrace();
						}
					}
					if (inputStream != null) {
						try {
							inputStream.close();
						} catch (IOException e) {
							// e.printStackTrace();
						}
					}
				}
			}
		}

		return fileCount;
	}

}
