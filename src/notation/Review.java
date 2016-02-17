package notation;

import java.util.HashMap;

public class Review {
	public String type;
	public String business_id;
	public String user_id;
	public double stars;
	public String text;
	public String date;
	public HashMap<String, Integer> votes = new HashMap<>();
}
