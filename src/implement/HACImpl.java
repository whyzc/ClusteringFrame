package implement;

import java.util.*;

import clustering.HAC;
import clusteringLayer.Group;
import clusteringLayer.HACAlgo;
import feature.GenerateFeature;
import model.EstablishModel;
import model.TextModel;
import text.Text;

/**
 * 面向客户使用的HAC接口的实现
 * @author yzc
 *
 */
public class HACImpl implements HAC
{
	@Override
	public Group hac(List<Text> ts)
	{
		// TODO Auto-generated method stub
		/***************对文本生成特征******************/
		GenerateFeature gf = new GenerateFeatureCharacterBased();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}
		
		
		/*****************生成文本模型*********************/	
		EstablishModel em = new EstablishModelCharacterBased();
		List<TextModel> tms = em.modeling(ts);
		
		
		/*****************KMeans算法*********************/	
		HACAlgo hac = new HACAlgoImpl();
		Group g = hac.merge(tms);
		
		return g;
	}

	

	@Override
	public Group hac(String pos)
	{
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Group> split(Group grp, int k)
	{
		// TODO Auto-generated method stub
		if (grp.getSubGroup().size() != grp.getSimValue().size())
		{
			throw new RuntimeException("簇内子簇数目与栈中相识度数目不符");
		}
		if (k<2)
		{
			throw new RuntimeException("分解个数太少");
		}
		
		int j = k;
		List<Group> result= new ArrayList<Group>();
		result.add(grp);
		
		while (j>1)
		{
			double sim_tmp = 1.1;		//记录最小相似度
			int site_tmp = -1;			//记录相似度最小的簇的位置
			Group grp_tmp;				//临时存放簇
			
			for (int i=0 ; i<result.size() ; i++)	//寻找最小相似度的簇
			{
				grp_tmp = result.get(i);
				if (grp_tmp.simValueSize()<1)
					break;
				if ( sim_tmp > grp_tmp.getSimValue().get(grp_tmp.simValueSize()-1) )
				{
					sim_tmp = grp_tmp.getSimValue().get(grp_tmp.simValueSize()-1);
					site_tmp = i;
				}
			}
			
			if (site_tmp > -1)			//对簇进行分割
			{
				result.add(result.get(site_tmp).pushGroup());
				result.get(site_tmp).pushSim();
			}
			
			j--;
		}
		return result;
	}



	@Override
	public List<Group> split(Group grp, double sim)
	{
		// TODO Auto-generated method stub
		if (grp.getSubGroup().size() != grp.getSimValue().size())
		{
			throw new RuntimeException("簇内子簇数目与栈中相识度数目不符");
		}
		if (sim<0 || sim>1)
		{
			throw new RuntimeException("相识度不符合要求");
		}
		
		List<Group> result= new ArrayList<Group>();
		result.add(grp);
		Group grp_tmp;			//临时存放簇
		
		for (int i=0 ; i<result.size() ; i++)		//遍历整个队列进行分割
		{
			grp_tmp = result.get(i);
			if (grp_tmp.simValueSize()<1)			//没有子簇，则跳过
			{
				continue;
			}
			
			int site_tmp = -1;
			int k = grp_tmp.simValueSize()-1;
			while (sim >= grp_tmp.getSimValue().get(k))		//寻找相似度小于sim的簇位置
			{
				site_tmp = k;
				k--;
				if (k<0)
				{
					site_tmp = 0;
					break;
				}
			}
			
			if (site_tmp == -1)
			{
				continue;
			}
			
			for (int j=site_tmp, m=grp_tmp.simValueSize() ; j<m ; j++)	//将所有相似度小于要求的进行分割
			{
				result.add(result.get(i).pushGroup());
				result.get(i).pushSim();
			}
			
		}
		
		return result;
	}

}
