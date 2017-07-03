package root.test0.mapping;

import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;

public class Fashion {

	@Render(Resolver.HTML)
	public String ultimate(int id) {

		return "ultimate() - id=" + id;
	}

	@Render(Resolver.HTML)
	public String baz(int id) {

		return "baz() - id=" + id;
	}
}
