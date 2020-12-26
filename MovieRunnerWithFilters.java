import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
   
    public void printAverageRatings(){
      
      ThirdRatings tr = new ThirdRatings("data/ratings.csv");
    //ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
    
     MovieDatabase.initialize("ratedmoviesfull.csv");
    
    System.out.println("loaded. " + tr.getRaterSize() + "raters and "
    +MovieDatabase.size() + " movies in file ");
    
    //inside () is the number of ratings
    ArrayList<Rating> arrayList = tr.getAverageRatings(35);
    Collections.sort(arrayList);
        for (Rating rating : arrayList) {
            double avg = rating.getValue();
            String title = MovieDatabase.getTitle(rating.getItem());
            
           System.out.printf("%s %s \n", avg, title);
        }
        System.out.println("list size " + arrayList.size());
    
     }
     
     public void printAverageRatingsByYear(){
       // ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
       ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
       
        MovieDatabase md = new MovieDatabase();
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        System.out.println("loaded. " + tr.getRaterSize() + "raters and "
       +MovieDatabase.size() + " movies in file ");
    
        YearAfterFilter filter = new YearAfterFilter(2000);
        ArrayList<Rating> al = tr.getAverageRatingsByFilter(20,filter);
         Collections.sort(al);
         System.out.println("found " + al.size() + " movies");
         for(Rating r : al){
        
           System.out.println(r.getValue() + " " + md.getTitle(r.getItem())  + " year " +
           md.getYear(r.getItem()) );
         
        }
    }
     
    public void printAverageRatingsByGenre(){
       // ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
       ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
       
        MovieDatabase md = new MovieDatabase();
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        System.out.println("loaded. " + tr.getRaterSize() + "raters and "
       +MovieDatabase.size() + " movies in file ");
    
       
       //add Genre here
       GenreFilter filter = new GenreFilter("Comedy");
        ArrayList<Rating> al = tr.getAverageRatingsByFilter(20,filter);
         Collections.sort(al);
         System.out.println("found " + al.size() + " movies");
         for(Rating r : al){
        System.out.println(r.getValue() + " " + md.getTitle(r.getItem())  + " year " +
           md.getYear(r.getItem()) );
         System.out.println("\t" + md.getGenres(r.getItem()));
    }
   }

   public void printAverageRatingsByMinutes(){
   
       // ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
       ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
       
        MovieDatabase md = new MovieDatabase();
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        System.out.println("loaded. " + tr.getRaterSize() + "raters and "
       +MovieDatabase.size() + " movies in file ");
    
       
       //add Genre here
       MinutesFilter filter = new MinutesFilter(105,135);
        ArrayList<Rating> al = tr.getAverageRatingsByFilter(5,filter);
         Collections.sort(al);
         System.out.println("found " + al.size() + " movies");
         
         for(Rating r : al){
        System.out.println(r.getValue() + " " + md.getTitle(r.getItem())  + " year " +
           md.getYear(r.getItem()) );
         System.out.println("\t" + " min = " + md.getMinutes(r.getItem()));
       }
    
   }

   public void printAverageRatingsByDirectors(){
       
       // ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
       ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
       
        MovieDatabase md = new MovieDatabase();
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        System.out.println("loaded. " + tr.getRaterSize() + "raters and "
       +MovieDatabase.size() + " movies in file ");
    
       
       //directors
        ArrayList<String> dir = new ArrayList(Arrays.asList("Clint Eastwood","Joel Coen","Martin Scorsese","Roman Polanski","Nora Ephron","Ridley Scott","Sydney Pollack"));
        DirectorFilter filter = new DirectorFilter(dir);
        ArrayList<Rating> al = tr.getAverageRatingsByFilter(4,filter);
         Collections.sort(al);
         System.out.println("found " + al.size() + " movies");
         
         for(Rating r : al){
          
        System.out.println(r.getValue() + " " + md.getTitle(r.getItem()) );
         System.out.println("\t" + " director(s) : " + md.getDirector(r.getItem()));
       
    }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        
        // ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
       ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
       
        MovieDatabase md = new MovieDatabase();
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        System.out.println("loaded. " + tr.getRaterSize() + "raters and "
       +MovieDatabase.size() + " movies in file ");
    
       
       //
        
       AllFilters af = new AllFilters();
       YearAfterFilter ya = new YearAfterFilter(1990);
         af.addFilter(ya);
         GenreFilter gf = new GenreFilter("Drama");
         af.addFilter(gf);
       
       
       
        ArrayList<Rating> al = tr.getAverageRatingsByFilter(8,af);
         Collections.sort(al);
         System.out.println("found " + al.size() + " movies");
         
         for(Rating r : al){
          
        System.out.println(r.getValue() + " " + md.getTitle(r.getItem()) + " year " + md.getYear(r.getItem()) );
         System.out.println("\t" + " genre(s) : " + md.getGenres(r.getItem()));
       
    } 
}
      
    public void printAverageRatingsByDirectorsAndMinutes(){ 
        
         //   ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
       ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
       
        MovieDatabase md = new MovieDatabase();
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        System.out.println("loaded. " + tr.getRaterSize() + "raters and "
       +MovieDatabase.size() + " movies in file ");
    
       
       //
        
         AllFilters af = new AllFilters();
         MinutesFilter mf = new MinutesFilter(90,180);
         af.addFilter(mf);
         
         ArrayList<String> dir = new ArrayList(Arrays.asList("Clint Eastwood","Joel Coen","Tim Burton","Ron Howard","Nora Ephron","Sydney Pollack"));
         
         DirectorFilter df = new DirectorFilter(dir);
         af.addFilter(df);
       
       
       
        ArrayList<Rating> al = tr.getAverageRatingsByFilter(3,af);
         Collections.sort(al);
         System.out.println("found " + al.size() + " movies");
         
         for(Rating r : al){
          
        System.out.println(r.getValue() + " " + md.getTitle(r.getItem()) + " Time: " + md.getMinutes(r.getItem()) );
         System.out.println("\t" + " director(s) : " + md.getDirector(r.getItem()));
       
    } 
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

