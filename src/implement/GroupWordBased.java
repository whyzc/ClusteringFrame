package implement;

import java.util.*;

import clusteringLayer.Group;
import model.TextModel;

/**
 * 根据以字生成的文本模型而建立的簇
 * @author yzc
 *
 */
public class GroupWordBased extends Group
{

	public GroupWordBased()
	{
		clusterPoint = new TextModelWordBased();
	}
	
	@Override
	public boolean updataClusterPoint()
	{
		// TODO Auto-generated method stub
		if (member.size()<1)				//member中无成员，无法更新聚点
		{
			throw new RuntimeException("簇内无成员");
		}
		
		List common = new ArrayList();
		common.addAll( ((TextModelWordBased)member.get(0)).getTextModel() );
				//((TextModel_2)member.get(0)).getTextModel();		//记录member中各成员文本模型的交集
		
		for (int i=1 ; i<common.size() ; i++)					//求member中各成员文本模型的交集
		{
			
			//new ArrayList()
			String word = (String)common.get(i);
			for (int j=1 ; j<member.size() ; j++)
			{
				if ( ((TextModelWordBased)member.get(j)).getTextModel().contains(word) )
				{
					continue;
				}
				common.remove(i);
				i--;
				break;
			}
		}
		
		/***************验证common与原聚点是否相同，不相同则更新聚点并返回false，相同则返回true*******************/
		List s = ((TextModelWordBased)clusterPoint).getTextModel();
		if (s.size() != common.size())
		{
			((TextModelWordBased)clusterPoint).setTextModel(common);
			return false;
		}
		
		for (int i=0 ; i<common.size() ; i++)
		{
			if (!s.contains(common.get(i)))
			{
				((TextModelWordBased)clusterPoint).setTextModel(common);
				return false;
			}
		}

		return true;
	}

	@Override
	public void setClusterPoint(TextModel tm)
	{
		// TODO Auto-generated method stub
		
		TextModelWordBased tm2 = (TextModelWordBased)clusterPoint;
		
		if (tm instanceof TextModelWordBased)
		{
			tm2.setTextModel( ((TextModelWordBased)tm).getTextModel() );
		}
		else 
		{
			throw new RuntimeException("tm类型不对");
		}
			
	}
	
	private double sim(TextModel tm, Group g)
	{
		List<Group> queue = new ArrayList<Group>();			//队列，用于遍历g
		double max = 0;										//存放最大相识度的值
		List<Group> grps;									//存放子簇序列
		max = tm.sim(g.getInitialMember());
		
		
		if (!g.isSubGroupEmpty())				//如果g中有子簇，将子簇全部压入栈
		{
			grps = g.getSubGroup();
			for (int i=0 ; i<g.subGroupSize() ; i++)
			{
				queue.add(grps.get(i));
			}
		}
		
		while (!queue.isEmpty())				//队列不为空
		{
			Group top = queue.get(0);			//取出队首
			queue.remove(0);
			double s = tm.sim(top.getInitialMember());
			if (s > max)							/****此处取相似度最大的值返回******/
			{
				max = s;
			}
			if (!top.isSubGroupEmpty())				//如果队首簇中有子簇，将子簇全部压入栈
			{
				grps = top.getSubGroup();
				for (int i=0 ; i<top.subGroupSize() ; i++)
				{
					queue.add(grps.get(i));
				}
			}
			
		}
		return max;
	}
	
	
	public double sim(Group g)				//返回本簇与簇g的相识度
	{
		List<Group> queue = new ArrayList<Group>();
		double max = 0;
		List<Group> grps;
		max = this.sim(this.getInitialMember() , g);
		
		if (!this.isSubGroupEmpty())				//如果本簇中有子簇，将子簇全部压入栈
		{
			grps = this.getSubGroup();
			for (int i=0 ; i<this.subGroupSize() ; i++)
			{
				queue.add(grps.get(i));
			}
		}
		
		while (!queue.isEmpty())				//队列不为空
		{
			Group top = queue.get(0);			//取出队首
			double s = this.sim(top.getInitialMember(), g);
			if (s > max)							/****此处取相似度最大的值返回******/
			{
				max = s;
			}
			if (!top.isSubGroupEmpty())				//如果队首簇中有子簇，将子簇全部压入栈
			{
				grps = top.getSubGroup();
				for (int i=0 ; i<top.subGroupSize() ; i++)
				{
					queue.add(grps.get(i));
				}
			}
			queue.remove(0);
		}
		
		return max;
	}


}
