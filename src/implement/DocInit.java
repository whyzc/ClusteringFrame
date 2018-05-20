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
	
	private String filePos = null;					//�ļ��е�ַ
	private List<List<String>> sampleSet;			//�������������������
	private List<String> matchKeywords;				//����ƥ��Ĺؼ��֣����ڹ���sampleSet
	private int textNum = 0;						//�ļ�����
	private String delimiter;						//�ָ���
	private boolean needToEvaluate = false;			//�������ϣ���Ҫ������		

	/**
	 * ���췽���������ļ��е�ַ�����������ϣ���������
	 * @param pos �ĵ����ڵ�ַ
	 */
	public DocInit(String pos)
	{
		filePos = pos;
		needToEvaluate = false;
	}
	
	/**
	 * ���췽���������ļ��е�ַ����������
	 * @param pos �ĵ����ڵ�ַ
	 * @param delimiter �����ļ���������ͬһ��ķָ�����Ϊnullʱͬһ�ļ�����Ϊͬһ��
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
	 * ��ȡ�ļ�
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
	 * �ݹ����pos�ļ����������ļ�
	 * @param pos ���������ļ���
	 * @param obj �洢���������������ò������������ļ��ķ���processingFiles(File f, Object obj)
	 */
	private void traversingFilesForRecursiveTraversal(String pos, List<Text> texts) 
	{
		textNum = 0;
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
	 * ��α���pos�ļ����������ļ�
	 * @param pos ���������ļ���
	 * @param texts �洢��������������
	 */
	private void travaringFilesForLevelTraversal(String pos, List<Text> texts)
	{
		textNum = 0;
		List<File> files = new ArrayList<File>();
		File tmpFile = new File(pos);
		if (pos == null)
		{
			throw new RuntimeException("��ַΪ��");
		}
		if (!tmpFile.exists())
		{
			throw new RuntimeException("�ļ���ַ������");
		}
		
		files.add(tmpFile);
		while (!files.isEmpty())
		{
			File[] subFiles;
			tmpFile = files.remove(0);
			if (tmpFile.isFile())
			{
				processingFiles(tmpFile, texts);		//�����ļ�
				textNum++;
			}
			else if (tmpFile.isDirectory())
			{
				subFiles = tmpFile.listFiles();
				for (int i=0 ; i<subFiles.length ; i++)
				{
					files.add(subFiles[i]);
				}
				if (needToEvaluate)		//����Ԥ�ϣ��������۷�����
					this.extractFileInformationInDirectory(tmpFile, sampleSet);
			}
		}
		
	}

	
	/**
	 * �����ĵ�
	 * @param f ��������ĵ�
	 * @param texts �洢���ɵ��ı����ı�
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
	 * ��ȡ�ļ���dir���ļ�����Ϣ���ɽ����ѷֺ���������ķ�������collection����sampleSet������sampleSet�������ھ��������
	 * @param dir ��������ļ����ļ�
	 * @param collection �洢��ȡ������Ϣ
	 */
	private void extractFileInformationInDirectory(File dir, List<List<String>> collection)
	{
		if (!dir.isDirectory())
		{
			throw new RuntimeException("�ļ����Ͳ����ļ���");
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
	 * �������������ı��ı������������������
	 * @param delimiter �ָ���
	 */
	public void dealWithSampleSet(String delimiter)
	{
		if (delimiter == null)
		{
			throw new RuntimeException("�޷ָ���");
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
//	 * ����������������÷�����generateSampleSet(totalTitles)��ִ��һ����Ϊ���۵ĳ�ʼ����
//	 * @param sampleSet �����õ����������
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
	 * ��ȡ����ƥ�����Ĺؼ���
	 * @param str �ļ���
	 * @param delimiter �ָ���
	 * @return
	 */
	private String extractKeyword(String str, String delimiter)		//��ȡ����ƥ��Ĺؼ���
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
