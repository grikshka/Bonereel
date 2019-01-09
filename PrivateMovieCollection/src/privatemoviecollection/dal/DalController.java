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
    public Movie createMovie(String title, List<Category> categories, String path, int time, Double rating)
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
    
}
