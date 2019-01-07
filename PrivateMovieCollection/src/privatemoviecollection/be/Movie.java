/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class Movie {
    
    private String title;
    private List<String> categories;
    private double rating;
    private String path;
    private int time;
    
    public Movie(String title, List<String> categories, String path, int time)
    {
        this.title = title;
        this.categories = categories;
        this.path = path;
        this.time = time;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }
    
    public void addCategory(String category)
    {
        categories.add(category);
    }
    
    public void removeCategory(String category)
    {
        categories.remove(category);
    }

    public double getRating() 
    {
        return rating;
    }

    public void setRating(double rating) 
    {
        this.rating = rating;
    }

    public String getPath() 
    {
        return path;
    }

    public void setPath(String path) 
    {
        this.path = path;
    }

    public int getTime() 
    {
        return time;
    }
    
    
    
}
