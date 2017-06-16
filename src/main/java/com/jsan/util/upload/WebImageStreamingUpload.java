package com.jsan.util.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

/**
 * Web 图片上传(默认允许格式 "jpg", "jpeg", "png", "gif", "bmp")。
 *
 */

public class WebImageStreamingUpload extends FileStreamingUpload {

	{
		setAllowFileTypes("jpg", "jpeg", "png", "gif", "bmp");
	}

	protected boolean extractDimension; // 是否提取图片尺寸

	public WebImageStreamingUpload(HttpServletRequest request) {
		super(request);

	}

	@Override
	protected void handleItemStream(InputStream in, File file, FileInfo info) throws Exception {

		if (isExtractDimension()) {
			super.handleItemStream(in, file, info);
		} else {

			BufferedImage bi = ImageIO.read(in);

			info.setWidth(bi.getWidth());
			info.setHeight(bi.getHeight());

			// info.getType()为jpg时这个格式能否，等待测试
			ImageIO.write(bi, info.getType(), file);// 将图片保存为文件

			info.setSize(file.length());
		}

	}

	/**
	 * 返回是否提取图片尺寸。
	 * 
	 * @return
	 */
	public boolean isExtractDimension() {
		return extractDimension;
	}

	/**
	 * 设置是否提取图片尺寸。
	 * 
	 * @param extractDimension
	 */
	public void setExtractDimension(boolean extractDimension) {
		this.extractDimension = extractDimension;
	}

}
