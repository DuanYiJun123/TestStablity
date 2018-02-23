package com.cloudwalk.test.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonDecoder {
	private static JsonDecoder instance = new JsonDecoder();

	private JsonDecoder() {
	}

	public static JsonDecoder instance() {
		return instance;
	}

	public <T> T jsonToObj(Class<T> clz, String json) {
		try {
			JSONObject jsonObject = JSONObject.parseObject(json);
			return jsonToObj(clz, jsonObject);
		} catch (Exception e) {
			throw new ClientSystemException("json decode error json = " + json);
		}
	}

	public <T> T jsonToObj(Class<T> clz, JSONObject jsonObj) {
		if(jsonObj==null){
			return null;
		}
		try {
			Constructor<T> declaredConstructor = clz.getDeclaredConstructor();
			declaredConstructor.setAccessible(true);
			T newInstance = declaredConstructor.newInstance();
			Field[] fields = clz.getFields();
			for (Field field : fields) {
				JsonObject jsonObjectAnnotation = field.getAnnotation(JsonObject.class);
				JsonArrary jsonArraryAnnotation = field.getAnnotation(JsonArrary.class);
				if (jsonObjectAnnotation == null && jsonArraryAnnotation == null) {
					setField(field, newInstance, jsonObj);
				} else if (jsonObjectAnnotation != null && jsonArraryAnnotation == null) {
					JSONObject jsonObject = jsonObj.getJSONObject(field.getName());
					field.set(newInstance, jsonToObj(field.getType(), jsonObject));
				} else if (jsonObjectAnnotation == null && jsonArraryAnnotation != null) {
					if (!field.getType().isAssignableFrom(List.class)) {
						throw new ClientSystemException("json arrary annotation must be assignable from list");
					}

					JSONArray jsonArray = jsonObj.getJSONArray(field.getName());
					if (jsonArray == null) {
						field.set(newInstance, null);
					} else {
						ArrayList<Object> list = new ArrayList<>();

						for (int i = 0; i < jsonArray.size(); i++) {
							Type genericType = field.getGenericType();
							if (!ParameterizedType.class.isAssignableFrom(genericType.getClass())) {
								throw new ClientSystemException("json arrary annotation must be assignable from list");
							}
							Type[] types = ((ParameterizedType) genericType).getActualTypeArguments();
							if (types == null || types.length != 1) {
								throw new ClientSystemException("json arrary annotation must be assignable from list");
							}

							list.add(jsonToObj(getClass(types[0]), jsonArray.getJSONObject(i)));
						}
						field.set(newInstance, list);
					}
				} else {
					throw new ClientSystemException("JsonObject or JsonArrary must be only one");
				}
			}
			return newInstance;
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException | ClassNotFoundException e) {
			throw new ClientSystemException("this class instantiated error", e);
		}

	}

	private static final String TYPE_CLASS_NAME_PREFIX = "class ";
	private static final String TYPE_INTERFACE_NAME_PREFIX = "interface ";

	private static String getClassName(Type type) {
		if (type == null) {
			return "";
		}
		String className = type.toString();
		if (className.startsWith(TYPE_CLASS_NAME_PREFIX)) {
			className = className.substring(TYPE_CLASS_NAME_PREFIX.length());
		} else if (className.startsWith(TYPE_INTERFACE_NAME_PREFIX)) {
			className = className.substring(TYPE_INTERFACE_NAME_PREFIX.length());
		}
		return className;
	}

	private static Class<?> getClass(Type type) throws ClassNotFoundException {
		String className = getClassName(type);
		if (className == null || className.isEmpty()) {
			return null;
		}
		return Class.forName(className);
	}

	private static void setField(Field field, Object obj, JSONObject json) {
		Class<?> type = field.getType();
		try {
			if (type.isAssignableFrom(Integer.class)) {
				field.set(obj, json.getInteger(field.getName()));
			} else if (type.isAssignableFrom(String.class)) {
				field.set(obj, json.getString(field.getName()));
			} else if (type.isAssignableFrom(Double.class)) {
				field.set(obj, json.getDouble(field.getName()));
			} else if (type.isAssignableFrom(Float.class)) {
				field.set(obj, json.getFloat(field.getName()));
			} else if (type.isAssignableFrom(Boolean.class)) {
				field.set(obj, json.getBoolean(field.getName()));
			} else if (type.isAssignableFrom(Byte.class)) {
				field.set(obj, json.getByte(field.getName()));
			} else if (type.isAssignableFrom(Byte[].class)) {
				field.set(obj, json.getBytes(field.getName()));
			} else if (type.isAssignableFrom(Long.class)) {
				field.set(obj, json.getLong(field.getName()));
			} else if (type.isAssignableFrom(Short.class)) {
				field.set(obj, json.getShort(field.getName()));
			} else if (type.isAssignableFrom(Date.class)) {
				field.set(obj, json.getDate(field.getName()));
			} else if (type.isAssignableFrom(Timestamp.class)) {
				field.set(obj, json.getTimestamp(field.getName()));
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ClientSystemException("this class instantiated error", e);
		}
	}
}
