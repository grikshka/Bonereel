/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;

/**
 *
 * @author Acer
 */
public class MainViewController implements Initializable {

    @FXML
    private TableView<Movie> tblMovies;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickEditCategories(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/CategoriesView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Categories");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void clickAddMovie(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/MovieView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Movie");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void clickEditMovie(ActionEvent event) throws IOException {
        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/MovieView.fxml"));
        MovieViewController controller = fxmlLoader.getController();
        controller.setEditingMode(selectedMovie);
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Movie");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
}
