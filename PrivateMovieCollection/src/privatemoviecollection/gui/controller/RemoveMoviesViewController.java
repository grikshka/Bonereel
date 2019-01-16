/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;
import privatemoviecollection.gui.exceptions.ModelException;
import privatemoviecollection.gui.model.MainModel;
import privatemoviecollection.gui.util.WarningDisplayer;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class RemoveMoviesViewController implements Initializable {
    
    private MainModel model;
    private WarningDisplayer warningDisplayer;

    @FXML
    private TableView<Movie> tblMovies;
    @FXML
    private TableColumn<Movie, String> colTitle;
    @FXML
    private TableColumn<Movie, String> colCategories;
    @FXML
    private TableColumn<Movie, Integer> colTime;
    @FXML
    private TableColumn<Movie, String> colRating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        warningDisplayer = new WarningDisplayer();
        try 
        {
            model = MainModel.createInstance();
        } 
        catch (ModelException e) 
        {
            Stage currentStage = (Stage) tblMovies.getScene().getWindow();
            warningDisplayer.displayError(currentStage, "Error", e.getMessage());
        }
        loadData();
    }    
    
    private void loadData()
    {
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colCategories.setCellValueFactory(new PropertyValueFactory("categoriesInString"));
        colTime.setCellValueFactory(new PropertyValueFactory("timeInString"));
        colRating.setCellValueFactory(new PropertyValueFactory("ratingInString"));
        tblMovies.setItems(model.getMoviesToDelete());
    }

    @FXML
    private void clickCancel(ActionEvent event) {
        Stage currentStage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void clickDelete(ActionEvent event) {
    }

    @FXML
    private void clickOnMovies(MouseEvent event) {
    }

    @FXML
    private void clickClose(ActionEvent event) {
        Stage currentStage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        currentStage.close();
    }
    
}
