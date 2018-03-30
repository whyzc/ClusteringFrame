package test2;

import java.util.*;

import feature.GenerateFeature;
import text.Text;

public class GenerateFeature_2 implements GenerateFeature
{

	@Override
	public void generateFeature(Text t)
	{
		// TODO Auto-generated method stub
		Map<String, Double> fm = new TreeMap<String, Double>();
		for(int i=0; i<t.getContent().length(); i++)
		{
			fm.put(t.getContent().substring(i, i+1) , -1.0);
		}
		t.setFeature(fm);
	}
}
