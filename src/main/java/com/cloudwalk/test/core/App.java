package com.cloudwalk.test.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.dom4j.Element;

import com.cloudwalk.test.client.ClientFactory;
import com.cloudwalk.test.client.ClientFactory.Client;
import com.cloudwalk.test.client.FileUtil;
import com.cloudwalk.test.client.IFace.FeatureResult;
import com.cloudwalk.test.client.IFace.SimilarityByFeatureResult;
import com.cloudwalk.test.client.ResourcesUtil;
import com.cloudwalk.test.client.XmlUtil;

public class App {
	public static final String SERVER = get("server");

	private static final int HOUR = Integer.valueOf(get("hour"));
	private static final int THREADSIZE = Integer.valueOf(get("thread-size"));
	private static final ExecutorService SERVICE = Executors.newFixedThreadPool(THREADSIZE);
	private static final Client CLIENT = ClientFactory.getClient();
	private static final Map<String, String> RESULT_MAP = new ConcurrentHashMap<>();

	private static final File OUT = new File(
			ResourcesUtil.getAppRoot() + File.separator + "out-file" + File.separator + "Stablity.txt");
	private static final File tmp = new File(
			ResourcesUtil.getAppRoot() + File.separator + "out-file" + File.separator + "stabTmp.txt");

	private static boolean flg = true;

	private static String get(String key) {
		Element element = XmlUtil
				.get(ResourcesUtil.getAppRoot() + File.separator + "config" + File.separator + "config.xml")
				.element(key);
		if (element != null) {
			return element.getData().toString();
		}
		return null;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < THREADSIZE; i++) {
			SERVICE.execute(() -> {
				while (System.currentTimeMillis() - start <= HOUR * 3600_000) {
					doStablity();
				}
			});
		}

		SERVICE.shutdown();

		while (!SERVICE.isTerminated()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		FileUtil.createFileIfExitsDelete(OUT);
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUT), "utf-8"))) {
			if (RESULT_MAP.isEmpty()) {
				writer.write("Stablity OK");
				writer.write(" duration = " + HOUR + "h" + " thread-size = " + THREADSIZE);
				writer.newLine();
			} else {
				writer.write("Stablity ERROR");
				writer.newLine();
			}
			RESULT_MAP.forEach((k, v) -> {
				try {
					writer.write(k + " : " + v);
					writer.newLine();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!flg) {
			System.exit(-1);
		}
	}

	private static void doStablity() {
		getFeature();
	}

	private static void getFeature() {
		String path = get("pics");
		String flg = get("compare");
		File file = new File(path);
		if (!file.isDirectory()) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("check config.xml");
				e.printStackTrace();
			}
		}
		File[] listFiles = file.listFiles();
		for (File f : listFiles) {
			String img = FileUtil.FileToBase64(f);
			FeatureResult featureByImg = CLIENT.featureByImg(img);
			String feature = featureByImg.feature;
			int re = featureByImg.result;

			skip(featureByImg, f);

			if (Boolean.valueOf(flg)) {
				if (re == 0) {
					SimilarityByFeatureResult similarityByFeature = CLIENT.similarityByFeature(feature, feature);
					skip(similarityByFeature, f);
				}
			}
		}
	}

	private static String getBaseDir() {
		return FileUtil.getAppRoot();
	}

	private static void error(String mapping, String result) {
		flg = false;
		if (!RESULT_MAP.containsKey(mapping)) {
			RESULT_MAP.put(mapping, result);
		} else {
			RESULT_MAP.put(mapping, RESULT_MAP.get(mapping) + " plus " + result);
		}
	}

	private static void skip(Object object, File f) {
		String result = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(new Date());
		try {
			String name = object.getClass().getName();
			Field field = object.getClass().getField("result");
			if (field != null) {
				result = field.get(object).toString();
				System.out.println("time: " + format + " name " + name + " picName: " + f.getName() + " " + result);
				BufferedWriter bw = null;
				try {
					if (!tmp.exists()) {
						FileUtil.createDirAndFileIfNotExits(tmp);
					}
					bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmp, true)));
					if (!result.equals("0")) {
						bw.write("time: " + format + " name " + name + " picName: " + f.getName() + " " + result);
						bw.newLine();
						bw.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (result != null && result.replaceAll(" ", "").equals("0")) {
					return;
				}
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		error(object.getClass().getSimpleName(), result);
	}

}
