package clusteringLayer;

import java.util.*;

import model.TextModel;

public abstract class Group
{
	protected List<TextModel> member = new ArrayList<TextModel>();			//��Ա������KMeans
	protected List<Group> subGroup = new ArrayList<Group>();				//����Ӵ�
	protected List<Double> simValue = new ArrayList<Double>();				//������ƶ�
	protected TextModel clusterPoint = null; 								//�۵㣬����KMeans
	protected TextModel initialMember;										//��ʼ��Ա�����ڲ�ξ���
	
	/**
	 * ����member�еĳ�Ա���¾۵�
	 * @return ���۵��޸ķ���true����֮����false
	 */
	public abstract boolean updataClusterPoint();			//���¾۵㣬����KMeans�㷨
	
	
	/**
	 * ���㱾�����g�����ʶ�ȣ����ڲ�ξ���
	 * @param g	�뱾�رȽϵĴ�
	 * @return	���ص���ʶ��
	 */
	public abstract double sim(Group g);					//��ؼ����ʶ�ȣ����ڲ�ξ���
	
	
	/**
	 * ��ȡ�۵�
	 * @return ���ؾ۵�
	 */
	public TextModel getClusterPoint()
	{
		return this.clusterPoint;
	}
	
	/**
	 * ���þ۵㣬����ֻ���ڴصĳ�ʼ��������KMeans�㷨
	 * @param clusterPoint ��Ҫ���óɵľ۵�
	 */
	public void setClusterPoint(TextModel clusterPoint)		//���þ۵㣬����ֻ���ڴصĳ�ʼ��������KMeans�㷨
	{
		this.clusterPoint = clusterPoint;
	}
	
	
	/**
	 * �������ı�ģ�ͼ��뵽������
	 * @param member �������ı�ģ��
	 */
	public void addMember(TextModel member)						//���ӳ�Ա
	{
		this.member.add(member);
	}
	
	/**
	 * ��һ���ı�ģ�ͼ��뵽������
	 * @param members �����һ���ı�ģ��
	 */
	public void addMember(List<TextModel> members)				//���ӳ�Ա
	{
		for (int i=0 ; i<members.size() ; i++)
		{
			this.member.add(members.get(i));
		}
	}
	
	/**
	 * ��������е����г�Ա
	 */
	public void clearMember()								//�����Ա
	{
		this.member.clear();
	}
	
	/**
	 * ��ȡ�����г�Ա������
	 * @return ��Ա������
	 */
	public int memberSize()									//��Ա����
	{
		return member.size();
	}
	
	/**
	 * ��һ�������Ӧ����ʶ��ͬʱѹ���Ӵ�ջ����ʶ��ջ
	 * @param grp ��ѹ��ջ�Ĵ�
	 * @param s ��ѹ��ջ����ʶ��
	 */
	public void pop(Group grp , Double s)					//ѹ��ص�ջ�Ͷ�Ӧ����ʶ�ȵ�ջ
	{
		subGroup.add(grp);
		simValue.add(s);
	}
	
	/**
	 * �����Ӵ�ջβ�Ĵ�
	 * @return �����Ĵ�
	 */
	public Group pushGroup()								//����ջβ�Ĵ�
	{
		if (subGroup.size() < 1)
			return null;
		Group g = subGroup.get(subGroup.size()-1);
		subGroup.remove(subGroup.size()-1);
		return g;
	}
	
	/**
	 * ������ʶ��ջβ����ʶ��
	 * @return ��������ʶ��
	 */
	public Double pushSim()									//����ջβ����ʶ��
	{
		if (simValue.size() < 1)
			return null;
		Double s = simValue.get(simValue.size()-1);
		simValue.remove(simValue.size()-1);
		return s;
	}
	
	/**
	 * ��ȡ�Ӵ�ջ�дص�����
	 * @return �Ӵ�ջ�дص�����
	 */
	public int subGroupSize()							//�Ӵصĸ���
	{
		return subGroup.size();
	}
	
	/**
	 * ��ȡ��ʶ��ջ����ʶ�ȵ�����
	 * @return ��ʶ��ջ����ʶ�ȵ�����
	 */
	public int simValueSize()							//�Ӵض�Ӧ��ʶ�ȵĸ���
	{
		return simValue.size();
	}
	
	/**
	 * �Ӵ�ջ�Ƿ�Ϊ��
	 * @return ջ�շ���true����֮����false
	 */
	public boolean isSubGroupEmpty()					//�Ӵ��Ƿ�Ϊ��
	{
		return subGroup.isEmpty();
	}
	
	/**
	 * ��ȡ�Ӵ�
	 * @return	�����Ӵ�
	 */
	public List<Group> getSubGroup()					//��ȡ�Ӵ�
	{
		return subGroup;
	}
	
	/**
	 * ��ȡ�������г�Ա
	 * @return �������г�Ա
	 */
	public List<TextModel> getMember()					//��ȡ��Ա
	{
		return member;
	}
	
	/**
	 * ���ñ��س�Ա
	 * @param members �����õĳ�Ա
	 */
	public void setMember(List<TextModel> members)
	{
		this.member = members;
	}
	
	/**
	 * ��ȡ��ʶ��ջ
	 * @return ��ʶ��ջ
	 */
	public List<Double> getSimValue()
	{
		return simValue;
	}
	
	/**
	 * ������ʶ��
	 * @param simValue �����õ���ʶ��
	 */
	public void setSimValue(List<Double> simValue)
	{
		this.simValue = simValue;
	}
	
	/**
	 * �����Ӵ�
	 * @param subGroup �����õ��Ӵ�
	 */
	public void setSubGroup(List<Group> subGroup)
	{
		this.subGroup = subGroup;
	}

	/**
	 * ��ȡ��ʼ��Ա
	 * @return ��ʼ��Ա
	 */
	public TextModel getInitialMember()
	{
		return initialMember;
	}

	/**
	 * ���ó�ʼ��Ա
	 * @param initialMember �����õĳ�ʼ��Ա
	 */
	public void setInitialMember(TextModel initialMember)
	{
		this.initialMember = initialMember;
	}
}
