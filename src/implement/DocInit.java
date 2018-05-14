package implement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import text.Text;

/**
 * �ĵ���ʼ��
 *
 */
public class DocInit
{
	
	//�ļ��е�ַ
	private String filePos = null;
	private List<String> totalTitles;

	/**
	 * ���췽���������ļ��е�ַ
	 * @param pos
	 */
	public DocInit(String pos)
	{
		filePos = pos;
	}
	
	/**
	 * ��ȡ�ļ�
	 * @throws FileNotFoundException 
	 */
	public void readFile(Object obj) throws FileNotFoundException
	{
		totalTitles = new ArrayList<String>();
		traversingFiles(filePos, obj);
	}
	
	/**
	 * �ݹ����pos�ļ����������ļ�
	 * @param pos ���������ļ���
	 * @throws FileNotFoundException 
	 */
	private void traversingFiles(String pos, Object obj) throws FileNotFoundException
	{
		
		if (pos == null)
		{
			throw new RuntimeException("��ַΪ��");
		}
		File file = new File(pos);
		File[] files = file.listFiles();
		if (files == null) 
		{// ���Ŀ¼Ϊ�գ�ֱ���˳�  
			throw new RuntimeException("���ļ���");  
        }
		
		for (int i=0 ; i<files.length ; i++) 
		{  
			
            if (files[i].isFile()) 
            {  
            	processingFiles(files[i], obj);  
            } 
            else if (files[i].isDirectory()) 
            {   
            	traversingFiles(files[i].getAbsolutePath(), obj); 
            }  
        }
	}

	/**
	 * �����ı�
	 * @param f ��������ı�
	 * @throws FileNotFoundException 
	 */
	public void processingFiles(File f, Object obj) throws FileNotFoundException
	{
		// TODO Auto-generated method stub
		if (obj instanceof List)
		{
			List texts = (List) obj;
			totalTitles.add(f.getName());
			
			StringBuffer contents = new StringBuffer();
			String tmp;
			Reader read = new FileReader(f);
			BufferedReader bufr = new BufferedReader(read);
			try
			{
				while ((tmp=bufr.readLine()) != null)
				{
					tmp.trim();
					contents.append(tmp);
				}
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (contents.length() > 0)
			{
				Text t = new TextImpl();
				t.setContent(contents.toString());
				t.setNo(texts.size()-1);
				t.setTitle(f.getName());
				texts.add(t);
			}
		}
	}
	
	
	
	public List<String> getTotalTitles()
	{
		return totalTitles;
	}
}
