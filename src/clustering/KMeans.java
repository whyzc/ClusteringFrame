package clustering;

import java.util.List;

import similarity.TextModel;

public interface KMeans
{
	/**
	 * KMeans�㷨
	 * @param tm һ���ı�ģ��
	 * @param k ������
	 * @return k����
	 */
	public List<Group> kMeans(List<TextModel> tm , int k);
}
