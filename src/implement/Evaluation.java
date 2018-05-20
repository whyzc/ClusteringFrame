package implement;

import java.util.*;

import clusteringLayer.Group;

public class Evaluation
{
	private List<List<String>> clusteringResult;	//聚类结果
	private List<int[]> analysisOfResults;			//对聚类结果的分析
	private List<List<String>> sampleSet;
	private int textNum;
	
	
	private int TP = 0;
	private int FP = 0;
	private int TN = 0;
	private int FN = 0;
	
	public List<int[]> getAnalysisOfResults()
	{
		return analysisOfResults;
	}
	
	
	public Evaluation(List<Group> grps, DocInit docInit)
	{
		clusteringResult = new ArrayList<List<String>>();
		for (int i=0 ; i<grps.size() ; i++)
		{		
			List<String> titles = grps.get(i).getTitlesInFlatGroup();
			clusteringResult.add(titles);
		}
		
		if (!docInit.isNeedToEvaluate())
		{
			throw new RuntimeException("非评价语料，无法评价"	);
		}
		this.textNum = docInit.getTextNum();
		this.sampleSet = docInit.getSampleSet();
		
		decisionInit();
	}
	
	/**
	 * 计算聚类结果的纯度。
	 * @return 纯度
	 */
	public double purity()
	{
		int sum=0;
		for (int i=0 ; i<analysisOfResults.size() ; i++)
		{
			sum += maxValueInArray(analysisOfResults.get(i));
		}
		return sum*1.0/textNum;
	}
	
	/**
	 * 计算RI值
	 * @return 返回RI值
	 */
	public double RI()
	{
		//System.out.println("TP="+TP+";  FP="+FP+";  FN="+FN+";  TN="+TN);
		return (TP+TN)*1.0/(TP+FP+FN+TN);
	}
	
	public double FValue(int beta)
	{
		
		double P;
		double R;
		double F;
		P = TP*1.0/(TP+FP);
		R = TP*1.0/(TP+FN);
		F = (beta*beta + 1)*P*R / (beta*beta*P + R);
		return F;
	}
	
	
	
	
	private static int arrange(int a, int b)	//排列
	{
		int smallerValue;
		int biggerValue;
		int result = 1;
		if (a<0 || b<0)
		{
			return -1;
		}
		if (a<=b)
		{
			smallerValue = a;
			biggerValue = b;
		}
		else
		{
			smallerValue = b;
			biggerValue = a;
		}
		
		for (int i=0 ; i<smallerValue ; i++)
		{
			result *= (biggerValue-i);
		}
		return result;
	}
	
	private static int combine(int a, int b)	//组合
	{
		int smallerValue;
		int biggerValue;
		int molecular;
		int denominator;
		if (a<0 || b<0)
		{
			return -1;
		}
		if (a<=b)
		{
			smallerValue = a;
			biggerValue = b;
		}
		else
		{
			smallerValue = b;
			biggerValue = a;
		}
		
		molecular = arrange(a, b);
		denominator = arrange(smallerValue, smallerValue);
		return molecular/denominator;
	}
	
	private int[] analysisOfGroup(List<String> g)	//对一个簇进行解析，找出簇内包含各个类别的文档数并将其返回
	{
		int[] result = new int[sampleSet.size()];
		
		for (int i=0 ; i<g.size() ; i++)
		{
			for (int j=0 ; j<sampleSet.size() ; j++)
			{
				if (sampleSet.get(j).contains(g.get(i)))
				{
					result[j]++;
				}
			}
		}
		return result;
	}
	
	/**
	 * 计算TP、FP、FN、TN，在计算RI值和F值前调用
	 */
	private void decisionInit()
	{
		int TPPlusFP = 0;
		TP = 0;
		FP = 0;
		TN = 0;
		FN = 0;
		
		analysisOfResults = new ArrayList<int[]>();
		for (int i=0 ; i<clusteringResult.size() ; i++)				//分析聚类结果，生成analysisOfResults
		{
			analysisOfResults.add(analysisOfGroup(clusteringResult.get(i)));
		}
		
		for (int i=0 ; i<clusteringResult.size() ; i++)				//算出TP+FP
		{
			if (clusteringResult.get(i).size()>1)
				TPPlusFP += combine(clusteringResult.get(i).size(), 2);
		}
		
		for (int i=0 ; i<analysisOfResults.size() ; i++)			//算出TP
		{
			for (int j=0 ; j<analysisOfResults.get(i).length ; j++)
			{
				int num;
				if ((num = analysisOfResults.get(i)[j]) > 1)
				{
					TP += combine(num, 2);
				}
			}
		}
		
		FP = TPPlusFP - TP;				//算出FP
		
//		for (int i=0 ; i<analysisOfResults.size() ; i++)			//算出FP
//		{
//			for (int j=0 ; j<analysisOfResults.get(i).length ; j++)
//			{
//				for (int k=j+1 ; k<analysisOfResults.get(i).length ; k++)
//				{
//					FP += analysisOfResults.get(i)[j] * analysisOfResults.get(i)[k];
//				}
//			}
//		}
		
		for (int i=0 ; i<analysisOfResults.size() ; i++)			//算出FN
		{
			for (int j=i+1 ; j<analysisOfResults.size() ; j++)
			{
				for (int k=0 ; k<analysisOfResults.get(i).length ; k++)
				{
					FN += analysisOfResults.get(i)[k] * analysisOfResults.get(j)[k];
				}
			}
		}
		
		TN = textNum * (textNum-1) /2 - TPPlusFP - FN;		//算出TN
	}
	
	private int maxValueInArray(int[] array)
	{
		int max = -1;
		for (int i=0 ; i<array.length ; i++)
		{
			if (max < array[i])
				max = array[i];
		}
		return max;
	}
}
