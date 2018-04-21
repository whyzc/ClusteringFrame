package clustering;

import java.util.*;

import clusteringLayer.Group;
import text.Text;

/**
 * �����û���KMeans�㷨�ӿ�
 * @author yzc
 *
 */
public interface KMeans
{
	/**
	 * KMeans�㷨
	 * @param pos �ļ�λ��
	 * @param k �������
	 * @return k����
	 */
	public List<Group> kMeans(String pos , int k);
	
	/**
	 * KMeans�㷨
	 * @param ts һ���ı�
	 * @param k �������
	 * @return k����
	 */
	public List<Group> kMeans(List<Text> ts , int k);
}
