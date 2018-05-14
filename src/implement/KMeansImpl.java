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
 * 面向客户使用的KMeans接口的实现
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
		/***************对文本生成特征******************/
		GenerateFeature gf = new GenerateFeatureWordBased();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}
		
		
		/*****************生成文本模型*********************/	
		EstablishModel em = new EstablishModelForSpaceVector();
		List<TextModel> tms = em.modeling(ts);
		
		
		/*****************KMeans算法*********************/	
		KMeansAlgo km = new KMeansAlgoImpl();
		List<Group> g = km.kMeans(tms, k);
		
		return g;
	}

}
