package com.cloudwalk.test.client;

public interface IBankOcr {
	/**
	 * <b>���п�ʶ����</b></br>
	 * <br>
	 * 
	 * <b>result:</b> ���ؽ����0 ��ʾ�ɹ����� 0 Ϊ��Ӧ����� </br>
	 * <b>info:</b> ������ϸ��Ϣ </br>
	 * <b>BankName:</b> ������ </br>
	 * <b>CardNum:</b> ���� </br>
	 * <b>CardName:</b>���� </br>
	 * <b>CardType:</b> ���� </br>
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
	 * <b>���п�ʶ��</b></br>
	 * </br>
	 * 
	 * �ӿ����ڼ�⼰ʶ�����п�ͼƬ�Ŀ��š� �ð汾֧�ֵ����п�������ƽ�濨����͹�������� ƽ�濨֧�� 19�� 6-13, 4-4-4-4-3,
	 * 4-4-4-7 ���ְ�ʽ ��͹��֧�� 19�� 6-13�� 4-4-4-4�� 6-6-6 ���ְ�ʽ</br>
	 * 
	 * @param url
	 *            ������ͼƬ�� URL ���� ͨ�� POST �����ϴ���ͼƬ���� <b> (base64 ����)</b>��ԭʼͼƬ��С��ҪС��
	 *            <b>3M</b>
	 */
	@Mapping("/ocr/bank")
	@HttpType(HttpPostType.KEY_VALUE)
	BankOcrResult bankOcrByUrl(@URLEncod @Key("url") String url);

	/**
	 * <b>���п�ʶ��</b></br>
	 * </br>
	 * 
	 * �ӿ����ڼ�⼰ʶ�����п�ͼƬ�Ŀ��š� �ð汾֧�ֵ����п�������ƽ�濨����͹�������� ƽ�濨֧�� 19�� 6-13, 4-4-4-4-3,
	 * 4-4-4-7 ���ְ�ʽ ��͹��֧�� 19�� 6-13�� 4-4-4-4�� 6-6-6 ���ְ�ʽ</br>
	 * 
	 * @param img
	 *            ������ͼƬ�� URL ���� ͨ�� POST �����ϴ���ͼƬ���� <b> (base64 ����)</b>��ԭʼͼƬ��С��ҪС��
	 *            <b>3M</b>
	 */
	@Mapping("/ocr/bankcard")
	@HttpType(HttpPostType.KEY_VALUE)
	BankOcrResult bankOcrByImg(@URLEncod @Key("img") String img);
}
