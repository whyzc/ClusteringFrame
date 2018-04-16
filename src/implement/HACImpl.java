package implement;

import java.util.*;

import clustering.HAC;
import clusteringLayer.Group;
import clusteringLayer.HACAlgo;
import feature.GenerateFeature;
import similarity.EstablishModel;
import similarity.TextModel;
import text.Text;

/**
 * 面向客户使用的HAC接口的实现
 * @author yzc
 *
 */
public class HACImpl implements HAC<String>
{
	@Override
	public Group<String> hac(List<Text> ts)
	{
		// TODO Auto-generated method stub
		/***************对文本生成特征******************/
		GenerateFeature gf = new GenerateFeatureWordBased();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}
		
		
		/*****************生成文本模型*********************/	
		EstablishModel<String> em = new EstablishModelWordBased();
		List<TextModel<String>> tms = em.modeling(ts);
		
		
		/*****************KMeans算法*********************/	
		HACAlgo<String> hac = new HACAlgoImpl();
		Group<String> g = hac.merge(tms);
		
		return g;
	}

	

	@Override
	public Group<String> hac(String pos)
	{
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Group<String>> split(Group<String> grp, int k)
	{
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Group<String>> split(Group<String> grp, double sim)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
