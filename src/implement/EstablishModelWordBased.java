package implement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import similarity.TextModel;
import similarity.EstablishModel;
import text.Text;

/**
 * 基于字建立文本模型
 * @author yzc
 *
 */
public class EstablishModelWordBased implements EstablishModel<String>
{

	@Override
	public List<TextModel<String>> modeling(List<Text> ts)
	{
		// TODO Auto-generated method stub
		List<TextModel<String>> result = new ArrayList<TextModel<String>>();		//记录所有生成的文本模型
		
		for(int i=0 ; i<ts.size() ; i++)			//对ts中所有的文本依次建立文本模型，并加入到result中
		{
			TextModelWordBased tm = new TextModelWordBased();
			
			Iterator<String> it = ts.get(i).getFeature().keySet().iterator();
			
			while (it.hasNext())
			{
				String key = it.next();
				tm.getTextModel().add(key);
			}
			
			tm.setNo(ts.get(i).getNo());			//设置文本编号
			tm.setTitle(ts.get(i).getTitle());		//设置文本标题
			
			result.add(tm);
		}
		
		return result;
	}

	@Override
	public TextModel<String> modeling(Text t)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
