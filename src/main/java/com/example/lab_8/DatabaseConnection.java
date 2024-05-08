package com.example.lab_8;
//package com.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
   public Connection link;
    public Connection getConnection(){
        String dbname = "pokedex";
        String user = "root";
        String pass = "1234";

        String url = "jdbc:mysql://localhost/"+dbname;

        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           link = DriverManager.getConnection(url, user, pass);
        } catch (Exception e){
            e.printStackTrace();
        }

        return link;
    }
}
