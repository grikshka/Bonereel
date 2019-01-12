/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.be;

import java.util.ArrayList;
import java.util.List;
import privatemoviecollection.bll.util.TimeConverter;

/**
 *
 * @author Acer
 */
public class Movie {
    
    private int id;
    private String title;
    private List<Category> categories;
    private Integer rating;
    private String path;
    private int time;
    
    public Movie(int id, String title, String path, int time, Integer rating)
    {
        this.id = id;
        this.title = title;
        this.path = path;
        this.time = time;
        this.rating = rating;
        categories = new ArrayList();
    }
    
    public Movie(int id, String title, List<Category> categories, String path, int time, Integer rating)
    {
        this.id = id;
        this.title = title;
        this.categories = categories;
        this.path = path;
        this.time = time;
        this.rating = rating;
    }

    public int getId()
    {
        return id;
    }
    
    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }
    
    public void addCategory(Category category)
    {
        categories.add(category);
    }
    
    public void removeCategory(String category)
    {
        categories.remove(category);
    }
    
    public List<Category> getCategories()
    {
        return categories;
    }
    
    public String getCategoriesInString()
    {
        String categoriesInString = "";
        for(Category category: categories)
        {
            categoriesInString += category.getName() + ", ";
        }
        categoriesInString = categoriesInString.substring(0,categoriesInString.length()-2);
        return categoriesInString;
    }

    public Integer getRating() 
    {
        return rating;
    }

    public void setRating(Integer rating) 
    {
        this.rating = rating;
    }
    
    public String getRatingInString()
    {
        if(rating == null)
        {
            return "-";
        }
        else
        {
            return rating.toString();
        }
    }

    public String getPath() 
    {
        return path;
    }

    public void setPath(String path) 
    {
        this.path = path;
    }

    public void setTime(int time)
    {
        this.time = time;
    }
    
    public int getTime() 
    {
        return time;
    }
    
    public String getTimeInString()
    {
        return TimeConverter.convertToString(time);
    }
    
    
    
}
