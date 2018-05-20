package text;

import java.util.*;

public class Text
{
	private String content = null;								//��������
	private String title = null;								//������Ŀ
	private static int NEXTID = 0;								//�����ۼ��ĵ����
	private int no;												//�������
	private Map<String, Double> feature = null;					//���µ�����

	public Text()
	{
		this.no= NEXTID;
		NEXTID++;	
	}
	
	public Text(String content)
	{
		this.content = content;
	}
	
	public Text(String content, String title)
	{
		this.content = content;
		this.title = title;
	}
	
	public String getContent()
	{
		return content;
	}



	public void setContent(String content)
	{
		this.content = content;
	}



	public String getTitle()
	{
		return title;
	}



	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getNo()
	{
		return no;
	}

	public Map<String, Double> getFeature()
	{
		return feature;
	}

	public void setFeature(Map<String, Double> feature)
	{
		this.feature = feature;
	}


	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Text other = (Text) obj;
		if (title == null)
		{
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (content == null)
		{
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}
	
}
