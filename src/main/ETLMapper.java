package main;

import java.io.IOException;

import get_info.GetInfo;
import notation.*;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ETLMapper extends Mapper<Object, Text, Text, InfoWritable>{
	private InfoWritable info = new InfoWritable();
	
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
		String[] lines = value.toString().split("\\n");
		for(String line : lines){
			Business business = GetInfo.getBusiness(line);
			if(business.review_count > 3 && business.categories.contains("Restaurants")){
				info.setName(business.name);
				info.setStars(business.stars);
				context.write(new Text(business.city), info);
			}
		}
	}
}
