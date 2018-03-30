package test2;

import java.util.*;

import clustering.Group;
import clustering.KMeans;
import feature.GenerateFeature;
import similarity.EstablishModel;
import similarity.TextModel;
import text.Text;

public class Test2
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Text t1 = new Text_2("我今天很开心", "1", 1);
		Text t2 = new Text_2("今天天气很好", "2", 2);
		Text t3 = new Text_2("月亮真的好漂亮", "3", 3);
		
		GenerateFeature gf = new GenerateFeature_2();
		gf.generateFeature(t1);
		gf.generateFeature(t2);
		gf.generateFeature(t3);
		
		List<Text> ts = new ArrayList<Text>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		
		
		EstablishModel em = new EstablishModel_2();
		List<TextModel> tms = em.modeling(ts);
		
		KMeans km = new KMeans_2();
	
		List<Group> g = km.kMeans(tms, 2);
		
		
		
		Iterator it  = g.get(0).getMember().iterator();
		while(it.hasNext())
		{
			TextModel_2 t = (TextModel_2)it.next();
			sop(t.no);
		}
		
		it  = g.get(1).getMember().iterator();
		while(it.hasNext())
		{
			TextModel_2 t = (TextModel_2)it.next();
			sop(t.no);
		}
	}
	
	public static void sop(Object obj)
	{
		System.out.println(obj);
	}

	
}
