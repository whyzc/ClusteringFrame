package implement;

import java.util.*;

import clusteringLayer.Group;
import similarity.TextModel;

/**
 * �����������ɵ��ı�ģ�Ͷ������Ĵ�
 * @author yzc
 *
 */
public class GroupWordBased extends Group<String>
{

	public GroupWordBased()
	{
		clusterPoint = new TextModelWordBased();
	}
	
	@Override
	public boolean updataClusterPoint()
	{
		// TODO Auto-generated method stub
		if (member.size()<1)				//member���޳�Ա���޷����¾۵�
		{
			throw new RuntimeException("�����޳�Ա");
		}
		
		List<String> common = new ArrayList<String>();
		common.addAll( ((TextModelWordBased)member.get(0)).getTextModel() );
				//((TextModel_2)member.get(0)).getTextModel();		//��¼member�и���Ա�ı�ģ�͵Ľ���
		
		for (int i=1 ; i<common.size() ; i++)					//��member�и���Ա�ı�ģ�͵Ľ���
		{
			
			//new ArrayList<String>()
			String word = common.get(i);
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
		
		/***************��֤common��ԭ�۵��Ƿ���ͬ������ͬ����¾۵㲢����false����ͬ�򷵻�true*******************/
		List<String> s = ((TextModelWordBased)clusterPoint).getTextModel();
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
	public void setClusterPoint(TextModel<String> tm)
	{
		// TODO Auto-generated method stub
		
		TextModelWordBased tm2 = (TextModelWordBased)clusterPoint;
		
		if (tm instanceof TextModelWordBased)
		{
			tm2.setTextModel( ((TextModelWordBased)tm).getTextModel() );
		}
		else 
		{
			throw new RuntimeException("tm���Ͳ���");
		}
			
	}
	
	private double sim(TextModel<String> tm, Group<String> g)
	{
		List<Group<String>> queue = new ArrayList<Group<String>>();			//���У����ڱ���g
		double max = 0;										//��������ʶ�ȵ�ֵ
		List<Group<String>> grps;									//����Ӵ�����
		max = tm.sim(g.getInitialMember());
		
		
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
			Group<String> top = queue.get(0);			//ȡ������
			queue.remove(0);
			double s = tm.sim(top.getInitialMember());
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
			
		}
		return max;
	}
	
	
	public double sim(Group<String> g)				//���ر������g����ʶ��
	{
		List<Group<String>> queue = new ArrayList<Group<String>>();
		double max = 0;
		List<Group<String>> grps;
		max = this.sim(this.getInitialMember() , g);
		
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
			Group<String> top = queue.get(0);			//ȡ������
			double s = this.sim(top.getInitialMember(), g);
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
