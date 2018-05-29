package model;

public abstract class TextModelForArray extends TextModel
{
	private double[] TextModelForArray;

	public double[] getTextModelForArray()
	{
		return TextModelForArray;
	}

	public void setTextModelForArray(double[] textModelForArray)
	{
		TextModelForArray = textModelForArray;
	}

	/**
	 * 向量长度
	 * @return 向量长度
	 */
	public double vectorLen()		
	{
		double sumOfSquares = 0;
		for (int i=0 ; i<TextModelForArray.length ; i++)
		{
			sumOfSquares += TextModelForArray[i]*TextModelForArray[i];
		}
		
		return Math.sqrt(sumOfSquares);
	}
	
	
	/**
	 * 向量积
	 * @param tm 作向量积的对象
	 * @return 两个向量的向量积
	 */
	public double ScalarProduct(TextModel tm)
	{
		TextModelForArray tmfa = (TextModelForArray)tm;
		double[] v = tmfa.getTextModelForArray();
		double result = 0;
		for (int i=0 ; i<TextModelForArray.length ; i++)
		{
			result += TextModelForArray[i] * v[i];
		}
		return result;
	}
	
	/**
	 * 向量余弦值
	 * @param tm 作余弦的对象
	 * @return	两个向量的余弦值
	 */
	public double cosineOfVectors(TextModel tm)
	{
		TextModelForArray tmfa = (TextModelForArray)tm;
		double denominater = vectorLen() * tmfa.vectorLen();
		if (denominater == 0)
		{
			return 0;
			
		}
		else
		{
			return (ScalarProduct(tm) / denominater);
		}
	}
}
