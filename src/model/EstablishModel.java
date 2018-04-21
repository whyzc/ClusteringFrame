package model;

import java.util.List;

import text.Text;

/**
 * 建立文本模型
 * @author yzc
 * 
 */
public interface  EstablishModel
{
	/**
	 * 将单个文本生成文本模型
	 * @param t 进行模型生成的文本
	 * @return 返回生成的模型
	 */
	public TextModel modeling(Text t);
	
	/**
	 * 将多个文本生成文本模型
	 * @param ts 进行模型生成的文本
	 * @return 返回生成的模型
	 */
	public List<TextModel> modeling(List<Text> ts);
}
