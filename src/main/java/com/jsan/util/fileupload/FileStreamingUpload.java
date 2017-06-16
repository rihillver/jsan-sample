package com.jsan.util.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

public class FileStreamingUpload extends AbstractStreamingUpload {

	public FileStreamingUpload(HttpServletRequest request) {
		super(request);
	}

	@Override
	protected void handleItemStream(InputStream in, File file, FileInfo info) throws Exception {

		OutputStream out = null;

		try {

			out = new FileOutputStream(file);

			long fileSize = 0;

			byte[] buf = new byte[1024 * 4];
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
				fileSize += len;
			}

			info.setSize(fileSize);

		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
