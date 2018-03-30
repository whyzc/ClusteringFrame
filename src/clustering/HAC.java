package clustering;

import java.util.List;

import similarity.TextModel;

public interface HAC
{
	/**
	 * 将一组文本模型按层次聚类合并成一个簇
	 * @param tms 需合并的一组模型
	 * @return 合并后的层次簇
	 */
	public Group merge(List<TextModel> tms);
	
	
	
}
