package test2;

import java.util.*;

import clustering.Group;
import similarity.TextModel;

public class Group_2 extends Group
{

	public Group_2()
	{
		clusterPoint = new TextModel_2();
	}
	
	@Override
	public boolean updataClusterPoint()
	{
		// TODO Auto-generated method stub
		if (member.size()<1)
		{
			throw new RuntimeException("�����޳�Ա");
		}
		
		List<String> common = ((TextModel_2)member.get(0)).t;
		for (int i=0 ; i<common.size() ; i++)
		{
			
			//new ArrayList<String>()
			String word = common.get(i);
			for (int j=1 ; j<member.size() ; j++)
			{
				if (((TextModel_2)member.get(j)).t.contains(word))
				{
					continue;
				}
				common.remove(i);
				i--;
				break;
			}
		}
		
		
		List<String> s = ((TextModel_2)clusterPoint).t;
		if (s.size() != common.size())
		{
			((TextModel_2)clusterPoint).t = common;
			return false;
		}
		
		for (int i=0 ; i<common.size() ; i++)
		{
			if (!s.contains(common.get(i)))
			{
				((TextModel_2)clusterPoint).t = common;
				return false;
			}
		}
		
		((TextModel_2)clusterPoint).t = common;
		return true;
	}

	@Override
	public void setClusterPoint(TextModel tm)
	{
		// TODO Auto-generated method stub
		
		TextModel_2 tm2 = (TextModel_2)clusterPoint;
		if (tm instanceof TextModel_2)
		{
			tm2.t = ((TextModel_2)tm).t;
		}
		else 
		{
			throw new RuntimeException("tm���Ͳ���");
		}
			
	}
	
	private double sim(TextModel tm, Group g)
	{
		List<Group> queue = new ArrayList<Group>();			//���У����ڱ���g
		double max = 0;										//��������ʶ�ȵ�ֵ
		List<Group> grps;									//����Ӵ�����
		max = tm.sim(g.getMember().get(0));
		
		
		if (!g.isSubGroupEmpty())				//���g�����Ӵأ����Ӵ�ȫ��ѹ��ջ
		{
			grps = g.getSubGroup();
			for (int i=0 ; i<g.subGroupSize() ; i++)
			{
				queue.add(grps.get(i));
			}
		}
		
		while (!queue.isEmpty())				//���в�Ϊ��
		{
			Group top = queue.get(0);			//ȡ������
			double s = tm.sim(top.getMember().get(0));
			if (s > max)							/****�˴�ȡ���ƶ�����ֵ����******/
			{
				max = s;
			}
			if (!top.isSubGroupEmpty())				//������״������Ӵأ����Ӵ�ȫ��ѹ��ջ
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
	
	
	public double sim(Group g)				//���ر������g����ʶ��
	{
		List<Group> queue = new ArrayList<Group>();
		double max = 0;
		List<Group> grps;
		max = this.sim(this.getMember().get(0), g);
		
		if (!this.isSubGroupEmpty())				//������������Ӵأ����Ӵ�ȫ��ѹ��ջ
		{
			grps = this.getSubGroup();
			for (int i=0 ; i<this.subGroupSize() ; i++)
			{
				queue.add(grps.get(i));
			}
		}
		
		while (!queue.isEmpty())				//���в�Ϊ��
		{
			Group top = queue.get(0);			//ȡ������
			double s = this.sim(top.getMember().get(0), g);
			if (s > max)							/****�˴�ȡ���ƶ�����ֵ����******/
			{
				max = s;
			}
			if (!top.isSubGroupEmpty())				//������״������Ӵأ����Ӵ�ȫ��ѹ��ջ
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
