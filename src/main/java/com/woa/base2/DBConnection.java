package com.woa.base2;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DBConnection {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void getData(String query) throws SQLException, IOException {
        ResultSet rs = DBConnection.getResultSet(query);
        ArrayList <String> bookNamesList = new ArrayList<>();
        while (rs.next()){
            bookNamesList.add(rs.getString("productName"));}

    }
    public static ResultSet getResultSet(String query) throws IOException, SQLException {
        String url = PropertiesFile.getInfo("db.url");
        String username = PropertiesFile.getInfo("db.username");
        String password = PropertiesFile.getInfo("db.password");

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }


//    public static ArrayList<String> getDataFromDB(){
//        try {
//            connection = DriverManager.getConnection(getInfo("db.url"), getInfo("db.username"), getInfo("db.password"));
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM classicmodels.products");
//        } catch (SQLException | IOException e) {
//            System.out.println("no connection");
//        }
//        ArrayList <String> data = new ArrayList<>();
//        return new ArrayList<>();
//    }
    public static String getInfo( String value) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/config.properties"));

        String info = properties.getProperty(value);

        return info;
    }

}