package clustering;

import java.util.List;

import clusteringLayer.Group;
import text.Text;

/**
 * 面向用户的HAC算法接口
 * @author yzc
 *
 */
public interface HAC
{
	/**
	 * HAC算法
	 * @param pos 文件位置
	 * @return 一个层次簇
	 */
	public Group hac(String pos);
	
	/**
	 * HAC算法
	 * @param ts 一组文本
	 * @return 一个层次簇
	 */
	public Group hac(List<Text> ts);
	
	/**
	 * 将层次簇分解成k个簇
	 * @param grp 待分解的层次簇
	 * @param k 需分解的个数
	 * @return k个簇
	 */
	public List<Group> split(Group grp, int k);
	
	
	/**
	 * 将层次簇按相识度分成一组簇
	 * @param grp 待分解的层次簇
	 * @param sim 相识度
	 * @return 一组簇
	 */
	public List<Group> split(Group grp, double sim);
}
