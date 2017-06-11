package com.sample.util.reserve;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

/**
 * 拖拽排序处理工具类，可通过配合前端 jquery.dragsort.js 和 jquery.nestable.js 实现排序结果处理。<br>
 * <br>
 * 
 * 依赖：gson.jar
 *
 */

public class SortUtils {

	/**
	 * 返回经过处理后的排序。
	 * 
	 * @param sortJson
	 * @return
	 */
	public static Map<String, Integer> getDragsort(String sortJson) {

		return getDragsort(sortJson, false);
	}

	/**
	 * 返回经过处理后的排序。
	 * 
	 * @param sortJson
	 * @return
	 */
	public static Map<String, Integer> getDragsort(LinkedHashMap<String, Integer> sortJson) {

		return getDragsort(sortJson, false);
	}

	/**
	 * 返回经过处理后的排序，指定升序（false）或降序（true）。
	 * 
	 * @param sortJson
	 * @param desc
	 * @return
	 */
	public static Map<String, Integer> getDragsort(String sortJson, boolean desc) {

		Type type = new TypeToken<LinkedHashMap<String, Integer>>() {
		}.getType();
		LinkedHashMap<String, Integer> linkedHashMap = new Gson().fromJson(sortJson, type);

		return getDragsort(linkedHashMap, desc);
	}

	/**
	 * 返回经过处理后的排序，指定升序（false）或降序（true）。
	 * 
	 * @param sortJson
	 * @param desc
	 * @return
	 */
	public static Map<String, Integer> getDragsort(LinkedHashMap<String, Integer> sortJson, boolean desc) {

		List<Integer> list = new ArrayList<Integer>();

		for (Integer i : sortJson.values()) {
			list.add(i);
		}

		Collections.sort(list);
		if (desc) {
			Collections.reverse(list);
		}

		Set<String> keySet = sortJson.keySet();
		int i = 0;
		for (String key : keySet) {
			sortJson.put(key, list.get(i++));
		}

		return sortJson;
	}

	/**
	 * 返回经过处理后的排序。
	 * 
	 * @param sortJson
	 * @param idStr
	 * @return
	 */
	public static List<Map<String, Integer>> getNestable(String sortJson, final String idStr) {

		return getNestable(sortJson, idStr, null, null, null);
	}

	/**
	 * 返回经过处理后的排序。
	 * 
	 * @param sortJson
	 * @param idStr
	 * @param pidStr
	 * @return
	 */
	public static List<Map<String, Integer>> getNestable(String sortJson, final String idStr, final String pidStr) {

		return getNestable(sortJson, idStr, pidStr, null, null);
	}

	/**
	 * 返回经过处理后的排序。
	 * 
	 * @param sortJson
	 * @param idStr
	 * @param pidStr
	 * @param levelStr
	 * @return
	 */
	public static List<Map<String, Integer>> getNestable(String sortJson, final String idStr, final String pidStr, final String levelStr) {

		return getNestable(sortJson, idStr, pidStr, levelStr, null);
	}

	/**
	 * 返回经过处理后的排序。
	 * 
	 * @param sortJson
	 * @param idStr
	 * @param pidStr
	 * @param levelStr
	 * @param sortStr
	 * @return
	 */
	public static List<Map<String, Integer>> getNestable(String sortJson, final String idStr, final String pidStr, final String levelStr, final String sortStr) {

		int startPid = 0; // 起始父节点 ID
		int startLevel = 0; // 起始节点层级数

		final String nestName = "children"; // Nestable 默认嵌套 JSON 属性名为 children

		final List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(sortJson);

		class Inner {

			void setSortList(JsonElement element, int pid, int level) {

				if (element.isJsonArray()) {

					JsonArray jsonArray = element.getAsJsonArray();
					for (int i = 0; i < jsonArray.size(); i++) {
						JsonElement e = jsonArray.get(i);
						setSortList(e, pid, level);
					}

				} else if (element.isJsonObject()) {

					Map<String, Integer> map = new HashMap<String, Integer>();

					JsonObject jsonObject = element.getAsJsonObject();
					JsonPrimitive jsonPrimitive = jsonObject.getAsJsonPrimitive(idStr.toLowerCase()); // 由于 data-* 属性名不应该包含任何大写字母，因此对应的 JSON 字符串的键名也是小写的，所以在此处做转换小写
					int id = jsonPrimitive.getAsInt();

					map.put(idStr, id);
					if (pidStr != null) {
						map.put(pidStr, pid);
					}
					if (levelStr != null) {
						map.put(levelStr, level);
					}
					if (sortStr != null) {
						map.put(sortStr, list.size()); // 根据 list 的大小作为排序编号
					}
					list.add(map);

					JsonArray jsonArray = jsonObject.getAsJsonArray(nestName);
					if (jsonArray != null) {
						level++;
						for (int i = 0; i < jsonArray.size(); i++) {
							JsonElement e = jsonArray.get(i);
							setSortList(e, id, level);
						}
					}
				}

			}
		}

		new Inner().setSortList(jsonElement, startPid, startLevel);

		return list;
	}

	// public static void main(String[] args) {
	//
	// String json = "[{\"deptid\":16,\"children\":[{\"deptid\":17,\"children\":[{\"deptid\":18}]},{\"deptid\":40,\"children\":[{\"deptid\":19}]}]},{\"deptid\":20,\"children\":[{\"deptid\":22,\"children\":[{\"deptid\":39}]},{\"deptid\":38,\"children\":[{\"deptid\":36}]}]},{\"deptid\":37,\"children\":[{\"deptid\":41,\"children\":[{\"deptid\":42}]},{\"deptid\":43,\"children\":[{\"deptid\":44}]}]}]";
	//
	// List<Map<String, Integer>> list = SortUtil.getNestable(json, "deptId", "deptPid", "deptLevel", "deptSort");
	//
	// for (Map<String, Integer> map : list) {
	// System.out.println(map);
	// }
	//
	// System.out.println();
	// json = "{\"4\":20,\"5\":34,\"7\":64,\"20\":80,\"34\":92,\"64\":104,\"79\":105,\"80\":106,\"92\":107,\"19\":79}";
	//
	// Map<String, Integer> map = getDragsort(json);
	//
	// for (Map.Entry<String, Integer> entry : map.entrySet()) {
	// System.out.println(entry.getKey() + " : " + entry.getValue());
	// }
	//
	// }

}
