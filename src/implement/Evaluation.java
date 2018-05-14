package implement;

import java.util.*;

import clusteringLayer.Group;
import model.TextModel;

public class Evaluation
{
	
	private List<List<String>> sampleSet;			//样本结果集
	private List<String> matchKeywords;				//用于匹配的关键字
	private List<List<String>> clusteringResult;	//聚类结果
	private List<int[]> analysisOfResults;			//对聚类结果的分析
	
	private int TP = 0;
	private int FP = 0;
	private int TN = 0;
	private int FN = 0;
	
	public List<int[]> getAnalysisOfResults()
	{
		return analysisOfResults;
	}

	int totalText = 0;
	
	
	public Evaluation(List<Group> grps)
	{
		clusteringResult = new ArrayList<List<String>>();
		for (int i=0 ; i<grps.size() ; i++)
		{		
			List<String> titles = grps.get(i).getTitlesInFlatGroup();
			clusteringResult.add(titles);
		}
	}
	
	/**
	 * 计算聚类结果的纯度。必须先完成初始化（执行generateSampleSet(totalTitles)或setSampleSet(sampleSet)方法）
	 * @return 纯度
	 */
	public double purity()
	{
		List<String> tmp;
		int[] count = new int[sampleSet.size()];
		int[] purityNum = new int[clusteringResult.size()];
		int site;
		
		if (clusteringResult.size()<1 || sampleSet.size()<1)
		{
			throw new RuntimeException("评价初始化出错！");
		}
		if (totalText<1)
		{
			throw new RuntimeException("评价初始化出错！");
		}
		
		for (int i=0 ; i<clusteringResult.size() ; i++)
		{
			
			tmp = clusteringResult.get(i);
			
			
			for (int j=0 ; j<count.length ; j++)	//计数数组count清空
			{
				count[j] = 0;
			}
			
			for (int j=0 ; j<tmp.size() ; j++)		//
			{
				if ((site = matchKeywords.indexOf(extractKeyword(tmp.get(j)))) != -1)
				{
					count[site]++;
				}
			}
			
			int maxSite = -1;
			int max = -1;
			for (int j=0 ; j<count.length ; j++)
			{
				if (max < count[j])
				{
					max = count[j];
					maxSite = j;
				}
			}
			if (maxSite >= 0)
			{
				purityNum[i] = max;
			}
		}
		
		int sum=0;
		for (int i=0 ; i<purityNum.length ; i++)
		{
			sum += purityNum[i];
		}
		return sum*1.0/totalText;
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
	
	
	/**
	 * 根据所有样本文本的标题生成样本结果集；该方法与setSampleSet(sampleSet)需执行一个作为评价的初始化。
	 * @param totalTitles 存有所有样本文本的标题
	 */
	public void generateSampleSet(List<String> totalTitles)
	{
		sampleSet = new ArrayList<List<String>>();
		matchKeywords = new ArrayList<String>();
		int site;
		String keyword;
		
		if ((keyword = extractKeyword(totalTitles.get(0))) != null)		//初始化第一个匹配关键字
		{
			matchKeywords.add(keyword);
			
			sampleSet.add(new ArrayList<String>());
			sampleSet.get(0).add(totalTitles.get(0));
		}
		
		for (int i=1 ; i<totalTitles.size() ; i++)
		{
			keyword = extractKeyword(totalTitles.get(i));
			if (keyword == null)
				continue;
		
			if (matchKeywords.contains(keyword))
			{
				site = matchKeywords.indexOf(keyword);
				sampleSet.get(site).add(totalTitles.get(i));
			}
			else
			{
				matchKeywords.add(keyword);
				sampleSet.add(new ArrayList<String>());
				sampleSet.get(sampleSet.size()-1).add(totalTitles.get(i));
			}
		}
		totalText = totalTitles.size();
		decisionInit();
	}
	
	/**
	 * 设置样本结果集；该方法与generateSampleSet(totalTitles)需执行一个作为评价的初始化。
	 * @param sampleSet 被设置的样本结果集
	 */
	public void setSampleSet(List<List<String>> sampleSet)
	{
		this.sampleSet = sampleSet;
		totalText = 0;
		for (int i=0 ; i<sampleSet.size() ; i++)
		{
			totalText += sampleSet.get(i).size();
		}
		
		decisionInit();
	}
	
	private String extractKeyword(String str)		//提取用于匹配的关键字
	{
		String[] keywords = str.split("-");
		if (keywords.length == 0)
		{
			return null;
		}
		return keywords[0];
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
		int site;
		for (int i=0 ; i<g.size() ; i++)
		{
			if ((site=matchKeywords.indexOf(extractKeyword(g.get(i)))) != -1)
			{
				result[site]++;
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
		
		TN = totalText * (totalText-1) /2 - TPPlusFP - FN;		//算出TN
	}
}
