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
	
	private String filePos = null;					//文件夹地址
	private List<List<String>> sampleSet;			//样本结果集，用于评价
	private List<String> matchKeywords;				//用于匹配的关键字，用于构建sampleSet
	private int textNum = 0;						//文件总数
	private String delimiter;						//分隔符
	private boolean needToEvaluate = false;			//评价语料，需要做评价		

	/**
	 * 构造方法，传入文件夹地址，非评价语料，不做评价
	 * @param pos 文档所在地址
	 */
	public DocInit(String pos)
	{
		filePos = pos;
		needToEvaluate = false;
	}
	
	/**
	 * 构造方法，传入文件夹地址，评价语料
	 * @param pos 文档所在地址
	 * @param delimiter 语料文件名中区分同一类的分隔符，为null时同一文件夹下为同一类
	 */
	public DocInit(String pos, String delimiter)
	{
		filePos = pos;
		needToEvaluate = true;
		this.delimiter = delimiter;
	}
	
	public String getFilePos()
	{
		return filePos;
	}

	public void setFilePos(String filePos)
	{
		this.filePos = filePos;
	}

	public List<List<String>> getSampleSet()
	{
		return sampleSet;
	}

	public List<String> getMatchKeywords()
	{
		return matchKeywords;
	}

	public boolean isNeedToEvaluate()
	{
		return needToEvaluate;
	}

	
	
	/**
	 * 读取文件
	 */
	public void readFile(List<Text> texts) 
	{
		if (needToEvaluate)
			sampleSet = new ArrayList<List<String>>();
		travaringFilesForLevelTraversal(filePos, texts);
		if (needToEvaluate && delimiter != null)
			dealWithSampleSet(delimiter);		
	}
	
	/**
	 * 递归遍历pos文件夹下所有文件
	 * @param pos 待遍历的文件夹
	 * @param obj 存储处理结果的容器，该参数赋给处理文件的方法processingFiles(File f, Object obj)
	 */
	private void traversingFilesForRecursiveTraversal(String pos, List<Text> texts) 
	{
		textNum = 0;
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
            	processingFiles(files[i], texts);
            	textNum++;
            } 
            else if (files[i].isDirectory()) 
            {   
            	traversingFilesForRecursiveTraversal(files[i].getAbsolutePath(), texts); 
            }  
        }
	}
	
	/**
	 * 层次遍历pos文件夹下所有文件
	 * @param pos 待遍历的文件夹
	 * @param texts 存储处理结果的容器。
	 */
	private void travaringFilesForLevelTraversal(String pos, List<Text> texts)
	{
		textNum = 0;
		List<File> files = new ArrayList<File>();
		File tmpFile = new File(pos);
		if (pos == null)
		{
			throw new RuntimeException("地址为空");
		}
		if (!tmpFile.exists())
		{
			throw new RuntimeException("文件地址不存在");
		}
		
		files.add(tmpFile);
		while (!files.isEmpty())
		{
			File[] subFiles;
			tmpFile = files.remove(0);
			if (tmpFile.isFile())
			{
				processingFiles(tmpFile, texts);		//处理文件
				textNum++;
			}
			else if (tmpFile.isDirectory())
			{
				subFiles = tmpFile.listFiles();
				for (int i=0 ; i<subFiles.length ; i++)
				{
					files.add(subFiles[i]);
				}
				if (needToEvaluate)		//评价预料，生成评价分类结果
					this.extractFileInformationInDirectory(tmpFile, sampleSet);
			}
		}
		
	}

	
	/**
	 * 处理文档
	 * @param f 待处理的文档
	 * @param texts 存储生成的文本层文本
	 */
	private void processingFiles(File f, List<Text> texts)
	{
		
		StringBuffer contents = new StringBuffer();
		String tmp;
		Reader read;
		BufferedReader bufr;
		try
		{
			read = new FileReader(f);
			bufr = new BufferedReader(read);
		
			while ((tmp=bufr.readLine()) != null)
			{
				tmp.trim();
				contents.append(tmp);
			}
			
		} catch (IOException e)
		{
			e.printStackTrace();
		} 

		if (contents.length() > 0)
		{
			Text t = new Text();
			t.setContent(contents.toString());
			t.setTitle(f.getName());
			texts.add(t);
		}
		
	}
	
	/**
	 * 提取文件夹dir内文件的信息，可建立已分好类的样本的分类结果（collection传入sampleSet，构建sampleSet），用于聚类的评价
	 * @param dir 待处理的文件夹文件
	 * @param collection 存储提取出的信息
	 */
	private void extractFileInformationInDirectory(File dir, List<List<String>> collection)
	{
		if (!dir.isDirectory())
		{
			throw new RuntimeException("文件类型不是文件夹");
		}
		
		List<String> sameClass = new ArrayList<String>();
		File[] allFile = dir.listFiles();
		for (int i=0 ; i<allFile.length ; i++)
		{
			if (allFile[i].isFile())
				sameClass.add(allFile[i].getName());
		}
		if (!sameClass.isEmpty())
			collection.add(sameClass);
		
	}
	
	
	
	/**
	 * 根据所有样本文本的标题生成样本结果集。
	 * @param delimiter 分隔符
	 */
	public void dealWithSampleSet(String delimiter)
	{
		if (delimiter == null)
		{
			throw new RuntimeException("无分隔符");
		}
		
		List<List<String>> newSampleSet = new ArrayList<List<String>>();
		matchKeywords = new ArrayList<String>();
		int site;
		String keyword;
		
		for (int i=0 ; i<sampleSet.size() ; i++)
		{
			for (int j=0 ; j<sampleSet.get(i).size() ; j++)
			{
				keyword = extractKeyword(sampleSet.get(i).get(j), delimiter);
				
				if (keyword == null)
					continue;
				
				if (matchKeywords.contains(keyword))
				{
					site = matchKeywords.indexOf(keyword);
					newSampleSet.get(site).add(sampleSet.get(i).get(j));
				}
				else
				{
					matchKeywords.add(keyword);
					newSampleSet.add(new ArrayList<String>());
					newSampleSet.get(newSampleSet.size()-1).add(sampleSet.get(i).get(j));
				}
			}	
		}
		sampleSet = newSampleSet;
	}
	
//	/**
//	 * 设置样本结果集；该方法与generateSampleSet(totalTitles)需执行一个作为评价的初始化。
//	 * @param sampleSet 被设置的样本结果集
//	 */
//	public void setSampleSet(List<List<String>> sampleSet)
//	{
//		this.sampleSet = sampleSet;
//		textNum = 0;
//		for (int i=0 ; i<sampleSet.size() ; i++)
//		{
//			textNum += sampleSet.get(i).size();
//		}
//		
//	}
	
	/**
	 * 提取用于匹配各类的关键字
	 * @param str 文件名
	 * @param delimiter 分隔符
	 * @return
	 */
	private String extractKeyword(String str, String delimiter)		//提取用于匹配的关键字
	{
		String[] keywords = str.split(delimiter);
		if (keywords.length == 0)
		{
			return null;
		}
		return keywords[0];
	}
	
	public int getTextNum()
	{
		return textNum;
	}
}
