package implement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import similarity.TextModel;
import similarity.EstablishModel;
import text.Text;

/**
 * �����ֽ����ı�ģ��
 * @author yzc
 *
 */
public class EstablishModelWordBased implements EstablishModel<String>
{

	@Override
	public List<TextModel<String>> modeling(List<Text> ts)
	{
		// TODO Auto-generated method stub
		List<TextModel<String>> result = new ArrayList<TextModel<String>>();		//��¼�������ɵ��ı�ģ��
		
		for(int i=0 ; i<ts.size() ; i++)			//��ts�����е��ı����ν����ı�ģ�ͣ������뵽result��
		{
			TextModelWordBased tm = new TextModelWordBased();
			
			Iterator<String> it = ts.get(i).getFeature().keySet().iterator();
			
			while (it.hasNext())
			{
				String key = it.next();
				tm.getTextModel().add(key);
			}
			
			tm.setNo(ts.get(i).getNo());			//�����ı����
			tm.setTitle(ts.get(i).getTitle());		//�����ı�����
			
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
