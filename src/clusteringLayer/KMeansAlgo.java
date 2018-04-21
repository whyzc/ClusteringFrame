package clusteringLayer;

import java.util.List;

import model.TextModel;

/**
 * ����ڲ���KMeans�㷨��
 * @author yzc
 *
 * @param  �ı�ģ�Ͳ��õ���������
 */
public interface KMeansAlgo
{
	/**
	 * KMeans�㷨
	 * @param tm һ���ı�ģ��
	 * @param k ������
	 * @return k����
	 */
	public List<Group> kMeans(List<TextModel> tm , int k);
}
