package com.cloudwalk.test.client;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	public static JSONObject jsonCheck(String json) {
		if (json == null || json.equals("")) {
			throw new ClientSystemException("json is null");
		}
		try {
			JSONObject obj = JSONObject.parseObject(json);
			return obj;
		} catch (Exception e) {
			throw new ClientSystemException("json decode error  json = " + json);
		}
	}

	public static JSONObject jsonCheck(JSONObject json) {
		if (json == null || json.equals("")) {
			throw new ClientSystemException("json is null");
		}
		return json;
	}
	
}
