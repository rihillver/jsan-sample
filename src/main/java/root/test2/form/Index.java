package root.test2.form;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsan.convert.AbstractRecursiveableConverter;
import com.jsan.convert.JsonConvertService;
import com.jsan.convert.SplitConvertService;
import com.jsan.convert.SplitTrimConvertService;
import com.jsan.convert.annotation.ConvertServiceRegister;
import com.jsan.convert.annotation.ConverterRegister;
import com.jsan.convert.annotation.DateTimePattern;
import com.jsan.convert.annotation.NumberPattern;
import com.jsan.convert.support.split.trim.AbstractMapSplitTrimConverter;
import com.jsan.convert.support.split.trim.ListSplitTrimConverter;
import com.jsan.mvc.MappingInfo;
import com.jsan.mvc.MvcConfig;
import com.jsan.mvc.View;
import com.jsan.mvc.annotation.FormConvert;
import com.jsan.mvc.annotation.Get;
import com.jsan.mvc.annotation.JsonConvert;
import com.jsan.mvc.annotation.MultiValue;
import com.jsan.mvc.annotation.ParamName;
import com.jsan.mvc.annotation.Post;
import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;
import com.jsan.util.PathUtils;
import com.jsan.util.RandomUtils;
import com.jsan.util.StreamUtils;
import com.jsan.util.crypto.Base64Utils;
import com.sample.www.custom.GifResolver;
import com.sample.www.custom.JpegResolver;

public class Index {

	@Render
	public void list() {

	}

	@Get
	@Render
	public void foo() {

	}

	/**
	 * 常用表单转换测试。
	 * 
	 */
	@Post
	@Render(url = "foo-result")
	@ConvertServiceRegister(SplitConvertService.class)
	public void foo(View view, int id, String name, Boolean sex, Double height, @MultiValue String[] address,
			@MultiValue List<String> interest, Set<String> like, @ParamName("like") String[] like0,
			@ConvertServiceRegister(SplitTrimConvertService.class) @ParamName("like") List<String> like1,
			@ConvertServiceRegister(SplitTrimConvertService.class) List<Integer> score, Map<String, String> family,
			@ConvertServiceRegister(SplitTrimConvertService.class) @ParamName("family") Map<String, String> family0,
			@ConvertServiceRegister(JsonConvertService.class) @JsonConvert List<Double> jsonArray,
			@ConvertServiceRegister(JsonConvertService.class) @JsonConvert User jsonObject) {

		view.add("id", id);
		view.add("name", name);
		view.add("sex", sex);
		view.add("height", height);
		view.add("address", Arrays.toString(address));
		view.add("interest", interest);
		view.add("like", like);
		view.add("like0", Arrays.toString(like0));
		view.add("like1", like1);
		view.add("score", score);
		view.add("family", family);
		view.add("family0", family0);
		view.add("jsonArray", jsonArray);
		view.add("jsonObject", jsonObject);

	}

	@Get
	@Render
	public void bar() {

	}

	/**
	 * 自定义转换器测试。
	 * <p>
	 * Custom_IntegerConverter、Custom_UserConverter
	 * 
	 */
	@Post
	@Render(url = "bar-result")
	public void bar(View view, @ConverterRegister(Custom_IntegerConverter.class) Integer number,
			@ConvertServiceRegister(SplitTrimConvertService.class) List<String> like,
			@ConvertServiceRegister(SplitTrimConvertService.class) @ConverterRegister(Custom_ListConverter.class) @ParamName("like") List<String> like0,
			@ConvertServiceRegister(SplitTrimConvertService.class) HashMap<String, String> family,
			@ConvertServiceRegister(SplitTrimConvertService.class) @ConverterRegister(Custom_HashtableConverter.class) @ParamName("family") Hashtable<String, String> family0,
			@ConverterRegister(Custom_UserConverter.class) User user) {

		view.add("number", number);
		view.add("like", like);
		view.add("like0", like0);
		view.add("family", family);
		view.add("family0", family0);
		view.add("user", user);

		System.out.println("like.length=" + like.size());
		System.out.println("like0.length=" + like0.size());
	}

	public static class Custom_IntegerConverter extends AbstractRecursiveableConverter {
		@Override
		public Integer convert(Object source, Type type) {
			return 888888;
		}
	}

	@Get
	@Render
	public void baz() {

	}

	/**
	 * 日期时间、数字格式测试。
	 * <p>
	 * 缺省日期时间格式：yyyy-MM-dd HH:mm:ss
	 * 
	 */
	@Post
	@Render(url = "baz-result")
	@NumberPattern("#,###.00")
	public void baz(View view, Date date, @DateTimePattern("yyyy-MM-dd") Date date0,
			@DateTimePattern("yyyy-MM-dd") @ParamName("date0") java.sql.Date sqlDate,
			@DateTimePattern("yyyy年M月d日") Date date1,
			@DateTimePattern("yyyy年M月d日") @ParamName("date1") Timestamp sqlTimestamp,
			@DateTimePattern("yyyy年MM月dd日 HH时mm分ss秒") Date date2,
			@DateTimePattern("yyyy年MM月dd日 HH时mm分ss秒") @ParamName("date2") Time sqlTime,
			@DateTimePattern("yyyy#MM#dd") Date date3, @DateTimePattern("yyyy-MM-dd HH:mm:ss.S") Calendar calendar,
			int number, double number1, Double number2, float number3, Float number4,
			@NumberPattern("0.00%") double number5) {

		view.add("date", date);
		view.add("date0", date0);
		view.add("sqlDate", sqlDate);
		view.add("date1", date1);
		view.add("sqlTimestamp", sqlTimestamp);
		view.add("date2", date2);
		view.add("sqlTime", sqlTime);
		view.add("date3", date3);
		view.add("calendar", calendar);

		view.add("number", number);
		view.add("number1", number1);
		view.add("number2", number2);
		view.add("number3", number3);
		view.add("number4", number4);
		view.add("number5", number5);
	}

	/**
	 * 客户端转发测试1、2。
	 * 
	 */
	@Render(value = Resolver.REDIRECT, url = "qur")
	public void qux() {

	}

	@Render(value = Resolver.REDIRECT, url = "quz")
	public void qur() {

		System.out.println(getClass() + ".qur() has been running.");
	}

	@Render(Resolver.HTML)
	public String quz() {

		return getClass() + ".quz()";
	}

	/**
	 * 客户端转发测试3、4。
	 * 
	 */
	@Render(value = Resolver.REDIRECT, url = "test.html")
	public void dux() {

	}

	/**
	 * 客户端转发测试5、6。
	 * 
	 */
	@Render(value = Resolver.REDIRECT, url = "/about")
	public void fux() {

	}

	/**
	 * 客户端转发测试7。
	 * 
	 */
	@Render(value = Resolver.REDIRECT, url = "/index.html")
	public void gux() {

	}

	/**
	 * 客户端转发测试8。
	 * 
	 */
	@Render(value = Resolver.REDIRECT, url = "/")
	public void hux() {

	}

	/**
	 * 客户端转发测试9。
	 * 
	 */
	@Render(value = Resolver.JUMP, url = "https://www.baidu.com")
	public void kux() {

	}

	/**
	 * 客户端转发测试10。
	 * 
	 */
	@Render(value = Resolver.JUMP, url = "http://tuijian.hao123.com/finance")
	public void lux() {

	}

	/**
	 * 图片输出测试1-Byte。
	 * 
	 */
	@Render(Resolver.BYTE)
	public byte[] one(View view) throws IOException {

		File file = null;

		int i = RandomUtils.getInt(1, 5);

		switch (i) {
		case 1:
			file = getFile("jpg");
			view.setContentType("image/jpeg");
			break;
		case 2:
			file = getFile("png");
			view.setContentType("image/png");
			break;
		case 3:
			file = getFile("gif");
			view.setContentType("image/gif");
			break;
		case 4:
			file = getFile("bmp");
			view.setContentType("image/bmp");
			break;

		default:
			view.setContentType("image/gif");
			return getGifByBase64();
		}

		byte[] bytes = StreamUtils.readFileToByte(file);
		return bytes;
	}

	/**
	 * 图片输出测试2-Steam。
	 * 
	 */
	@Render(Resolver.STREAM)
	public InputStream two(View view) throws FileNotFoundException {

		File file = null;

		int i = RandomUtils.getInt(1, 5);

		switch (i) {
		case 1:
			file = getFile("jpg");
			view.setContentType("image/jpeg");
			break;
		case 2:
			file = getFile("png");
			view.setContentType("image/png");
			break;
		case 3:
			file = getFile("gif");
			view.setContentType("image/gif");
			break;
		case 4:
			file = getFile("bmp");
			view.setContentType("image/bmp");
			break;

		default:
			view.setContentType("image/gif");
			return new ByteArrayInputStream(getGifByBase64());
		}

		return new FileInputStream(file);
	}

	/**
	 * 自定义视图解析器测试1。
	 * 
	 */
	@Render(JpegResolver.JPEG)
	public byte[] three() throws IOException {

		byte[] bytes = StreamUtils.readFileToByte(getFile("jpg"));
		return bytes;
	}

	/**
	 * 自定义视图解析器测试2。
	 * 
	 */
	@Render(GifResolver.GIF)
	public byte[] four() {

		return getGifByBase64();
	}

	/**
	 * 自定义视图解析器测试3。
	 * 
	 */
	@Render(resolver = Custom_PngResolver.class)
	public String five() {

		return "/res/file/a.png";
	}

	/**
	 * 自定义视图解析器测试4、5、6、7。
	 * 
	 */
	@Render(Resolver.HTML)
	public String six(View view, String url) {

		if (url == null) {
			url = "custom resolver test.";
		}

		if (url.endsWith(".png")) {
			view.setResolver(custom_PngResolver);
		}

		return url;
	}

	@Get
	@Render(url = "map-bean")
	public Object toMap() {

		return null;
	}

	/**
	 * 表单转Map测试。
	 * 
	 */
	@Post
	@Render(Resolver.HTML)
	public Object toMap(@FormConvert @MultiValue("address") Map<String, Object> map,
			@FormConvert(params = { "id", "name", "age" }) HashMap<String, String> map1) {

		if (map.get("address").getClass().isArray()) {
			System.out.println("address length=" + ((String[]) map.get("address")).length);
		}
		System.out.println("school.address type=" + map.get("school.address").getClass());

		return "map = " + map + "<hr>map1 = " + map1;
	}

	@Get
	@Render(url = "map-bean")
	public Object toBean() {

		return null;
	}

	/**
	 * 表单转Bean测试。
	 * 
	 */
	@Post
	@Render(Resolver.HTML)
	public Object toBean(@FormConvert(true) @MultiValue("address") User user,
			@FormConvert(params = { "name", "age", "address" }) @MultiValue("address") User user1,
			@FormConvert(params = { "number", "shortName" }) School school, @ParamName("school.name") String sName,
			@ConvertServiceRegister(SplitTrimConvertService.class) @ParamName("school.address") String[] sAddress,
			@FormConvert @MultiValue({ "address" }) School school1) {

		if (user != null && school != null) {
			school.setName(sName);
			school.setAddress(sAddress);
			System.out.println("school address length=" + sAddress.length);
			user.setSchool(school);
		}
		if (user1 != null && school1 != null) {
			school1.setName(sName);
			System.out.println("school1 address length=" + school1.getAddress().length);
			user1.setSchool(school1);
		}
		return "user(" + user.getClass() + ") = " + user + "<hr>user1(" + user1.getClass() + ") = " + user1;
	}

	private static final Custom_PngResolver custom_PngResolver = new Custom_PngResolver();

	public static class Custom_PngResolver implements Resolver {
		@Override
		public void execute(View view, MvcConfig mvcConfig, MappingInfo mappingInfo, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			response.sendRedirect((String) view.getValue());
		}
	}

	private File getFile(String fileType) {

		return new File(PathUtils.getWebRootFile(), "/res/file/a." + fileType);
	}

	private byte[] getGifByBase64() {

		String str = "R0lGODlhrgCQALMAAIPAM8HemvP57ZjLVdzsxo3GQ6vUdf///+rz3cvkqrbbh6DPZM/nsIi7RJnMRPr89iH5BAAHAP8ALAAAAACuAJAAAAT/sMhJq7046827/2AojmRpnmiqrmzrvnAsz3Rt33iu73zv/8CgcEgsGo/IpHLJbDqf0Kh0Sq1ar9isdsvter/gsHhMLpvP6LR6zW673/C4fE6v2+/4vH7P7/v/gIGCg4SFhoeIiYqLjI2Oj5CRNQAAF5SVki2VCwoJDASgBAkKCwOUmSoDAQQPB66vsAgJBgWYqCIAAwkIsL2+DwQLtyMKvL7HvQ8JpsMeAcjQvggLts0XAwTR2rACBtXWFNnb4wfd1acVl48M5O3T6pwBngwMowYDtd+FAAGt7eQEBhjY5e+YAAIK8CVaIODfPwQNySEIwMwQO4cY/wkIsM9AxIwg/8cF09fnYsiT2t4ByvURpUuDtP4AUPCyJjIB1P6YtMnTFYKKfMT1HEqgD8uhSA8oIDknF4OCSW3+ZAoHgAFjUYcGoOpmZsusPKfaUQAVbM+ldciajcqADgCGa6MioDMAa1ykQN/svDvUW1UDZfnyRAtHqOChCbiWsRr4cM3EhR0n3foGruShhNs8u9xXMRnDnGtyfPM1tMsHbT2DWdDYNMqAqrtYdY20qBp+tJFCRoM7d88Hfs8ASOCbqEIzvYsPjp0lufKatpHTfG7zQV4xjKnbDG6mtHaQlM/Y/R5yt5nN5E8yYI7lbev05NanAQ3/n/nF0+tjzGymrn6MOfGW3+J/48zFBn0EIjPaGh4lmFKAtw3ooC8LtkHchNJct8ZeE5ojB4cviRLXAwrUEYB3IbVVQDHtPIBiNALwF4dV44EU4wRvJfDiKwQYYACC0PTIHhqqvLcNahBOoAoBAhTkIgIM3HNKADVKQ9GQaixApUMCRFmLBgIpIKYBwliQiwIMIPDAmmwiQMCVf3DCpDYHBVBmB5eog0ElYYqpwD35BMKnAQF8EgoBDARAZqAv5JmnIXqaiSU4lFZq6aWYZqrpppx26umnoIYq6qiklmrqqaimquqqrLbq6quwxirrrLTWWkYEADs=";
		return Base64Utils.decodeToByte(str);
	}

	public static class Custom_UserConverter extends AbstractRecursiveableConverter {
		@Override
		public User convert(Object source, Type type) {
			User user = null;
			if (source != null) {
				user = new User();
				String[] strs = ((String) source).split(",");
				try {
					user.setId((int) lookupConverter(int.class).convert(strs[0], int.class));
					user.setName((String) lookupConverter(String.class).convert(strs[1], String.class));
					user.setSex((boolean) lookupConverter(boolean.class).convert(strs[2], boolean.class));
					user.setAge((Integer) lookupConverter(Integer.class).convert(strs[3], Integer.class));
				} catch (Exception e) {
				}
			}
			return user;
		}
	}

	/**
	 * 自定义去除所有空格以及替换中文逗号。
	 *
	 */
	public static class Custom_ListConverter extends ListSplitTrimConverter {
		@Override
		public List<?> convert(Object source, Type type) {
			if (source instanceof String) {
				source = ((String) source).replaceAll(" ", "").replaceAll("，", ",");
			}
			return super.convert(source, type);
		}
	}

	/**
	 * 增加 Hashtable 类型的支持。
	 *
	 */
	public static class Custom_HashtableConverter extends AbstractMapSplitTrimConverter {
		@Override
		public Hashtable<?, ?> convert(Object source, Type type) {
			return getMapConvert(Hashtable.class, source, type);
		}
	}

	public static class User {

		int id;
		String name;
		boolean sex;
		Double height;
		Integer age;
		List<String> address;
		School school;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isSex() {
			return sex;
		}

		public void setSex(boolean sex) {
			this.sex = sex;
		}

		public Double getHeight() {
			return height;
		}

		public void setHeight(Double height) {
			this.height = height;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public List<String> getAddress() {
			return address;
		}

		public void setAddress(List<String> address) {
			this.address = address;
		}

		public School getSchool() {
			return school;
		}

		public void setSchool(School school) {
			this.school = school;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", height=" + height + ", age=" + age
					+ ", address=" + address + ", school=" + school + "]";
		}

	}

	public static class School {

		String number;
		String name;
		String shortName;
		String[] address;

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getShortName() {
			return shortName;
		}

		public void setShortName(String shortName) {
			this.shortName = shortName;
		}

		public String[] getAddress() {
			return address;
		}

		public void setAddress(String[] address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "School [number=" + number + ", name=" + name + ", shortName=" + shortName + ", address="
					+ Arrays.toString(address) + "]";
		}

	}

}
