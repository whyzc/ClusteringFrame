package implement;

import java.util.*;

import model.EstablishModel;
import model.TextModel;
import text.Text;

public class EstablishModelForSpaceVector implements EstablishModel
{

	@Override
	public TextModel modeling(Text t)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TextModel> modeling(List<Text> ts)
	{
		List<String> totalSpace = new ArrayList<String>();
		List<TextModel> result = new ArrayList<TextModel>();
		
		for(int i=0 ; i<ts.size() ; i++)			//����һ���ܿռ�
		{
			
			Iterator it = ts.get(i).getFeature().keySet().iterator();
			while (it.hasNext())
			{
				String key = (String)it.next();
				if (!totalSpace.contains(key))
					totalSpace.add(key);
			}
			totalSpace.sort(null);
		}
		
		for(int i=0 ; i<ts.size() ; i++)			//�����ռ�����ģ��
		{
			TextModelForSpaceVector tm = new TextModelForSpaceVector();
			double[] v = new double[totalSpace.size()];
			
			for (int j=0 ; j<totalSpace.size() ; j++)
			{
				if (ts.get(i).getFeature().containsKey(totalSpace.get(j)))
				{
					v[j]=1;
				}
				else
					v[j]=0;
			}
			
			tm.setTextModelForArray(v);
			if (tm.getTextModelForArray().length != totalSpace.size())
			{
				throw new RuntimeException("��ģ����");
			}
			
			tm.setNo(ts.get(i).getNo());			//�����ı����
			tm.setTitle(ts.get(i).getTitle());		//�����ı�����
			result.add(tm);
		}
		return result;
	}

}
