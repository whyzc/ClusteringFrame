package implement;

import java.io.FileNotFoundException;
import java.util.*;

import clustering.KMeans;
import clusteringLayer.Group;
import text.Text;

public class KMeans_main
{
	private final static int K = 4;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		// TODO Auto-generated method stub
		/**********�����ı�����***************/
		List<Text> ts = new ArrayList<Text>();
		//DocInit di = new DocInit("����6��");
		DocInit di = new DocInit("����217��");
		//DocInit di = new DocInit("D:\\JavaProjForJse\\tc-corpus-answer\\answer");
		di.readFile(ts);
		
		/*************����KMeans**************/
		KMeans userkm = new KMeansImpl();
		List<Group> g = userkm.kMeans(ts, K);
		
		
		/*****************��ӡ������*********************/
		for (int i=0 ; i<K ; i++)
		{		
			List<String> titles = g.get(i).getTitlesInFlatGroup();
			System.out.println(titles);
		}
		
		/*****************����****************************/
		System.out.println();
		System.out.println();
		System.out.println("/*****************����****************************/");
		Evaluation eva = new Evaluation(g);
		eva.generateSampleSet(di.getTotalTitles());
		double purity = eva.purity();
		double ri = eva.RI();
		double f1 = eva.FValue(1);
		double f5 = eva.FValue(5);
		System.out.println("����Ϊ��"+purity);
		System.out.println("RI = "+ri);
		System.out.println("F1 = "+f1);
		System.out.println("F5 = "+f5);

	}
}
