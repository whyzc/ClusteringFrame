package model;

import java.util.*;

/**
 * �ı�ģ��
 * @author yzc
 *
 * @param  �ı�ģ�͵ĵ���Ԫ�ص���������
 */
public abstract class TextModel
{
	
	private int no = -1;											//�ı����
	private String title = null;									//�ı�����
	
	/**
	 * ������ģ�ͼ�ľ���
	 * @param tm ���Ƚϵ��ı�ģ��
	 * @return ��ģ�ͼ�ľ���
	 */
	public abstract double distance(TextModel tm);
	
	/**
	 * ������ģ�ͼ����ʶ��
	 * @param tm ���Ƚϵ��ı�ģ��
	 * @return ��ģ�ͼ����ʶ��
	 */
	public abstract double sim(TextModel tm);
	
	/**
	 * �����ı�ģ��
	 * @param obj �����õ��ı�ģ��
	 */
	public abstract void setTextModel(Object obj);

	/**��ȡ���
	 * @return �ı����
	 */
	public int getNo()
	{
		return no;
	}

	/**
	 * ���ñ��
	 * @param no �����õı��
	 */
	public void setNo(int no)
	{
		this.no = no;
	}

	/**
	 * ��ȡ�ı�����
	 * @return �ı�����
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * �����ı�����
	 * @param title �����õ��ı�����
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	

	
	
//	Map<String, Double> getModel();
//	void setModel(Map<String, Double> m);
//	void add(String Str);
//	void add(String str, Double dou);
//	int size();
//	Set<String> keySet();
//	boolean isEmpty();
//	boolean containsKey(String key);
//	Double remove(String key);
//	void clear();
//	Double get(String key);
}
