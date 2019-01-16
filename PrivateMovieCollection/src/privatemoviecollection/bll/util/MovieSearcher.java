/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll.util;

import java.util.ArrayList;
import java.util.List;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;

/**
 *
 * @author Acer
 */
public class MovieSearcher {
    
    public static List<Movie> searchMoviesByCategory(List<Movie> allMovies, List<Category> filteringCategories)
    {
        List<Movie> filteredMovies = new ArrayList();
        filteredMovies.addAll(allMovies);
        for(Movie movie : allMovies)
        {
            for(Category filteringCategory : filteringCategories)
            {
                boolean isCategoryInMovie = false;
                for(Category movieCategory : movie.getCategories())
                {
                    if(filteringCategory.getName().equals(movieCategory.getName()))
                    {
                        isCategoryInMovie = true;
                    }
                }
                if(!isCategoryInMovie)
                {
                    filteredMovies.remove(movie);
                    break;
                }
            }
        }
        return filteredMovies;
    }
    

    public static List<Movie> searchMoviesByTitle(List<Movie> allMovies, String filter)
    {
        filter = filter.toLowerCase();
        List<Movie> filteredMovies = new ArrayList();
        for(Movie movie : allMovies)
        {
            if(filter.length() <= movie.getTitle().length() && filter.equals(movie.getTitle().toLowerCase().substring(0, filter.length())))
            {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    
    public static List<Movie> searchMoviesWithHigherRating(List<Movie> allMovies, Integer rating)
    {
        if(rating == null)
        {
            return allMovies;
        }
        List<Movie> filteredMovies = new ArrayList();
        for(Movie movie : allMovies)
        {
            if((int) movie.getRating() >= (int) rating)
            {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    
    public static List<Movie> searchMoviesWithLowerRating(List<Movie> allMovies, Integer rating)
    {
        if(rating == null)
        {
            return allMovies;
        }
        List<Movie> filteredMovies = new ArrayList();
        for(Movie movie : allMovies)
        {
            if((int) movie.getRating() <= (int) rating)
            {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
    
}
