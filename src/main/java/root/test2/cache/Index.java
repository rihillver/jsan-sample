package root.test2.cache;

import static com.jsan.mvc.resolve.Resolver.HTML;
import static com.jsan.mvc.resolve.Resolver.TEXT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsan.mvc.View;
import com.jsan.mvc.annotation.Cache;
import com.jsan.mvc.annotation.Render;
import com.jsan.util.RandomUtils;

/**
 * 开启 View 缓存需要在 mvc 配置中设置 cacheable 为 true。
 *
 */
@Cache(Index.CACHE30S) // 该类下的映射方法均缓存30秒
public class Index {

	public final static String CACHE1M = "cache1m";
	public final static String CACHE10M = "cache10m";
	public final static String CACHE30S = "cache30s";
	public final static String CACHE10S = "cache10s";

	@Render
	public void list() {

	}

	/**
	 * 缓存10秒。
	 * 
	 */
	@Cache(CACHE10S)
	@Render(TEXT)
	public String foo() {

		return "缓存10秒：" + RandomUtils.getWordChar(12);
	}

	/**
	 * 缓存30秒。
	 * 
	 */
	@Render(HTML)
	public String bar() {

		return "缓存30秒：" + RandomUtils.getChinese(12);
	}

	/**
	 * 不缓存。
	 * 
	 */
	@Cache("")
	@Render(HTML)
	public String baz() {

		return "不缓存：" + RandomUtils.getWordChar(12);
	}

	/**
	 * 通过在 View 中添加对象引用的方式可以得到特定输出不缓存的方式。
	 * 
	 */
	@Render(url = "result")
	public View qux() {

		View view = new View();

		view.add("one", RandomUtils.getChinese(10));
		view.add("two", RandomUtils.getWordChar(10));
		view.add("three", new Three()); // 通过缓存对象引用的方式，然后再调用其方法的方式获得不缓存的值

		return view;
	}

	/**
	 * 方法内对 HttpServletRequest、HttpServletResponse、HttpSession 进行了设置操作的情况不适用于使用
	 * View 缓存。
	 * 
	 */
	@Cache("")
	@Render(HTML)
	public String foobar(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		request.setAttribute("test", 100);
		response.setHeader("test", "aaa");
		session.setAttribute("test", "bbb");

		return "不适用使用缓存";
	}

	public static class Three {

		public String getRandomStr() {

			return RandomUtils.getEnglish(10);
		}
	}

}
