package implement;

import java.util.ArrayList;

import model.TextModel;
import model.TextModelForList;

/**
 * 基于字的文本模型
 * @author yzc
 *
 */
public class TextModelWordBased extends TextModelForList
{
	public TextModelWordBased()
	{
		this.setTextModelForList(new ArrayList<String>());
	}
	@Override
	public double distance(TextModel tm)
	{
		// TODO Auto-generated method stub
		double count = 0;
		TextModelWordBased tm2 = (TextModelWordBased)tm;
		for (int i=0 ; i<this.getTextModelForList().size() ; i++)
		{
			String str = (String)this.getTextModelForList().get(i);
			if (tm2.getTextModelForList().contains(str))
			{
				count++;
			}
		}
		return count;
	}

	@Override
	public double sim(TextModel tm)
	{
		TextModelWordBased tmwb = (TextModelWordBased)tm;
		double inter = this.distance(tm);
		double result = inter / (this.getTextModelForList().size() + tmwb.getTextModelForList().size() - inter);
		return result;
	}


	@Override
	public void setTextModel(Object obj)
	{
		// TODO Auto-generated method stub
		
	}

}
