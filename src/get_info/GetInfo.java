package get_info;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import notation.*;

public class GetInfo {
	public static String getNext(String line, int[] s){
		int start = s[0];
		while(line.charAt(start) != ':'){
			++start;
		}
		while(line.charAt(start) == ' '){
			++start;
		}
		int end = start;
		if(line.charAt(start) == '"'){
			++start;
			++end;
			while(line.charAt(end) != '"'){
				++end;
			}
		}else if(line.charAt(start) >= '0' && line.charAt(start) <= '9' || line.charAt(start) == '.'){
			while(line.charAt(end) != ','){
				++end;
			}
		}else{
			LinkedList<Character> stack = new LinkedList<>();
			if(line.charAt(start) == '{'){
				stack.offerLast('{');
			}else if(line.charAt(start) == '['){
				stack.offerLast('[');
			}
			++end;
			while(!stack.isEmpty()){
				if(line.charAt(end) == '{'){
					stack.offerLast('{');
				}else if(line.charAt(end) == '['){
					stack.offerLast('[');
				}else if(line.charAt(end) == '}' && stack.peekLast() == '{'){
					stack.pollLast();
				}else if(line.charAt(end) == ']' && stack.peekLast() == '['){
					stack.pollLast();
				}
				++end;
			}
		}
		s[0] = end;
		return line.substring(start, end);
	}
	public static Business getBusiness(String line){
		Business business = new Business();
		JSONObject obj = new JSONObject(line);
		business.type = obj.getString("type");
		business.business_id = obj.getString("business_id");
		business.full_address = obj.getString("full_address");
		business.open = obj.getBoolean("open");
		business.city = obj.getString("city");
		business.state = obj.getString("state");
		business.review_count = obj.getInt("review_count");
		business.stars = obj.getDouble("stars");
		business.name = obj.getString("name");
		business.longitude = obj.getDouble("longitude");
		business.latitude = obj.getDouble("latitude");
		JSONArray temp = obj.getJSONArray("categories");
		for(int i = 0; i < temp.length(); ++i){
			business.categories.add(temp.getString(i));
		}
		temp = obj.getJSONArray("neighborhoods");
		for(int i = 0; i < temp.length(); ++i){
			business.neighborhoods.add(temp.getString(i));
		}
		return business;
	}
	public static Review getReview(String line){
		Review review = new Review();
		JSONObject obj = new JSONObject(line);
		review.type = obj.getString("type");
		review.business_id = obj.getString("business_id");
		review.user_id = obj.getString("user_id");
		review.stars = obj.getDouble("stars");
		review.text = obj.getString("text");
		review.date = obj.getString("date");
		review.votes.put("funny", obj.getJSONObject("votes").getInt("funny"));
		review.votes.put("useful", obj.getJSONObject("votes").getInt("useful"));
		review.votes.put("cool", obj.getJSONObject("votes").getInt("cool"));
		return review;
	}
	public static User getUser(String line){
		User user = new User();
		JSONObject obj = new JSONObject(line);
		user.type = obj.getString("type");
		user.user_id = obj.getString("user_id");
		user.name = obj.getString("name");
		user.review_count = obj.getInt("review_count");
		user.average_stars = obj.getDouble("average_stars");
		user.votes.put("funny", obj.getJSONObject("votes").getInt("funny"));
		user.votes.put("useful", obj.getJSONObject("votes").getInt("useful"));
		user.votes.put("cool", obj.getJSONObject("votes").getInt("cool"));
		return user;
	}
	public static Checkin getCheckin(String line){
		Checkin checkin = new Checkin();
		JSONObject obj = new JSONObject(line);
		checkin.type = obj.getString("type");
		checkin.business_id = obj.getString("business_id");
		obj = obj.getJSONObject("checkin_info");
		for(String key : obj.keySet()){
			checkin.checkin_info.put(key, obj.getInt(key));
		}
		return checkin;
	}

}
