package implement;



import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.lc.nlp4han.segment.WordSegFactory;
import com.lc.nlp4han.segment.WordSegmenter;
import com.lc.nlp4han.util.CharTypeUtil;

import feature.GenerateFeature;
import text.Text;

public class GenerateFeatureWordsBased implements GenerateFeature
{

	@Override
	public void generateFeature(Text t)
	{
		Map<String, Double> fm = new TreeMap<String, Double>();
		WordSegmenter segmenter;
		String[] words;
		try
		{
			segmenter = WordSegFactory.getWordSegmenter();
			words = segmenter.segment(t.getContent());
			for (int i=0 ; i<words.length ; i++)
			{
				if (featureFilter(words[i]))
					continue;
				if (fm.containsKey(words[i]))
				{
					double v = fm.get(words[i]);
					v++;
					fm.put(words[i], v);
				}
				else
				{
					fm.put(words[i], 1.0);
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		t.setFeature(fm);
	}
	
	private boolean featureFilter(String str)
	{
		if (CharTypeUtil.isChinesePunctuation(str))
			return true;
		for (int i=0 ; i<str.length() ; i++)
		{
			if (isChinese(str.charAt(i)))
				return false;
			if (CharTypeUtil.isLetter(str.charAt(i)))
				return false;
		}
		return true;
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
