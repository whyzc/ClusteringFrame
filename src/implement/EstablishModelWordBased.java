package implement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.EstablishModel;
import model.TextModel;
import text.Text;

/**
 * 基于字建立文本模型
 * @author yzc
 *
 */
public class EstablishModelWordBased implements EstablishModel
{

	@Override
	public List<TextModel> modeling(List<Text> ts)
	{
		// TODO Auto-generated method stub
		List<TextModel> result = new ArrayList<TextModel>();		//记录所有生成的文本模型
		
		for(int i=0 ; i<ts.size() ; i++)			//对ts中所有的文本依次建立文本模型，并加入到result中
		{
			TextModelWordBased tm = new TextModelWordBased();
			
			Iterator it = ts.get(i).getFeature().keySet().iterator();
			
			while (it.hasNext())
			{
				String key = (String)it.next();
				tm.getTextModel().add(key);
			}
			
			tm.setNo(ts.get(i).getNo());			//设置文本编号
			tm.setTitle(ts.get(i).getTitle());		//设置文本标题
			
			result.add(tm);
		}
		
		return result;
	}

	@Override
	public TextModel modeling(Text t)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
