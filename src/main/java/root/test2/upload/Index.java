package root.test2.upload;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;

import com.jsan.mvc.View;
import com.jsan.mvc.annotation.Get;
import com.jsan.mvc.annotation.Post;
import com.jsan.mvc.annotation.Render;
import com.jsan.util.PathUtils;
import com.jsan.util.upload.ByteStreamingUpload;
import com.jsan.util.upload.FileStreamingUpload;
import com.jsan.util.upload.FileUpload;
import com.jsan.util.upload.NamingAdapter;
import com.jsan.util.upload.UUIDNamingAdapter;
import com.jsan.util.upload.UploadUtils;
import com.jsan.util.upload.WebImageStreamingUpload;
import com.jsan.util.upload.WebImageStreamingUpload.WebImageIOException;

public class Index {

	@Render
	public void list() {

	}

	@Get
	@Render
	public void foo(View view) {

		view.addValue("FileUpload 测试：最多允许上传3个文件，单个文件最大5M，整个请求最大6M，允许上传的文件类型：docx、pdf、无后缀的文件名，不允许上传空文件（大小为0的文件）");
	}

	/**
	 * FileUpload 测试。
	 * <p>
	 * 如果不设置 FileItemFactory，那么默认内存缓冲区大小为 10240（Byte） ，临时文件夹在 "java.io.tmpdir"。
	 * 
	 * @param view
	 * @param request
	 * @throws Exception
	 */
	@Post
	@Render(url = "result")
	public void foo(View view, HttpServletRequest request) throws Exception {

		FileItemFactory factory = UploadUtils.getFileItemFactory(100 * 1024,
				PathUtils.getWebRootPath() + "/upload/temp");

		FileUpload upload = new FileUpload(request);
		upload.setFileItemFactory(factory);
		upload.setCharacterEncoding("utf-8");
		upload.setSaveDirectory("upload/FileUpload");
		upload.setFileSizeMax(1024 * 1024 * 5);
		upload.setSizeMax(1024 * 1024 * 6);
		upload.setFileMax(3);
		// upload.setAllowEmptyFile(true);
		upload.setAllowFileTypes("docx", "pdf", "");
		// upload.setFileExtToUppercase(true);
		// upload.setFileNames("A","B","C","D");
		upload.setNamingAdapter(new NamingAdapter() {
			@Override
			public String getName(String fieldName, String fileName, int number) {
				return String.valueOf(System.currentTimeMillis()) + "-" + number;
			}
		});

		int i = 0;
		try {
			i = upload.executeUpload();
		} catch (FileSizeLimitExceededException e) {
			System.out.println("==字段名：" + e.getFieldName());
			System.out.println("==文件名：" + e.getFileName());
			System.out.println("==超出单个上传文件的大小限制");
		} catch (SizeLimitExceededException e1) {
			System.out.println("==超出整个请求上传数据的大小限制");
		}

		view.add("success", i);
		view.add("fileInfoList", upload.getFileInfoList());
		view.add("id", upload.getParameter("id"));
		view.add("name", upload.getParameter("name"));
		view.add("sex", upload.getParameter("sex"));
		view.add("address", Arrays.toString(upload.getParameterValues("address")));
	}

	@Get
	@Render(url = "foo")
	public void bar(View view) {

		view.addValue("FileStreamingUpload 测试：不限上传文件数，整个请求最大6M，允许上传的文件类型：docx、pdf、gif，Streaming模式下允许上传空文件（大小为0的文件）");
	}

	/**
	 * FileStreamingUpload 测试。
	 * <p>
	 * 由于 Streaming 模式下无法对空文件进行判断，因此默认是否允许上传空文件的。
	 * 
	 * @param view
	 * @param request
	 * @throws Exception
	 */
	@Post
	@Render(url = "result")
	public void bar(View view, HttpServletRequest request) throws Exception {

		FileStreamingUpload upload = new FileStreamingUpload(request);
		upload.setCharacterEncoding("utf-8");
		upload.setSaveDirectory("upload/FileStreamingUpload");
		// upload.setFileSizeMax(1024 * 1024 * 5); // 已无意义，因为不能限制单个文件大小
		upload.setSizeMax(1024 * 1024 * 6);

		upload.setAllowFileTypes("docx", "pdf", "gif");
		upload.setNamingAdapter(new UUIDNamingAdapter(true));

		int i = 0;
		try {
			i = upload.executeUpload();
		} catch (FileSizeLimitExceededException e) {
			System.out.println("==字段名：" + e.getFieldName());
			System.out.println("==文件名：" + e.getFileName());
			System.out.println("==超出单个上传文件的大小限制");
		} catch (SizeLimitExceededException e1) {
			System.out.println("==超出整个请求上传数据的大小限制");
		}

		view.add("success", i);
		view.add("fileInfoList", upload.getFileInfoList());
		view.add("id", upload.getParameter("id"));
		view.add("name", upload.getParameter("name"));
		view.add("sex", upload.getParameter("sex"));
		view.add("address", Arrays.toString(upload.getParameterValues("address")));
	}

	@Get
	@Render(url = "foo")
	public void baz(View view) {

		view.addValue(
				"ByteStreamingUpload 测试：最多允许上传4个文件，整个请求数据最大512K，允许上传的文件类型：txt、无后缀的文件名，Streaming模式下允许上传空文件（大小为0的文件）");
	}

	/**
	 * ByteStreamingUpload 测试。
	 * <p>
	 * 由于 Streaming 模式下无法对空文件进行判断，因此默认是否允许上传空文件的。
	 * <p>
	 * 由于 ByteStreamingUpload 将上传文件存入内存，因此适用于较小的文件，大文件则不可取。
	 * <p>
	 * 通过 FileInfo.getBytes() 获取文件的字节数组进行加工处理。
	 * 
	 * @param view
	 * @param request
	 * @throws Exception
	 */
	@Post
	@Render(url = "baz-result")
	public void baz(View view, HttpServletRequest request) throws Exception {

		ByteStreamingUpload upload = new ByteStreamingUpload(request);
		upload.setCharacterEncoding("utf-8");
		// upload.setSaveDirectory("upload/ByteStreamingUpload"); //已无意义，因为放在内存中
		// upload.setFileSizeMax(1024 * 512); // 已无意义，因为不能限制单个文件大小
		upload.setSizeMax(1024 * 512); // 限制整个请求512K以内
		upload.setFileMax(4);

		upload.setAllowFileTypes("txt", "");
		upload.setFileExtToUppercase(true); // 文件后缀大写
		upload.setFileNames("A", "B", "C", "D");
		// upload.setNamingAdapter(new UUIDNamingAdapter(true)); // 意义不大

		int i = 0;
		try {
			i = upload.executeUpload();
		} catch (SizeLimitExceededException e1) {
			System.out.println("==超出整个请求上传数据的大小限制");
		}

		view.add("success", i);
		view.add("fileInfoList", upload.getFileInfoList());
		view.add("id", upload.getParameter("id"));
		view.add("name", upload.getParameter("name"));
		view.add("sex", upload.getParameter("sex"));
		view.add("address", Arrays.toString(upload.getParameterValues("address")));
	}

	@Get
	@Render(url = "foo")
	public void qux(View view) {

		view.addValue(
				"WebImageStreamingUpload 测试：最多允许上传4个文件，整个请求数据最大10M，允许上传的文件类型： jpg、jpeg、png、gif、bmp，Streaming模式下允许上传空文件（大小为0的文件）");
	}

	/**
	 * WebImageStreamingUpload 测试。
	 * <p>
	 * 由于 Streaming 模式下无法对空文件进行判断，因此默认是否允许上传空文件的。
	 * 
	 * @param view
	 * @param request
	 * @throws Exception
	 */
	@Post
	@Render(url = "result")
	public void qux(View view, HttpServletRequest request) throws Exception {

		WebImageStreamingUpload upload = new WebImageStreamingUpload(request);
		upload.setCharacterEncoding("utf-8");
		upload.setSaveDirectory("upload/WebImageStreamingUpload");
		// upload.setFileSizeMax(1024 * 1024 * 2); // 已无意义，因为不能限制单个文件大小
		upload.setSizeMax(1024 * 1024 * 10); // 限制整个请求10M以内
		upload.setFileMax(4);

		// 可指定文件类型，此时以指定的文件类型为准，忽略默认的 "jpg", "jpeg", "png", "gif", "bmp" 这些格式
		// upload.setAllowFileTypes("jpg", "jpeg", "png");

		upload.setFileExtToUppercase(true); // 文件扩展名设置为大写

		// upload.setExtractDimension(true);//extractDimension为true时对所有文件均提取图片尺寸
		upload.setExtractDimensionTypes("jpg", "jpeg", "png"); // 仅对指定的格式提取图片尺寸，前提是extractDimension为false

		upload.setFileNames("A", "B", "C", "D"); // 优先级别高于upload.setNamingAdapter()
		// upload.setNamingAdapter(new SequenceCodeNamingAdapter());

		int i = 0;
		
		try {
			i = upload.executeUpload();
		} catch (SizeLimitExceededException e1) {
			System.out.println("==超出整个请求上传数据的大小限制");
		} catch (WebImageIOException e2) {
			System.out.println("==字段名：" + e2.getFieldName());
			System.out.println("==文件名：" + e2.getFileName());
			System.out.println("==非规范的图片文件");
		}

		view.add("success", i);
		view.add("fileInfoList", upload.getFileInfoList());
		view.add("id", upload.getParameter("id"));
		view.add("name", upload.getParameter("name"));
		view.add("sex", upload.getParameter("sex"));
		view.add("address", Arrays.toString(upload.getParameterValues("address")));
	}
}
