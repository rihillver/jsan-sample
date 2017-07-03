package root.test0.mapping;

import com.jsan.mvc.annotation.Render;
import static com.jsan.mvc.resolve.Resolver.*;

public class Sports {

	@Render(TEXT)
	public String ultimate(int id, String name, Integer age) {

		return "ultimate() - id=" + id + ",name=" + name + ",age=" + age;
	}

	@Render(TEXT)
	public String qux(int id, String name, Integer age) {

		return "qux() - id=" + id + ",name=" + name + ",age=" + age;
	}

}
