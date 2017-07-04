package root.test1.mapping;

import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;

public class Foo_bar {

	@Render(Resolver.HTML)
	public String ultimate() {

		return "ultimate()";
	}

}
