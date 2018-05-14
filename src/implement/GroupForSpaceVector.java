package implement;

import clusteringLayer.Group;
import model.TextModel;
import model.TextModelForArray;

public class GroupForSpaceVector extends Group
{
	public GroupForSpaceVector() 
	{
		this.clusterPoint = new TextModelForSpaceVector();
	}

	@Override
	public boolean updataClusterPoint()
	{
		if (member.size()<1)				//member���޳�Ա���޷����¾۵�
		{
			throw new RuntimeException("�����޳�Ա");
		}
		
		TextModelForSpaceVector tmfsv = (TextModelForSpaceVector)clusterPoint;
		int dimension = ((TextModelForArray)member.get(0)).getTextModelForArray().length;
		
		double[] newClusterPoint = new double[dimension];
		double[] tmp;

		for (int i=0 ; i<dimension ; i++)
		{
			newClusterPoint[i] = 0;
		}
		
		for (int i=0 ; i<member.size() ; i++)
		{
			tmp = ((TextModelForArray)member.get(i)).getTextModelForArray();
			for (int j=0 ; j<dimension ; j++)					
			{
				newClusterPoint[j] += tmp[j];
				
			}
		}
		
		for (int i=0 ; i<dimension ; i++)
		{
			newClusterPoint[i] /= this.memberSize();
		}
		/***************��֤�¾۵���ԭ�۵��Ƿ���ͬ������ͬ����¾۵㲢����true����ͬ�򷵻�false*******************/
		double[] oldClusterPoint = tmfsv.getTextModelForArray();
		boolean flag = false;
		
		for (int i=0 ; i<dimension ; i++)
		{
			if (oldClusterPoint[i] - newClusterPoint[i] > 0.0001 || oldClusterPoint[i] - newClusterPoint[i] < -0.0001)
			{
				flag = true;
				break;
			}
		}
//		if (flag)
//		{
//			System.out.println("..........�۵����........"+this.memberSize());
//			
//		}
//		else
//			System.out.println(".............�۵�û����");
		
//		StringBuilder print = new StringBuilder("[");
//		for (int i=0 ; i<dimension ; i++)
//		{
//			print.append(newClusterPoint[i]+", ");
//		}
//		print.deleteCharAt(print.length()-1);
//		print.append("]");
//		System.out.println(print);
				
		tmfsv.setTextModelForArray(newClusterPoint);
		return flag;
	}

	@Override
	public double sim(Group g)
	{
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public void setClusterPoint(TextModel clusterPoint)
//	{
//		// TODO Auto-generated method stub
//		TextModelForSpaceVector tmfsv = new TextModelForSpaceVector();
//		if (!(clusterPoint instanceof TextModelForSpaceVector))
//		{
//			throw new RuntimeException("���Ͳ��ԣ�"); 
//		}
//		TextModelForSpaceVector tmp = (TextModelForSpaceVector)clusterPoint.clone();
//		
//	}
	
	
}
