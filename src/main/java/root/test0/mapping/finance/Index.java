package root.test0.mapping.finance;

import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;

public class Index {

	@Render(Resolver.HTML)
	public String index() {

		return "index()";
	}

	@Render(Resolver.HTML)
	public String bar() {

		return "bar()";
	}
}
