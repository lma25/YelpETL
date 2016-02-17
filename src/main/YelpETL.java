package main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class YelpETL {
	public static void ETLMR(String input, String output) throws Exception{
		Configuration conf = new Configuration();
		@SuppressWarnings("deprecation")
		Job job = new Job(conf);
		job.setJarByClass(YelpETL.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(InfoWritable.class);
		job.setMapperClass(ETLMapper.class);
		job.setReducerClass(ETLReducer.class);
		job.setNumReduceTasks(1);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path(input));
		Path outPath = new Path(output);
		FileOutputFormat.setOutputPath(job, outPath);
		outPath.getFileSystem(conf).delete(outPath, true);
		
		job.waitForCompletion(true);
	}
	
	public static void main(String[] args) throws Exception{
		ETLMR(args[0], args[1]);
	}
}
