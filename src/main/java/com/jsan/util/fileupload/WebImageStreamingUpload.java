package com.jsan.util.fileupload;

import javax.servlet.http.HttpServletRequest;

public class WebImageUpload extends StreamingUpload {

	protected boolean extractDimension; // 是否提取图片尺寸

	public WebImageUpload(HttpServletRequest request) {
		super(request);
	}

}
