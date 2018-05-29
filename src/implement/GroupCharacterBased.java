package implement;

import java.util.*;

import clusteringLayer.Group;
import model.TextModel;

/**
 * �����������ɵ��ı�ģ�Ͷ������Ĵ�
 * @author yzc
 *
 */
public class GroupCharacterBased extends Group
{

	public GroupCharacterBased()
	{
		clusterPoint = new TextModelCharacterBased();
	}
	
	@Override
	public boolean updataClusterPoint()
	{
		if (member.size()<1)				//member���޳�Ա���޷����¾۵�
		{
			throw new RuntimeException("�����޳�Ա");
		}
		
		List common = new ArrayList();
		common.addAll( ((TextModelCharacterBased)member.get(0)).getTextModelForList() );
				//((TextModel_2)member.get(0)).getTextModel();		//��¼member�и���Ա�ı�ģ�͵Ľ���
		
		String word;
		for (int i=1 ; i<common.size() ; i++)					//��member�и���Ա�ı�ģ�͵Ľ���
		{
			
			//new ArrayList()
			word = (String)common.get(i);
			for (int j=1 ; j<member.size() ; j++)
			{
				if ( ((TextModelCharacterBased)member.get(j)).getTextModelForList().contains(word) )
				{
					continue;
				}
				common.remove(i);
				i--;
				break;
			}
		}
		
		/***************��֤common��ԭ�۵��Ƿ���ͬ������ͬ����¾۵㲢����false����ͬ�򷵻�true*******************/
		List s = ((TextModelCharacterBased)clusterPoint).getTextModelForList();
		if (s.size() != common.size())
		{
			((TextModelCharacterBased)clusterPoint).setTextModel(common);
			return false;
		}
		
		for (int i=0 ; i<common.size() ; i++)
		{
			if (!s.contains(common.get(i)))
			{
				((TextModelCharacterBased)clusterPoint).setTextModel(common);
				return false;
			}
		}

		return true;
	}

	@Override
	public void setClusterPoint(TextModel tm)
	{
		// TODO Auto-generated method stub
		
		TextModelCharacterBased tm2 = (TextModelCharacterBased)clusterPoint;
		
		if (tm instanceof TextModelCharacterBased)
		{
			tm2.setTextModel( ((TextModelCharacterBased)tm).getTextModelForList() );
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
			Group top = queue.get(0);			//ȡ������
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
	
	
	public double sim(Group g)				//���ر������g����ʶ��
	{
		List<Group> queue = new ArrayList<Group>();
		double max = 0;
		List<Group> grps;
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
			Group top = queue.get(0);			//ȡ������
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
