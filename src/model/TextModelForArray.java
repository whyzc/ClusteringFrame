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
	 * ��������
	 * @return ��������
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
	 * ������
	 * @param tm ���������Ķ���
	 * @return ����������������
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
	 * ��������ֵ
	 * @param tm �����ҵĶ���
	 * @return	��������������ֵ
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
