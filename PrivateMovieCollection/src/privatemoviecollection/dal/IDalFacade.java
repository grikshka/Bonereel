/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal;

import java.sql.SQLException;
import java.util.List;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.User;
import privatemoviecollection.dal.exceptions.DalException;

/**
 *
 * @author Acer
 */
public interface IDalFacade {
    
    Movie createMovie(User user, String title, List<Category> categories, String path, int time, Integer rating);
    
    List<Movie> getAllMovies(User user);
    
    Movie updateMovie(Movie movie, String title, List<Category> categories, String path, int time, Integer rating);
    
    void deleteMovie(Movie movieToDelete);
    
    Category createCategory(User user, String name) throws DalException;
    
    List<Category> getAllCategories(User user) throws DalException;
    
    void deleteCategory(Category categoryToDelete) throws DalException;
    
    User createUser(String email, String password) throws DalException;

    User getUser(String email, String password) throws DalException;
}
