package implement;

import java.io.FileNotFoundException;
import java.util.*;

import clustering.KMeans;
import clusteringLayer.Group;
import text.Text;

public class KMeans_main
{
	private final static int K = 3;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		// TODO Auto-generated method stub
		/**********设置文本内容***************/
		List<Text> ts = new ArrayList<Text>();
		//DocInit di = new DocInit("样本6个", "-");
		DocInit di = new DocInit("样本217个", "-");		
		//DocInit di = new DocInit("D:\\JavaProjForJse\\ClusteringFrame\\新建文件夹");
		//DocInit di = new DocInit("D:\\JavaProjForJse\\语料库\\tc-corpus-answer", null);
		di.readFile(ts);
		
		/*************调用KMeans**************/
		KMeans userkm = new KMeansImpl();
		List<Group> g = userkm.kMeans(ts, K);
		
		
		/*****************打印聚类结果*********************/
		for (int i=0 ; i<K ; i++)
		{		
			List<String> titles = g.get(i).getTitlesInFlatGroup();
			System.out.println(titles);
		}
		
		/*****************评价****************************/
		System.out.println();
		System.out.println();
		System.out.println("/*****************评价****************************/");
		Evaluation eva = new Evaluation(g, di);
		double purity = eva.purity();
		double ri = eva.RI();
		double f1 = eva.FValue(1);
		double f5 = eva.FValue(5);
		System.out.println("纯度为："+purity);
		System.out.println("RI = "+ri);
		System.out.println("F1 = "+f1);
		System.out.println("F5 = "+f5);

	}
}
