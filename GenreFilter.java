import java.util.*;
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
    private String g;
    public GenreFilter(String genre){
        g = genre;
    }
    public boolean satisfies(String id){
        return MovieDatabase.getGenres(id).indexOf(g) >= 0;
    }
}
