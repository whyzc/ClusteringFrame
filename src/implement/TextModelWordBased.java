package implement;

import model.TextModel;

/**
 * 基于字的文本模型
 * @author yzc
 *
 */
public class TextModelWordBased extends TextModel
{
	
	@Override
	public double distance(TextModel tm)
	{
		// TODO Auto-generated method stub
		double count = 0;
		TextModelWordBased tm2 = (TextModelWordBased)tm;
		for (int i=0 ; i<this.getTextModel().size() ; i++)
		{
			String str = (String)this.getTextModel().get(i);
			if (tm2.getTextModel().contains(str))
			{
				count++;
			}
		}
		return count;
	}
	

	@Override
	public double sim(TextModel tm)
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
