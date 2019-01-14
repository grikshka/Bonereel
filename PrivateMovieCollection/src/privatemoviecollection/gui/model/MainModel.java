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
import privatemoviecollection.be.User;
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
    private static User loggedInUser;
    
    private MainModel()
    {
        bllManager = new BllManager();
        movies = FXCollections.observableArrayList(bllManager.getAllMovies(loggedInUser));       
    }
    
    public static MainModel createInstance()
    {
        if(instance == null)
        {
            instance = new MainModel();
        }
        return instance;
    }
    
    public static void setUser(User user)
    {
        loggedInUser = user;
    }
    
    public ObservableList<Movie> getMovies()
    {
        return movies;
    }

    public void createMovie(String title, List<Category> categories, String path, int time, Integer rating)
    {
        Movie createdMovie = bllManager.createMovie(loggedInUser, title, categories, path, time, rating);
        movies.add(createdMovie);
    }
    
    public void updateMovie(Movie movie, String title, List<Category> categories, String path, int time, Integer rating)
    {
        bllManager.updateMovie(movie, title, categories, path, time, rating);
        updateListOfMovies(movie);
    }
    
    private void updateListOfMovies(Movie movie)
    {
        int index = movies.indexOf(movie);
        movies.set(index, movie);
    }
    
    public void deleteMovie(Movie movie) {
        bllManager.deleteMovie(movie);
        movies.remove(movie);
    }
    
    public ObservableList<Movie> getFilteredMovies(List<Category> categories, String filter, Integer rating)
    {
        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList(MovieSearcher.searchMoviesByCategory(movies, categories));
        filteredMovies = FXCollections.observableArrayList(MovieSearcher.searchMoviesByTitle(filteredMovies, filter));
        filteredMovies = FXCollections.observableArrayList(MovieSearcher.searchMoviesByRating(filteredMovies, rating));
        return filteredMovies;
    }
    
    
}
