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
		
		/**********�����ı�����***************/
		Text t1 = new Text_2("�ҽ���ܿ���", "1", 1);
		Text t2 = new Text_2("���������ܺ�", "2", 2);
		Text t3 = new Text_2("������ĺ�Ư��", "3", 3);
		
		List<Text> ts = new ArrayList<Text>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		
		/*************����KMeans**************/
		UserHAC<String> userhac = new UserHAC_2();
		Group<String> g = userhac.hac(ts);
		
		
	}	
		
}
