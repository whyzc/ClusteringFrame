package instance;

import java.util.*;

import feature.GenerateFeature;
import text.Text;

/**
 * �������������ı�����������ģ�ʹ����ı��ڡ�
 * @author yzc
 *
 */
public class GenerateFeature_2 implements GenerateFeature
{

	@Override
	public void generateFeature(Text t)
	{
		// TODO Auto-generated method stub
		Map<String, Double> fm = new TreeMap<String, Double>();			//�ı�����
		
		for(int i=0; i<t.getContent().length(); i++)					//�Ե�����Ϊ����������У�ֵ�д���-1
		{
			fm.put(t.getContent().substring(i, i+1) , -1.0);
		}
		
		t.setFeature(fm);												//�����ɵ��ı����������ı���
	}
}
