package com.jsan.util.upload;

import javax.servlet.http.HttpServletRequest;

/**
 * WEB 图片上传简易封装工具类（Streaming 模式）。 <br>
 * <br>
 * 
 * 图片类型："jpg", "jpeg", "png", "gif", "bmp"
 *
 */

public class WebImageUpload extends StreamUpload {

	protected final static String[] WEB_IMAGE_TYPE = { "jpg", "jpeg", "png", "gif", "bmp" };

	public WebImageUpload(HttpServletRequest request) {

		super(request);
		setFileType(WEB_IMAGE_TYPE);
		setImageType(WEB_IMAGE_TYPE);
	}

}
