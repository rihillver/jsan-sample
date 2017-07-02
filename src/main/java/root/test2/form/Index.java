package root.test2.form;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jsan.convert.JsonConvertService;
import com.jsan.convert.SplitConvertService;
import com.jsan.convert.SplitTrimConvertService;
import com.jsan.convert.annotation.ConvertServiceRegister;
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
