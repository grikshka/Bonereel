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
import privatemoviecollection.bll.util.MovieSearcher;

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
        movies = FXCollections.observableArrayList(bllManager.getAllMovies());       
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
        bllManager.deleteMovie(movie);
        movies.remove(movie);
    }
    
    public ObservableList<Movie> getFilteredMovies(List<Category> categories, String filter, Double rating)
    {
        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList(MovieSearcher.searchMoviesByCategory(movies, categories));
        filteredMovies = FXCollections.observableArrayList(MovieSearcher.searchMoviesByTitle(filteredMovies, filter));
        filteredMovies = FXCollections.observableArrayList(MovieSearcher.searchMoviesByRating(filteredMovies, rating));
        return filteredMovies;
    }
    
    
}
