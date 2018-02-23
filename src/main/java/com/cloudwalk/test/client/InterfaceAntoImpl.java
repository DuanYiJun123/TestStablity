package com.cloudwalk.test.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.net.URLEncoder;
import java.util.HashMap;

public class InterfaceAntoImpl {

	public static Object autoHttpImpl(String ip, String port, String interfaceClassName) {
		try {
			Class<?> clz = Thread.currentThread().getContextClassLoader().loadClass(interfaceClassName);
			Object newProxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
					new Class[] { clz }, new MyInvocationHandler(ip, port));
			return newProxyInstance;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object autoHttpImpl(String address, String interfaceClassName) {
		try {
			Class<?> clz = Thread.currentThread().getContextClassLoader().loadClass(interfaceClassName);
			Object newProxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
					new Class[] { clz }, new MyInvocationHandler(address));
			return newProxyInstance;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object autoHttpImpl(String address, Class<?> interfaceClass) {
		Object newProxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				new Class[] { interfaceClass }, new MyInvocationHandler(address));
		return newProxyInstance;
	}

	private static class MyInvocationHandler implements InvocationHandler {
		private String address;

		MyInvocationHandler(String ip, String port) {
			this.address = ip + ":" + port;
		}

		MyInvocationHandler(String address) {
			this.address = address;
		}

		@SuppressWarnings("deprecation")
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			HashMap<String, String> params = new HashMap<>();

			Parameter[] parameters = method.getParameters();
			if (args.length != parameters.length) {
				throw new ClientSystemException("proxy method args count error");
			}

			Mapping mapping = method.getAnnotation(Mapping.class);
			if (mapping == null) {
				throw new ClientSystemException("proxy method Mapping not found");
			}

			HttpType httpType = method.getAnnotation(HttpType.class);
			if (httpType == null) {
				throw new ClientSystemException("proxy method httpType not found");
			}

			for (int i = 0; i < parameters.length; i++) {
				Parameter parameter = parameters[i];
				Key key = parameter.getAnnotation(Key.class);
				if (key == null) {
					throw new ClientSystemException("proxy method parameter name not found");
				}
				String value = args[i].toString();
				if (parameter.getAnnotation(URLEncod.class) != null) {
					value = URLEncoder.encode(value);
				}
				params.put(key.value(), value);
			}

			ExtParams extParams = method.getAnnotation(ExtParams.class);
			if (extParams != null) {
				String[] keys = extParams.key();
				String[] values = extParams.value();
				if (keys.length != values.length) {
					throw new ClientSystemException("proxy method ext params count error");
				}
				for (int i = 0; i < keys.length; i++) {
					params.put(keys[i], values[i]);
				}
			}

			String resultJson = HttpClientUtil.sendPostRequestByJava(this.address + mapping.value(), params);

			Class<?> returnType = method.getReturnType();

			return JsonDecoder.instance().jsonToObj(returnType, resultJson);

		}

	}
}
