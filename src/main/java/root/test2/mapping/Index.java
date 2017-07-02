package root.test2.mapping;

import com.jsan.mvc.annotation.MethodValue;
import com.jsan.mvc.annotation.Render;

public class Index {

	@Render
	public void list() {

	}

	/**
	 * 无匹配时默认调用的方法。
	 * 
	 */
	@Render
	public String ultimate(@MethodValue String value) {

		if (value.length() == 5) {
			return handleLength(value);
		} else if (value.matches("\\d+")) { // 匹配纯数字
			return handleNumber(value);
		} else if (value.matches("[a-zA-Z]+")) { // 匹配纯英文
			return handleAlphabet(value);
		} else if (value.matches("[a-zA-Z0-9]+")) { // 匹配纯数字英文
			return handleNumberAndAlphabet(value);
		}

		return "methodValue = " + value;
	}

	private String handleLength(String value) {

		return "长度为5：" + value;
	}

	private String handleNumber(String value) {

		return "纯数字：" + value;
	}

	private String handleAlphabet(String value) {

		return "纯英文：" + value;
	}

	private String handleNumberAndAlphabet(String value) {

		return "纯数字英文：" + value;
	}

	@Render(url = "ultimate")
	public String foo() {

		return "foo()";
	}

	@Render(url = "ultimate")
	public String bar(int id, String name, boolean sex, Integer age) {

		return "bar() --> id=" + id + ",name=" + name + ",sex=" + sex + ",age=" + age;
	}

	@Render(url = "ultimate")
	public String testFoo() {

		return "testFoo()";
	}

	@Render(url = "ultimate")
	public String foo_bar() {

		return "foo_bar()";
	}

}
