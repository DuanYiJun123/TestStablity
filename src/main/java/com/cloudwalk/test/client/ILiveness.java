package com.cloudwalk.test.client;

public interface ILiveness {

	/**
	 * <b>动作活体识别结果</b></br>
	 * </br>
	 * <b>result:</b> 0 表示活体检测过程成功，非 0 表示失败</br>
	 * <b>code:</b> 1:判断为活体 2:判断为扣眼攻击 3:判断为扣嘴攻击 4:判断为半张脸攻击 5:判断为视频回放攻击 6:判断为黑白图片
	 * 7:判断为纸面攻击 8:判断为边框（包括纸面、手机等边框） 9:判断为摩尔纹攻击 10:判断为脸优攻击 11:判断为纸面攻击(光流)
	 * 12:无法判断</br>
	 * 
	 * 
	 * @author xue.wen yijun.duan
	 */
	public static class LivenessResult {
		public Integer result;
		public Integer code;
	}

	/**
	 * * <b>动作活体识别</b></br>
	 * 
	 * @param param
	 *            图片数据的 base64 编码字符串,图片大小限制为 3M 以内 </br>
	 *            格式为：原图片base64,x1,x2,x3,x4,x5,x6,x7,x8,x9,y1,y2,y3,y4,y5,y6,y7,
	 *            y8,y9,关键点分数,pitch,yaw,roll_原图片base64,x1,x2,x3,x4,x5,x6,x7,x8,
	 *            x9,y1,y2,y3,y4,y5,y6,y7,y8,y9,关键点分数,pitch,yaw,roll
	 * @return {@link LivenessResult}
	 */
	@Mapping("/faceliveness")
	@HttpType(HttpPostType.KEY_VALUE)
	LivenessResult liveness(@URLEncod @Key("param") String param);
}
