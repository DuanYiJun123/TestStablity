package com.cloudwalk.test.client;

public interface IIDCardOcr {
	/**
	 * <b>人脸�?测结�?</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>type:</b> 1 表示正面�?0 表示反面</br>
	 * <b>code:</b> 0�? 正常身份�? 1�? 复印件身份证 2�? 手机翻拍身份�? 3�?
	 * 电脑翻拍身份�?</br>
	 * <b>address:</b>地址</br>
	 * <b>name:</b>姓名</br>
	 * <b>folk:</b>民族</br>
	 * <b>cardno:</b>身份证号�?</br>
	 * <b>birthday:</b>生日 </br>
	 * <b>sex:</b>性别，男或女</br>
	 * <b>province:</b>�?(�?)，如重庆�?</br>
	 * <b>city:</b>�?(�?)，如九龙坡区</br>
	 * <b>face:</b>{@link IDCardOcrResultItemFace}</br>
	 * <b>Head_Rect:</b>{@link IDCardOcrResultItemHeadRect}</br>
	 * <b>validdate1:</b>有效期起 yyyymmdd，如 2010022</br>
	 * <b>Validdate2:</b>有效期止 yyyymmdd，如 2010022</br>
	 * <b>authority:</b>签发机关 </br>
	 * 
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class IDCardOcrResult {
		public Integer result;
		public String info;
		public String type;
		public String code;

		public String address;
		public String name;
		public String folk;
		public String cardno;
		public String birthday;
		public String sex;
		public String province;
		public String city;
		public String validdate1;
		public String Validdate2;
		public String authority;

		@JsonObject
		public IDCardOcrResultItemFace face;
		@JsonObject
		public IDCardOcrResultItemHeadRect Head_Rect;

		/**
		 * <b>人脸信息 getFace 等于 1 时才会输�?</b></br>
		 * </br>
		 * <b>left:</b> 人脸在身份证�? x 坐标</br>
		 * <b>top:</b> 人脸在身份证�? y 坐标</br>
		 * <b>width:</b> 人脸宽度 </br>
		 * <b>height:</b> 人脸高度 </br>
		 * <b>image:</b> 人脸图片<b>(base64 编码)</b> </br>
		 * 
		 * 
		 * @author xue.wen yijun.duan
		 *
		 */
		public static class IDCardOcrResultItemFace {
			public Integer left;
			public Integer top;
			public Integer width;
			public Integer height;
			public String image;
		}

		/**
		 * <b>身份证中人脸框的位置，分别包含左上�?�右上�?�右下�?�左下四个角�?</b></br>
		 * </br>
		 * <b>lb:</b> 左下</br>
		 * <b>lt:</b> 左上</br>
		 * <b>rb:</b> 右下</br>
		 * <b>rt:</b> 右上</br>
		 * 
		 * @author xue.wen yijun.duan
		 *
		 */
		public static class IDCardOcrResultItemHeadRect {
			@JsonObject
			public Node lb;
			@JsonObject
			public Node lt;
			@JsonObject
			public Node rb;
			@JsonObject
			public Node rt;

			/**
			 * <b>坐标</b></br>
			 * </br>
			 * 
			 * <b>x:</b> 横坐�?</br>
			 * <b>y:</b> 纵坐�?</br>
			 * 
			 * @author xue.wen yijun.duan
			 *
			 */
			public static class Node {
				public Double x;
				public Double y;
			}
		}
	}

	/**
	 * <b>身份证识�?</b></br>
	 * </br>
	 * 
	 * 1. 身份证正反面自动识别�? </br>
	 * 2. 身份证正面信息识别，包括姓名、出生年月�?�地�?、民族�?? </br>
	 * 3. 返回身份证正面图片中人脸位置及人脸截图�?? </br>
	 * 4. 身份证反面信息识别，包括签发机关、有效期限�?? </br>
	 * 
	 * 
	 * 
	 * @param url
	 *            待处理图片的 URL 或�?? 通过 POST 方法上传的图片数�? (base64
	 *            编码)，原始图片大小需要小�? 3M
	 *            输入图像应保证身份证部分占照片绝大部分，且身份证短边的长 度不低于 350
	 *            像素，长边的长度不低�? 450 像素�?
	 * 
	 * @param getFace
	 *            是否返回人脸, 正面身份证才返回结果
	 * @return {@link IDCardOcrResult}
	 */

	@Mapping("/ocr")
	@HttpType(HttpPostType.KEY_VALUE)
	IDCardOcrResult ocrByUrl(@URLEncod @Key("url") String url, @Key("getFace") boolean getFace);

	/**
	 * <b>身份证识�?</b></br>
	 * </br>
	 * 
	 * 1. 身份证正反面自动识别�? </br>
	 * 2. 身份证正面信息识别，包括姓名、出生年月�?�地�?、民族�?? </br>
	 * 3. 返回身份证正面图片中人脸位置及人脸截图�?? </br>
	 * 4. 身份证反面信息识别，包括签发机关、有效期限�?? </br>
	 * 
	 * 
	 * 
	 * @param img
	 *            待处理图片的 URL 或�?? 通过 POST 方法上传的图片数�? (base64
	 *            编码)，原始图片大小需要小�? 3M
	 *            输入图像应保证身份证部分占照片绝大部分，且身份证短边的长 度不低于 350
	 *            像素，长边的长度不低�? 450 像素�?
	 * 
	 * @param getFace
	 *            是否返回人脸, 正面身份证才返回结果
	 * @return {@link IDCardOcrResult}
	 */
	@Mapping("/ocr")
	@HttpType(HttpPostType.KEY_VALUE)
	IDCardOcrResult ocrByImg(@URLEncod @Key("img") String img, @Key("getFace") boolean getFace);
}
