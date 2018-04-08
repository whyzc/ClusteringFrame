package test2;

import similarity.TextModel;

public class TextModel_2 extends TextModel<String>
{
	
	@Override
	public double distance(TextModel<String> tm)
	{
		// TODO Auto-generated method stub
		double count = 0;
		TextModel_2 tm2 = (TextModel_2)tm;
		for (int i=0 ; i<this.getTextModel().size() ; i++)
		{
			String str = this.getTextModel().get(i);
			if (tm2.getTextModel().contains(str))
			{
				count++;
			}
		}
		return count;
	}
	

	@Override
	public double sim(TextModel<String> tm)
	{
		// TODO Auto-generated method stub
		double inter = this.distance(tm);
		double result = inter / (this.textModel.size() + tm.getTextModel().size() - inter);
		return result;
	}


	@Override
	public void setTextModel(Object obj)
	{
		// TODO Auto-generated method stub
		
	}

}
