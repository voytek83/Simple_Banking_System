package banking;

import java.sql.*;

public class DatabaseConnect {

    public static void connectDatabase(String dbUrl, String updateString) throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(dbUrl);
        Statement statement = conn.createStatement();
        statement.executeUpdate(updateString);
        conn.close();

    }

    public static String readDatabaseString(String dbUrl, String readString, String label) throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(dbUrl);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(readString);
        String temp = resultSet.getString(label);
        conn.close();
        return temp;

    }

    public static Integer readDatabaseInteger(String dbUrl, String readString, String label) throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(dbUrl);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(readString);
        Integer temp = resultSet.getInt(label);
        conn.close();
        return temp;
    }

    public static boolean readDatabaseBoolean(String dbUrl, String readString) throws SQLException {
        Connection conn;
        conn = DriverManager.getConnection(dbUrl);
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(readString);
        boolean temp = resultSet.getBoolean(1);
        conn.close();
        return temp;
    }

}
