
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
 int minMin;
 int maxMin;
    public MinutesFilter(int min, int max){
        minMin = min;
        maxMin = max;
    }
    
    public boolean satisfies(String Id){
     return MovieDatabase.getMinutes(Id) >=minMin && MovieDatabase.getMinutes(Id) <=maxMin;
    }
}
