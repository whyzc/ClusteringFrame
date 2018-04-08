package clustering;

import java.util.List;

import similarity.TextModel;

public interface KMeans<T>
{
	/**
	 * KMeans算法
	 * @param tm 一组文本模型
	 * @param k 类型数
	 * @return k个簇
	 */
	public List<Group<T>> kMeans(List<TextModel<T>> tm , int k);
}
