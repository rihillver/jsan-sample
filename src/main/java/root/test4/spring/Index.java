package root.test4.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.resolve.Resolver;
import com.jsan.spring.ContextUtils;
import com.sample.www.dao.FooDao;
import com.sample.www.service.BarService;
import com.sample.www.service.FooService;
import com.sample.www.util.ProjectUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(ProjectUtils.SPRING_CONFIG_FILE)
@Controller
public class Index {

	@Autowired
	@Qualifier("impl.fooServiceImpl")
	private FooService service;

	@Autowired
	private BarService bar;

	@Autowired
	private FooDao fooDao;

	@Autowired
	private com.sample.www.dao.table.FooDao tableFooDao;

	/**
	 * Spring IOC 容器中的 Bean 列表。
	 * 
	 */
	@Render
	public void list() {

		System.out.println("=============== Bean Definition Names ===============");
		String[] strs = ContextUtils.getApplicationContext().getBeanDefinitionNames();
		for (String str : strs) {
			System.out.println(str);
		}
		System.out.println("=====================================================");

	}

	@Test
	public void testOne() {

		System.out.println(one());
	}

	@Render(Resolver.HTML)
	public String one() {

		return service.getData();
	}

	@Render(Resolver.HTML)
	public String two() {

		return bar.getData();
	}

	@Render(Resolver.HTML)
	public String three() {

		return fooDao.getData();
	}

	@Render(Resolver.HTML)
	public String four() {

		return tableFooDao.getData();
	}

	/**
	 * 将 Bean 动态注册到 Spring IOC 容器。
	 * 
	 */
	@Render(Resolver.HTML)
	public User five(int id, String name) {

		DefaultListableBeanFactory beanFactory = ContextUtils.getDefaultListableBeanFactory();

		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
		builder.addPropertyValue("id", id);
		builder.addPropertyValue("name", name);
		builder.setScope("prototype"); // 多例
		beanFactory.registerBeanDefinition("user9", builder.getRawBeanDefinition());

		return ContextUtils.getBean("user9", User.class);
	}

	/**
	 * 动态注册 Bean。
	 * 
	 */
	@Render(Resolver.HTML)
	public User six(int id, String name) throws ClassNotFoundException {

		BeanDefinitionRegistry registry = ContextUtils.getBeanDefinitionRegistry();

		String className = "root.test4.spring.Index$User";
		Class<?> clazz = Class.forName(className);
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
		builder.addPropertyValue("id", id);
		builder.addPropertyValue("name", name);
		// builder.setScope("singleton"); // 单例，默认单例
		registry.registerBeanDefinition("user10", builder.getRawBeanDefinition());

		User user = ContextUtils.getBean("user10", User.class);
		user.setName("徐十");

		return ContextUtils.getBean("user10", User.class);
	}

	@Render(Resolver.HTML)
	public User foo() {

		return ContextUtils.getBean(User.class.getCanonicalName() + "#0", User.class);
	}

	@Render(Resolver.HTML)
	public User bar() {

		return ContextUtils.getBean(User.class.getCanonicalName() + "#1", User.class);
	}

	@Render(Resolver.HTML)
	public User baz() {

		System.out.println(ContextUtils.getBean("user2", User.class));
		return ContextUtils.getBean("user5", User.class);
	}

	@Render(Resolver.HTML)
	public User qux() {

		return ContextUtils.getBean("user", User.class);
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
				obj.setName("刘九");
			} catch (Throwable e) {
				e.printStackTrace();
			}

			System.out.println(joinPoint.getSignature().getName() + " ---> around ...");

			return obj;
		}
	}

	public static class User {

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
			return "User [id=" + id + ", name=" + name + "]";
		}

	}
}
