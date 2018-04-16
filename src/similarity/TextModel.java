package similarity;

import java.util.*;

/**
 * 文本模型
 * @author yzc
 *
 * @param <T> 文本模型的单个元素的数据类型
 */
public abstract class TextModel<T>
{
	protected List<T> textModel = new ArrayList<T>();				//文本模型
	protected int no = -1;											//文本编号
	protected String title = null;									//文本标题
	
	/**
	 * 计算两模型间的距离
	 * @param tm 被比较的文本模型
	 * @return 两模型间的距离
	 */
	public abstract double distance(TextModel<T> tm);
	
	/**
	 * 计算两模型间的相识度
	 * @param tm 被比较的文本模型
	 * @return 两模型间的相识度
	 */
	public abstract double sim(TextModel<T> tm);
	
	/**
	 * 设置文本模型
	 * @param obj 需设置的文本模型
	 */
	public abstract void setTextModel(Object obj);

	/**获取编号
	 * @return 文本编号
	 */
	public int getNo()
	{
		return no;
	}

	/**
	 * 设置编号
	 * @param no 需设置的编号
	 */
	public void setNo(int no)
	{
		this.no = no;
	}

	/**
	 * 获取文本标题
	 * @return 文本标题
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 设置文本标题
	 * @param title 需设置的文本标题
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**获取文本模型
	 * @return 文本模型
	 */
	public List<T> getTextModel()
	{
		return textModel;
	}

	/**设置文本模型
	 * @param textModel 需设置的文本模型
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
