package implement;

import java.util.*;

import clustering.KMeans;
import clusteringLayer.Group;
import text.Text;

public class KMeans_main
{
	private final static int K = 2;
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		/**********�����ı�����***************/
		Text t1 = new TextImpl("�ҽ���ܿ���", "����1", 1);
		Text t2 = new TextImpl("���������ܺ�", "����2", 2);
		Text t3 = new TextImpl("������ĺ�Ư��", "����3", 3);
		
		List<Text> ts = new ArrayList<Text>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		
		/*************����KMeans**************/
		KMeans userkm = new KMeansImpl();
		List<Group> g = userkm.kMeans(ts, K);
		
		
		/*****************��ӡ������*********************/
		for (int i=0 ; i<K ; i++)
		{		
			List<String> titles = g.get(i).getTitlesInFlatGroup();
			System.out.println(titles);
		}
	}
}
