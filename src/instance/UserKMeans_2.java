package instance;

import java.util.*;
import clustering.Group;
import clustering.KMeans;
import feature.GenerateFeature;
import similarity.EstablishModel;
import similarity.TextModel;
import text.Text;
import user.UserKMeans;

public class UserKMeans_2 implements UserKMeans<String>
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
		/***************对文本生成特征******************/
		GenerateFeature gf = new GenerateFeature_2();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}
		
		
		/*****************生成文本模型*********************/	
		EstablishModel<String> em = new EstablishModel_2();
		List<TextModel<String>> tms = em.modeling(ts);
		
		
		/*****************KMeans算法*********************/	
		KMeans<String> km = new KMeans_2();
		List<Group<String>> g = km.kMeans(tms, k);
		
		return g;
	}

}
