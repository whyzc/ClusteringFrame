package test2;

import java.util.ArrayList;
import java.util.List;

import clustering.Group;
import clustering.KMeans;
import similarity.TextModel;

public class KMeans_2 implements KMeans
{
	
	public List<Group> groups = new ArrayList<Group>();			//簇
	
	@Override
	public List<Group> kMeans(List<TextModel> tm, int k)
	{
		// TODO Auto-generated method stub
		if (tm.size() < k)
		{
			throw new RuntimeException("");
		}
		else if (tm.size() == k)
		{
			//文章数等于聚类数
		}
		
		groups.clear();
		
		for (int i=0 ; i<k ; i++)				//初始化k个簇，并设置初始聚点
		{
			Group g = new Group_2();
			g.setClusterPoint(tm.get(i));	
			groups.add(g);
		}
		
		for (int c=0, sign = 0 ; c < 3 ; c++)			//循环3次
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
				groups.get(i).updataClusterPoint();			//更新聚点	
			}
		}
		return groups;
	}
	
	int minDistanceGroup(TextModel tm)		//返回文本模型tm与聚点集合u中距离最近的聚点位子
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
