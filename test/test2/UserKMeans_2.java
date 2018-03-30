package test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import clustering.Group;
import clustering.KMeans;
import feature.GenerateFeature;
import similarity.EstablishModel;
import similarity.TextModel;
import text.Text;
import user.UserKMeans;

public class UserKMeans_2 implements UserKMeans
{

	@Override
	public List<Group> kMeans(String pos , int k)
	{
		// TODO Auto-generated method stub
		File file = new File("pos");
		File[] subfiles = file.listFiles();
		try
		{
			for (int i=0 ; i<subfiles.length ; i++)
			{
				
				FileReader fr = new FileReader(subfiles[i]);
				BufferedReader br = new BufferedReader(fr);
				
//				while (br.)
//				String content = br.readLine();
//				
			}
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Group> kMeans(List<Text> ts , int k)
	{
		// TODO Auto-generated method stub
		GenerateFeature gf = new GenerateFeature_2();
		for (int i=0 ; i<ts.size() ; i++)
		{
			gf.generateFeature(ts.get(i));
		}
		
		EstablishModel em = new EstablishModel_2();
		List<TextModel> tms = em.modeling(ts);
		
		KMeans km = new KMeans_2();
		List<Group> g = km.kMeans(tms, k);
		return g;
	}

}
