package clustering;

import java.util.List;

import similarity.TextModel;

public interface HAC<T>
{
	/**
	 * ��һ���ı�ģ�Ͱ���ξ���ϲ���һ����
	 * @param tms ��ϲ���һ��ģ��
	 * @return �ϲ���Ĳ�δ�
	 */
	public Group<T> merge(List<TextModel<T>> tms);
	
	
	
}
