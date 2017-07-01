package root.test2.json;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jsan.convert.JsonConvertService;
import com.jsan.convert.annotation.ConvertServiceRegister;
import com.jsan.convert.annotation.DateTimePattern;
import com.jsan.mvc.View;
import com.jsan.mvc.annotation.Get;
import com.jsan.mvc.annotation.JsonConvert;
import com.jsan.mvc.annotation.Post;
import com.jsan.mvc.annotation.Render;
import com.jsan.mvc.json.AbstractJsonParserConfigurator;
import com.jsan.mvc.json.AbstractJsonSerializeConfigurator;
import com.jsan.mvc.json.GeneralJsonSerializeConfigurator;
import com.jsan.mvc.json.JsonSerializeConfigurator;
import com.jsan.mvc.resolve.Resolver;
import com.jsan.util.DateUtils;
import com.jsan.util.RandomUtils;

public class Index {

	@Render
	public void list() {

	}

	@Get
	@Render
	public void one() {

	}

	/**
	 * json 字符串转数组、集合。
	 * 
	 */
	@Post
	@Render(url = "one-result")
	@ConvertServiceRegister(JsonConvertService.class)
	public void one(View view, int id, String name, boolean sex, double[] json1, Object[] json2, HashSet<String> json3,
			List<ArrayList<BigDecimal>> json4, double[][] json5) {

		view.add("id", id);
		view.add("name", name);
		view.add("sex", sex);

		view.add("json1", Arrays.toString(json1));
		view.add("json2", Arrays.toString(json2));
		view.add("json3", json3);
		view.add("json4", json4);
		view.add("json5", Arrays.toString(json5));
		view.add("json5Array", json5);
	}

	@Get
	@Render
	public void two() {

	}

	/**
	 * json 字符串转Map。
	 * 
	 */
	@Post
	@Render(url = "two-result")
	@ConvertServiceRegister(JsonConvertService.class)
	@DateTimePattern("yyyy年MM月dd日")
	public void two(View view, Map<Integer, String> json1, HashMap<String, List<Date>> json2,
			Map<String, Double[]> json3) {

		view.add("json1", json1);
		view.add("json2", json2);
		view.add("json3", json3);
		view.add("json3Map", json3);
	}

	@Get
	@Render
	public void three() {

	}

	/**
	 * json 字符串转Bean。
	 * <p>
	 * 可以通过jsanmvc.properties自定义全局配置JsonParserConfigurator
	 * <p>
	 * 由于school指定了School_JsonParserConfigurator.class，因此short_name将不会被匹配到school的shortName字段。
	 * 
	 */
	@Post
	@Render(url = "three-result")
	public void three(View view, @JsonConvert User user,
			@JsonConvert(School_JsonParserConfigurator.class) School school) {

		view.add("user", user);
		view.add("school", school);
	}

	// --------------------------------------------------

	/**
	 * 数组、集合转 json 字符串。
	 * 
	 */
	@Render(Resolver.JSON)
	public void four(View view, String type) {

		// 设置json序列化配置器，可以通过jsanmvc.properties自定义全局配置JsonSerializeConfigurator
		// projectJsonSerializeConfigurator设置了关闭循环引用检测的功能
		view.setJsonSerializeConfigurator(testJsonSerializeConfigurator);

		if (type == null) {
			type = "";
		}

		Object obj;

		switch (type) {
		case "array":
			obj = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			break;
		case "list":
			List<School> list = new ArrayList<>();
			list.add(new School("1", "北京大学", "北大", "北京市", Grade.ONE));
			list.add(new School("2", "上海大学", "上大", "上海市", Grade.TWO));
			list.add(new School("3", "南京大学", "南大", "南京市", Grade.THREE));
			obj = list;
			break;
		case "set":
			// 注意：下面的代码存在循环引用，使用fastjson默认将输出fastjson专有的引用表示格式
			// 可以通过配置SerializerFeature.DisableCircularReferenceDetect关闭循环引用检测的功能
			Set<Map<Date, String>> set = new LinkedHashSet<>();
			Map<Date, String> map = new LinkedHashMap<>();
			for (int i = 0; i < 10; i++) {
				map.put(DateUtils.getOffsetDays(-RandomUtils.getInt(4)), RandomUtils.getWordChar(5));
				set.add(map);
			}
			obj = set;
			break;
		case "collection":
			Collection<double[]> collection = new HashSet<>();
			collection.add(new double[] { 1.1, 2.2, 3.3 });
			collection.add(new double[] { 10.01, 20.01, 30.01, 40.01 });
			collection.add(new double[] { 10, 20, 30, 40, 50 });
			obj = collection;
			break;
		default:
			obj = new String[] { "one", "two", "three", "four", "five" };
			break;
		}

		view.addValue(obj);

	}

	private static JsonSerializeConfigurator myJsonSerializeConfigurator = createMyJsonSerializeConfigurator();

	private static JsonSerializeConfigurator createMyJsonSerializeConfigurator() {

		GeneralJsonSerializeConfigurator configurator = new GeneralJsonSerializeConfigurator();
		configurator.setDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		configurator.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);

		return configurator;
	}

	/**
	 * Map 转 json 字符串。
	 * <p>
	 * 这里将使用自定义的 ProjectJsonSerializeConfigurator 序列化配置器。
	 * 
	 */
	@Render(Resolver.JSON)
	public Map<String, Object> five(View view) {

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("object", new School("1", "北京大学", "北大", "北京市", Grade.ONE));
		map.put("array", new Boolean[] { true, false, null, true, true, false });
		List<Date> list = new LinkedList<>();
		list.add(DateUtils.getOffsetMonths(1));
		list.add(DateUtils.getOffsetMonths(2));
		list.add(DateUtils.getOffsetMonths(3));
		list.add(null);
		map.put("list", list);
		Set<School> set = new LinkedHashSet<>();
		set.add(new School("2", "上海大学", "上大", "上海市", Grade.ONE));
		set.add(new School("3", "南京大学", "南大", "南京市", Grade.THREE));
		map.put("set", set);
		map.put("null", null);

		return map;
	}

	/**
	 * Bean 转 json 字符串。
	 * 
	 */
	@Render(Resolver.JSON)
	public User six(View view) {

		view.setJsonSerializeConfigurator(myJsonSerializeConfigurator);

		return createUser();
	}

	private User createUser() {

		User user = new User();
		user.setSchool(new School("1", "北京大学", "北大", "北京市", Grade.ONE));

		user.setId(123);
		user.setName("张三");
		user.setNickName("小三");
		user.setHeight(1.65);
		user.setWeight(60);
		user.setCellphone("13900005566");
		user.setSex(true);
		user.setAge(28);
		user.setBirth(DateUtils.parseDate(1983, 12, 20));
		user.setRegtime(Calendar.getInstance());
		List<String> addresses = new LinkedList<>();
		addresses.add("北京市");
		addresses.add("西城区");
		addresses.add("西土城路1号");
		user.setAddresses(addresses);
		return user;
	}

	// --------------------------------------------------

	/**
	 * jsonp 测试。
	 * 
	 */
	@Render(Resolver.JSONP)
	public User seven() {

		return createUser();
	}

	/**
	 * jsonp 测试（指定回调函数名）。
	 * 
	 */
	@Render(Resolver.JSONP)
	public User eight(View view, String callback) {

		view.setJsonpCallback(callback);
		return createUser();
	}

	// --------------------------------------------------

	public static enum Grade {
		ONE, TWO, THREE
	}

	public static class School {

		String number;
		String name;
		String shortName;
		String address;
		Grade grade;

		public School() {

		}

		public School(String number, String name, String shortName, String address, Grade grade) {
			super();
			this.number = number;
			this.name = name;
			this.shortName = shortName;
			this.address = address;
			this.grade = grade;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@JSONField(name = "short_name") // 指定序列化的名称
		public String getShortName() {
			return shortName;
		}

		public void setShortName(String shortName) {
			this.shortName = shortName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Grade getGrade() {
			return grade;
		}

		public void setGrade(Grade grade) {
			this.grade = grade;
		}

		@Override
		public String toString() {
			return "School [number=" + number + ", name=" + name + ", shortName=" + shortName + ", address=" + address
					+ ", grade=" + grade + "]";
		}

	}

	public static class User {

		int id;
		String name;

		@JSONField(serialize = false) // 指定不序列化
		String nickName;

		@JSONField(name = "HEIGHT") // 指定序列化的名称
		Double height;

		@JSONField(deserializeUsing = WeightDeserializer.class) // 忽略值后面的kg
		float weight;

		School school;

		@JSONField(deserialize = false) // 指定不反序列化
		String cellphone;

		boolean sex;

		Integer age;

		@JSONField(format = "yyyy#MM#dd") // 指定日期格式，但对于Calendar指定格式则不行
		Date birth;

		Calendar regtime;

		List<String> addresses;

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

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public Double getHeight() {
			return height;
		}

		public void setHeight(Double height) {
			this.height = height;
		}

		public float getWeight() {
			return weight;
		}

		public void setWeight(float weight) {
			this.weight = weight;
		}

		public School getSchool() {
			return school;
		}

		public void setSchool(School school) {
			this.school = school;
		}

		public String getCellphone() {
			return cellphone;
		}

		public void setCellphone(String cellphone) {
			this.cellphone = cellphone;
		}

		public boolean isSex() {
			return sex;
		}

		public void setSex(boolean sex) {
			this.sex = sex;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public Date getBirth() {
			return birth;
		}

		public void setBirth(Date birth) {
			this.birth = birth;
		}

		public Calendar getRegtime() {
			return regtime;
		}

		public void setRegtime(Calendar regtime) {
			this.regtime = regtime;
		}

		public List<String> getAddresses() {
			return addresses;
		}

		public void setAddresses(List<String> addresses) {
			this.addresses = addresses;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", nickName=" + nickName + ", weight=" + weight + ", height="
					+ height + ", school=" + school + ", cellphone=" + cellphone + ", sex=" + sex + ", age=" + age
					+ ", birth=" + birth + ", regtime=" + regtime + ", addresses=" + addresses + "]";
		}

	}

	/**
	 * 自定义 ObjectDeserializer，将体重中若含有 "kg" 的则去除。
	 *
	 */
	public static class WeightDeserializer implements ObjectDeserializer {

		@SuppressWarnings("unchecked")
		@Override
		public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {

			String weight = parser.parseObject(String.class);

			if (weight.contains("kg")) {
				weight = weight.replaceAll("kg", "");
			}
			try {
				return (T) new Float(weight);
			} catch (Exception e) {
				return (T) new Float(0);
			}

		}

		@Override
		public int getFastMatchToken() {

			return 0;
		}

	}

	public static class School_JsonParserConfigurator extends AbstractJsonParserConfigurator {

		public School_JsonParserConfigurator() {

			setFeatures(Feature.DisableFieldSmartMatch); // 禁止模糊匹配，只能精确匹配，从而提升性能
		}
	}

	private static JsonSerializeConfigurator testJsonSerializeConfigurator = new Test_JsonSerializeConfigurator();

	public static class Test_JsonSerializeConfigurator extends AbstractJsonSerializeConfigurator {

		{
			// 设置格式化输出（当前版本1.2.33有个Bug，格式化输出不稳定，部分情况无效）
			// 设置关闭循环引用检测的功能
			setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect);
			// 设置日期格式
			setDateFormat("yyyy/MM/dd HH:mm:ss.S");
		}
	}

}
