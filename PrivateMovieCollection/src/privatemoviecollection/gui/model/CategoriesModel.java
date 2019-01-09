/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.Category;
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
    
    private CategoriesModel()
    {
        bllManager = new BllManager();
        categories = FXCollections.observableArrayList(bllManager.getAllCategories());
    }
    
    public static CategoriesModel createInstance()
    {
        if(instance == null)
        {
            instance = new CategoriesModel();
        }
        return instance;
    }
    
    public ObservableList<Category> getCategories()
    {
        return categories;
    }
    
    public void createCategory(String name)
    {
        Category createdCategory = bllManager.createCategory(name);
        categories.add(createdCategory);
    }
    
    public void deleteCategory(Category category)
    {
        bllManager.deleteCategory(category);
        categories.remove(category);
    }
}
