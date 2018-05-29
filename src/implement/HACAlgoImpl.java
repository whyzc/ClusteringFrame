package implement;

import java.util.*;

import clusteringLayer.Group;
import clusteringLayer.HACAlgo;
import model.TextModel;

/**
 * HAC算法层的实现
 * @author yzc
 *
 */
public class HACAlgoImpl implements HACAlgo
{
	@Override
	public Group merge(List<TextModel> tms)
	{
		// TODO Auto-generated method stub
		List<Group> grps = new ArrayList<Group>();
		for (int i=0 ; i<tms.size() ; i++)				//每一个文本分为一个簇
		{
			Group g = new GroupCharacterBased();
			g.setInitialMember(tms.get(i));
			grps.add(g);
		}
		
		double[][] m = new double[tms.size()][tms.size()];		//相识度矩阵
		
		for (int i=0 ; i<tms.size() ; i++)						//矩阵赋值
		{
			for (int j=i+1 ; j<tms.size() ; j++)
			{
				m[i][j] = grps.get(i).sim(grps.get(j));
				m[j][i] = m[i][j];
			}
		}
		
		int q = tms.size();					//簇的个数
		
		for (int i = 1 ; i<tms.size() ; i++)					//进行tms.size()-1次合并，最后生成一个层次簇
		{
			int[] site;
			site = maxSimSite(m, q);
			mergeGroup(grps, site[0], site[1], m);
			updataMatrix(m, site[0], site[1], q, grps);
			q=q-1;
		}
		
		
		if (grps.size() != 1)
			return null;
		return grps.get(0);
	}
	
	private static int[] maxSimSite(double[][] m, int q)		//找出二阶矩阵中相识度最大的一对簇，位置以数组返回
	{
		double tmp = 0;
		int iSite=-1, jSite=-1;
		
		for (int i=0 ; i<q ; i++)	
		{
			for (int j=i+1 ; j<q ; j++)
			{
				if (m[i][j] <= 0)
					continue;
				if (tmp<m[i][j])
				{
					tmp = m[i][j];
					iSite = i;
					jSite = j;
				}
			}
		}
		
		int[] site = new int[2];
		site[0] = iSite;
		site[1] = jSite;
		return site;
	}
	
	private static void mergeGroup(List<Group> gs, int i, int j , double[][] m)		//将j位置的Group合并入i位置的Group
	{
		gs.get(i).pop(gs.get(j), m[i][j]);
		gs.remove(j);
	}
	
	private static void updataMatrix(double[][] m, int i, int j, int q, List<Group> grps)		//合并后，删除矩阵
	{																			//中j行和j列的数，并重新计算新Group的距离
		//删除j行和j列
		for (int k=j ; k<q-1 ; k++)
		{
			for (int t=0 ; t<q ; t++)
			{
				m[k][t] = m[k+1][t];
			}
		}
		for (int k=j ; k<q-1 ; k++)
		{
			for (int t=0 ; t<q ; t++)
			{
				m[t][k] = m[t][k+1];
			}
		}
		
		
		//重新计算合并后的group与其他group的距离
		for (int k=0 ; k<q-1 ; k++)
		{
			m[i][k] = grps.get(i).sim(grps.get(k));
			m[k][i] = m[i][k];
		}
	}
}
