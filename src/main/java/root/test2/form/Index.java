package root.test2.form;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jsan.convert.AbstractRecursiveableConverter;
import com.jsan.convert.JsonConvertService;
import com.jsan.convert.SplitConvertService;
import com.jsan.convert.SplitTrimConvertService;
import com.jsan.convert.annotation.ConvertServiceRegister;
import com.jsan.convert.annotation.ConverterRegister;
import com.jsan.convert.support.split.trim.AbstractMapSplitTrimConverter;
import com.jsan.convert.support.split.trim.ListSplitTrimConverter;
import com.jsan.mvc.View;
import com.jsan.mvc.annotation.Get;
import com.jsan.mvc.annotation.JsonConvert;
import com.jsan.mvc.annotation.MultiValue;
import com.jsan.mvc.annotation.ParamName;
import com.jsan.mvc.annotation.Post;
import com.jsan.mvc.annotation.Render;

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
	 * 
	 */
	@Post
	@Render(url = "bar-result")
	public void bar(View view, @ConvertServiceRegister(SplitTrimConvertService.class) List<String> like,
			@ConvertServiceRegister(SplitTrimConvertService.class) @ConverterRegister(Custom_ListConverter.class) @ParamName("like") List<String> like0,
			@ConvertServiceRegister(SplitTrimConvertService.class) HashMap<String, String> family,
			@ConvertServiceRegister(SplitTrimConvertService.class) @ConverterRegister(Custom_HashtableConverter.class) @ParamName("family") Hashtable<String, String> family0,
			@ConverterRegister(Custom_UserConverter.class) User user) {

		view.add("like", like);
		view.add("like0", like0);
		view.add("family", family);
		view.add("family0", family0);
		view.add("user", user);

		System.out.println("like.length=" + like.size());
		System.out.println("like0.length=" + like0.size());
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
					// skip
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
		Integer age;
		List<String> address;

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

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", address=" + address + "]";
		}

	}

}
