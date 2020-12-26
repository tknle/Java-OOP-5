                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.text.DecimalFormat;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender {
    
 private static DecimalFormat df2 = new DecimalFormat(".##");

    public ArrayList<String> getItemsToRate (){
        ArrayList<String> ret = new ArrayList<String>();
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        // AllFilters af = new AllFilters();
        // af.addFilter(new YearAfterFilter(2010));
        // af.addFilter(new MinutesFilter(70,200));
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Random rand = new Random();

        for(int k=0;k<10;k++){
            int randomIndex = rand.nextInt(movies.size());
            ret.add(movies.get(randomIndex));
            movies.remove(randomIndex);
        }
        return ret;
    }

    public void printRecommendationsFor (String webRaterID){
        FourthRatings sr = new FourthRatings();
       
        ArrayList<Rating> movieIdWeightAverageList = sr.getSimilarRatings(webRaterID,15,3);
        int count = 0;
        Rater wr = RaterDatabase.getRater(webRaterID);
        String ret = "";
        String str1="<style>body {background-color: #00008B;}.topdiv {font-family: \"Courier New\", Courier, monospace;text-align: center;background-color: #C0C0C0;}.cleardiv {font-family: Verdana, Geneva, sans-serif;}.boxdiv {background-color: #4169E1;font-family: Verdana, Geneva, sans-serif;}table, th, td {border: 1px solid black;}</style>";
        String str2="<div class=\"topdiv\"><h1>Recommend top movies just for you</h1><b>Based on your rating, we suggest a list of the best movies below !</b></div><div class=\"cleardiv\"><h2>Best movies ever !</h2><em><b>Hope you love it</b></em></div>";
        String str3="<div class=\"boxdiv\"><h3>Movie: </h3>";
        String str4="<table style=\"width:100%\"><tr><th>#</th><th>Title</th><th>Genre</th><th>Year</th><th>Time(Minutes)</th></tr>";
     
        String str5="</table>";
        String str6="</div>";
        String tmp = "";

        for(Rating rating : movieIdWeightAverageList){
            if(wr.hasRating(rating.getItem())){
                continue;
            }
           
            count += 1;
            String img_str = MovieDatabase.getPoster(rating.getItem());
            String title = MovieDatabase.getTitle(rating.getItem());
            String genre = MovieDatabase.getGenres(rating.getItem());
            int year = MovieDatabase.getYear(rating.getItem());
            int time = MovieDatabase.getMinutes(rating.getItem());
                                                            //<img src=\"%s\" style=\"width:90px;height:120px;\"
            tmp = tmp + String.format("<tr><td>%d</td><td>%s</td><td>%s</td><td>%d</td><td>%d</td></tr>",count,title,genre,year,time);
            if(count >= 10){
                break;
            }
        }

        if(count == 0){
            String tmp2 = "No movies could be recommended, please try again.";
            ret = str1+str2+str3+tmp2+str6;
            System.out.println(ret);
        }
        else{
            ret = str1+str2+str3+str4+tmp+str5+str6;
            System.out.println(ret);
        }
        
        
             
    }
    
    public static void main(String args[]){
     new RecommendationRunner().getItemsToRate();
     String webRaterID = "data/ratedmoviesfull.csv";
     new RecommendationRunner().printRecommendationsFor(webRaterID);
    }
}



