package root.test0.mapping.fashion;

import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;

public class Index {

	@Render(Resolver.HTML)
	public String ultimate(int id) {

		return "ultimate() - id=" + id;
	}

	@Render(Resolver.HTML)
	public String qux(int id) {

		return "qux() - id=" + id;
	}
}
