package root.test0.mapping;

import com.jsan.mvc.annotation.Render;
import static com.jsan.mvc.resolve.Resolver.*;

public class News {

	@Render(HTML)
	public String ultimate() {

		return "ultimate()";
	}

	@Render(HTML)
	public String index(String id, String name, Boolean sex) {

		return "index() - id=" + id + ",name=" + name + ",sex=" + sex;
	}

	@Render(HTML)
	public String foo(Integer id, String name, boolean sex) {

		return "foo() - id=" + id + ",name=" + name + ",sex=" + sex;
	}

	@Render(HTML)
	public String bar() {

		return "bar()";
	}

	@Render(HTML)
	public String baz() {

		return "baz()";
	}

	@Render(HTML)
	public String qux() {

		return "qux()";
	}

}
