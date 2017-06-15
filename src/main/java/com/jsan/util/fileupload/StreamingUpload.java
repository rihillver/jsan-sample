package com.jsan.util.fileupload;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class StreamingUpload extends AbstractUpload {

	public StreamingUpload(HttpServletRequest request) {
		super(request);
	}

	@Override
	protected int executeUpload(ServletFileUpload upload) throws Exception {

		FileItemIterator iterator = upload.getItemIterator(request);

		return handleFileItem(iterator);
	}

	protected int handleFileItem(FileItemIterator iterator) {

		return 0;
	}

}
