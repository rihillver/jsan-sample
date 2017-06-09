package com.test.lib.fastjson;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Fastjson 测试类。
 * <p>
 * Fastjson 默认根据 Getter 输出，只要含有公共的 Getter 均可输出，包括父类的 Getter。
 *
 */

public class FastjsonTest {

	User user = new User();
	Status status = new Status();

	@Before
	public void init() {

		user.setId(0);
		user.setName("sh'an!");
		user.setFatherName("jiang");
		user.setBirth(new Date());

		user.setCountry("中\"国\"");
		user.setCode(86);

		status.setClassTeacher("jack ma");
		status.setNumber(99);
		status.setGrade(3);

		user.setStatus(status);

	}

	/**
	 * 全局修改 SerializerFeature 参数使用方法如下例子：
	 * 
	 * <li>a) 传入SerializerFeature.MapSortField参数。 JSON.toJSONString(map,
	 * SerializerFeature.MapSortField);</li>
	 * <li>b) 通过代码修改全局缺省配置。 JSON.DEFAULT_GENERATE_FEATURE |=
	 * SerializerFeature.MapSortField.getMask();</li>
	 * <li>c) 通过JVM启动参数配置修改全局配置
	 * -Dfastjson.serializerFeatures.MapSortField=true</li>
	 * <li>d) 通过类路径下的fastjson.properties来配置
	 * fastjson.serializerFeatures.MapSortField=true</li>
	 */
	protected static final SerializerFeature[] features = {

			// features的默认值为以下四个特性的默认或值，关闭SerializerFeature.SortField会导致很多问题
			// features |= SerializerFeature.QuoteFieldNames.getMask();
			// features |= SerializerFeature.SkipTransientField.getMask();
			// features |= SerializerFeature.WriteEnumUsingName.getMask();
			// features |= SerializerFeature.SortField.getMask();

			SerializerFeature.QuoteFieldNames, // 输出key时是否使用双引号，默认为true

			SerializerFeature.UseSingleQuotes, // 使用单引号而不是双引号，默认为false

			SerializerFeature.WriteMapNullValue, // 是否输出值为null的字段，默认为false

			SerializerFeature.UseISO8601DateFormat, // Date使用ISO8601格式输出，默认为false

			SerializerFeature.SkipTransientField, // 如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true

			SerializerFeature.SortField, // 按字段名称排序后输出。默认为true

			// SerializerFeature.WriteTabAsSpecial,把\t做转义输出，默认为false，已废弃

			SerializerFeature.PrettyFormat, // 结果是否格式化，默认为false

			SerializerFeature.WriteClassName, // 序列化时写入类型信息，默认为false。反序列化是需用到

			SerializerFeature.NotWriteRootClassName, // 含义，与WriteClassName结合？

			SerializerFeature.WriteSlashAsSpecial, // 对斜杠'/'进行转义

			SerializerFeature.BrowserCompatible, // 将中文都会序列化为\\uXXXX格式，字节数会多一些，但是能兼容IE6，IE6不支持JSON带中文字符串，默认为false

			// SerializerFeature.DisableCheckSpecialChar,一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false，已废弃

			SerializerFeature.WriteDateUseDateFormat, // 使用全局设定的日期格式，默认为false。JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";JSON.toJSONString(obj,SerializerFeature.WriteDateUseDateFormat);

			SerializerFeature.BeanToArray, // 将对象转为array输出

			SerializerFeature.BrowserSecure, // 最安全的方式，即将那些可能引起浏览器兼容的符号均转为unicode码，比如空格、单引号、双引号、中文等等

			SerializerFeature.IgnoreNonFieldGetter, // 忽略没有字段的Getter，默认根据Getter输出，不管有没有字段都会输出，除非指明

			SerializerFeature.WriteEnumUsingName, // 用枚举name()输出，由于枚举序列化特性WriteEnumUsingToString和WriteEnumUsingName不能共存

			SerializerFeature.WriteEnumUsingToString, // 用枚举toString()值输出，由于枚举序列化特性WriteEnumUsingToString和WriteEnumUsingName不能共存

			SerializerFeature.DisableCircularReferenceDetect, // 打开循环引用检测，消除对同一对象循环引用的问题，默认为false，JSONField(serialize=false)不循环

			SerializerFeature.WriteNonStringKeyAsString, // 如果key不为String则转换为String，比如Map的key为Integer

			SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null

			SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null

			SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null

			SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null

			SerializerFeature.NotWriteDefaultValue, // 不输出缺省值，可以减少序列化后文本的大小，比如int类型的字段值为0，通过setter设置成0也不会输出，即只认该类型的值是否为缺省值，主要针对基本数据类型

			SerializerFeature.IgnoreErrorGetter, // 忽略有异常的 Getter

			SerializerFeature.WriteBigDecimalAsPlain, // BigDecimal以toPlainString()方法输出

			SerializerFeature.MapSortField, // 1.2.3之后的版本，Map的序列化没有做排序再输出，原因是通过TreeMap排序很影响性能。1.2.27版本中增加SerializerFeature.MapSortField实现同样的功能

	};

	@Test
	public void foo() {

		String json = JSON.toJSONString(user, SerializerFeature.PrettyFormat);

		// json = JSON.toJSONString(user, SerializerFeature.PrettyFormat,
		// SerializerFeature.IgnoreNonFieldGetter);

		// json = JSON.toJSONString(user, SerializerFeature.PrettyFormat,
		// SerializerFeature.IgnoreErrorGetter);

		// json = JSON.toJSONString(user, SerializerFeature.PrettyFormat,
		// SerializerFeature.BrowserCompatible);

		// json = JSON.toJSONString(user, SerializerFeature.PrettyFormat,
		// SerializerFeature.NotWriteDefaultValue);

		// json = JSON.toJSONString(user, SerializerFeature.PrettyFormat,
		// SerializerFeature.BrowserSecure);

		System.out.println(json);

		System.out.println(JSON.parseObject(json, User.class));

		bar();
	}

	public void bar() {

		String json = null;

		// ==================================================
		// 通过这种方式可以让字段不排序，按照xxx.class.getMethods()方法返回的顺序，
		// xxx.class.getMethods()方法返回数组中的元素没有排序，也没有任何特定的顺序，
		// 如果要指定排序顺序可使用@JSONField(ordinal=x)或@JSONType(orders={xxx,xxx,xxx}，
		// @JSONType(orders={xxx,xxx,xxx})对于继承有父类的情况下则指定的排序也无效，
		// @JSONType(orders={xxx,xxx,xxx})时必须开启SerializerFeature.SortField
		// ==================================================
		int features = 0;
		features |= SerializerFeature.QuoteFieldNames.getMask();
		features |= SerializerFeature.SkipTransientField.getMask();
		features |= SerializerFeature.WriteEnumUsingName.getMask();
		// features |= SerializerFeature.SortField.getMask();
		json = JSON.toJSONString(status, features, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
		// ==================================================

		// ==================================================
		// 通过这种方式也可以让字段不排序，效果同上
		// ==================================================
		JSON.DEFAULT_GENERATE_FEATURE ^= SerializerFeature.SortField.getMask(); // 开发中可不要随意更改全局设置
		json = JSON.toJSONString(status, SerializerFeature.PrettyFormat, SerializerFeature.BrowserSecure);
		// ==================================================

		System.out.println(json);
	}

}
