package implement;

import java.util.*;

import clustering.KMeans;
import clusteringLayer.Group;
import clusteringLayer.KMeansAlgo;
import feature.GenerateFeature;
import similarity.EstablishModel;
import similarity.TextModel;
import text.Text;

/**
 * ����ͻ�ʹ�õ�KMeans�ӿڵ�ʵ��
 * @author yzc
 *
 */
public class KMeansImpl implements KMeans<String>
{

	@Override
	public List<Group<String>> kMeans(String pos , int k)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group<String>> kMeans(List<Text> ts , int k)
	{
		// TODO Auto-generated method stub
		/***************���ı���������******************/
		GenerateFeature gf = new GenerateFeatureWordBased();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}
		
		
		/*****************�����ı�ģ��*********************/	
		EstablishModel<String> em = new EstablishModelWordBased();
		List<TextModel<String>> tms = em.modeling(ts);
		
		
		/*****************KMeans�㷨*********************/	
		KMeansAlgo<String> km = new KMeansAlgoImpl();
		List<Group<String>> g = km.kMeans(tms, k);
		
		return g;
	}

}
