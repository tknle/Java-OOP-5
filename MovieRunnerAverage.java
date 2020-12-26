import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
public void printAverageRatings(){
    //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
    SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
    System.out.println("There are " + sr.getRaterSize() + " raters and "
    + sr.getMoviesSize() + " movies in file ");
    //inside () is the number of ratings
    ArrayList<Rating> arrayList = sr.getAverageRatings(12);

        for (Rating rating : arrayList) {
            double avg = rating.getValue();
            String title = sr.getTitle(rating.getItem());
            
           System.out.printf("%s %s \n", avg, title);
        }
        System.out.println("list size " + arrayList.size());
    }
    
    public void getAverageRatingOneMovie() {

        SecondRatings secondRatings = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String titleForSearch = "Vacation";
        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(1);

        for (Rating rating : ratingArrayList) {
            if (titleForSearch.equals(secondRatings.getTitle(rating.getItem())))
                System.out.println(rating.getValue());
        }
    }
}

