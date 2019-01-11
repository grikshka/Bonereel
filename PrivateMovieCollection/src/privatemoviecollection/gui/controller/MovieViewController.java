/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.gui.model.CategoriesModel;
import privatemoviecollection.gui.model.MainModel;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class MovieViewController implements Initializable {
    
    private MainModel mainModel;
    private CategoriesModel categoriesModel;
    
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtFile;
    @FXML
    private TextField txtTime;
    @FXML
    private ComboBox<String> cmbRating;
    @FXML
    private ListView<Category> lstSelectedCategories;
    @FXML
    private ComboBox<Category> cmbCategories;

    public MovieViewController()
    {
        mainModel = MainModel.createInstance();
        categoriesModel = CategoriesModel.createInstance();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbCategories.setItems(categoriesModel.getCategories());
        createRatingCombo();
    }   
    
    private void createRatingCombo()
    {
        ObservableList<String> ratings = FXCollections.observableArrayList();
        ratings.add("-");
        for(int i = 1; i <= 10; i++) 
        {
            ratings.add(Integer.toString(i));          
        }
        cmbRating.setItems(ratings);
        
    }
    
    public void setEditingMode(Movie editingMovie)
    {
        //TO DO
    }

    @FXML
    private void clickSave(ActionEvent event) 
    {
        //TO DO
    }

    @FXML
    private void clickChooseFilePath(ActionEvent event) {
        FileChooser fileChooser = createMovieChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null)
        {
            txtFile.setText(selectedFile.getPath());
            setTimeField(selectedFile);
        }
    }
    
    private FileChooser createMovieChooser()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a movie");
        FileChooser.ExtensionFilter generalFilter = new FileChooser.ExtensionFilter("All Video Files", "*.mpeg4", "*.mp4", "*.m4a", "*.m4v");
        FileChooser.ExtensionFilter mpeg4Filter = new FileChooser.ExtensionFilter("MPEG4 (*.mpeg4)", "*.mpeg4");
        FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 (*.mp4, *.m4a, *.m4v)","*.mp4", "*.m4a", "*.m4v");
        fileChooser.getExtensionFilters().add(generalFilter);
        fileChooser.getExtensionFilters().add(mpeg4Filter);
        fileChooser.getExtensionFilters().add(mp4Filter);       
        return fileChooser;
    }
    
    public void setTimeField(File selectedFile)
    {
        Media mediaFile = new Media(selectedFile.toURI().toString());
        MediaPlayer player = new MediaPlayer(mediaFile);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                int time = (int) player.getMedia().getDuration().toSeconds();
                txtTime.setText(Integer.toString(time));
            }
            
        };
        scheduler.schedule(task, 200, TimeUnit.MILLISECONDS);
        scheduler.shutdown();
    }

    @FXML
    private void clickSelectCategory(ActionEvent event) 
    {
        Category selectedCategory = cmbCategories.getSelectionModel().getSelectedItem();
        if(!lstSelectedCategories.getItems().contains(selectedCategory))
        {
            lstSelectedCategories.getItems().add(selectedCategory);
        }
    }
    
}
