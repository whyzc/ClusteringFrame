package clusteringLayer;

import java.util.*;

import model.TextModel;

public abstract class Group
{
	protected List<TextModel> member = new ArrayList<TextModel>();			//成员，用于KMeans
	protected List<Group> subGroup = new ArrayList<Group>();				//层次子簇
	protected List<Double> simValue = new ArrayList<Double>();				//层次相似度
	protected TextModel clusterPoint = null; 								//聚点，用于KMeans
	protected TextModel initialMember;										//初始成员，用于层次聚类
	
	/**
	 * 根据member中的成员更新聚点,用于KMeans算法
	 * @return 若聚点修改返回true，反之返回false
	 */
	public abstract boolean updataClusterPoint();			//更新聚点，用于KMeans算法
	
	
	/**
	 * 计算本簇与簇g间的相识度，用于层次聚类
	 * @param g	与本簇比较的簇
	 * @return	两簇的相识度
	 */
	public abstract double sim(Group g);					//求簇间的相识度，用于层次聚类
	
	
	/**
	 * 获取聚点
	 * @return 返回聚点
	 */
	public TextModel getClusterPoint()
	{
		return this.clusterPoint;
	}
	
	/**
	 * 设置聚点，往往只用于簇的初始化，用于KMeans算法
	 * @param clusterPoint 须要设置成的聚点
	 */
	public void setClusterPoint(TextModel clusterPoint)		//设置聚点，往往只用于簇的初始化，用于KMeans算法
	{
		this.clusterPoint = clusterPoint;
	}
	
	
	/**
	 * 将单个文本模型加入到本簇中，用于KMeans算法
	 * @param member 需加入的文本模型
	 */
	public void addMember(TextModel member)						//增加成员
	{
		this.member.add(member);
	}
	
	/**
	 * 将一组文本模型加入到本簇中，用于KMeans算法
	 * @param members 需入的一组文本模型
	 */
	public void addMember(List<TextModel> members)				//增加成员
	{
		for (int i=0 ; i<members.size() ; i++)
		{
			this.member.add(members.get(i));
		}
	}
	
	/**
	 * 清除本簇中的所有成员，用于KMeans算法
	 */
	public void clearMember()								//清除成员
	{
		this.member.clear();
	}
	
	/**
	 * 获取本簇中成员的数量，用于KMeans算法
	 * @return 成员的数量
	 */
	public int memberSize()									//成员数量
	{
		return member.size();
	}
	
	/**
	 * 将一个簇与对应的相识度同时压入子簇栈和相识度栈，用于HAC算法
	 * @param grp 需压入栈的簇
	 * @param s 需压入栈的相识度
	 */
	public void pop(Group grp , Double s)					//压入簇的栈和对应的相识度的栈
	{
		subGroup.add(grp);
		simValue.add(s);
	}
	
	/**
	 * 弹出子簇栈尾的簇，用于HAC算法
	 * @return 弹出的簇
	 */
	public Group pushGroup()								//弹出栈尾的簇
	{
		if (subGroup.size() < 1)
			return null;
		Group g = subGroup.get(subGroup.size()-1);
		subGroup.remove(subGroup.size()-1);
		return g;
	}
	
	/**
	 * 弹出相识度栈尾的相识度，用于HAC算法
	 * @return 弹出的相识度
	 */
	public Double pushSim()									//弹出栈尾的相识度
	{
		if (simValue.size() < 1)
			return null;
		Double s = simValue.get(simValue.size()-1);
		simValue.remove(simValue.size()-1);
		return s;
	}
	
	/**
	 * 获取子簇栈中簇的数量，用于HAC算法
	 * @return 子簇栈中簇的数量
	 */
	public int subGroupSize()							//子簇的个数
	{
		return subGroup.size();
	}
	
	/**
	 * 获取相识度栈中相识度的数量，用于HAC算法
	 * @return 相识度栈中相识度的数量
	 */
	public int simValueSize()							//子簇对应相识度的个数
	{
		return simValue.size();
	}
	
	/**
	 * 子簇栈是否为空，用于HAC算法
	 * @return 栈空返回true，反之返回false
	 */
	public boolean isSubGroupEmpty()					//子簇是否为空
	{
		return subGroup.isEmpty();
	}
	
	/**
	 * 获取子簇，用于HAC算法
	 * @return	返回子簇
	 */
	public List<Group> getSubGroup()					//获取子簇
	{
		return subGroup;
	}
	
	/**
	 * 获取本簇所有成员，用于KMeans算法
	 * @return 本簇所有成员
	 */
	public List<TextModel> getMember()					//获取成员
	{
		return member;
	}
	
	/**
	 * 设置本簇成员，用于KMeans算法
	 * @param members 需设置的成员
	 */
	public void setMember(List<TextModel> members)
	{
		this.member = members;
	}
	
	/**
	 * 获取相识度栈
	 * @return 相识度栈
	 */
	public List<Double> getSimValue()
	{
		return simValue;
	}
	
	/**
	 * 设置相识度
	 * @param simValue 需设置的相识度
	 */
	public void setSimValue(List<Double> simValue)
	{
		this.simValue = simValue;
	}
	
	/**
	 * 设置子簇
	 * @param subGroup 需设置的子簇
	 */
	public void setSubGroup(List<Group> subGroup)
	{
		this.subGroup = subGroup;
	}

	/**
	 * 获取初始成员
	 * @return 初始成员
	 */
	public TextModel getInitialMember()
	{
		return initialMember;
	}

	/**
	 * 设置初始成员
	 * @param initialMember 需设置的初始成员
	 */
	public void setInitialMember(TextModel initialMember)
	{
		this.initialMember = initialMember;
	}
	
	/**
	 * 遍历层次簇，返回簇中包含的所有成员的标题，用于HAC算法
	 * @return 簇中包含文章标题的List集合
	 */
	public List<String> getTitlesInHierarchicalGroup()
	{
		List<String> result = new ArrayList<String>();
		List<Group> queue = new ArrayList<Group>();
		Group grp_tmp;
		queue.add(this);
		while (!queue.isEmpty())
		{
			grp_tmp = queue.remove(0);
			if (grp_tmp.subGroupSize() > 0)
			{
				for (int i=0 ; i<grp_tmp.subGroupSize() ; i++)
				{
					queue.add(grp_tmp.getSubGroup().get(i));
				}
			}
			result.add(grp_tmp.getInitialMember().getTitle());
			
		}
		return result;
	}
	
	/**
	 * 遍历层次簇，返回簇中包含的所有成员的序号，用于HAC算法
	 * @return 簇中包含文章序号的List集合
	 */
	public List<Integer> getNoInHierarchicalGroup()
	{
		List<Integer> result = new ArrayList<Integer>();
		List<Group> queue = new ArrayList<Group>();
		Group grp_tmp;
		queue.add(this);
		while (!queue.isEmpty())
		{
			grp_tmp = queue.remove(0);
			if (grp_tmp.subGroupSize() > 0)
			{
				for (int i=0 ; i<grp_tmp.subGroupSize() ; i++)
				{
					queue.add(grp_tmp.getSubGroup().get(i));
				}
			}
			result.add(grp_tmp.getInitialMember().getNo());
			
		}
		return result;
	}
	
	/**
	 * 遍历扁平簇，返回簇中包含的所有成员的标题，用于KMeans算法
	 * @return 簇中包含文章标题的List集合
	 */
	public List<String> getTitlesInFlatGroup()
	{
		List<String> result = new ArrayList<String>();
		for (int i=0 ; i<this.member.size() ; i++)
		{
			result.add(this.member.get(i).getTitle());
		}
		return result;
	} 
	
	/**
	 * 遍历扁平簇，返回簇中包含的所有成员的序号，用于KMeans算法
	 * @return 簇中包含文章序号的List集合
	 */
	public List<Integer> getNoInFlatGroup()
	{
		List<Integer> result = new ArrayList<Integer>();
		for (int i=0 ; i<this.member.size() ; i++)
		{
			result.add(this.member.get(i).getNo());
		}
		return result;
	} 
}
