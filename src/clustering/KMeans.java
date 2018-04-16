package clustering;

import java.util.*;

import clusteringLayer.Group;
import text.Text;

/**
 * �����û���KMeans�㷨�ӿ�
 * @author yzc
 *
 */
public interface KMeans<T>
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
