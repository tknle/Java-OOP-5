import java.util.*;
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientRater implements Rater {

    private String myID;
    private HashMap<String, Rating> map;

    public EfficientRater(String id) {
        myID = id;
        map = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
       map.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(map.containsKey(item)){
            return true;
        }
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if(map.containsKey(item)){
        return map.get(item).getValue();
    }
        return -1;
    }

    public int numRatings() {
        return map.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : map.keySet()){
            list.add(s);
        }
        return list;
    }
    
     public HashMap<String,Rating> getMyrating(){
		return map;
	}
}


