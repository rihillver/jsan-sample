package root.test2.upload;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.jsan.mvc.View;
import com.jsan.mvc.annotation.Get;
import com.jsan.mvc.annotation.Post;
import com.jsan.mvc.annotation.Render;
import com.jsan.util.upload.FileUpload;

public class Index {

	@Render
	public void list() {

	}

	@Get
	@Render
	public void foo() {

	}

	@Post
	@Render(url = "foo-result")
	public void foo(View view, HttpServletRequest request) throws Exception {

		FileUpload upload = new FileUpload(request);
		upload.setCharacterEncoding("utf-8");
		upload.executeUpload();
		view.add("fileInfo", upload.getFileInfoList());
		view.add("id", upload.getParameter("id"));
		view.add("name", upload.getParameter("name"));
		view.add("sex", upload.getParameter("sex"));
		view.add("address", Arrays.toString(upload.getParameterValues("address")));
	}
}
