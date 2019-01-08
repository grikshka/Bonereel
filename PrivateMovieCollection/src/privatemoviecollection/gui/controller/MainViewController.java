/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;
import privatemoviecollection.gui.model.MainModel;

/**
 *
 * @author Acer
 */
public class MainViewController implements Initializable {
    
    private MainModel model;

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
    @FXML
    private Button btnRemoveMovie;
    @FXML
    private Button btnEditMovie;
    
    public MainViewController()
    {
        model = new MainModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disableElements();
        loadData();
    }    
    
    private void disableElements()
    {
        btnEditMovie.setDisable(true);
        btnRemoveMovie.setDisable(true);
    }
    
    private void loadData()
    {
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));
        colCategories.setCellValueFactory(new PropertyValueFactory("categoriesInString"));
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        colRating.setCellValueFactory(new PropertyValueFactory("ratingInString"));
        tblMovies.setItems(model.getMovies());
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
        Parent root = (Parent) fxmlLoader.load();
        MovieViewController controller = (MovieViewController) fxmlLoader.getController();
        controller.setEditingMode(selectedMovie);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Movie");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void clickOnMovies(MouseEvent event) {
        if(tblMovies.getSelectionModel().getSelectedItem() != null)
        {
            btnEditMovie.setDisable(false);
            btnRemoveMovie.setDisable(false);
        }
    }

    @FXML
    private void clickRemoveMovie(ActionEvent event) {
        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete \"" + selectedMovie.getTitle() + " from your movies?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get() == ButtonType.OK)
        {
            model.deleteMovie(selectedMovie);
        }
    }
    
}
