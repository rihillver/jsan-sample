package com.jsan.util.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

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

	public boolean isExtractDimension() {
		return extractDimension;
	}

	public void setExtractDimension(boolean extractDimension) {
		this.extractDimension = extractDimension;
	}

}
