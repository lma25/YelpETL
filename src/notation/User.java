package notation;

import java.util.HashMap;

public class User {
	public String type;
	public String user_id;
	public String name;
	public int review_count;
	public double average_stars;
	public HashMap<String, Integer> votes = new HashMap<>();

}
