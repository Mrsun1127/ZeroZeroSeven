package com.ffn.zerozeroseven.utlis;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装的是使用Gson解析json的方法
 * @author Administrator
 *
 */
public class JsonUtil {

	/**
	 * 把一个map变成json字符串
	 * @param map
	 * @return	 */
	public static String parseMapToJson(Map<?, ?> map) {
		try {
			Gson gson = new Gson();
			return gson.toJson(map);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 把一个对象变成json字符串
	 * @return	 */
	public static String  parseBeanToJson(Object object) {
		try {
			Gson gson = new Gson();
			return  gson.toJson(object);
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 把一个json字符串变成对象
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> T parseJsonToBean(String json, Class<T> cls) {
		Gson gson = new Gson();

		T t = null;
		try {
			t = gson.fromJson(json, cls);
		} catch (Exception e) {
		}
		return t;
	}

	/**
	 * 把json字符串变成map
	 * @param json
	 * @return
	 */
	public static HashMap<String, Object> parseJsonToMap(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<HashMap<String, Object>>() {
		}.getType();
		HashMap<String, Object> map = null;
		try {
			map = gson.fromJson(json, type);
		} catch (Exception e) {
		}
		return map;
	}

	/**
	 * 把json字符串变成集合
	 * params: new TypeToken<List<yourbean>>(){}.getType(),
	 * 
	 * @param json
	 * @param type  new TypeToken<List<yourbean>>(){}.getType()
	 * @return
	 */
	public static ArrayList<?> parseJsonToList(String json, Type type) {
		Gson gson = new Gson();
		ArrayList<?> list = gson.fromJson(json, type);
		return list;
	}


	/**
	 * 
	 * 获取json串中某个字段的值，注意，只能获取同一层级的value
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getFieldValue(String json, String key) {
		if (TextUtils.isEmpty(json))
			return "";
		if (!json.contains(key))
			return "";
		JSONObject jsonObject = null;
		String value = "";
		try {
			jsonObject = new JSONObject(json);
			value = jsonObject.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

}
