package notation;

import java.util.HashSet;

public class Business {
	public String type;
	public String business_id;
	public String full_address;
	public boolean open;
	public HashSet<String> categories = new HashSet<>();
	public String city;
	public String state;
	public int review_count;
	public double stars;
	public String name;
	public HashSet<String> neighborhoods = new HashSet<>();
	public double longitude;
	public double latitude;
}
