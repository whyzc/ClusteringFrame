package implement;

import java.util.*;

import clustering.HAC;
import clusteringLayer.Group;
import text.Text;

public class HAC_main
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		/**********�����ı�����***************/
		Text t1 = new TextImpl("�ҽ���ܿ���", "1", 1);
		Text t2 = new TextImpl("���������ܺ�", "2", 2);
		Text t3 = new TextImpl("������ĺ�Ư��", "3", 3);
		
		List<Text> ts = new ArrayList<Text>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		
		/*************����KMeans**************/
		HAC<String> userhac = new HACImpl();
		Group<String> g = userhac.hac(ts);
		
		
	}	
		
}
