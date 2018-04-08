package test2;

import java.util.*;

import clustering.Group;
import similarity.TextModel;

public class Group_2 extends Group<String>
{

	public Group_2()
	{
		clusterPoint = new TextModel_2();
	}
	
	@Override
	public boolean updataClusterPoint()
	{
		// TODO Auto-generated method stub
		if (member.size()<1)				//member中无成员，无法更新聚点
		{
			throw new RuntimeException("簇内无成员");
		}
		
		List<String> common = new ArrayList<String>();
		common.addAll( ((TextModel_2)member.get(0)).getTextModel() );
				//((TextModel_2)member.get(0)).getTextModel();		//记录member中各成员文本模型的交集
		
		for (int i=1 ; i<common.size() ; i++)					//求member中各成员文本模型的交集
		{
			
			//new ArrayList<String>()
			String word = common.get(i);
			for (int j=1 ; j<member.size() ; j++)
			{
				if ( ((TextModel_2)member.get(j)).getTextModel().contains(word) )
				{
					continue;
				}
				common.remove(i);
				i--;
				break;
			}
		}
		
		/***************验证common与原聚点是否相同，不相同则更新聚点并返回false，相同则返回true*******************/
		List<String> s = ((TextModel_2)clusterPoint).getTextModel();
		if (s.size() != common.size())
		{
			((TextModel_2)clusterPoint).setTextModel(common);
			return false;
		}
		
		for (int i=0 ; i<common.size() ; i++)
		{
			if (!s.contains(common.get(i)))
			{
				((TextModel_2)clusterPoint).setTextModel(common);
				return false;
			}
		}

		return true;
	}

	@Override
	public void setClusterPoint(TextModel<String> tm)
	{
		// TODO Auto-generated method stub
		
		TextModel_2 tm2 = (TextModel_2)clusterPoint;
		
		if (tm instanceof TextModel_2)
		{
			tm2.setTextModel( ((TextModel_2)tm).getTextModel() );
		}
		else 
		{
			throw new RuntimeException("tm类型不对");
		}
			
	}
	
	private double sim(TextModel<String> tm, Group<String> g)
	{
		List<Group<String>> queue = new ArrayList<Group<String>>();			//队列，用于遍历g
		double max = 0;										//存放最大相识度的值
		List<Group<String>> grps;									//存放子簇序列
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
			Group<String> top = queue.get(0);			//取出队首
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
	
	
	public double sim(Group<String> g)				//返回本簇与簇g的相识度
	{
		List<Group<String>> queue = new ArrayList<Group<String>>();
		double max = 0;
		List<Group<String>> grps;
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
			Group<String> top = queue.get(0);			//取出队首
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
