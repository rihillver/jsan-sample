package root.test2.intercept;

import static com.jsan.mvc.resolve.Resolver.HTML;

import javax.servlet.ServletException;

import com.jsan.mvc.annotation.FormConvert;
import com.jsan.mvc.annotation.InterceptorRegister;
import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.intercept.Interceptor;
import com.jsan.mvc.intercept.Invocation;

/**
 * 拦截器测试。
 *
 */

@InterceptorRegister(Index.Index_Intercepter.class)
public class Index {

	@Render
	public void list() {

	}

	@Render(HTML)
	public String foo() {

		System.out.println("foo() running...");

		return getClass() + ".foo()";
	}

	@Render(HTML)
	@InterceptorRegister(Index_Bar_Intercepter.class)
	public String bar(int id) {

		System.out.println("bar() running...");
		return getClass() + ".bar()";
	}

	@Render(HTML)
	@InterceptorRegister(Index_Baz_Intercepter.class)
	public String baz(Integer id) throws ServletException {

		if (id == null) {
			return getClass() + ".baz()";
		} else if (id < 0) {
			throw new IllegalArgumentException("id < 0");
		} else if (id > 0) {
			throw new ServletException("id > 0");
		} else {
			throw new RuntimeException("id == 0");
		}

	}

	@Render(HTML)
	@InterceptorRegister(Index_Qux_Intercepter.class)
	public Person qux(@FormConvert Person person) {

		return person;
	}

	public static class Index_Qux_Intercepter implements Interceptor {

		@Override
		public void before(Invocation inv) {

			// 改变qux()方法的参数值
			Person person = (Person) inv.getArg(0);
			person.setId(888);
			person.setName("李四");
		}

		@Override
		public void after(Invocation inv) {
		}

		@Override
		public void afterReturning(Invocation inv, Object result) {
		}

		@Override
		public void afterThrowing(Invocation inv, Exception e) {
		}

	}

	public static class Index_Baz_Intercepter implements Interceptor {

		@Override
		public void before(Invocation inv) {
		}

		@Override
		public void after(Invocation inv) {
		}

		@Override
		public void afterReturning(Invocation inv, Object result) {
		}

		@Override
		public void afterThrowing(Invocation inv, Exception e) {

			// 处理异常
			if (e instanceof IllegalArgumentException) {
				System.out.println(getClass() + " --> afterThrowing... --> IllegalArgumentException");
			} else if (e instanceof ServletException) {
				System.out.println(getClass() + " --> afterThrowing... --> ServletException");
			} else {
				System.out.println(getClass() + " --> afterThrowing... --> " + e.getClass().getSimpleName());
			}
		}

	}

	public static class Index_Bar_Intercepter implements Interceptor {

		@Override
		public void before(Invocation inv) {
			System.out.println(getClass() + " --> before...");
			System.out.println("id=" + inv.getArg(0)); // 获取bar()方法的参数值
		}

		@Override
		public void after(Invocation inv) {
			System.out.println(getClass() + " --> after...");
		}

		@Override
		public void afterReturning(Invocation inv, Object result) {
			System.out.println(getClass() + " --> afterReturning... --> result=" + result);

			if (((int) inv.getArg(0)) > 0) {
				inv.setReturnValue(result + " --> return value is changed."); // 改变返回值
			}
		}

		@Override
		public void afterThrowing(Invocation inv, Exception e) {
		}

	}

	public static class Index_Intercepter implements Interceptor {

		@Override
		public void before(Invocation inv) {
			System.out.println(getClass() + " --> before...");
		}

		@Override
		public void after(Invocation inv) {
			System.out.println(getClass() + " --> after...");
		}

		@Override
		public void afterReturning(Invocation inv, Object result) {
		}

		@Override
		public void afterThrowing(Invocation inv, Exception e) {
		}

	}

	public static class Person {

		int id;
		String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Person [id=" + id + ", name=" + name + "]";
		}

	}

}
