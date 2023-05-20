package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Label songLabel;
    @FXML
    private Button playButton, pauseButton, restartButton, previousButton, nextButton,closeButton;
    @FXML
    private Slider volumeBar;

    public ProgressBar getSongProgressBar() {
        return songProgressBar;
    }

    @FXML
    private ProgressBar songProgressBar;

    private Media media;
    private MediaPlayer mediaPlayer;

    private ArrayList< File > songs;

    private int songNumber;

    private Timer timer;
    private TimerTask task;

    private boolean running;
    private Parent root;
    private Scene scene;
    private Stage stage;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) throws IndexOutOfBoundsException {

        songs = new ArrayList<>();
//remember to change the path when testing this, the folder(music) is in the project.
        File directory = new File("C:\\Users\\vulpe\\Desktop\\Stuff\\P3 Project-MusicPlayer\\src\\music");

        File[] files = directory.listFiles();

        if ( files != null ) {

            Collections.addAll(songs, files);
        }
        songLabel.setText(songs.get(songNumber).getName());
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        volumeBar.setValue(mediaPlayer.getVolume() * 600);
        volumeBar.valueProperty().addListener(observable -> mediaPlayer.setVolume(volumeBar.getValue() / 600));
        songProgressBar.setStyle("-fx-accent: #ff85a5;");
    }
    public void beginTimer() {

        timer = new Timer();

        task = new TimerTask() {

            public void run() {

                boolean running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current / end);

                if ( current / end == 1 ) {

                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {

        running = false;
        timer.cancel();
    }

    public void playMedia() {

        beginTimer();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }

    public void pauseMedia() {

        cancelTimer();
        mediaPlayer.pause();
    }

    public void restartMedia() {

        songProgressBar.setProgress(0);
        mediaPlayer.seek(Duration.seconds(0));
    }

    public void previousMedia() {

        if ( songNumber > 0 ) {

            songNumber--;

        } else {

            songNumber = songs.size() - 1;

        }
        mediaPlayer.stop();
        if ( running ) {

            cancelTimer();
        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());
        playMedia();
    }

    public void nextMedia() {

        if ( songNumber < songs.size() - 1 ) {

            songNumber++;

            mediaPlayer.stop();

            if ( running ) {

                cancelTimer();
            }

        } else {

            songNumber = 0;

            mediaPlayer.stop();

        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());
        playMedia();
    }

    public void exit(){
        System.exit(0);
    }

    public void switchToUpscale(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("bigView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
