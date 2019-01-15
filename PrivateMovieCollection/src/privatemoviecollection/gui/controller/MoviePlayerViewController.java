/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import privatemoviecollection.be.Movie;
import privatemoviecollection.bll.util.TimeConverter;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class MoviePlayerViewController implements Initializable {
    
    private Movie movieToPlay;
    private MediaPlayer mediaPlayer;
    private double previousVolume;
    private boolean movieTimeChanged = false;
    private double xOffset;
    private double yOffset;
    
    @FXML
    private MediaView mdvPlayer;
    @FXML
    private Slider sldTime;
    @FXML
    private Slider sldVolume;
    @FXML
    private ToggleButton btnMute;
    @FXML
    private Label lblMovieCurrentTime;
    @FXML
    private Label lblMovieEndTime;
    @FXML
    private ToggleButton btnPlay;
    @FXML
    private Button btnClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblSlash;
    @FXML
    private Rectangle rctBottom;
    @FXML
    private Rectangle rctTop;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createVolumeSliderListener();
        createTimeSliderListener();
    }   
    
    private void createTimeSliderListener()
    {
        sldTime.valueProperty().addListener(new ChangeListener()
            {
                @Override
                public void changed(ObservableValue arg0, Object arg1, Object arg2)
                {
                    lblMovieCurrentTime.setText(TimeConverter.convertToString((int)sldTime.getValue()));
                }
            }     
        );
    }
    
    private void createVolumeSliderListener()
    {
        sldVolume.valueProperty().addListener(new ChangeListener()
            {
                @Override
                public void changed(ObservableValue arg0, Object arg1, Object arg2)
                {
                    if(btnMute.isSelected() && sldVolume.getValue() != 0)
                    {
                        btnMute.setSelected(false);
                    }
                    else if(!btnMute.isSelected() && sldVolume.getValue() == 0)
                    {
                        previousVolume=0;
                        btnMute.setSelected(true);
                    }
                    if(mediaPlayer != null)
                    {
                        mediaPlayer.setVolume(sldVolume.getValue());
                    }
                }
            }     
        );
    }
    
    public void playMovie(Movie movie)
    {
        movieToPlay = movie;
        File file = new File(movie.getPath());
        Media mediaFile = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(mediaFile);
        setTimeListener();
        sldTime.setMax(movie.getTime());
        lblTitle.setText(movie.getTitle());
        lblMovieEndTime.setText(movie.getTimeInString());
        mdvPlayer.setMediaPlayer(mediaPlayer);
        mediaPlayer.setVolume(sldVolume.getValue());
        mediaPlayer.play();
    }
    
    private void setTimeListener()
    {
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>()
            {
                @Override
                public void changed(ObservableValue arg0, Duration arg1, Duration arg2)
                {
                    if(!sldTime.isPressed() && !movieTimeChanged)
                    {
                        sldTime.setValue(arg2.toSeconds());
                    }
                    else if(movieTimeChanged)
                        movieTimeChanged=false;
                }
            }
        );
    }

    @FXML
    private void clickPlay(ActionEvent event) 
    {
        if(mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))
        {
            mediaPlayer.pause();
        }
        else
        {
            mediaPlayer.play();
        }
    }

    @FXML
    private void clickMute(ActionEvent event) {
        if(btnMute.isSelected())
        {
            previousVolume = sldVolume.getValue();
            sldVolume.setValue(0);
        }
        else
        {
            if(sldVolume.getValue() == 0)
            {
                btnMute.setSelected(true);
            }
            sldVolume.setValue(previousVolume);
        }
    }

    @FXML
    private void dropTimeSlider(MouseEvent event) {
        mediaPlayer.seek(Duration.seconds(sldTime.getValue()));
        movieTimeChanged=true;
    }

    @FXML
    private void clickClose(ActionEvent event) {
        mediaPlayer.stop();
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void hoverOnHud(MouseEvent event) {
        sldTime.setVisible(true);
        sldVolume.setVisible(true);
        btnMute.setVisible(true);
        btnPlay.setVisible(true);
        lblMovieCurrentTime.setVisible(true);
        lblMovieEndTime.setVisible(true);
        lblTitle.setVisible(true);
        lblSlash.setVisible(true);
        rctTop.setVisible(true);
        rctBottom.setVisible(true);

    }

    @FXML
    private void hoverOffHud(MouseEvent event) {
        if(!mediaPlayer.getStatus().equals(MediaPlayer.Status.PAUSED))
        {
            sldTime.setVisible(false);
            sldVolume.setVisible(false);
            btnMute.setVisible(false);
            btnPlay.setVisible(false);
            lblMovieCurrentTime.setVisible(false);
            lblMovieEndTime.setVisible(false);
            lblTitle.setVisible(false);
            lblSlash.setVisible(false);
            rctTop.setVisible(false);
            rctBottom.setVisible(false);
        }
    }

    @FXML
    private void clickOnMediaView(MouseEvent event) 
    {
        if(mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))
        {
            mediaPlayer.pause();
            //change play button image
            sldTime.setVisible(true);
            sldVolume.setVisible(true);
            btnMute.setVisible(true);
            btnPlay.setVisible(true);
            lblMovieCurrentTime.setVisible(true);
            lblMovieEndTime.setVisible(true);
            lblTitle.setVisible(true);
            lblSlash.setVisible(true);
            rctTop.setVisible(true);
            rctBottom.setVisible(true);
        }
        else
        {
            mediaPlayer.play();
            //change play button image
            sldTime.setVisible(false);
            sldVolume.setVisible(false);
            btnMute.setVisible(false);
            btnPlay.setVisible(false);
            lblMovieCurrentTime.setVisible(false);
            lblMovieEndTime.setVisible(false);
            lblTitle.setVisible(false);
            lblSlash.setVisible(false);
            rctTop.setVisible(false);
            rctBottom.setVisible(false);
        }
    }

    @FXML
    private void clickMouseDragged(MouseEvent event) {
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }

    @FXML
    private void clickMousePressed(MouseEvent event) {
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }

    
}
