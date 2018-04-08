package test2;

import java.util.*;

import clustering.Group;
import clustering.HAC;
import feature.GenerateFeature;
import similarity.EstablishModel;
import similarity.TextModel;
import text.Text;
import user.UserHAC;

public class UserHAC_2 implements UserHAC<String>
{



	@Override
	public Group<String> hac(List<Text> ts)
	{
		// TODO Auto-generated method stub
		/***************���ı���������******************/
		GenerateFeature gf = new GenerateFeature_2();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}
		
		
		/*****************�����ı�ģ��*********************/	
		EstablishModel<String> em = new EstablishModel_2();
		List<TextModel<String>> tms = em.modeling(ts);
		
		
		/*****************KMeans�㷨*********************/	
		HAC<String> hac = new HAC_2();
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
