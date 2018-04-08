package user;

import java.util.List;

import clustering.Group;
import text.Text;

/**
 * �����û���HAC�㷨�ӿ�
 * @author yzc
 *
 */
public interface UserHAC<T>
{
	/**
	 * HAC�㷨
	 * @param pos �ļ�λ��
	 * @return һ����δ�
	 */
	public Group<T> hac(String pos);
	
	/**
	 * HAC�㷨
	 * @param ts һ���ı�
	 * @return һ����δ�
	 */
	public Group<T> hac(List<Text> ts);
	
	/**
	 * ����δطֽ��k����
	 * @param grp ���ֽ�Ĳ�δ�
	 * @param k ��ֽ�ĸ���
	 * @return k����
	 */
	public List<Group<T>> split(Group<T> grp, int k);
	
	
	/**
	 * ����δذ���ʶ�ȷֳ�һ���
	 * @param grp ���ֽ�Ĳ�δ�
	 * @param sim ��ʶ��
	 * @return һ���
	 */
	public List<Group<T>> split(Group<T> grp, double sim);
}
