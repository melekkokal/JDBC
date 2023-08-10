package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    static  Connection connection;
    static Statement statement;
    static ResultSet resultSet;


    private static Statement establishConnection()  {
        try {
            connection = DriverManager.getConnection(
                    getProp("connection_string"),
                    getProp("username"),
                    getProp("password")
            );
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        }catch(SQLException ex){
            throw new RuntimeException("Could not connect to databse");
        }
        return statement;

    }

    public static ResultSet queryDB(String query) throws SQLException {
        statement = establishConnection();
        try{
            resultSet = statement.executeQuery(query);
            return  resultSet;
        }catch (SQLException ex){
            throw new RuntimeException("Failed to execute query!");
        }


    }


    private static String getProp(String key){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(new File("src/test/resources/database.properties")));
        }catch (IOException ex){
            throw new RuntimeException("Could not Find a file");
        }
        return properties.getProperty(key);

    }

    public static void closeConnection() {
        //System.out.println("connection closed"); to make sure it is working you might sout

        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }


        } catch (SQLException ex) {
            ex.printStackTrace(); //your print exception. instead of typing like
                                    //the upper ones.
        }
    }


}
