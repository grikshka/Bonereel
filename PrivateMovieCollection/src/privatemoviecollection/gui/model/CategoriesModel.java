/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.User;
import privatemoviecollection.bll.BllManager;
import privatemoviecollection.bll.IBllFacade;

/**
 *
 * @author Acer
 */
public class CategoriesModel {
    
    private static CategoriesModel instance;
    private IBllFacade bllManager;
    private ObservableList<Category> categories;
    private static User loggedInUser;
    
    private CategoriesModel()
    {
        bllManager = new BllManager();
        categories = FXCollections.observableArrayList(bllManager.getAllCategories(loggedInUser));
    }
    
    public static CategoriesModel createInstance()
    {
        if(instance == null)
        {
            instance = new CategoriesModel();
        }
        return instance;
    }
    
    public static void setUser(User user)
    {
        loggedInUser = user;
    }
    
    public ObservableList<Category> getCategories()
    {
        return categories;
    }
    
    public boolean createCategory(String name)
    {
        if(isCategoryExisting(name))
        {
            return false;
        }
        Category createdCategory = bllManager.createCategory(loggedInUser, name);
        categories.add(createdCategory);
        return true;
    }
    
    private boolean isCategoryExisting(String name)
    {
        for(Category category : categories)
        {
            if(category.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    
    public void deleteCategory(Category category)
    {
        bllManager.deleteCategory(category);
        categories.remove(category);
    }
}
