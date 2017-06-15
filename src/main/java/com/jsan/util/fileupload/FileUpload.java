package com.jsan.util.fileupload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUpload extends AbstractUpload {

	public FileUpload(HttpServletRequest request) {
		super(request);
	}

	private FileItemFactory fileItemFactory;

	@Override
	protected int executeUpload(ServletFileUpload upload) throws Exception {
		
		if (fileItemFactory == null) { // 非 Streaming 方式必须使用 FileItemFactory
			fileItemFactory = new DiskFileItemFactory();
		}
		
		upload.setFileItemFactory(fileItemFactory);

		List<FileItem> list = upload.parseRequest(request); // parseRequest(request) 方法必须确保 fileItemFactory 不为 null
		
		if (list == null) {
			return 0;
		}

		return handleFileItem(list);
	}

	protected int handleFileItem(List<FileItem> list) throws Exception {

		int fileCount = 0;
		
		for (FileItem item : list) {

			if (item.isFormField()) {
				String value = characterEncoding == null ? item.getString() : item.getString(characterEncoding);
				handleFormField(item.getFieldName(), value);
			} else {

				if (fileMax > 0 && fileCount >= fileMax) { // 判断最多上传文件数量
					continue;
				}

				if (item.getName().isEmpty()) { // 空文件表单
					continue;
				}

				if (item.getSize() == 0 && !allowEmptyFile) { // 判断空文件是否允许上传
					continue;
				}
				
				

				fileCount++;
			}
		}

		return fileCount;
	}

}
