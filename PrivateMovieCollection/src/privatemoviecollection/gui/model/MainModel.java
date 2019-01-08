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
import privatemoviecollection.be.Movie;

/**
 *
 * @author Acer
 */
public class MainModel {
    
    private ObservableList<Movie> movies;
    
    public MainModel()
    {
        movies = FXCollections.observableArrayList();
        
        //temporary - later we will fetch data from database
        List<String> categories = new ArrayList();
        categories.add("Biography");
        movies.add(new Movie("Ray", categories, "path to file", 999999));
    }
    
    public ObservableList<Movie> getMovies()
    {
        return movies;
    }

    public void deleteMovie(Movie selectedMovie) {
        movies.remove(selectedMovie);
    }
    
    
}
