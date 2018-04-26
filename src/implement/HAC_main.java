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
		Text t1 = new TextImpl("我今天很开心", "文章1", 1);
		Text t2 = new TextImpl("今天天气很好", "文章2", 2);
		Text t3 = new TextImpl("月亮真的好漂亮", "文章3", 3);
		
		List<Text> ts = new ArrayList<Text>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		
		/*************调用HAC,获得聚类后的层次簇**************/
		HAC userhac = new HACImpl();
		Group g = userhac.hac(ts);
		
		/**************对聚类后的簇进行分割****************/
//		List<Group> grps = userhac.split(g, 2);
		List<Group> grps = userhac.split(g, 0.02);
		
		/**************打印结果****************/
		List<String> title;
		for (int i=0 ; i<grps.size() ; i++)
		{
			title = grps.get(i).printGroup();
			System.out.println(title);
		}
		
		
		
	}	
		
}
