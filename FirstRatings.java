import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of FirstRatings here.
 * 
 * @author (khanhnhi) 
 * @version (a version number or a date)
 */
public class FirstRatings {

    public ArrayList<Movie> loadMovies(String fileName){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        int count = 0;
      
        for(CSVRecord record : parser){
       Movie m = new Movie(
           record.get("id"), record.get("title"),
           record.get("year"), record.get("genre"),
           record.get("director"), record.get("country"),
           record.get("poster"), 
           Integer.parseInt(record.get("minutes"))
           );
         movies.add(m);  
    }
    
    return movies;
    }
    
    public void testLoadMovies(){
       String fileName = "data/ratedmoviesfull.csv";
        
        ArrayList<Movie> list = loadMovies(fileName);
        /*System.out.println("Total number of movies : " + list.size());
        for(Movie m : list){
            System.out.println(m);
        }
        
        String fileName = "data/ratedmoviesfull.csv";
        ArrayList<Movie> list = loadMovies(fileName);
        System.out.println("Total number of movies : " + list.size());
        */
       int numOfComedy = 0;
       int numOfLength =0;
       HashMap<String, Integer> dirAndMo = new HashMap<>();
       String[] currDir;
      for(Movie m : list){
          if(m.getGenres().contains("Comedy")){
              numOfComedy++;
      }
        if(m.getMinutes() > 150){
            numOfLength++;
        }
        
        currDir = m.getDirector().split(", ");
        for(String s : currDir){
            if(dirAndMo.containsKey(s)){
            dirAndMo.put(s, dirAndMo.get(s)+1);
        } else {
            dirAndMo.put(s, 1);
        }
        
     }
}
int maxCount =0;
for(int currCount : dirAndMo.values()){
    if( currCount > maxCount){
        maxCount = currCount;
    }
}

for(String director: dirAndMo.keySet()){
    if( dirAndMo.get(director) == maxCount){
        System.out.println(director);
    }
}



  System.out.println("the number of movies is " + list.size());
  System.out.println("There are " + numOfComedy +" Comedy in file");  
  System.out.println("There are " + numOfLength + " movies have greater than 150 mins");
  System.out.println("The max count of movies directed is "+ maxCount);
}

public ArrayList<Rater> loadRaters( String filename){
   ArrayList<Rater> ratedata = new ArrayList<Rater>();
   FileResource fr = new FileResource(filename);
   CSVParser parser = fr.getCSVParser();
   
   HashMap<String, Rater> rateMap = new HashMap<>();
   
   String raterID = null;
   String movieId = null;
   int rating = 0;
   for(CSVRecord record : parser){
    Rater rater;
    raterID = record.get("rater_id");
    movieId =  record.get("movie_id");
    rating = Integer.parseInt(record.get("rating"));
    
    if(!rateMap.containsKey(raterID)){
        rater = new EfficientRater(raterID);
        rateMap.put(raterID, rater);
        ratedata.add(rater);
    }
    else {
        rater = rateMap.get(raterID);
    }
    rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
    }
 return  ratedata;  
}


public void testLoadRaters(){
   ArrayList<Rater> rater = loadRaters("data/ratings.csv");
  // ArrayList<Rater> rater = loadRaters("data/ratings_short.csv");
   
   int numRaters = rater.size();  
   System.out.println("Number of raters: " + numRaters);
    
   int count = 0;
   String raterID = "193";
   int maxRating = -1;
   String movID= "1798709";
   Set<String> moviesRated = new HashSet<String>();
   for(Rater r : rater){
       if(r.getID().equals(raterID)){
          System.out.println("RaterID " + raterID + " has " + r.numRatings() + " ratings");
         
        }
         
        if(r.numRatings() > maxRating){
            maxRating = r.numRatings();
        }
        
        if(r.hasRating(movID)){
            count++;
        }
    
    ArrayList<String> ratedItems = r.getItemsRated();
    for (String item : ratedItems)
    {
    if(!moviesRated.contains(item))
     {
      moviesRated.add(item);
      }
    }
        }
        
   for (Rater r : rater){
    if (r.numRatings() == maxRating)
    {
    System.out.println("Rater id " + r.getID() + " has maximum number of ratings");
    }
        }
	System.out.println("Max ratings are " + maxRating);
	System.out.println("MovieID " + movID + " has rating " + count);
	System.out.println("Number of movies rated " + moviesRated.size());
		
	}
    
}



