import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
 public void printAverageRatings(){
      
      FourthRatings tr = new FourthRatings();
    //FourthRatings tr = new FourthRatings( );
     RaterDatabase.initialize("ratings.csv");
     MovieDatabase.initialize("ratedmoviesfull.csv");
    
    System.out.println("loaded. " + RaterDatabase.size() + "raters and "
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
   
     public void printAverageRatingsByYearAfterAndGenre(){
        
        // FourthRatings tr = new FourthRatings();
       FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase md = new MovieDatabase();
        MovieDatabase.initialize("ratedmoviesfull.csv"); 
        System.out.println("loaded. " + RaterDatabase.size() + "raters and "
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
 
     public void printAverageRatingsByYear(){
       // ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
       ThirdRatings tr = new ThirdRatings( "data/ratings.csv");
        RaterDatabase.initialize("ratings.csv");
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
    
    public void printSimilarRatings(){
                FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		ArrayList<Rating> ratingList = fourthR.getSimilarRatings("71",20,5);
		System.out.println("found " +ratingList.size() + " movies that matches");
		
		//System.out.println(ratingList);
		Collections.sort(ratingList);
		Collections.reverse(ratingList);
		for(int i=0;i<ratingList.size();i++){
		System.out.println(MovieDatabase.getTitle(
		ratingList.get(i).getItem()) + " " +
		MovieDatabase.getGenres(ratingList.get(i).getItem()) + " " +
		MovieDatabase.getYear(ratingList.get(i).getItem()) + " " +
		MovieDatabase.getDirector(ratingList.get(i).getItem()));
              }
	}
      
	public void printSimilarRatingsByGenre(){
	    FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		
		Filter filter = new GenreFilter("Drama");
		ArrayList<Rating> ratingList = fourthR.getSimilarRatingsByFilter("168",20,5, filter);
		System.out.println("found " +ratingList.size() + " movies that matches");
		
		
		Collections.sort(ratingList);
		Collections.reverse(ratingList);
		for(int i=0;i<ratingList.size();i++){
		System.out.println(MovieDatabase.getTitle(
		ratingList.get(i).getItem()) + "\n" +
		MovieDatabase.getGenres(ratingList.get(i).getItem()) + "\t " +
		MovieDatabase.getYear(ratingList.get(i).getItem()) + "\t " +
		MovieDatabase.getDirector(ratingList.get(i).getItem()));
              }
     
	   }
	   
	   public void printSimilarRatingsByDirector(){
	        FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		
		 ArrayList<String> dir = new ArrayList(Arrays.asList("Clint Eastwood","J.J. Abrams","Alfred Hitchcock","Sydney Pollack","David Cronenberg","Oliver Stone","Mike Leigh"));
		Filter f = new DirectorFilter(dir);
		ArrayList<Rating> ratingList = fourthR.getSimilarRatingsByFilter("120",10,2,f);
		System.out.println("found " +ratingList.size() + " movies that matches");
		
		
		Collections.sort(ratingList);
		Collections.reverse(ratingList);
		for(int i=0;i<ratingList.size();i++){
		System.out.println(MovieDatabase.getTitle(
		ratingList.get(i).getItem()) + "\n" +
		MovieDatabase.getGenres(ratingList.get(i).getItem()) + "\t " +
		MovieDatabase.getYear(ratingList.get(i).getItem()) + "\t " +
		MovieDatabase.getDirector(ratingList.get(i).getItem())); 
	   }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		
		AllFilters af = new AllFilters();
		Filter genre = new GenreFilter("Drama");
		Filter min = new MinutesFilter(80,160);
		af.addFilter(genre);
		af.addFilter(min);
		
		String id ="168";
		int minimalRaters = 3;
		int topsimilar = 10;
		ArrayList<Rating> ratingList = fourthR.getSimilarRatingsByFilter(id,topsimilar,minimalRaters,af);
		System.out.println("found " +ratingList.size() + " movies that matches");
		
		
		Collections.sort(ratingList);
		Collections.reverse(ratingList);
		for(int i=0;i<ratingList.size();i++){
		System.out.println(MovieDatabase.getTitle(
		ratingList.get(i).getItem()) + "\n" +
		MovieDatabase.getGenres(ratingList.get(i).getItem()) + "\t " +
		MovieDatabase.getYear(ratingList.get(i).getItem()) + "\t " +
		MovieDatabase.getDirector(ratingList.get(i).getItem())); 
	   } 
        
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
       FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		
		AllFilters af = new AllFilters();
		Filter yearafter = new YearAfterFilter(1975);
		Filter min = new MinutesFilter(70,200);
		af.addFilter(yearafter);
		af.addFilter(min);
		
		String id ="314";
		int minimalRaters = 5;
		int topsimilar = 10;
		ArrayList<Rating> ratingList = fourthR.getSimilarRatingsByFilter(id,topsimilar,minimalRaters,af);
		System.out.println("found " +ratingList.size() + " movies that matches");
		
		
		Collections.sort(ratingList);
		Collections.reverse(ratingList);
		for(int i=0;i<ratingList.size();i++){
		System.out.println(MovieDatabase.getTitle(
		ratingList.get(i).getItem()) + "\n" +
		MovieDatabase.getGenres(ratingList.get(i).getItem()) + "\t " +
		MovieDatabase.getYear(ratingList.get(i).getItem()) + "\t " +
		MovieDatabase.getDirector(ratingList.get(i).getItem())); 
	   } 
        
    }  
        
}  
      
