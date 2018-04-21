package clusteringLayer;

import java.util.List;

import model.TextModel;

/**
 * ����ڲ���HAC�㷨��
 * @author yzc
 *
 * @param  �ı�ģ�Ͳ��õ���������
 */
public interface HACAlgo
{
	/**
	 * ��һ���ı�ģ�Ͱ���ξ���ϲ���һ����
	 * @param tms ��ϲ���һ��ģ��
	 * @return �ϲ���Ĳ�δ�
	 */
	public Group merge(List<TextModel> tms);
	
	
	
}
