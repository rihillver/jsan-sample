package com.jsan.util.fileupload;

import javax.servlet.http.HttpServletRequest;

public class BytesUpload extends StreamingUpload {

	public BytesUpload(HttpServletRequest request) {
		super(request);
	}

}
