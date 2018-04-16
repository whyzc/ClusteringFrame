package clusteringLayer;

import java.util.List;

import similarity.TextModel;

/**
 * ����ڲ���KMeans�㷨��
 * @author yzc
 *
 * @param <T> �ı�ģ�Ͳ��õ���������
 */
public interface KMeansAlgo<T>
{
	/**
	 * KMeans�㷨
	 * @param tm һ���ı�ģ��
	 * @param k ������
	 * @return k����
	 */
	public List<Group<T>> kMeans(List<TextModel<T>> tm , int k);
}
