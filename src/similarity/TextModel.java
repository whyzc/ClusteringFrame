package similarity;

import java.util.*;

/**
 * �ı�ģ��
 * @author yzc
 *
 * @param <T> �ı�ģ�͵ĵ���Ԫ�ص���������
 */
public abstract class TextModel<T>
{
	protected List<T> textModel = new ArrayList<T>();				//�ı�ģ��
	protected int no = -1;											//�ı����
	protected String title = null;									//�ı�����
	
	/**
	 * ������ģ�ͼ�ľ���
	 * @param tm ���Ƚϵ��ı�ģ��
	 * @return ��ģ�ͼ�ľ���
	 */
	public abstract double distance(TextModel<T> tm);
	
	/**
	 * ������ģ�ͼ����ʶ��
	 * @param tm ���Ƚϵ��ı�ģ��
	 * @return ��ģ�ͼ����ʶ��
	 */
	public abstract double sim(TextModel<T> tm);
	
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

	/**��ȡ�ı�ģ��
	 * @return �ı�ģ��
	 */
	public List<T> getTextModel()
	{
		return textModel;
	}

	/**�����ı�ģ��
	 * @param textModel �����õ��ı�ģ��
	 */
	public void setTextModel(List<T> textModel)
	{
		this.textModel = textModel;
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
