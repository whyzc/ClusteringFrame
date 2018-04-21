package clusteringLayer;

import java.util.List;

import model.TextModel;

/**
 * 框架内部的HAC算法层
 * @author yzc
 *
 * @param  文本模型采用的数据类型
 */
public interface HACAlgo
{
	/**
	 * 将一组文本模型按层次聚类合并成一个簇
	 * @param tms 需合并的一组模型
	 * @return 合并后的层次簇
	 */
	public Group merge(List<TextModel> tms);
	
	
	
}
