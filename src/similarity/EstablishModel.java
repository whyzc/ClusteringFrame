package similarity;

import java.util.List;

import text.Text;

/**
 * 
 * @author yzc
 * 
 */
public interface  EstablishModel<T>
{
	/**
	 * �������ı������ı�ģ��
	 * @param t ����ģ�����ɵ��ı�
	 * @return �������ɵ�ģ��
	 */
	public TextModel<T> modeling(Text t);
	
	/**
	 * ������ı������ı�ģ��
	 * @param ts ����ģ�����ɵ��ı�
	 * @return �������ɵ�ģ��
	 */
	public List<TextModel<T>> modeling(List<Text> ts);
}
