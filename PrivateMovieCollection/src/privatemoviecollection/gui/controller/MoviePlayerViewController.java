/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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
    
}
