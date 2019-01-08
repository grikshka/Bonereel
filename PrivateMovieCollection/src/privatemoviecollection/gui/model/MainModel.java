/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.bll.BllManager;
import privatemoviecollection.bll.IBllFacade;

/**
 *
 * @author Acer
 */
public class MainModel {
    
    private static MainModel instance;
    private IBllFacade bllManager;
    private ObservableList<Movie> movies;
    
    private MainModel()
    {
        bllManager = new BllManager();
        movies = FXCollections.observableArrayList();
        
        //temporary - later we will fetch data from database
        List<Category> categories = new ArrayList();
        categories.add(new Category(1, "Biography"));
        movies.add(new Movie(1, "Ray", categories, "path to file", 999999));
    }
    
    public static MainModel createInstance()
    {
        if(instance == null)
        {
            instance = new MainModel();
        }
        return instance;
    }
    
    public ObservableList<Movie> getMovies()
    {
        return movies;
    }

    public void createMovie(String title, List<Category> categories, String path, int time, Double rating)
    {
        Movie createdMovie = bllManager.createMovie(title, categories, path, time, rating);
        movies.add(createdMovie);
    }
    
    public void deleteMovie(Movie movie) {
        movies.remove(movie);
    }
    
    
}
