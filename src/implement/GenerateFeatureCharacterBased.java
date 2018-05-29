package implement;

import java.util.*;

import feature.GenerateFeature;
import text.Text;

/**
 * 基于字生成文本特征，并将模型存入文本内。
 * @author yzc
 *
 */
public class GenerateFeatureCharacterBased implements GenerateFeature
{

	@Override
	public void generateFeature(Text t)
	{
		// TODO Auto-generated method stub
		Map<String, Double> fm = new TreeMap<String, Double>();			//文本特征
		
		for(int i=0; i<t.getContent().length(); i++)					//以单个字为特征存入键中，值中存入-1
		{
			char c = t.getContent().charAt(i);
			if (isChinese(c))
				fm.put(String.valueOf(c), -1.0);
		}
		
		t.setFeature(fm);												//将生成的文本特征存入文本中
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
