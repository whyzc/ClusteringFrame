package test2;

import java.util.*;

import clustering.Group;
import clustering.KMeans;
import feature.GenerateFeature;
import similarity.EstablishModel;
import similarity.TextModel;
import text.Text;
import user.UserKMeans;

public class KMeans_main
{
	private final static int K = 2;
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		/**********�����ı�����***************/
		Text t1 = new Text_2("�ҽ���ܿ���", "1", 1);
		Text t2 = new Text_2("���������ܺ�", "2", 2);
		Text t3 = new Text_2("������ĺ�Ư��", "3", 3);
		
		List<Text> ts = new ArrayList<Text>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		
		/*************����KMeans**************/
		UserKMeans<String> userkm = new UserKMeans_2();
		List<Group<String>> g = userkm.kMeans(ts, K);
		
		
		/*****************��ӡ������*********************/
		for (int i=0 ; i<K ; i++)
		{
			Iterator it  = g.get(i).getMember().iterator();
			System.out.printf("��%d�飺", i+1);
			while(it.hasNext())
			{
				TextModel_2 t = (TextModel_2)it.next();
				System.out.print(t.getNo() + ", ");
			}
			System.out.println();
		}
	}
}
