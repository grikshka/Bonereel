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
import privatemoviecollection.dal.daos.CategoriesDAO;
import privatemoviecollection.dal.daos.MovieDAO;

/**
 *
 * @author Acer
 */
public class DalController implements IDalFacade{
    
    private MovieDAO mDao;
    private CategoriesDAO cDao;
    
    public DalController()
    {
        mDao = new MovieDAO();
        cDao = new CategoriesDAO();
    }

    @Override
    public Movie createMovie(String title, List<Category> categories, String path, int time, Integer rating)
    {
        Movie createdMovie = null;
        try
        {
            createdMovie = mDao.createMovie(title, categories, path, time, rating);
        }
        catch(SQLException e)
        {
            //TO DO
        }
        return createdMovie;
        
    }
    
    @Override
    public List<Movie> getAllMovies()
    {
        List<Movie> allMovies = null;
        try
        {
            allMovies = mDao.getAllMovies();
        }
        catch(SQLException e)
        {
            //TO DO
        }
        return allMovies;
    }
    
    @Override
    public Movie updateMovie(Movie movie, String title, List<Category> categories, String path, int time, Integer rating)
    {
        Movie updatedMovie = null;
        try
        {
            updatedMovie = mDao.updateMovie(movie, title, categories, path, time, rating);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return updatedMovie;
    }
    
    @Override
    public void deleteMovie(Movie movieToDelete)
    {
        try
        {
            mDao.deleteMovie(movieToDelete);
        }
        catch(SQLException e)
        {
            //TO DO
        }
    }

    @Override
    public Category createCategory(String name) 
    {
        Category category = null;
        try
        {
            category = cDao.createCategory(name);
        }
        catch(SQLException e)
        {
            //TO DO
        }
        return category;
    }
    
    @Override
    public List<Category> getAllCategories()
    {
        List<Category> allCategories = null;
        try
        {
            allCategories = cDao.getAllCategories();
        }
        catch(SQLException e)
        {
            //TO DO
        }
        return allCategories;
    }
    
    @Override
    public void deleteCategory(Category categoryToDelete)
    {
        try
        {
            cDao.deleteCategory(categoryToDelete);
        }
        catch(SQLException e)
        {
            //TO DO
        }
    }
    
}
