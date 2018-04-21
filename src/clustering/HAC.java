package clustering;

import java.util.List;

import clusteringLayer.Group;
import text.Text;

/**
 * �����û���HAC�㷨�ӿ�
 * @author yzc
 *
 */
public interface HAC
{
	/**
	 * HAC�㷨
	 * @param pos �ļ�λ��
	 * @return һ����δ�
	 */
	public Group hac(String pos);
	
	/**
	 * HAC�㷨
	 * @param ts һ���ı�
	 * @return һ����δ�
	 */
	public Group hac(List<Text> ts);
	
	/**
	 * ����δطֽ��k����
	 * @param grp ���ֽ�Ĳ�δ�
	 * @param k ��ֽ�ĸ���
	 * @return k����
	 */
	public List<Group> split(Group grp, int k);
	
	
	/**
	 * ����δذ���ʶ�ȷֳ�һ���
	 * @param grp ���ֽ�Ĳ�δ�
	 * @param sim ��ʶ��
	 * @return һ���
	 */
	public List<Group> split(Group grp, double sim);
}
