package com.cloudwalk.test.client;

public interface ILipreading {
	/**
	 * <b>�����ж����</b></br>
	 * <br>
	 * 
	 * <b>result:</b> ���ؽ����0 ��ʾ�ɹ����� 0 Ϊ��Ӧ����� </br>
	 * <b>errorInfo:</b> ������Ϣ </br>
	 * <b>code:</b> ��������ִ���ʱ�����ؾ��������</br>
	 * <b>faceImage:</b> result Ϊ 0 ������£������������ jpg ��ʽ</br>
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
	 * <b>�����ж�</b></br>
	 * </br>
	 * 
	 * ���ݴ����ǩ��Ϣ�ʹ�����Ƶ�ж��Ƿ�ͨ�����
	 * 
	 * @param video
	 *            ��Ƶ�� <b>base64</b> ����
	 * @param label
	 *            ����Ĵ�����Ϣ
	 * @param type
	 *            ��Ƶ�����ͣ��� mp4
	 * @param token
	 *            Token ��Ϣ
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
	 * <b>��ǩ��ȡ���</b></br>
	 * <br>
	 * 
	 * <b>result:</b> ���ؽ����0 ��ʾ�ɹ����� 0 Ϊ��Ӧ����� </br>
	 * <b>label:</b> ���ص� labe </br>
	 * <b>token:</b> token ��Ϣ </br>
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
	 * <b>��ǩ��ȡ</b><br>
	 * <br>
	 * 
	 * ���ô���ʶ��ӿ���Ҫ�Ȼ�ȡ��ǩ��Ϣ��Ȼ����ݸñ�ǩ��Ϣ�����Ӧ�Ĵ�����Ƶ��
	 * 
	 * @param len
	 *            ��Ҫ�೤�� labels
	 * @return {@link LabelsResult}
	 */

	@Mapping("/labels")
	@HttpType(HttpPostType.KEY_VALUE)
	public LabelsResult labels(@Key("len") int len);

}
