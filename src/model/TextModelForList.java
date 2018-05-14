package model;

import java.util.*;

public abstract class TextModelForList extends TextModel
{
	private List<String> textModelForList;						//文本模型

	public List<String> getTextModelForList()
	{
		return textModelForList;
	}

	public void setTextModelForList(List<String> textModelForList)
	{
		this.textModelForList = textModelForList;
	}

}
