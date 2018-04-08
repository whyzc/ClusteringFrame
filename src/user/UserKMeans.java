package user;

import java.util.*;
import clustering.Group;
import text.Text;

/**
 * �����û���KMeans�㷨�ӿ�
 * @author yzc
 *
 */
public interface UserKMeans<T>
{
	/**
	 * KMeans�㷨
	 * @param pos �ļ�λ��
	 * @param k �������
	 * @return k����
	 */
	public List<Group<T>> kMeans(String pos , int k);
	
	/**
	 * KMeans�㷨
	 * @param ts һ���ı�
	 * @param k �������
	 * @return k����
	 */
	public List<Group<T>> kMeans(List<Text> ts , int k);
}
