package implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import clusteringLayer.Group;
import clusteringLayer.KMeansAlgo;
import model.TextModel;

/**
 * KMeans算法层的实现
 * @author yzc
 *
 */
public class KMeansAlgoImpl implements KMeansAlgo
{
	private final int TIMES = 50;
	public List<Group> groups = new ArrayList<Group>();			//簇
	
	@Override
	public List<Group> kMeans(List<TextModel> tm, int k)
	{
		// TODO Auto-generated method stub
		boolean sign = false;
		
		if (tm.size() <= k)				//文本个数小于分类
		{
			throw new RuntimeException("文本个数与类别数不匹配！");
		}

		groups.clear();					//对簇清空
		
		/*************取随机数初始化**************/
		
		Random random = new Random();
		List<Integer> initRandom = new ArrayList<Integer>();
		for (int i=0 ; i<k ; i++)		//在所有的模型中取k个随机数（存入initRandom中），用于初始化
		{
			int tmp = -1;
			do{
				tmp = random.nextInt(tm.size());
			} while ( initRandom.contains(tmp) || tmp<0 );
			initRandom.add(tmp);
		}
		for (int i=0 ; i<k ; i++)				//初始化k个簇，并设置初始聚点
		{
			Group g = new GroupForSpaceVector();
			g.setClusterPoint(((TextModelForSpaceVector)tm.get(initRandom.get(i))).clone());	
			groups.add(g);
		}
		/***************************************/
		
		/****************顺序初始化****************/
		/*
		for (int i=0 ; i<k ; i++)				//初始化k个簇，并设置初始聚点
		{
			Group g = new GroupForSpaceVector();
			
			g.setClusterPoint(((TextModelForSpaceVector)tm.get(i)).clone());	
			groups.add(g);
		}*/
		/*******************************************/
		
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
			
			sign = false;
			for (int i=0 ; i<k ; i++)
			{
				if ( groups.get(i).updataClusterPoint() )			//更新聚点
				{
					sign = true;
				}
			}
			
			
			//System.out.println("...................................第" +(c+1)+ "次迭代......................................");
			if (!sign)								//如果所有聚点都没变，则退出聚类
				break;
		}
		return groups;
	}
	
	int minDistanceGroup(TextModel tm)		//返回文本模型tm与聚点集合u中距离最近的聚点位子
	{
		int minDistanceSite = -1;
		double minDistance;
		TextModelForSpaceVector tmwb = (TextModelForSpaceVector)tm;
		if (groups.size()<1)
		{
			throw new RuntimeException("比较距离时，无簇");
		}
		else if (groups.size()==1)
		{
			throw new RuntimeException("比较距离时，只有一个簇");
		}
		
		minDistance = tmwb.distance(groups.get(0).getClusterPoint());
		minDistanceSite = 0;
		for (int i=1 ; i<groups.size() ; i++)
		{
			if (minDistance > tmwb.distance(groups.get(i).getClusterPoint()))			//遍历，找到最近距离
			{
				minDistanceSite = i;
				minDistance = tmwb.distance(groups.get(i).getClusterPoint());
			}
		}
		
//		if (tmwb.sim(groups.get(minDistanceSite).getClusterPoint()) < 0.2)	//和各簇相识度过低，首先往空簇中填充
//		{
//			for (int i=0 ; i<groups.size() ; i++)
//			{
//				if (groups.get(i).memberSize() < 1)
//				{
//					System.out.println("位子："+i);
//					return i;
//				}
//			}
//		}
		//System.out.println("距离："+minDistance+"......位子："+minDistanceSite);
		return minDistanceSite;
	}
}
