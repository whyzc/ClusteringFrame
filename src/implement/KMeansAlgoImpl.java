package implement;

import java.util.ArrayList;
import java.util.List;

import clusteringLayer.Group;
import clusteringLayer.KMeansAlgo;
import similarity.TextModel;

/**
 * KMeans算法层的实现
 * @author yzc
 *
 */
public class KMeansAlgoImpl implements KMeansAlgo<String>
{
	private final int TIMES = 5;
	public List<Group<String>> groups = new ArrayList<Group<String>>();			//簇
	
	@Override
	public List<Group<String>> kMeans(List<TextModel<String>> tm, int k)
	{
		// TODO Auto-generated method stub
		boolean sign = true;
		
		if (tm.size() <= k)				//文本个数小于分类
		{
			throw new RuntimeException("文本个数与类别数不匹配！");
		}

		groups.clear();					//对簇清空
		
		for (int i=0 ; i<k ; i++)				//初始化k个簇，并设置初始聚点
		{
			Group<String> g = new GroupWordBased();
			g.setClusterPoint(tm.get(i));	
			groups.add(g);
		}
		
		for (int c=0 ; c < TIMES ; c++)			//循环TIMES次后退出聚类
		{
			if (groups.size() != k)
			{
				throw new RuntimeException("簇的个数出错");
			}
			
			for (int i=0 ; i<k ; i++)		//簇Ci清空
			{
				groups.get(i).clearMember();
			}
			
			for (int i=0, site=-1; i<tm.size() ; i++)			
			{
				site = minDistanceGroup(tm.get(i));
				groups.get(site).addMember(tm.get(i));		//将文章tm.get(i)加入到相应的簇中
				
			}
			
			for (int i=0 ; i<k ; i++)
			{
				if ( groups.get(i).updataClusterPoint() )			//更新聚点
				{
					sign = false;
				}
			}
			
			
			
			if (sign)								//如果所有聚点都没变，则退出聚类
				break;
		}
		return groups;
	}
	
	int minDistanceGroup(TextModel<String> tm)		//返回文本模型tm与聚点集合u中距离最近的聚点位子
	{
		int minDistanceSite = -1;
		double minDistance;
		if (groups.size()<1)
		{
			throw new RuntimeException("比较距离时，无簇");
		}
		else if (groups.size()==1)
		{
			throw new RuntimeException("比较距离时，只有一个簇");
		}
		
		minDistance = tm.distance(groups.get(0).getClusterPoint());
		minDistanceSite = 0;
		for (int i=1 ; i<groups.size() ; i++)
		{
			if (minDistance < tm.distance(groups.get(i).getClusterPoint()))			//相同字的数目越多，距离越近，这里找相同字数最多的
			{
				minDistanceSite = i;
				minDistance = tm.distance(groups.get(i).getClusterPoint());
			}
		}
		return minDistanceSite;
	}
	
//	private void updateClusterPoint(int s)
//	{
//		if (groups.get(s).members.size() == 1)
//		{
//			u.get(s).set
//		}
//	}
}
