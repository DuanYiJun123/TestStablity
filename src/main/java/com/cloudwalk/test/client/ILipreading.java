package com.cloudwalk.test.client;

public interface ILipreading {
	/**
	 * <b>唇语判定结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果，0 表示成功，非 0 为对应错误号 </br>
	 * <b>errorInfo:</b> 错误信息 </br>
	 * <b>code:</b> 唇语检测出现错误时，返回具体错误码</br>
	 * <b>faceImage:</b> result 为 0 的情况下，返回最佳人脸 jpg 格式</br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class LipreadingResult {
		public Integer result;
		public Integer code;
		public String errorInfo;
		public String faceImage;
	}

	/**
	 * 
	 * <b>唇语判断</b></br>
	 * </br>
	 * 
	 * 根据唇语标签信息和唇语视频判断是否通过检查
	 * 
	 * @param video
	 *            视频的 <b>base64</b> 编码
	 * @param label
	 *            输入的唇语信息
	 * @param type
	 *            视频的类型，如 mp4
	 * @param token
	 *            Token 信息
	 * @return {@link LipreadingResult}
	 * 
	 * @author xue.wen yijun.duan
	 */
	@Mapping("/lipreading")
	@HttpType(HttpPostType.KEY_VALUE)
	public LipreadingResult lipreading(@URLEncod @Key("video") String video, @Key("label") String label,
			@Key("type") String type, @Key("token") String token);

	// ====================labels=====================

	/**
	 * <b>标签获取结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果，0 表示成功，非 0 为对应错误号 </br>
	 * <b>label:</b> 返回的 labe </br>
	 * <b>token:</b> token 信息 </br>
	 * 
	 * @author xue.wen yijun.duan
	 *
	 */
	public static class LabelsResult {
		public Integer result;
		public String label;
		public String token;
	}

	/**
	 * <b>标签获取</b><br>
	 * <br>
	 * 
	 * 调用唇语识别接口需要先获取标签信息，然后根据该标签信息传入对应的唇语视频。
	 * 
	 * @param len
	 *            需要多长的 labels
	 * @return {@link LabelsResult}
	 */

	@Mapping("/labels")
	@HttpType(HttpPostType.KEY_VALUE)
	public LabelsResult labels(@Key("len") int len);

}
