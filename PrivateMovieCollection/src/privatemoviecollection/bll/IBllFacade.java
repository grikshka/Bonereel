/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

import java.util.List;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.User;

/**
 *
 * @author Acer
 */
public interface IBllFacade {
    
    Movie createMovie(User user, String title, List<Category> categories, String path, int time, Integer rating);
    
    List<Movie> getAllMovies(User user);
    
    Movie updateMovie(Movie movie, String title, List<Category> categories, String path, int time, Integer rating);
    
    void deleteMovie(Movie movieToDelete);
    
    Category createCategory(User user, String name);
    
    List<Category> getAllCategories(User user);
    
    void deleteCategory(Category categoryToDelete);
    
    User createUser(String email, String password);

    User getUser(String email, String password);
    
}
