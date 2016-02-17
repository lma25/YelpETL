package main;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class InfoWritable implements Writable{
	private String name;
	private String stars;
	
	@Override
	public void readFields(DataInput in) throws IOException {
		this.name = in.readUTF();
		this.stars = in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(stars);
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setStars(double stars){
		this.stars = String.valueOf(stars);
	}
	public String getStars(){
		return this.stars;
	}
}
