package test2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import similarity.TextModel;
import similarity.EstablishModel;
import text.Text;

public class EstablishModel_2 implements EstablishModel
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
		// TODO Auto-generated method stub
		List<TextModel> tms = new ArrayList<TextModel>();
		for(int i=0 ; i<ts.size() ; i++)
		{
			TextModel_2 tm = new TextModel_2();
			
			Iterator<String> it = ts.get(i).getFeature().keySet().iterator();
			
			while (it.hasNext())
			{
				String key = it.next();
				tm.t.add(key);
			}
			
			tm.no = ts.get(i).getNo();
			tms.add(tm);
		}
		return tms;
	}

}
