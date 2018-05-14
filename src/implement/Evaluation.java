package implement;

import java.util.*;

import clusteringLayer.Group;
import model.TextModel;

public class Evaluation
{
	
	private List<List<String>> sampleSet;			//���������
	private List<String> matchKeywords;				//����ƥ��Ĺؼ���
	private List<List<String>> clusteringResult;	//������
	private List<int[]> analysisOfResults;			//�Ծ������ķ���
	
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
	 * ����������Ĵ��ȡ���������ɳ�ʼ����ִ��generateSampleSet(totalTitles)��setSampleSet(sampleSet)������
	 * @return ����
	 */
	public double purity()
	{
		List<String> tmp;
		int[] count = new int[sampleSet.size()];
		int[] purityNum = new int[clusteringResult.size()];
		int site;
		
		if (clusteringResult.size()<1 || sampleSet.size()<1)
		{
			throw new RuntimeException("���۳�ʼ������");
		}
		if (totalText<1)
		{
			throw new RuntimeException("���۳�ʼ������");
		}
		
		for (int i=0 ; i<clusteringResult.size() ; i++)
		{
			
			tmp = clusteringResult.get(i);
			
			
			for (int j=0 ; j<count.length ; j++)	//��������count���
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
	 * ����RIֵ
	 * @return ����RIֵ
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
	 * �������������ı��ı�������������������÷�����setSampleSet(sampleSet)��ִ��һ����Ϊ���۵ĳ�ʼ����
	 * @param totalTitles �������������ı��ı���
	 */
	public void generateSampleSet(List<String> totalTitles)
	{
		sampleSet = new ArrayList<List<String>>();
		matchKeywords = new ArrayList<String>();
		int site;
		String keyword;
		
		if ((keyword = extractKeyword(totalTitles.get(0))) != null)		//��ʼ����һ��ƥ��ؼ���
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
	 * ����������������÷�����generateSampleSet(totalTitles)��ִ��һ����Ϊ���۵ĳ�ʼ����
	 * @param sampleSet �����õ����������
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
	
	private String extractKeyword(String str)		//��ȡ����ƥ��Ĺؼ���
	{
		String[] keywords = str.split("-");
		if (keywords.length == 0)
		{
			return null;
		}
		return keywords[0];
	}
	
	private static int arrange(int a, int b)	//����
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
	
	private static int combine(int a, int b)	//���
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
	
	private int[] analysisOfGroup(List<String> g)	//��һ���ؽ��н������ҳ����ڰ������������ĵ��������䷵��
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
	 * ����TP��FP��FN��TN���ڼ���RIֵ��Fֵǰ����
	 */
	private void decisionInit()
	{
		int TPPlusFP = 0;
		TP = 0;
		FP = 0;
		TN = 0;
		FN = 0;
		
		analysisOfResults = new ArrayList<int[]>();
		for (int i=0 ; i<clusteringResult.size() ; i++)				//����������������analysisOfResults
		{
			analysisOfResults.add(analysisOfGroup(clusteringResult.get(i)));
		}
		
		for (int i=0 ; i<clusteringResult.size() ; i++)				//���TP+FP
		{
			if (clusteringResult.get(i).size()>1)
				TPPlusFP += combine(clusteringResult.get(i).size(), 2);
		}
		
		for (int i=0 ; i<analysisOfResults.size() ; i++)			//���TP
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
		
		FP = TPPlusFP - TP;				//���FP
		
//		for (int i=0 ; i<analysisOfResults.size() ; i++)			//���FP
//		{
//			for (int j=0 ; j<analysisOfResults.get(i).length ; j++)
//			{
//				for (int k=j+1 ; k<analysisOfResults.get(i).length ; k++)
//				{
//					FP += analysisOfResults.get(i)[j] * analysisOfResults.get(i)[k];
//				}
//			}
//		}
		
		for (int i=0 ; i<analysisOfResults.size() ; i++)			//���FN
		{
			for (int j=i+1 ; j<analysisOfResults.size() ; j++)
			{
				for (int k=0 ; k<analysisOfResults.get(i).length ; k++)
				{
					FN += analysisOfResults.get(i)[k] * analysisOfResults.get(j)[k];
				}
			}
		}
		
		TN = totalText * (totalText-1) /2 - TPPlusFP - FN;		//���TN
	}
}
