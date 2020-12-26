import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
 private HashMap<String, Rating> map;
   
    public double getAverageByID(String id, int minimalRaters) {

        double averageRating = 0.0;
        
        int counter = 0;
        for (Rater plainRater : RaterDatabase.getRaters()) {
            if (plainRater.hasRating(id)) {
                counter++;
                averageRating += plainRater.getRating(id);
            }
        }
        if (counter != 0 && counter >= minimalRaters)
            return averageRating / counter;

        return 0.0;
    }
    
   
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
       ArrayList<Rating> avgRating = new ArrayList<Rating>();
       ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
       FourthRatings mrf = new FourthRatings();

         for(String mv: movies){
             if(mrf.getAverageByID(mv,minimalRaters) > 0){
                  Rating rtng = new Rating(mv,mrf.getAverageByID(mv,minimalRaters));
                  avgRating.add(rtng);
                }
            }
        return avgRating;
    }
    
   
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRating, Filter filterCriteria){
        ArrayList<Rating> rl = new ArrayList<Rating>();
        ArrayList<String> idlist = new ArrayList<String>();
        
        MovieDatabase mdb = new MovieDatabase();
        mdb.initialize("ratedmoviesfull.csv");
        idlist = mdb.filterBy(filterCriteria);
        FourthRatings mrf = new FourthRatings();
        for(String mv : idlist){
        double r = mrf.getAverageByID(mv,minimalRating);
          if(r!=0){ Rating rtng = new Rating(mv,r);
            rl.add(rtng);}
        
       }
        return rl;
    }
    
    
    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> ratedMovieIDs = me.getItemsRated();
        double sum = 0;
        for (String movieID : ratedMovieIDs) {
            //change here
            if (r.hasRating(movieID)==true && me.hasRating(movieID)== true) {
                double meCentredRating = me.getRating(movieID) - 5;
                double rCentredRating = r.getRating(movieID) - 5;
                sum += meCentredRating * rCentredRating;
            }
        }
        return sum;
    }

   /* private double dotProduct(Rater me, Rater r){
            double product =0.0;
            ArrayList<String> myRate = me.getItemsRated();
            for(String id : myRate){
                if(r.hasRating(id)){
                    double rateByR = r.getRating(id);
                    double rateByMe = me.getRating(id);
                    rateByR -= 5;
                    rateByMe -=5;
                    product += rateByR * rateByMe;
                }
            }
            return product;
    }*/
   
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> rateralike = new ArrayList<>();
        ArrayList<Rater> myRater = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r: myRater){
            if(!r.equals(me)){
                double similarity = dotProduct(me,r);
                if(similarity >= 0){
                    Rating rating = new Rating(r.getID(), similarity);
                    rateralike.add(rating);
                }
            }
            
    }
    Collections.sort(rateralike,Collections.reverseOrder());
    
    return rateralike;
}
    
// public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
 //       return getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,new TrueFilter());
 //   }
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
     ArrayList<Rating> list = getSimilarities(id);
     ArrayList<Rating> MoviesList = new ArrayList<Rating>();
     ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
     double weightedTotal =0.0;
     double avg = 0.0;
     for(int i =0;i<myMovies.size();i++){
         double totalRaters = 0;
         for(int k=0;k<numSimilarRaters ; k++){
         String currRaterID = list.get(k).getItem();
         Rater currRater = RaterDatabase.getRater(currRaterID);
         if(currRater.hasRating(myMovies.get(i))==true){
            totalRaters++;
            weightedTotal += (list.get(k).getValue()) * (currRater.getRating(myMovies.get(i)));
          }
       }
            if(totalRaters >= minimalRaters){
                avg = weightedTotal/totalRaters;
                MoviesList.add(new Rating(myMovies.get(i), avg));
            }
        }
        Collections.sort(MoviesList, Collections.reverseOrder());
        return MoviesList;
       
    }
   /* public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters,Filter f){
      ArrayList<Rating> weighted = new ArrayList<>();
      ArrayList<Rating> similarity = getSimilarities(id);
      ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
      
      for(String s : movies){
          double weightedRate = 0.0;
          int count = 0;
          double sum =0.0;
          for (int i =0; i<numSimilarRaters; i++){
             Rating rater = similarity.get(i);
             String raterid = rater.getItem();
              double raterweight=rater.getValue();
            Rater myRater = RaterDatabase.getRater(raterid);
            if(myRater.hasRating(s)){
                count++;
                
                sum+=raterweight*myRater.getRating(s);
            }
        }
         if(count>=minimalRaters){
            weightedRate=sum/count;
            weighted.add(new Rating(s,weightedRate));
             }
     
        }
      Collections.sort(weighted, Collections.reverseOrder());
      return weighted;
    }
     */   
    
     public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
    ArrayList<Rating>  weighted= new ArrayList<Rating>();   
      ArrayList<Rating>  similar= getSimilarities(id);
      ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);	
      for(String movie:movies){
        double weightedrate=0.0;
        int count=0;
        double sum=0.0;
         for(int i = 0; i < numSimilarRaters-1; i++){
            Rating rater=similar.get(i);
            String raterid=rater.getItem();
            double raterweight=rater.getValue();
            Rater myRater = RaterDatabase.getRater(raterid);
            if(myRater.hasRating(movie)){
                count+=1;
                sum+=raterweight*myRater.getRating(movie);
            }
            
            
            }
         if(count>=minimalRaters){
             double value=sum/count;
            weighted.add(new Rating(movie,value));
            
            }
        
        }
      Collections.sort(weighted, Collections.reverseOrder());
      return weighted;
    
    }
    
public HashMap<String,Rating> getMyrating(){
		return map;
	}
}