package implement;

import java.util.ArrayList;
import java.util.List;

import clusteringLayer.Group;
import clusteringLayer.KMeansAlgo;
import similarity.TextModel;

/**
 * KMeans�㷨���ʵ��
 * @author yzc
 *
 */
public class KMeansAlgoImpl implements KMeansAlgo<String>
{
	private final int TIMES = 5;
	public List<Group<String>> groups = new ArrayList<Group<String>>();			//��
	
	@Override
	public List<Group<String>> kMeans(List<TextModel<String>> tm, int k)
	{
		// TODO Auto-generated method stub
		boolean sign = true;
		
		if (tm.size() <= k)				//�ı�����С�ڷ���
		{
			throw new RuntimeException("�ı��������������ƥ�䣡");
		}

		groups.clear();					//�Դ����
		
		for (int i=0 ; i<k ; i++)				//��ʼ��k���أ������ó�ʼ�۵�
		{
			Group<String> g = new GroupWordBased();
			g.setClusterPoint(tm.get(i));	
			groups.add(g);
		}
		
		for (int c=0 ; c < TIMES ; c++)			//ѭ��TIMES�κ��˳�����
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
				if ( groups.get(i).updataClusterPoint() )			//���¾۵�
				{
					sign = false;
				}
			}
			
			
			
			if (sign)								//������о۵㶼û�䣬���˳�����
				break;
		}
		return groups;
	}
	
	int minDistanceGroup(TextModel<String> tm)		//�����ı�ģ��tm��۵㼯��u�о�������ľ۵�λ��
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
