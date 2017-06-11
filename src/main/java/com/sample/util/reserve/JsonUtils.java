package com.jsan.util.reserve;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

/**
 * 基于 Gson 的 Json 工具类。<br>
 * <br>
 * 
 * 依赖：gson.jar
 *
 */

public class JsonUtils {

	private static final Gson gson = new Gson();
	private static final Gson gsonBySerializeNulls = new GsonBuilder().serializeNulls().create();
	private static final Gson gsonByPrettyPrinting = new GsonBuilder().setPrettyPrinting().create();
	private static final HashMap<String, Gson> gsonByDateFormatCache = new HashMap<String, Gson>();

	/**
	 * 返回 Gson 对象。
	 * 
	 * @return
	 */
	public static Gson getGson() {

		return gson;
	}

	/**
	 * 返回 Gson 对象（自定义时间转换格式）。
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static Gson getGsonByDateFormat(String dateFormat) {

		if (dateFormat == null) {
			return getGson();
		}

		Gson gson = gsonByDateFormatCache.get(dateFormat);
		if (gson == null) {
			synchronized (gsonByDateFormatCache) {
				gson = gsonByDateFormatCache.get(dateFormat);
				if (gson == null) {
					gson = new GsonBuilder().setDateFormat(dateFormat).create();
					gsonByDateFormatCache.put(dateFormat, gson);
				}
			}
		}

		return gson;
	}

	/**
	 * 返回 Gson 对象（可以序列化 null）。
	 * 
	 * @return
	 */
	public static Gson getGsonBySerializeNulls() {

		return gsonBySerializeNulls;
	}

	/**
	 * 返回 Gson 对象（格式化输出）。
	 * 
	 * @return
	 */
	public static Gson getGsonByPrettyPrinting() {

		return gsonByPrettyPrinting;
	}

	/**
	 * 返回 Collection 。
	 * 
	 * @param json
	 *            Json 数组形式
	 * @return
	 */
	public static Collection<?> fromJsonToCollection(String json) {

		return fromJson(json, Collection.class);
	}

	/**
	 * 返回 Set 。
	 * 
	 * @param json
	 *            Json 数组形式
	 * @return
	 */
	public static Set<?> fromJsonToSet(String json) {

		return fromJson(json, Set.class);
	}

	/**
	 * 返回 List 。
	 * 
	 * @param json
	 *            Json 数组形式
	 * @return
	 */
	public static List<?> fromJsonToList(String json) {

		return fromJson(json, List.class);
	}

	/**
	 * 返回 Map 。
	 * 
	 * @param json
	 *            Json 对象形式
	 * @return
	 */
	public static Map<?, ?> fromJsonToMap(String json) {

		return fromJson(json, Map.class);
	}

	/**
	 * 返回 T 对象。
	 * 
	 * @param json
	 *            Json 对象形式
	 * @param classOfT
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) {

		return getGson().fromJson(json, classOfT);
	}

	/**
	 * 返回 T 对象（自定义时间转换格式）。
	 * 
	 * @param json
	 *            Json 对象形式
	 * @param classOfT
	 * @param dateFormat
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> classOfT, String dateFormat) {

		return getGsonByDateFormat(dateFormat).fromJson(json, classOfT);
	}

	/**
	 * 返回 T 对象。
	 * 
	 * @param json
	 * @param typeOfT
	 * @return
	 */
	public static <T> T fromJson(String json, Type typeOfT) {

		return getGson().fromJson(json, typeOfT);
	}

	/**
	 * 返回 T 对象（自定义时间转换格式）。
	 * 
	 * @param json
	 * @param typeOfT
	 * @param dateFormat
	 * @return
	 */
	public static <T> T fromJson(String json, Type typeOfT, String dateFormat) {

		return getGsonByDateFormat(dateFormat).fromJson(json, typeOfT);
	}

	/**
	 * 返回 Json 字符串。
	 * 
	 * @param src
	 * @return
	 */
	public static String toJson(Object src) {

		return getGson().toJson(src);
	}

	/**
	 * 返回 Json 字符串（自定义时间转换格式）。
	 * 
	 * @param src
	 * @param dateFormat
	 * @return
	 */
	public static String toJson(Object src, String dateFormat) {

		return getGsonByDateFormat(dateFormat).toJson(src);
	}

	/**
	 * 返回 Json 字符串。
	 * 
	 * @param src
	 * @param typeOfSrc
	 * @return
	 */
	public static String toJson(Object src, Type typeOfSrc) {

		return getGson().toJson(src);
	}

	/**
	 * 返回 Json 字符串（自定义时间转换格式）。
	 * 
	 * @param src
	 * @param typeOfSrc
	 * @param dateFormat
	 * @return
	 */
	public static String toJson(Object src, Type typeOfSrc, String dateFormat) {

		return getGsonByDateFormat(dateFormat).toJson(src);
	}

	/**
	 * 返回指定 Json 字符串中指定键的值。
	 * 
	 * @param json
	 *            Json 对象形式
	 * @param key
	 * @return
	 */
	public static Object getJsonValue(String json, Object key) {

		Object value = null;
		Map<?, ?> map = fromJsonToMap(json);

		if (map != null) {
			value = map.get(key);
		}

		return value;
	}

	/**
	 * 返回指定 Json 字符串中指定索引的值。
	 * 
	 * @param json
	 *            Json 数组形式
	 * @param index
	 * @return
	 */
	public static Object getJsonValue(String json, int index) {

		Object value = null;
		List<?> list = fromJsonToList(json);

		if (list != null) {
			value = list.get(index);
		}

		return value;
	}

	/**
	 * 返回指定的字符串是否为 Json 数组字符串格式。
	 * 
	 * @param json
	 * @return
	 */
	public static boolean isJsonArray(String json) {

		if (json == null) {
			return false;
		} else {
			try {
				return new JsonParser().parse(json).isJsonArray();
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * 返回指定的字符串是否为 Json 对象字符串格式。
	 * 
	 * @param json
	 * @return
	 */
	public static boolean isJsonObject(String json) {

		if (json == null) {
			return false;
		} else {
			try {
				return new JsonParser().parse(json).isJsonObject();
			} catch (Exception e) {
				return false;
			}
		}
	}

//	public static void main(String[] args) {
//
//		Map<String, Date> map = new LinkedHashMap<String, Date>();
//
//		map.put("one", new Date());
//		map.put("two", DateUtils.getFirstDateOfMonth());
//
//		Type typeOfT = new TypeToken<Map<String, Date>>() {
//		}.getType();
//
//		String json = toJson(map, typeOfT, "yy年MM月dd日-HH时mm分ss");
//
//		System.out.println(json);
//
//		map = fromJson(json, typeOfT, "yy年MM月dd日-HH时mm分ss");
//
//		System.out.println(map);
//
//	}

}
