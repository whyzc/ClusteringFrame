package implement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.EstablishModel;
import model.TextModel;
import text.Text;

/**
 * �����ֽ����ı�ģ��
 * @author yzc
 *
 */
public class EstablishModelWordBased implements EstablishModel
{

	@Override
	public List<TextModel> modeling(List<Text> ts)
	{
		// TODO Auto-generated method stub
		List<TextModel> result = new ArrayList<TextModel>();		//��¼�������ɵ��ı�ģ��
		
		for(int i=0 ; i<ts.size() ; i++)			//��ts�����е��ı����ν����ı�ģ�ͣ������뵽result��
		{
			TextModelWordBased tm = new TextModelWordBased();
			
			Iterator it = ts.get(i).getFeature().keySet().iterator();
			
			while (it.hasNext())
			{
				String key = (String)it.next();
				tm.getTextModel().add(key);
			}
			
			tm.setNo(ts.get(i).getNo());			//�����ı����
			tm.setTitle(ts.get(i).getTitle());		//�����ı�����
			
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
