package implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import clusteringLayer.Group;
import clusteringLayer.KMeansAlgo;
import model.TextModel;

/**
 * KMeans�㷨���ʵ��
 * @author yzc
 *
 */
public class KMeansAlgoImpl implements KMeansAlgo
{
	private final int TIMES = 50;
	public List<Group> groups = new ArrayList<Group>();			//��
	
	@Override
	public List<Group> kMeans(List<TextModel> tm, int k)
	{
		// TODO Auto-generated method stub
		boolean sign = false;
		
		if (tm.size() <= k)				//�ı�����С�ڷ���
		{
			throw new RuntimeException("�ı��������������ƥ�䣡");
		}

		groups.clear();					//�Դ����
		
		/*************ȡ�������ʼ��**************/
		
		Random random = new Random();
		List<Integer> initRandom = new ArrayList<Integer>();
		for (int i=0 ; i<k ; i++)		//�����е�ģ����ȡk�������������initRandom�У������ڳ�ʼ��
		{
			int tmp = -1;
			do{
				tmp = random.nextInt(tm.size());
			} while ( initRandom.contains(tmp) || tmp<0 );
			initRandom.add(tmp);
		}
		for (int i=0 ; i<k ; i++)				//��ʼ��k���أ������ó�ʼ�۵�
		{
			Group g = new GroupForSpaceVector();
			g.setClusterPoint(((TextModelForSpaceVector)tm.get(initRandom.get(i))).clone());	
			groups.add(g);
		}
		/***************************************/
		
		/****************˳���ʼ��****************/
		/*
		for (int i=0 ; i<k ; i++)				//��ʼ��k���أ������ó�ʼ�۵�
		{
			Group g = new GroupForSpaceVector();
			
			g.setClusterPoint(((TextModelForSpaceVector)tm.get(i)).clone());	
			groups.add(g);
		}*/
		/*******************************************/
		
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
			
			sign = false;
			for (int i=0 ; i<k ; i++)
			{
				if ( groups.get(i).updataClusterPoint() )			//���¾۵�
				{
					sign = true;
				}
			}
			
			
			//System.out.println("...................................��" +(c+1)+ "�ε���......................................");
			if (!sign)								//������о۵㶼û�䣬���˳�����
				break;
		}
		return groups;
	}
	
	int minDistanceGroup(TextModel tm)		//�����ı�ģ��tm��۵㼯��u�о�������ľ۵�λ��
	{
		int minDistanceSite = -1;
		double minDistance;
		TextModelForSpaceVector tmwb = (TextModelForSpaceVector)tm;
		if (groups.size()<1)
		{
			throw new RuntimeException("�ȽϾ���ʱ���޴�");
		}
		else if (groups.size()==1)
		{
			throw new RuntimeException("�ȽϾ���ʱ��ֻ��һ����");
		}
		
		minDistance = tmwb.distance(groups.get(0).getClusterPoint());
		minDistanceSite = 0;
		for (int i=1 ; i<groups.size() ; i++)
		{
			if (minDistance > tmwb.distance(groups.get(i).getClusterPoint()))			//�������ҵ��������
			{
				minDistanceSite = i;
				minDistance = tmwb.distance(groups.get(i).getClusterPoint());
			}
		}
		
//		if (tmwb.sim(groups.get(minDistanceSite).getClusterPoint()) < 0.2)	//�͸�����ʶ�ȹ��ͣ��������մ������
//		{
//			for (int i=0 ; i<groups.size() ; i++)
//			{
//				if (groups.get(i).memberSize() < 1)
//				{
//					System.out.println("λ�ӣ�"+i);
//					return i;
//				}
//			}
//		}
		//System.out.println("���룺"+minDistance+"......λ�ӣ�"+minDistanceSite);
		return minDistanceSite;
	}
}
