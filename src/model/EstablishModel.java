package model;

import java.util.List;

import text.Text;

/**
 * �����ı�ģ��
 * @author yzc
 * 
 */
public interface  EstablishModel
{
	/**
	 * �������ı������ı�ģ��
	 * @param t ����ģ�����ɵ��ı�
	 * @return �������ɵ�ģ��
	 */
	public TextModel modeling(Text t);
	
	/**
	 * ������ı������ı�ģ��
	 * @param ts ����ģ�����ɵ��ı�
	 * @return �������ɵ�ģ��
	 */
	public List<TextModel> modeling(List<Text> ts);
}
