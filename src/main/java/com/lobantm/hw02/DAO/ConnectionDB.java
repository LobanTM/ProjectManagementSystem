package com.lobantm.hw02.DAO;

import java.sql.*;

public class ConnectionDB {
    private static Connection connection;

    //private static String url = "jdbc:mysql://localhost:3306/module01";
    private static String url = "jdbc:mysql://localhost:3306/module01?autoReconnect=true&useSSL=false";
    //private static String url = "jdbc:mysql://localhost:3306/module01?useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static String user = "root";
    private static String password = "root";

    private ConnectionDB(){}

    //singleton
    static {
        try {
            connection = DriverManager.getConnection(url, user, password);
            //System.out.println("Connection DB is OK");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static  Connection getConnection(){
        return connection;
    }

    public static void closeConnection() throws SQLException {
        try {
            if (connection != null)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
