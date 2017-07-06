package root.test1.mapping;

import com.jsan.mvc.annotation.Render;
import static com.jsan.mvc.resolve.Resolver.*;

public class News {

	@Render(HTML)
	public String ultimate() {

		return getClass() + ".ultimate()";
	}

	@Render(HTML)
	public String index(String id, String name, Boolean sex) {

		return getClass() + ".index() - id=" + id + ",name=" + name + ",sex=" + sex;
	}

	@Render(HTML)
	public String foo(Integer id, String name, boolean sex) {

		return getClass() + ".foo() - id=" + id + ",name=" + name + ",sex=" + sex;
	}

	@Render(HTML)
	public String bar() {

		return getClass() + ".bar()";
	}

	@Render(HTML)
	public String baz() {

		return getClass() + ".baz()";
	}

	@Render(HTML)
	public String qux() {

		return getClass() + ".qux()";
	}

}
