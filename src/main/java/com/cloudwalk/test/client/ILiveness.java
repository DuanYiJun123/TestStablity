package com.cloudwalk.test.client;

public interface ILiveness {

	/**
	 * <b>��������ʶ����</b></br>
	 * </br>
	 * <b>result:</b> 0 ��ʾ��������̳ɹ����� 0 ��ʾʧ��</br>
	 * <b>code:</b> 1:�ж�Ϊ���� 2:�ж�Ϊ���۹��� 3:�ж�Ϊ���칥�� 4:�ж�Ϊ���������� 5:�ж�Ϊ��Ƶ�طŹ��� 6:�ж�Ϊ�ڰ�ͼƬ
	 * 7:�ж�Ϊֽ�湥�� 8:�ж�Ϊ�߿򣨰���ֽ�桢�ֻ��ȱ߿� 9:�ж�ΪĦ���ƹ��� 10:�ж�Ϊ���Ź��� 11:�ж�Ϊֽ�湥��(����)
	 * 12:�޷��ж�</br>
	 * 
	 * 
	 * @author xue.wen yijun.duan
	 */
	public static class LivenessResult {
		public Integer result;
		public Integer code;
	}

	/**
	 * * <b>��������ʶ��</b></br>
	 * 
	 * @param param
	 *            ͼƬ���ݵ� base64 �����ַ���,ͼƬ��С����Ϊ 3M ���� </br>
	 *            ��ʽΪ��ԭͼƬbase64,x1,x2,x3,x4,x5,x6,x7,x8,x9,y1,y2,y3,y4,y5,y6,y7,
	 *            y8,y9,�ؼ������,pitch,yaw,roll_ԭͼƬbase64,x1,x2,x3,x4,x5,x6,x7,x8,
	 *            x9,y1,y2,y3,y4,y5,y6,y7,y8,y9,�ؼ������,pitch,yaw,roll
	 * @return {@link LivenessResult}
	 */
	@Mapping("/faceliveness")
	@HttpType(HttpPostType.KEY_VALUE)
	LivenessResult liveness(@URLEncod @Key("param") String param);
}
