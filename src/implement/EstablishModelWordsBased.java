package implement;

import java.util.*;

import model.EstablishModel;
import model.TextModel;
import text.Text;

public class EstablishModelWordsBased implements EstablishModel
{

	@Override
	public TextModel modeling(Text t)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TextModel> modeling(List<Text> ts)
	{
		Map<String, Integer> totalSpace = new TreeMap<String, Integer>();
		List<TextModel> result = new ArrayList<TextModel>();
		
		for(int i=0 ; i<ts.size() ; i++)			//构建一个总空间
		{
			
			Iterator it = ts.get(i).getFeature().keySet().iterator();
			while (it.hasNext())
			{
				String key = (String)it.next();
				if (!totalSpace.containsKey(key))
					totalSpace.put(key, 1);
				else
				{
					int tmp;
					tmp = totalSpace.get(key) + 1;
					totalSpace.put(key, tmp);
				}
			}
		}
		
		System.out.println(totalSpace.size());
		for(int i=0 ; i<ts.size() ; i++)			//建立空间向量模型
		{
			TextModelForVectorSpace tm;
			tm = new TextModelForVectorSpace();
			Iterator<String> allFeatures = totalSpace.keySet().iterator();
			double[] v;
			v = new double[totalSpace.size()];
			
			int site = 0;
			while (allFeatures.hasNext())
			{
				String key = allFeatures.next();
				
				if (ts.get(i).getFeature().containsKey(key))
				{
					v[site] = weight(ts.get(i).getFeature().get(key), totalSpace.get(key), ts.size());
				}
				else
				{
					v[site] = 0;
				}
				site++;
			}
			
			
			tm.setTextModelForArray(v);
			if (tm.getTextModelForArray().length != totalSpace.size())
			{
				throw new RuntimeException("建模出错！");
			}
			
			tm.setNo(ts.get(i).getNo());			//设置文本编号
			tm.setTitle(ts.get(i).getTitle());		//设置文本标题
			result.add(tm);
		}
		return result;
	}
	
	private double weight(double tf, double df, int n)
	{
		
		return tf*Math.log(n/df);
	}

}
