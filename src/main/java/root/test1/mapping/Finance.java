package root.test1.mapping;

import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;

public class Finance {

	@Render(Resolver.HTML)
	public String index() {

		return getClass() + ".index()";
	}

	@Render(Resolver.HTML)
	public String foo() {

		return getClass() + ".foo()";
	}
}
