/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

import java.util.List;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;

/**
 *
 * @author Acer
 */
public interface IBllFacade {
    
    Movie createMovie(String title, List<Category> categories, String path, int time, Integer rating);
    
    List<Movie> getAllMovies();
    
    Movie updateMovie(Movie movie, String title, List<Category> categories, String path, int time, Integer rating);
    
    void deleteMovie(Movie movieToDelete);
    
    Category createCategory(String name);
    
    List<Category> getAllCategories();
    
    void deleteCategory(Category categoryToDelete);
    
}
