package clusteringLayer;

import java.util.List;

import similarity.TextModel;

/**
 * ����ڲ���HAC�㷨��
 * @author yzc
 *
 * @param <T> �ı�ģ�Ͳ��õ���������
 */
public interface HACAlgo<T>
{
	/**
	 * ��һ���ı�ģ�Ͱ���ξ���ϲ���һ����
	 * @param tms ��ϲ���һ��ģ��
	 * @return �ϲ���Ĳ�δ�
	 */
	public Group<T> merge(List<TextModel<T>> tms);
	
	
	
}
