/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.gui.model.CategoriesModel;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class CategoriesViewController implements Initializable {

    private CategoriesModel model;
    @FXML
    private Button btnDelete;
    
    public CategoriesViewController()
    {
        model = CategoriesModel.createInstance();
    }
    
    @FXML
    private ListView<Category> lstCategories;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstCategories.setItems(model.getCategories());
        btnDelete.setDisable(true);
    }    

    @FXML
    private void clickNewCategory(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/NewCategoryView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("New Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void clickOnCategories(MouseEvent event) {
        if(lstCategories.getSelectionModel().getSelectedItem() != null)
        {
            btnDelete.setDisable(false);
        }
    }

    @FXML
    private void clickDelete(ActionEvent event) 
    {
        Category selectedCategory = lstCategories.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete \"" + selectedCategory.getName() + " from your categories?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get() == ButtonType.OK)
        {
            model.deleteCategory(selectedCategory);
        }
        lstCategories.getSelectionModel().clearSelection();
        btnDelete.setDisable(true);
    }

    @FXML
    private void clickClose(ActionEvent event) {
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
    
}
