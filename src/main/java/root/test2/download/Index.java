package root.test2.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.jsan.mvc.annotation.ParamName;
import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;
import com.jsan.util.PathUtils;
import com.jsan.util.RandomUtils;

public class Index {

	@Render
	public void list() {

	}

	/**
	 * 文件下载测试。
	 * 
	 */
	@Render(Resolver.STREAM)
	public InputStream foo(HttpServletResponse response, @ParamName("file") String fileName)
			throws FileNotFoundException {

		String fileDir = "res/file/";
		File f = new File(PathUtils.getWebRootPath(), fileDir + fileName);
		InputStream inputStream = new FileInputStream(f);
		response.addHeader("Content-Disposition", "attachment; filename=" + fileName);

		return inputStream;
	}

	/**
	 * 文件下载测试（随机文件名）。
	 * 
	 */
	@Render(Resolver.STREAM)
	public InputStream bar(HttpServletResponse response) throws FileNotFoundException {

		String fileName = RandomUtils.getWordChar(12) + ".rar";
		String filePath = "res/file/a.rar";
		File f = new File(PathUtils.getWebRootPath(), filePath);
		InputStream inputStream = new FileInputStream(f);
		response.addHeader("Content-Disposition", "attachment; filename=" + fileName);

		return inputStream;
	}

}
