package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class UpscalePage implements isDisliked, isLiked, Initializable {

    public TableView<Songs> songsList;
    public TableColumn<Songs, String> songName;
    public TableColumn<Songs, String> artistName;
    public TableColumn<Songs, String> albumName;
    public ObservableList<Songs> listed;
    public TableColumn<Songs, Integer> idSong;
    public Button like;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private final ObservableList<String> items = FXCollections.observableArrayList();
    public Button shrinkButton;
    public Button dislike;
    public ProgressBar progressBarv2;

    public void switchToRadio(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void liked(){
    addToLiked();
    }
    public void disliked(){
        addToDisliked();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    idSong.setCellValueFactory(new PropertyValueFactory<Songs, Integer>("id"));
    songName.setCellValueFactory(new PropertyValueFactory<Songs, String>("nameSong"));
    artistName.setCellValueFactory(new PropertyValueFactory<Songs, String>("artistName"));
    albumName.setCellValueFactory(new PropertyValueFactory<Songs, String>("albumName"));
    listed = Connect.getDataSongs();
    songsList.setItems(listed);
    TableView.TableViewSelectionModel<Songs> selectionModel =
                songsList.getSelectionModel();
    selectionModel.setSelectionMode(SelectionMode.SINGLE);
    ObservableList<Songs> selectedItems = selectionModel.getSelectedItems();
    }

    @Override
    public void addToLiked() {
        conn = Connect.Getconnection();
        String sql = "insert ignore into favorites (idfavorite, namefavorite,artistfavorite, albumfavorite) values (?,?,?,?)";
        try{
            assert conn != null;
            pst = conn.prepareStatement(sql);
            ObservableList<Songs> selectedRow,allRows;
            allRows = songsList.getItems();
            selectedRow = songsList.getSelectionModel().getSelectedItems();
            for (Songs songs : selectedRow) {

                pst.setString(1, String.valueOf(songs.getId()));
                pst.setString(2, songs.getNameSong());
                pst.setString(3, songs.getArtistName());
                pst.setString(4, songs.getAlbumName());

                pst.execute();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addToDisliked() {
        ObservableList<Songs> selectedRow,allRows;
        allRows = songsList.getItems();
        selectedRow = songsList.getSelectionModel().getSelectedItems();
        selectedRow.forEach(allRows :: remove);
    }
}
