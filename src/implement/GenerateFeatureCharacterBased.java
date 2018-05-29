package implement;

import java.util.*;

import feature.GenerateFeature;
import text.Text;

/**
 * �����������ı�����������ģ�ʹ����ı��ڡ�
 * @author yzc
 *
 */
public class GenerateFeatureCharacterBased implements GenerateFeature
{

	@Override
	public void generateFeature(Text t)
	{
		// TODO Auto-generated method stub
		Map<String, Double> fm = new TreeMap<String, Double>();			//�ı�����
		
		for(int i=0; i<t.getContent().length(); i++)					//�Ե�����Ϊ����������У�ֵ�д���-1
		{
			char c = t.getContent().charAt(i);
			if (isChinese(c))
				fm.put(String.valueOf(c), -1.0);
		}
		
		t.setFeature(fm);												//�����ɵ��ı����������ı���
	}
	
	
	public boolean isChinese(String strName) {
	    char[] ch = strName.toCharArray();
	    for (int i = 0; i < ch.length; i++) {
	        char c = ch[i];
	        if (isChinese(c)) {
	            return true;
	        }
	    }
	    return false;
	}
	 
	private boolean isChinese(char c) {
	    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C//jdk1.7  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D//jdk1.7  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT) {  
            return true;  
        }  
	    return false;
	}
}
