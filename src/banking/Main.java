package banking;

import java.sql.SQLException;

public class Main {

    static String dbUrl;

    public static void main(String[] args) throws SQLException {
        dbUrl = "jdbc:sqlite:" + args[1];
        String dbQuery = "CREATE TABLE IF NOT EXISTS card (" +
                "id INTEGER PRIMARY KEY," +
                "number TEXT," +
                "pin TEXT," +
                "balance INTEGER DEFAULT 0)";
        DatabaseConnect.connectDatabase(dbUrl, dbQuery);
        MainMenu.printMenu();

    }
}


