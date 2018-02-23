package com.cloudwalk.test.client;

import java.util.List;

public interface IFace {

	// ==============================================FaceDetect=================================================
	/**
	 * <b>人脸�?测结�?</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>faces:</b> 返回�?测到的人脸信息集�? {@link FaceDetectResultItem}</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class FaceDetectResult {
		public Integer result;
		public String info;
		@JsonArrary
		public List<FaceDetectResultItem> faces;

		/**
		 * <b>�?测到的人�?</b></br>
		 * </br>
		 * <b>x:</b> 人脸 x 坐标 </br>
		 * <b>y:</b> 人脸 y 坐标 </br>
		 * <b>width:</b> 人脸宽度 </br>
		 * <b>height:</b> 人脸高度 </br>
		 * <b>img:</b> Base64 编码的人脸图片数据，<b>如果 mode=false �?
		 * 为null</b> </br>
		 * 
		 * @author xue.wen yijun.duan
		 *
		 */
		public static class FaceDetectResultItem {
			public Integer x;
			public Integer y;
			public Integer width;
			public Integer height;
			public String img;
		}
	}

	/**
	 * <b>人脸�?�?</b><br>
	 * <br>
	 * 人脸�?测指在图片中准确地标定出人脸位置，对不同角度、光照�?�表情�?�遮挡�?�年龄的人脸都有较好的�?
	 * �应性�?�对于任意一幅给定的图像�?
	 * 采用�?定的策略对其进行搜索以确定其中是否含有人脸，如果有，则返回所有人脸的位置和大小�??
	 * 
	 * @param url
	 *            待处理图片的 URL 或�?? 通过 POST 方法上传的图片数�?<b>(base64 编码)</b>
	 *            ，原始图片大小需�? <b>小于 3M</b>，格式限定为<b>jpg,bmp,png</b> 三种格式
	 * @param mode
	 *            是否返回每张人脸图片 true 表示返回，false 表示不返�?
	 * @return {@link FaceDetectResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/detect")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceDetectResult detectByUrl(@URLEncod @Key("url") String url, @Key("mode") boolean mode);

	/**
	 * <b>人脸�?�?</b><br>
	 * <br>
	 * 人脸�?测指在图片中准确地标定出人脸位置，对不同角度、光照�?�表情�?�遮挡�?�年龄的人脸都有较好的�?
	 * �应性�?�对于任意一幅给定的图像�?
	 * 采用�?定的策略对其进行搜索以确定其中是否含有人脸，如果有，则返回所有人脸的位置和大小�??
	 * 
	 * @param img
	 *            待处理图片的 URL 或�?? 通过 POST 方法上传的图片数�?<b>(base64 编码)</b>
	 *            ，原始图片大小需�? <b>小于 3M</b>，格式限定为<b>jpg,bmp,png</b> 三种格式
	 * @param mode
	 *            是否返回每张人脸图片 true 表示返回，false 表示不返�?
	 * @return {@link FaceDetectResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/detect")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceDetectResult detectByUImg(@URLEncod @Key("img") String img, @Key("mode") boolean mode);

	/**
	 * <b>人脸属�?�分析结�?</b></br>
	 * </br>
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息 </br>
	 * <b>faces:</b> {@link FaceAttributeResultItem}</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class FaceAttributeResult {
		public Integer result;
		public String info;
		@JsonArrary
		public List<FaceAttributeResultItem> faces;

		/**
		 * <b>返回�?测到的人�?</b></br>
		 * 
		 * <b>x:</b> 人脸 x 坐标 </br>
		 * <b>y:</b> 人脸 u 坐标 </br>
		 * <b>width:</b> 人脸宽度 </br>
		 * <b>height:</b> 人脸高度 </br>
		 * <b>age:</b> 年龄 </br>
		 * <b>gender:</b> 性别 -1 表示女，1 表示�? </br>
		 * 
		 * @author xue.wen yijun.duan
		 *
		 */
		public static class FaceAttributeResultItem {
			public Integer x;
			public Integer y;
			public Integer width;
			public Integer height;
			public String age;
			public String gender;
		}
	}

	/**
	 * <b>人脸属�?�分�?</b></br>
	 * </br>
	 * 
	 * 人脸属�?�分析技术，是指对于任意�?副给定的人脸图像，对其进行分析，返回人脸的�?? 别�?�年�?</br>
	 * 
	 * 
	 * 
	 * @param img
	 *            待处理图片的 URL 或�?? 通过 POST 方法上传的图片数�? <b>(base64 编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b>，格式限定为<b> jpg,bmp,png</b> 三种格式
	 * @return {@link FaceAttributeResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/attribute")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceAttributeResult attributeByImg(@URLEncod @Key("img") String img);

	/**
	 * <b>人脸属�?�分�?</b></br>
	 * </br>
	 * 
	 * 人脸属�?�分析技术，是指对于任意�?副给定的人脸图像，对其进行分析，返回人脸的�?? 别�?�年�?</br>
	 * 
	 * 
	 * 
	 * @param url
	 *            待处理图片的 URL 或�?? 通过 POST 方法上传的图片数�? <b>(base64 编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b>，格式限定为<b> jpg,bmp,png</b> 三种格式
	 * @return {@link FaceAttributeResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/attribute")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceAttributeResult attributeByURL(@URLEncod @Key("url") String url);

	// ==============================================FaceCompare=================================================
	/**
	 * <b>人脸比对结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>score:</b> 返回人脸相似�?</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class FaceCompareResult {
		public Float score;
		public Integer result;
		public String info;
	}

	/**
	 * <b>人脸比对</b></br>
	 * </br>
	 * 
	 * 
	 * @param urlA
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b> </br>
	 *            1. 每张图片中的人脸可以�?90度�??180度�??270度旋�? </br>
	 *            2. 每张图片中只能有�?个人脸，当有多个人脸时取�?大的人脸为目标人�?</br>
	 * 
	 * 
	 * @param urlB
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b></br>
	 *            1. 每张图片中的人脸可以�?90度�??180度�??270度旋�? </br>
	 *            2. 每张图片中只能有�?个人脸，当有多个人脸时取�?大的人脸为目标人�?</br>
	 * 
	 * @return {@link FaceCompareResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/compare")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceCompareResult faceCompareByUrl(@URLEncod @Key("urlA") String urlA, @URLEncod @Key("urlB") String urlB);

	/**
	 * <b>人脸比对</b></br>
	 * </br>
	 * 
	 * 
	 * @param imgA
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b> </br>
	 *            1. 每张图片中的人脸可以�?90度�??180度�??270度旋�? </br>
	 *            2. 每张图片中只能有�?个人脸，当有多个人脸时取�?大的人脸为目标人�?</br>
	 * 
	 * 
	 * @param imgB
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b></br>
	 *            1. 每张图片中的人脸可以�?90度�??180度�??270度旋�? </br>
	 *            2. 每张图片中只能有�?个人脸，当有多个人脸时取�?大的人脸为目标人�?</br>
	 * 
	 * @return {@link FaceCompareResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/compare")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceCompareResult faceCompareByImg(@URLEncod @Key("imgA") String imgA, @URLEncod @Key("imgB") String imgB);

	// ==============================================FaceCompareExt1=================================================
	/**
	 * <b>人脸相似度ext比较结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>score:</b> 返回�?高相似度结果</br>
	 * <b>faces:</b> 返回人脸信息 {@link FaceCompareExt1ResultItem}</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class FaceCompareExt1Result {
		public Integer result;
		public Float score;
		public String info;

		/**
		 * <b>�?测到的人�?</b></br>
		 * </br>
		 * <b>x:</b> 人脸 x 坐标 </br>
		 * <b>y:</b> 人脸 y 坐标 </br>
		 * <b>width:</b> 人脸宽度 </br>
		 * <b>height:</b> 人脸高度 </br>
		 * <b>score:</b> 和第�?张人脸的相似�?</b> </br>
		 * 
		 * @author xue.wen yijun.duan
		 *
		 */
		public static class FaceCompareExt1ResultItem {
			public Integer x;
			public Integer y;
			public Integer width;
			public Integer height;
			public Float score;

		}

	}

	/**
	 * <b>人脸比对拓展方式1</b></br>
	 * </br>
	 * 
	 * 
	 * @param urlA
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b> </br>
	 *            1. 第一张图片中的人脸可以有90度�??180度�??270度旋转�??</br>
	 *            2. 第一张图片中只能有一个人脸，当有多个人脸时取�?大的人脸为目标人脸�??</br>
	 *            3. 第二张图片中的人脸只能是正常状�?�，不能�?90度�??180度�??270度旋转�??</br>
	 *            4. 第二张图片中可以有多个人脸�??</br>
	 *            5. 结果会返回第二张图片中每个人脸和第一张图片人脸的相似度�??</br>
	 * 
	 * 
	 * @param urlB
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b></br>
	 *            1. 第一张图片中的人脸可以有90度�??180度�??270度旋转�??</br>
	 *            2. 第一张图片中只能有一个人脸，当有多个人脸时取�?大的人脸为目标人脸�??</br>
	 *            3. 第二张图片中的人脸只能是正常状�?�，不能�?90度�??180度�??270度旋转�??</br>
	 *            4. 第二张图片中可以有多个人脸�??</br>
	 *            5. 结果会返回第二张图片中每个人脸和第一张图片人脸的相似度�??</br>
	 * 
	 * @return {@link FaceCompareExt1Result}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/compare/ext1")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceCompareExt1Result faceCompareExt1ByUrl(@URLEncod @Key("urlA") String urlA, @URLEncod @Key("urlB") String urlB);

	/**
	 * <b>人脸比对拓展方式1</b></br>
	 * </br>
	 * 
	 * 
	 * @param imgA
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b> </br>
	 *            1. 第一张图片中的人脸可以有90度�??180度�??270度旋转�??</br>
	 *            2. 第一张图片中只能有一个人脸，当有多个人脸时取�?大的人脸为目标人脸�??</br>
	 *            3. 第二张图片中的人脸只能是正常状�?�，不能�?90度�??180度�??270度旋转�??</br>
	 *            4. 第二张图片中可以有多个人脸�??</br>
	 *            5. 结果会返回第二张图片中每个人脸和第一张图片人脸的相似度�??</br>
	 * 
	 * 
	 * @param imgB
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b></br>
	 *            1. 第一张图片中的人脸可以有90度�??180度�??270度旋转�??</br>
	 *            2. 第一张图片中只能有一个人脸，当有多个人脸时取�?大的人脸为目标人脸�??</br>
	 *            3. 第二张图片中的人脸只能是正常状�?�，不能�?90度�??180度�??270度旋转�??</br>
	 *            4. 第二张图片中可以有多个人脸�??</br>
	 *            5. 结果会返回第二张图片中每个人脸和第一张图片人脸的相似度�??</br>
	 * 
	 * @return {@link FaceCompareExt1Result}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/compare/ext1")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceCompareExt1Result faceCompareExt1ByImg(@URLEncod @Key("imgA") String imgA, @URLEncod @Key("imgB") String imgB);

	// ==============================================removeWater=================================================
	/**
	 * <b>去除水印结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>img:</b> 返回图片base64编码 </br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class RemoveWaterResult {
		public String img;
		public Integer result;
		public String info;

	}

	/**
	 * <b>人脸去网�?</b></br>
	 * </br>
	 * 
	 * 
	 * @param url
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b> </br>
	 * @return {@link RemoveWaterResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/removeWater")
	@HttpType(HttpPostType.KEY_VALUE)
	RemoveWaterResult removeWaterByUrl(@URLEncod @Key("url") String url);

	/**
	 * <b>人脸去网�?</b></br>
	 * </br>
	 * 
	 * 
	 * @param img
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b> </br>
	 * @return {@link RemoveWaterResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/removeWater")
	@HttpType(HttpPostType.KEY_VALUE)
	RemoveWaterResult removeWaterByImg(@URLEncod @Key("img") String img);

	// ==============================================Feature=================================================
	/**
	 * <b>提取特征结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>feature:</b> 成功时返回特征数据（base64编码�? </br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class FeatureResult {
		public String feature;
		public Integer result;
		public String info;

	}

	/**
	 * <b>特征提取</b></br>
	 * </br>
	 * 
	 * 
	 * @param url
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b> </br>
	 *            1. 每张图片中的人脸可以�?90度�??180度�??270度旋�? </br>
	 *            2. 每张图片中只能有�?个人脸，当有多个人脸时取�?大的人脸为目标人�?</br>
	 * @return {@link FeatureResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/feature")
	@HttpType(HttpPostType.KEY_VALUE)
	FeatureResult featureByUrl(@URLEncod @Key("url") String url);

	/**
	 * <b>特征提取</b></br>
	 * </br>
	 * 
	 * 
	 * @param img
	 *            待处理图片的URL 或�?? 通过POST方法上传的图片数�?<b>(base64编码)</b>
	 *            ，原始图片大小需要小�? <b>3M</b> </br>
	 *            1. 每张图片中的人脸可以�?90度�??180度�??270度旋�? </br>
	 *            2. 每张图片中只能有�?个人脸，当有多个人脸时取�?大的人脸为目标人�?</br>
	 * @return {@link FeatureResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/feature")
	@HttpType(HttpPostType.KEY_VALUE)
	FeatureResult featureByImg(@URLEncod @Key("img") String img);

	// ==============================================similarityByFeature=================================================
	/**
	 * <b>特征相似度比较结�?</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>score:</b> 相似�? <b>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class SimilarityByFeatureResult {
		public Integer result;
		public String info;
		public Float score;

	}

	/**
	 * <b>特征相似度比�?</b></br>
	 * </br>
	 * 
	 * 
	 * @param featureA
	 *            待比较的特征数据(base64编码)
	 * @param featureB
	 *            待比较的特征数据(base64编码)
	 * @return {@link SimilarityByFeatureResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/tool/similarityByFeature")
	@HttpType(HttpPostType.KEY_VALUE)
	SimilarityByFeatureResult similarityByFeature(@URLEncod @Key("featureA") String featureA,
			@URLEncod @Key("featureB") String featureB);

	// ==============================================groupCreate=================================================
	/**
	 * <b>人脸管理组创建结�?</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class GroupCreateResult {
		public Integer result;
		public String info;

	}

	/**
	 * <b>人脸管理组创�?</b></br>
	 * </br>
	 * 
	 * 
	 * @param groupId
	 *            组编�?(仅支持数字和字母)
	 * @param tag
	 *            额外信息(128字节限制)
	 * @return {@link GroupCreateResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/clustering/group/create")
	@HttpType(HttpPostType.KEY_VALUE)
	GroupCreateResult groupCreate(@Key("groupId") String groupId, @Key("tag") String tag);

	// ==============================================groupDelete=================================================
	/**
	 * <b>人脸管理组删除结�?</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class GroupDeleteResult {
		public Integer result;
		public String info;

	}

	/**
	 * <b>人脸管理组删�?</b></br>
	 * </br>
	 * 
	 * 
	 * @param groupId
	 *            组编�?(仅支持数字和字母)
	 * 
	 * @return {@link GroupDeleteResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/clustering/group/delete")
	@HttpType(HttpPostType.KEY_VALUE)
	GroupDeleteResult groupDelete(@Key("groupId") String groupId);

	// ==============================================query====================================================
	/**
	 * <b>分页查询组信息结�?</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>total:</b> 返回总记录数</br>
	 * <b>rows:</b>记录信息</br>
	 * {@link groupQueryResultItem}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class GroupQueryResult {
		public Integer result;
		public String info;
		public Integer total;
		@JsonArrary
		public List<groupQueryResultItem> rows;

		/**
		 * <b>分页查询组信息结果信�?</b></br>
		 * <br>
		 * 
		 * <b>groupId:</b> 组编�? </br>
		 * <b>info:</b> 创建组时输入的tag</br>
		 * <b>count:</b> 当前组中人脸�?</br>
		 * 
		 * 
		 * @author xue.wen yijun.duan
		 *
		 */
		public static class groupQueryResultItem {
			public String groupId;
			public String info;
			public Integer count;
		}
	}

	/**
	 * <b>人脸管理通过页数查询组信�?</b></br>
	 * <br>
	 * 
	 * @param pageNumber
	 *            页号(�?1�?�?)
	 * 
	 * @param pageSize
	 *            每页记录条数
	 * @param faceCount
	 *            是否返回组中人脸数，true 时才返回
	 * @return {@link GroupQueryResult}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	@Mapping("/face/clustering/group/query")
	@HttpType(HttpPostType.KEY_VALUE)
	@ExtParams(key = { "type" }, value = { "queryByPage" })
	GroupQueryResult groupQueryByPage(@Key("pageNumber") int pageNumber, @Key("pageSize") int pageSize,
			@Key("faceCount") boolean faceCount);

	/**
	 * <b>人脸管理通过组id查询组信�?</b></br>
	 * <br>
	 * 
	 * @param groupId
	 *            组编�?
	 * 
	 * @return {@link GroupQueryResult}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	@Mapping("/face/clustering/group/query")
	@HttpType(HttpPostType.KEY_VALUE)
	@ExtParams(key = { "type" }, value = { "queryByGroupId" })
	GroupQueryResult groupQueryByGroupId(@Key("groupId") String groupId);

	// ==============================================faceCreate====================================================
	/**
	 * <b>添加人脸结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>faceId:</b>人脸的唯�?编号，用于后续人脸删�?</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class FaceCreateResult {
		public Integer result;
		public String info;
		public String faceId;
	}

	/**
	 * <b>通过url添加人脸</b></br>
	 * <br>
	 * 
	 * @param groupId
	 *            组编�?
	 * @param tag
	 *            额外信息
	 * @param url
	 *            待处理图片的URL 或�??
	 *            通过POST方法上传的图片数�?(base64编码)，原始图片大小需要小�?3M
	 * @return {@link FaceCreateResult}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	@Mapping("/face/clustering/face/create")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceCreateResult faceCreateByUrl(@Key("groupId") String groupId, @Key("tag") String tag,
			@URLEncod @Key("url") String url);

	/**
	 * <b>通过img添加人脸</b></br>
	 * <br>
	 * 
	 * @param groupId
	 *            组编�?
	 * @param tag
	 *            额外信息
	 * @param img
	 *            待处理图片的URL 或�??
	 *            通过POST方法上传的图片数�?(base64编码)，原始图片大小需要小�?3M
	 * @return {@link FaceCreateResult}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	@Mapping("/face/clustering/face/create")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceCreateResult faceCreateByImg(@Key("groupId") String groupId, @Key("tag") String tag,
			@URLEncod @Key("img") String img);

	// ==============================================faceDelete====================================================
	/**
	 * <b>刪除人脸结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class FaceDeleteResult {
		public Integer result;
		public String info;
	}

	/**
	 * <b>删除人脸</b></br>
	 * <br>
	 * 
	 * @param groupId
	 *            组编�?
	 * @param faceId
	 *            人脸的唯�?编号
	 * @return {@link FaceDeleteResult}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	@Mapping("/face/clustering/face/delete")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceDeleteResult faceDelete(@Key("groupId") String groupId, @Key("faceId") String faceId);

	// ==============================================faceEdit====================================================
	/**
	 * <b>编辑人脸结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class FaceEditResult {
		public String info;
		public Integer result;
	}

	/**
	 * <b>通过url来编辑人�?</b></br>
	 * <br>
	 * 
	 * @param groupId
	 *            组编�?
	 * @param faceId
	 *            人脸的唯�?编号
	 * @param url
	 *            待处理图片的URL 或�??
	 *            通过POST方法上传的图片数�?(base64编码)，原始图片大小需要小�?3M
	 * @param tag
	 *            额外信息
	 * @return {@link FaceEditResult}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	@Mapping("/face/clustering/face/edit")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceEditResult faceEditByUrl(@Key("groupId") String groupId, @Key("faceId") String faceId,
			@URLEncod @Key("url") String url, @Key("tag") String tag);

	/**
	 * <b>通过img来编辑人�?</b></br>
	 * <br>
	 * 
	 * @param groupId
	 *            组编�?
	 * @param faceId
	 *            人脸的唯�?编号
	 * @param img
	 *            待处理图片的URL 或�??
	 *            通过POST方法上传的图片数�?(base64编码)，原始图片大小需要小�?3M
	 * @param tag
	 *            额外信息
	 * @return {@link FaceEditResult}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	@Mapping("/face/clustering/face/edit")
	@HttpType(HttpPostType.KEY_VALUE)
	FaceEditResult faceEditByImg(@Key("groupId") String groupId, @Key("faceId") String faceId,
			@URLEncod @Key("img") String img, @Key("tag") String tag);

	// ==============================================QueryFace====================================================
	/**
	 * <b>查询人脸结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>total:</b>总记录数 <b>rows:</b>记录信息 {@link QueryFaceResultItem}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */

	public static class QueryFaceResult {
		public Integer result;
		public String info;
		public Integer total;
		@JsonArrary
		public List<QueryFaceResultItem> rows;

		/**
		 * <b>查询人脸结果记录信息</b></br>
		 * <br>
		 * 
		 * <b>faceId:</b> 人脸编号</br>
		 * <b>tag:</b> 创建人脸时输入的tag</br>
		 * 
		 * @author xue.wen yijun.duan
		 *
		 */
		public static class QueryFaceResultItem {
			public String faceId;
			public String tag;
		}
	}

	/**
	 * <b>通过FaceId来查询人�?</b></br>
	 * <br>
	 * 
	 * @param groupId
	 *            组编�?
	 * @param faceId
	 *            人脸的唯�?编号
	 * @return {@link QueryFaceResult}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	@Mapping("/face/clustering/face/query")
	@HttpType(HttpPostType.KEY_VALUE)
	@ExtParams(key = { "type" }, value = { "queryByFaceId" })
	QueryFaceResult queryFaceByFaceId(@Key("groupId") String groupId, @Key("faceId") String faceId);

	// ==================================================groupidentify====================================
	/**
	 * <b>组识别结�?</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>faces:</b>记录信息 {@link GroupIdentifyResultItem}</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class GroupIdentifyResult {
		public Integer result;
		public String info;
		@JsonArrary
		public List<GroupIdentifyResultItem> faces;

		/**
		 * <b>组识别结果条�?</b></br>
		 * <br>
		 * 
		 * <b>faceId:</b> 注册的人脸编�? </br>
		 * <b>score:</b> 相似�?</br>
		 * <b>tag:</b>创建人脸时输入的tag
		 * 
		 * @author xue.wen yijun.duan
		 *
		 */
		public static class GroupIdentifyResultItem {
			public String faceId;
			public Float score;
			public String tag;
		}
	}

	/**
	 * <b>组识�?</b></br>
	 * <br>
	 * 
	 * @param groupId
	 *            组编�?
	 * @param img
	 *            人脸图片数据(base64编码)，原始图片大小需要小�?3M
	 * @param topN
	 *            返回N个结�?
	 * @return {@link GroupIdentifyResult}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	@Mapping("/face/recog/group/identify")
	@HttpType(HttpPostType.KEY_VALUE)
	GroupIdentifyResult groupIdentify(@Key("groupId") String groupId, @URLEncod @Key("img") String img,
			@Key("topN") int topN);

	// ==================================================identify-ext1====================================
	/**
	 * <b>组识别拓展方�?1结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>faces:</b>记录信息 {@link IdentifyExt1ResultItem}</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class GroupIdentifyExt1Result {
		public Integer result;
		public String info;
		@JsonArrary
		public List<IdentifyExt1ResultItem> faces;

		/**
		 * <b>组识别拓展方�?1结果条目</b></br>
		 * <br>
		 * 
		 * <b>faceId:</b> 注册的人脸编�? </br>
		 * <b>x:</b> 人脸x坐标</br>
		 * <b>y:</b> 人脸y坐标</br>
		 * <b>width:</b> 人脸宽度</br>
		 * <b>height:</b> 人脸高度</br>
		 * <b>score:</b> 相似�?</br>
		 * <b>tag:</b>创建人脸时输入的tag�?128字节限制�?
		 * 
		 * @author xue.wen yijun.duan
		 *
		 */
		public static class IdentifyExt1ResultItem {
			public String faceId;
			public Float score;
			public Integer x;
			public Integer y;
			public Integer width;
			public Integer height;
			public String tag;
		}
	}

	/**
	 * <b>组识别扩展方�?1</b></br>
	 * <br>
	 * 
	 * @param groupId
	 *            组编�?
	 * @param img
	 *            人脸图片数据(base64编码)，原始图片大小需要小�?3M</br>
	 *            1. 图片中的人脸只能是正常状态，不能�?90度�??180度�??270度旋转�??</br>
	 *            2. 图片中可以有多个人脸�?
	 * 
	 * @return {@link GroupIdentifyExt1Result}
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	@Mapping("/face/recog/group/identify/ext1")
	@HttpType(HttpPostType.KEY_VALUE)
	GroupIdentifyExt1Result groupIdentifyExt1(@Key("groupId") String groupId, @URLEncod @Key("img") String img);

	/**
	 * <b>组识�?(传图片和人脸编号)结果</b></br>
	 * </br>
	 * 
	 * <b>result:</b> 返回结果�?0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息</br>
	 * <b>score:</b>相似�?</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class FaceGroupCompareResult {
		public Integer result;
		public String info;
		public Float score;
	}

	/**
	 * <b>组识�?(传图片和人脸编号)</b></br>
	 * </br>
	 * 
	 * 比对给定图片和组中指定人脸的相似�?
	 * 
	 * @param groupId
	 *            组编�?
	 * @param faceId
	 *            人脸编号
	 * @param img
	 *            人脸图片数据<b>(base64 编码)</b>，原始图片大小需�? <b>小于 3M</b>
	 *            ，格式限定为 <b>jpg,bmp,png</b> 三种格式
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/face/recog/group/compare")
	@HttpType(HttpPostType.KEY_VALUE)
	public FaceGroupCompareResult compare(@Key("groupId") String groupId, @Key("faceId") String faceId,
			@URLEncod @Key("img") String img);
}
