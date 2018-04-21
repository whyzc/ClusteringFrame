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
		GenerateFeature gf = new GenerateFeatureWordBased();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}
		
		
		/*****************�����ı�ģ��*********************/	
		EstablishModel em = new EstablishModelWordBased();
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
		return null;
	}



	@Override
	public List<Group> split(Group grp, double sim)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
