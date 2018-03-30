package user;

import java.util.*;
import clustering.Group;
import text.Text;

/**
 * 面向用户的KMeans算法接口
 * @author yzc
 *
 */
public interface UserKMeans
{
	/**
	 * KMeans算法
	 * @param pos 文件位置
	 * @param k 种类个数
	 * @return k个簇
	 */
	public List<Group> kMeans(String pos , int k);
	
	/**
	 * KMeans算法
	 * @param ts 一组文本
	 * @param k 种类个数
	 * @return k个簇
	 */
	public List<Group> kMeans(List<Text> ts , int k);
}
