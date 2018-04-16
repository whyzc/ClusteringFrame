package clusteringLayer;

import java.util.List;

import similarity.TextModel;

/**
 * 框架内部的HAC算法层
 * @author yzc
 *
 * @param <T> 文本模型采用的数据类型
 */
public interface HACAlgo<T>
{
	/**
	 * 将一组文本模型按层次聚类合并成一个簇
	 * @param tms 需合并的一组模型
	 * @return 合并后的层次簇
	 */
	public Group<T> merge(List<TextModel<T>> tms);
	
	
	
}
