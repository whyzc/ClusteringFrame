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
		
		/**********设置文本内容***************/
		Text t1 = new TextImpl("我今天很开心", "1", 1);
		Text t2 = new TextImpl("今天天气很好", "2", 2);
		Text t3 = new TextImpl("月亮真的好漂亮", "3", 3);
		
		List<Text> ts = new ArrayList<Text>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		
		/*************调用KMeans**************/
		HAC<String> userhac = new HACImpl();
		Group<String> g = userhac.hac(ts);
		
		
	}	
		
}
