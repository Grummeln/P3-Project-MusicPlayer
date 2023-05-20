package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
public class Connect {
public static Connection Getconnection(){
        try{
            Connection conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata","root","root");
            return conn;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return null;
    }

public static ObservableList<Songs> getDataSongs(){
    Connection conn = Getconnection();
    ObservableList<Songs> listSongs = FXCollections.observableArrayList();
    try {
        PreparedStatement ps = conn.prepareStatement("select * from music");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            listSongs.add(new Songs(Integer.parseInt(rs.getString("idMusic")), rs.getString("songName"), rs.getString("artistName"), rs.getString("albumName")));

        }
    }
    catch (Exception e){

    }
    return listSongs;

}


}