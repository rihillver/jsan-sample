package root.test4.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;
import com.jsan.spring.ContextUtils;
import com.sample.www.bean.Person;
import com.sample.www.service.PersonService;

@Controller
public class Index {

	@Autowired
	private PersonService service;

	@Render
	public void list() {

	}

	@Render(Resolver.HTML)
	public User foo() {

		return ContextUtils.getBean(User.class);
	}

	@Render(Resolver.HTML)
	public Person bar() {

		Person person = ContextUtils.getBean(Person.class);
		service.doSomething(person);
		return person;
	}

	@Render(Resolver.HTML)
	public Person baz() {

		return service.getPerson();
	}

	@Render(Resolver.HTML)
	public User qux() {

		return ContextUtils.getBean(User.class);
	}

	@Aspect
	@Component
	public static class Custom_Aspect {

		@Before("execution(* foo(..))")
		public void before(JoinPoint joinPoint) {

			System.out.println(joinPoint.getSignature().getName() + " --> before ...");
		}

		@After("execution(* ba*(..))")
		public void after(JoinPoint joinPoint) {

			System.out.println(joinPoint.getSignature() + " ---> after ...");
		}

		@AfterThrowing("execution(* foo(..))")
		public void afterThrowing(JoinPoint joinPoint) {

			System.out.println(joinPoint.getSignature().getName() + " --> afterThrowing ...");
		}

		@AfterReturning("execution(* foo(..))")
		public void afterReturning(JoinPoint joinPoint) {

			System.out.println(joinPoint.getSignature().getName() + " ---> afterReturning ...");
		}

		@Around("execution(* qux(..))")
		public User around(ProceedingJoinPoint joinPoint) {

			User obj = null;
			try {
				obj = (User) joinPoint.proceed(joinPoint.getArgs());
				obj.setName("李四");
			} catch (Throwable e) {
				e.printStackTrace();
			}

			System.out.println(joinPoint.getSignature().getName() + " ---> around ...");

			return obj;
		}
	}

	public static class User {

		int id;
		boolean sex;
		String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public boolean isSex() {
			return sex;
		}

		public void setSex(boolean sex) {
			this.sex = sex;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", sex=" + sex + ", name=" + name + "]";
		}

	}
}
