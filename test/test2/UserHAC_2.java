package test2;

import java.util.*;

import clustering.Group;
import feature.GenerateFeature;
import similarity.EstablishModel;
import similarity.TextModel;
import text.Text;
import user.UserHAC;

public class UserHAC_2 implements UserHAC
{

	@Override
	public Group hac(String pos)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group hac(List<Text> ts)
	{
		// TODO Auto-generated method stub
		GenerateFeature gf = new GenerateFeature_2();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}

		EstablishModel em = new EstablishModel_2();
		List<TextModel> tms = em.modeling(ts);
		
	
		Group g = new HAC_2().hac(tms);
		
		return g;
	}

}
