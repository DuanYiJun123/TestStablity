package com.cloudwalk.test.client;

public interface IBankOcr {
	/**
	 * <b>银行卡识别结果</b></br>
	 * <br>
	 * 
	 * <b>result:</b> 返回结果，0 表示成功，非 0 为对应错误号 </br>
	 * <b>info:</b> 返回详细信息 </br>
	 * <b>BankName:</b> 开卡行 </br>
	 * <b>CardNum:</b> 卡号 </br>
	 * <b>CardName:</b>卡名 </br>
	 * <b>CardType:</b> 卡种 </br>
	 * 
	 * @author xue.wen yijun.duan
	 */
	public static class BankOcrResult {
		public Integer result;
		public String info;
		public String BankName;
		public String CardNum;
		public String CardName;
		public String CardType;
	}

	/**
	 * <b>银行卡识别</b></br>
	 * </br>
	 * 
	 * 接口用于检测及识别银行卡图片的卡号。 该版本支持的银行卡类别包括平面卡及凹凸卡，其中 平面卡支持 19， 6-13, 4-4-4-4-3,
	 * 4-4-4-7 四种版式 凹凸卡支持 19， 6-13， 4-4-4-4， 6-6-6 四种版式</br>
	 * 
	 * @param url
	 *            待处理图片的 URL 或者 通过 POST 方法上传的图片数据 <b> (base64 编码)</b>，原始图片大小需要小于
	 *            <b>3M</b>
	 */
	@Mapping("/ocr/bank")
	@HttpType(HttpPostType.KEY_VALUE)
	BankOcrResult bankOcrByUrl(@URLEncod @Key("url") String url);

	/**
	 * <b>银行卡识别</b></br>
	 * </br>
	 * 
	 * 接口用于检测及识别银行卡图片的卡号。 该版本支持的银行卡类别包括平面卡及凹凸卡，其中 平面卡支持 19， 6-13, 4-4-4-4-3,
	 * 4-4-4-7 四种版式 凹凸卡支持 19， 6-13， 4-4-4-4， 6-6-6 四种版式</br>
	 * 
	 * @param img
	 *            待处理图片的 URL 或者 通过 POST 方法上传的图片数据 <b> (base64 编码)</b>，原始图片大小需要小于
	 *            <b>3M</b>
	 */
	@Mapping("/ocr/bankcard")
	@HttpType(HttpPostType.KEY_VALUE)
	BankOcrResult bankOcrByImg(@URLEncod @Key("img") String img);
}
