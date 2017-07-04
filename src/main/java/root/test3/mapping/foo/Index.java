package root.test3.mapping.foo;

import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;

public class Index {

	@Render(Resolver.HTML)
	public String index() {

		return "index()";
	}

}
