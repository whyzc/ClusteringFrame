package clustering;

import java.util.List;

import similarity.TextModel;

public interface KMeans<T>
{
	/**
	 * KMeans�㷨
	 * @param tm һ���ı�ģ��
	 * @param k ������
	 * @return k����
	 */
	public List<Group<T>> kMeans(List<TextModel<T>> tm , int k);
}
