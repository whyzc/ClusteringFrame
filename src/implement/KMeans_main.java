package implement;

import java.io.FileNotFoundException;
import java.util.*;

import clustering.KMeans;
import clusteringLayer.Group;
import clusteringLayer.KMeansAlgo;
import feature.GenerateFeature;
import model.EstablishModel;
import model.TextModel;
import text.Text;

public class KMeans_main
{
	private final static int K = 10;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		/**********�����ı�����***************/
		List<Text> ts = new ArrayList<Text>();
		//DocInit di = new DocInit("����6��", "-");
		//DocInit di = new DocInit("����217��", "-");		
		//DocInit di = new DocInit("D:\\JavaProjForJse\\���Ͽ�\\tc-corpus-answer", null);
		DocInit di = new DocInit("D:\\JavaProjForJse\\���Ͽ�\\�ı��������Ͽ�\\�ı��������Ͽ�", null);
		di.readFile(ts);
		
		/*************����KMeans**************/
		KMeans userkm = new KMeansImpl();
		List<Group> g = userkm.kMeans(ts, K);
		
		

		
		/*****************��ӡ������*********************/
		for (int i=0 ; i<K ; i++)
		{		
			List<String> titles = g.get(i).getTitlesInFlatGroup();
			//System.out.println("��"+(i+1)+"�أ�"+titles.size());
			System.out.println(titles);
		}
		
		/*****************����****************************/
		System.out.println();
		System.out.println();
		System.out.println("/*****************����****************************/");
		Evaluation eva = new Evaluation(g, di);
		double purity = eva.purity();
		double ri = eva.RI();
		double f1 = eva.FValue(1);
		System.out.println("����Ϊ��"+purity);
		System.out.println("RI = "+ri);
		System.out.println("F1 = "+f1);
	}
}
