package test2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import clustering.Group;
import clustering.KMeans;
import feature.GenerateFeature;
import similarity.EstablishModel;
import similarity.TextModel;
import text.Text;

public class Test_2
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Text t1 = new Text_2("我今天很开心", "标题1", 1);
		Text t2 = new Text_2("今天天气很好", "标题2", 2);
		Text t3 = new Text_2("月亮真的好漂亮", "标题3", 3);
		
		List<Text> ts = new ArrayList<Text>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		
		List<Group> g = new UserKMeans_2().kMeans(ts , 2);
		
		
		
		
	
//		Group g = new UserHAC_2().hac(ts);
		
//		System.out.println();
		Iterator it  = g.get(0).getMember().iterator();
		while(it.hasNext())
		{
			TextModel_2 t = (TextModel_2)it.next();
			sop (t.no);
		}
		
//		it  = g.get(1).getMember().iterator();
//		while(it.hasNext())
//		{
//			TextModel_2 t = (TextModel_2)it.next();
//			sop(t.no);
//		}
	}	
	
	public static void sop(Object obj)
	{
		System.out.println(obj);
	}
		
}
