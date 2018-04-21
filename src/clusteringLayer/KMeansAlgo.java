package clusteringLayer;

import java.util.List;

import model.TextModel;

/**
 * 框架内部的KMeans算法层
 * @author yzc
 *
 * @param  文本模型采用的数据类型
 */
public interface KMeansAlgo
{
	/**
	 * KMeans算法
	 * @param tm 一组文本模型
	 * @param k 类型数
	 * @return k个簇
	 */
	public List<Group> kMeans(List<TextModel> tm , int k);
}
