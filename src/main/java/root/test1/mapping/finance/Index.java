package root.test1.mapping.finance;

import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;

public class Index {

	@Render(Resolver.HTML)
	public String index() {

		return getClass() + ".index()";
	}

	@Render(Resolver.HTML)
	public String bar() {

		return getClass() + ".bar()";
	}
}
