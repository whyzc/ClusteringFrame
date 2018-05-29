package implement;

import java.util.*;

import clustering.HAC;
import clusteringLayer.Group;
import clusteringLayer.HACAlgo;
import feature.GenerateFeature;
import model.EstablishModel;
import model.TextModel;
import text.Text;

/**
 * ����ͻ�ʹ�õ�HAC�ӿڵ�ʵ��
 * @author yzc
 *
 */
public class HACImpl implements HAC
{
	@Override
	public Group hac(List<Text> ts)
	{
		// TODO Auto-generated method stub
		/***************���ı���������******************/
		GenerateFeature gf = new GenerateFeatureCharacterBased();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}
		
		
		/*****************�����ı�ģ��*********************/	
		EstablishModel em = new EstablishModelCharacterBased();
		List<TextModel> tms = em.modeling(ts);
		
		
		/*****************KMeans�㷨*********************/	
		HACAlgo hac = new HACAlgoImpl();
		Group g = hac.merge(tms);
		
		return g;
	}

	

	@Override
	public Group hac(String pos)
	{
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Group> split(Group grp, int k)
	{
		// TODO Auto-generated method stub
		if (grp.getSubGroup().size() != grp.getSimValue().size())
		{
			throw new RuntimeException("�����Ӵ���Ŀ��ջ����ʶ����Ŀ����");
		}
		if (k<2)
		{
			throw new RuntimeException("�ֽ����̫��");
		}
		
		int j = k;
		List<Group> result= new ArrayList<Group>();
		result.add(grp);
		
		while (j>1)
		{
			double sim_tmp = 1.1;		//��¼��С���ƶ�
			int site_tmp = -1;			//��¼���ƶ���С�Ĵص�λ��
			Group grp_tmp;				//��ʱ��Ŵ�
			
			for (int i=0 ; i<result.size() ; i++)	//Ѱ����С���ƶȵĴ�
			{
				grp_tmp = result.get(i);
				if (grp_tmp.simValueSize()<1)
					break;
				if ( sim_tmp > grp_tmp.getSimValue().get(grp_tmp.simValueSize()-1) )
				{
					sim_tmp = grp_tmp.getSimValue().get(grp_tmp.simValueSize()-1);
					site_tmp = i;
				}
			}
			
			if (site_tmp > -1)			//�Դؽ��зָ�
			{
				result.add(result.get(site_tmp).pushGroup());
				result.get(site_tmp).pushSim();
			}
			
			j--;
		}
		return result;
	}



	@Override
	public List<Group> split(Group grp, double sim)
	{
		// TODO Auto-generated method stub
		if (grp.getSubGroup().size() != grp.getSimValue().size())
		{
			throw new RuntimeException("�����Ӵ���Ŀ��ջ����ʶ����Ŀ����");
		}
		if (sim<0 || sim>1)
		{
			throw new RuntimeException("��ʶ�Ȳ�����Ҫ��");
		}
		
		List<Group> result= new ArrayList<Group>();
		result.add(grp);
		Group grp_tmp;			//��ʱ��Ŵ�
		
		for (int i=0 ; i<result.size() ; i++)		//�����������н��зָ�
		{
			grp_tmp = result.get(i);
			if (grp_tmp.simValueSize()<1)			//û���Ӵأ�������
			{
				continue;
			}
			
			int site_tmp = -1;
			int k = grp_tmp.simValueSize()-1;
			while (sim >= grp_tmp.getSimValue().get(k))		//Ѱ�����ƶ�С��sim�Ĵ�λ��
			{
				site_tmp = k;
				k--;
				if (k<0)
				{
					site_tmp = 0;
					break;
				}
			}
			
			if (site_tmp == -1)
			{
				continue;
			}
			
			for (int j=site_tmp, m=grp_tmp.simValueSize() ; j<m ; j++)	//���������ƶ�С��Ҫ��Ľ��зָ�
			{
				result.add(result.get(i).pushGroup());
				result.get(i).pushSim();
			}
			
		}
		
		return result;
	}

}
