package implement;

import model.TextModel;
import model.TextModelForArray;

public class TextModelForVectorSpace extends TextModelForArray
{

	@Override
	public double distance(TextModel tm)
	{
		TextModelForArray tmfa = (TextModelForArray)tm;
		double[] v1 = tmfa.getTextModelForArray();
		double[] v2 = this.getTextModelForArray();
		double squareOfResult = 0;
		for (int i=0 ; i<v1.length ; i++)
		{
			squareOfResult += (v1[i]-v2[i]) * (v1[i] - v2[i]);
		}
		return Math.sqrt(squareOfResult);
	}

	@Override
	public double sim(TextModel tm)
	{
		return this.cosineOfVectors(tm);
	}

	@Override
	public void setTextModel(Object obj)
	{
		// TODO Auto-generated method stub
		
	}
	
	public TextModelForVectorSpace clone() 
	{
		TextModelForVectorSpace result = new TextModelForVectorSpace();
		result.setTextModelForArray(this.getTextModelForArray().clone());
		return result;
	}

}
