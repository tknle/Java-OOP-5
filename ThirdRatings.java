import java.util.*;
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThirdRatings {

    private ArrayList<Rating> myRating;
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
       
        myRaters = firstRatings.loadRaters(ratingsfile);
    }


   

    public int getRaterSize() {
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters) {

        double averageRating = 0.0;

        int counter = 0;
        for (Rater plainRater : myRaters) {
            if (plainRater.hashRating(id)) {
                counter++;
                averageRating += plainRater.getRating(id);
            }
        }
        if (counter != 0 && counter >= minimalRaters)
            return averageRating / counter;

        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        myRating = new ArrayList<Rating>();
        Filter f = new TrueFilter();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String s : movies) {
            //String id = movie.getID();
            double avg = getAverageByID(s, minimalRaters);
            if (avg != 0.0)
                myRating.add(new Rating(s, avg));
        }

        Collections.sort(movies);
        return myRating;
    }
    
  //helper function  
    public ArrayList<Rating> getAverageRatingsByFilter ( int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> myRating = new ArrayList<>();
        ArrayList<String> moviesID = MovieDatabase.filterBy(filterCriteria);
        for(String s : moviesID){
            double avg = getAverageByID(s, minimalRaters);
            if(avg > 0.0){
                myRating.add(new Rating(s, avg));
            }
    }
    return myRating;
}
}
