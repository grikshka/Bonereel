/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

import java.util.List;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.dal.DalController;
import privatemoviecollection.dal.IDalFacade;

/**
 *
 * @author Acer
 */
public class BllManager implements IBllFacade{
    
    private IDalFacade dalController;
    
    public BllManager()
    {
        dalController = new DalController();
    }

    @Override
    public Movie createMovie(String title, List<Category> categories, String path, int time, Double rating) 
    {
        return dalController.createMovie(title, categories, path, time, rating);
    }

    @Override
    public Category createCategory(String name) 
    {
        return dalController.createCategory(name);
    }
    
}
