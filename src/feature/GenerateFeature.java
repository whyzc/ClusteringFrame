package feature;

import text.Text;

/**
 * 提取文本特征，并将特征写入文本内部
 * @author yzc
 *
 */
public interface GenerateFeature
{
	/**
	 * 提取文本特征，并将特征写入文本内部
	 * @param t 待提取特征的文本
	 */
	public void generateFeature(Text t);
}
