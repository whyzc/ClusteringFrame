package implement;

import java.util.*;

import clustering.KMeans;
import clusteringLayer.Group;
import clusteringLayer.KMeansAlgo;
import feature.GenerateFeature;
import model.EstablishModel;
import model.TextModel;
import text.Text;

/**
 * ����ͻ�ʹ�õ�KMeans�ӿڵ�ʵ��
 * @author yzc
 *
 */
public class KMeansImpl implements KMeans
{

	@Override
	public List<Group> kMeans(String pos , int k)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> kMeans(List<Text> ts , int k)
	{
		// TODO Auto-generated method stub
		/***************���ı���������******************/
		GenerateFeature gf = new GenerateFeatureWordBased();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}
		
		
		/*****************�����ı�ģ��*********************/	
		EstablishModel em = new EstablishModelForSpaceVector();
		List<TextModel> tms = em.modeling(ts);
		
		
		/*****************KMeans�㷨*********************/	
		KMeansAlgo km = new KMeansAlgoImpl();
		List<Group> g = km.kMeans(tms, k);
		
		return g;
	}

}
