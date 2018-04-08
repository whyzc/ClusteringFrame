package test2;

import java.util.*;
import clustering.Group;
import text.Text;
import user.UserHAC;

public class HAC_main
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		/**********设置文本内容***************/
		Text t1 = new Text_2("我今天很开心", "1", 1);
		Text t2 = new Text_2("今天天气很好", "2", 2);
		Text t3 = new Text_2("月亮真的好漂亮", "3", 3);
		
		List<Text> ts = new ArrayList<Text>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		
		/*************调用KMeans**************/
		UserHAC<String> userhac = new UserHAC_2();
		Group<String> g = userhac.hac(ts);
		
		
	}	
		
}
