package test2;

import java.util.*;

import similarity.TextModel;

public class TextModel_2 extends TextModel
{
	public List<String> t = new ArrayList<String>();
	public int no = -1;
	public String name = null;
	
	@Override
	public double distance(TextModel tm)
	{
		// TODO Auto-generated method stub
		int count = 0;
		TextModel_2 tm2 = (TextModel_2)tm;
		for (int i=0 ; i<t.size() ; i++)
		{
			String str = t.get(i);
			if (tm2.t.contains(str))
			{
				count++;
			}
		}
		return count;
	}
	
	@Override
	public void setTextModel(Object obj)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public double sim(TextModel tm)
	{
		// TODO Auto-generated method stub
		int commonWords;
		int totalWords;
		
		return 0;
	}

}
