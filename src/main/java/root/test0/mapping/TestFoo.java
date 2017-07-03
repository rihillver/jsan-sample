package root.test0.mapping;

import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;

public class TestFoo {

	@Render(Resolver.HTML)
	public String index() {

		return "index()";
	}

}
