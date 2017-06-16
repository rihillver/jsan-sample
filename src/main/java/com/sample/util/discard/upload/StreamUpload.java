package com.sample.util.discard.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

/**
 * 文件上传简易封装工具类（Streaming 模式）。 <br>
 * <br>
 * 
 * 参数： <br>
 * imageType ： 图片类型，在此指定的图片类型将获取其宽度和高度（再度读取文件，低效的） <br>
 * <br>
 * 
 * 注意：Streaming 模式下无须设置 FileItemFactory。
 *
 */

public class StreamUpload extends FileUpload {

	protected String[] imageType;
	protected List<String> imageTypeList;

	public StreamUpload(HttpServletRequest request) {

		super(request);
	}

	@Override
	protected int handleUpload(ServletFileUpload servletFileUpload) throws Exception {

		if (imageType != null) {
			imageTypeList = Arrays.asList(handleFileTypeToLowerCase(imageType));
		}

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

				OutputStream outputStream = null;

				try {

					outputStream = new FileOutputStream(file);

					long fileSize = 0;

					int len = 0;
					byte[] buffer = new byte[1024 * 4];

					while ((len = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, len);
						fileSize += len;
					}

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

					if (imageTypeList != null && imageTypeList.contains(type)) { // 获取图片宽度和高度

						outputStream.close();
						inputStream.close();

						// 关闭流，图片文件保存后才能读取
						handleImage(file, fileInfo);
					}

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

	/**
	 * 获取图片宽度和高度。
	 * 
	 * @param file
	 * @param fileInfo
	 */
	protected void handleImage(File file, FileInfo fileInfo) {

		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			fileInfo.setWidth(bufferedImage.getWidth());
			fileInfo.setHeight(bufferedImage.getHeight());
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	/**
	 * 返回图片类型。
	 * 
	 * @return
	 */
	public String[] getImageType() {

		return imageType;
	}

	/**
	 * 设置图片类型。
	 * 
	 * @param imageType
	 */
	public void setImageType(String... imageType) {

		this.imageType = imageType;
	}

}
