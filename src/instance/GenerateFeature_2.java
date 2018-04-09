package instance;

import java.util.*;

import feature.GenerateFeature;
import text.Text;

/**
 * 该类用于生成文本特征，并将模型存入文本内。
 * @author yzc
 *
 */
public class GenerateFeature_2 implements GenerateFeature
{

	@Override
	public void generateFeature(Text t)
	{
		// TODO Auto-generated method stub
		Map<String, Double> fm = new TreeMap<String, Double>();			//文本特征
		
		for(int i=0; i<t.getContent().length(); i++)					//以单个字为特征存入键中，值中存入-1
		{
			fm.put(t.getContent().substring(i, i+1) , -1.0);
		}
		
		t.setFeature(fm);												//将生成的文本特征存入文本中
	}
}
