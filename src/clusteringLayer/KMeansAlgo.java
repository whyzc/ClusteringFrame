package clusteringLayer;

import java.util.List;

import similarity.TextModel;

/**
 * 框架内部的KMeans算法层
 * @author yzc
 *
 * @param <T> 文本模型采用的数据类型
 */
public interface KMeansAlgo<T>
{
	/**
	 * KMeans算法
	 * @param tm 一组文本模型
	 * @param k 类型数
	 * @return k个簇
	 */
	public List<Group<T>> kMeans(List<TextModel<T>> tm , int k);
}
