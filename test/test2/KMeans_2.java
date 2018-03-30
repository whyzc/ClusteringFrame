package test2;

import java.util.ArrayList;
import java.util.List;

import clustering.Group;
import clustering.KMeans;
import similarity.TextModel;

public class KMeans_2 implements KMeans
{
	
	public List<Group> groups = new ArrayList<Group>();			//��
	
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
			//���������ھ�����
		}
		
		groups.clear();
		
		for (int i=0 ; i<k ; i++)				//��ʼ��k���أ������ó�ʼ�۵�
		{
			Group g = new Group_2();
			g.setClusterPoint(tm.get(i));	
			groups.add(g);
		}
		
		for (int c=0, sign = 0 ; c < 3 ; c++)			//ѭ��3��
		{
			if (groups.size() != k)
			{
				throw new RuntimeException("�صĸ�������");
			}
			
			for (int i=0 ; i<k ; i++)		//��Ci���
			{
				groups.get(i).clearMember();
			}
			
			for (int i=0, site=-1; i<tm.size() ; i++)			
			{
				site = minDistanceGroup(tm.get(i));
				groups.get(site).addMember(tm.get(i));		//������tm.get(i)���뵽��Ӧ�Ĵ���
				
			}
			
			for (int i=0 ; i<k ; i++)
			{
				groups.get(i).updataClusterPoint();			//���¾۵�	
			}
		}
		return groups;
	}
	
	int minDistanceGroup(TextModel tm)		//�����ı�ģ��tm��۵㼯��u�о�������ľ۵�λ��
	{
		int minDistanceSite = -1;
		double minDistance;
		if (groups.size()<1)
		{
			throw new RuntimeException("�ȽϾ���ʱ���޴�");
		}
		else if (groups.size()==1)
		{
			throw new RuntimeException("�ȽϾ���ʱ��ֻ��һ����");
		}
		
		minDistance = tm.distance(groups.get(0).getClusterPoint());
		minDistanceSite = 0;
		for (int i=1 ; i<groups.size() ; i++)
		{
			if (minDistance < tm.distance(groups.get(i).getClusterPoint()))			//��ͬ�ֵ���ĿԽ�࣬����Խ������������ͬ��������
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
