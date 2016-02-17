package main;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ETLReducer extends Reducer<Text, InfoWritable, Text, Text>{
	public void reduce(Text key, Iterable<InfoWritable> values, Context context) throws IOException, InterruptedException{
		double total = 0;
		int count = 0;
		for(InfoWritable info : values){
			++count;
			total += Double.parseDouble(info.getStars());
		}
		if(count == 0 || total / count < 4){
			context.write(key, new Text(String.valueOf(total / count)));
		}
	}
}
