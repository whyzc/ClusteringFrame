package implement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import text.Text;

/**
 * 文档初始化
 *
 */
public class DocInit
{
	
	//文件夹地址
	private String filePos = null;
	private List<String> totalTitles;

	/**
	 * 构造方法，传入文件夹地址
	 * @param pos
	 */
	public DocInit(String pos)
	{
		filePos = pos;
	}
	
	/**
	 * 读取文件
	 * @throws FileNotFoundException 
	 */
	public void readFile(Object obj) throws FileNotFoundException
	{
		totalTitles = new ArrayList<String>();
		traversingFiles(filePos, obj);
	}
	
	/**
	 * 递归遍历pos文件夹下所有文件
	 * @param pos 待遍历的文件夹
	 * @throws FileNotFoundException 
	 */
	private void traversingFiles(String pos, Object obj) throws FileNotFoundException
	{
		
		if (pos == null)
		{
			throw new RuntimeException("地址为空");
		}
		File file = new File(pos);
		File[] files = file.listFiles();
		if (files == null) 
		{// 如果目录为空，直接退出  
			throw new RuntimeException("空文件夹");  
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
	 * 处理文本
	 * @param f 被处理的文本
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
