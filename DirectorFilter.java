import java.util.*;
/**
 * Write a description of DirectorFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorFilter implements Filter {
private ArrayList<String> directors;
    public DirectorFilter(ArrayList<String> dir){
        directors= dir;
        
    }
    
    public boolean satisfies(String id){
        for(String s: directors){
            if(MovieDatabase.getDirector(id).indexOf(s) >= 0){
                return true;
            }
        }
        return false;
 }
}