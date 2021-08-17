package banking;

import java.sql.*;
import java.util.Scanner;

public class Account {

    static void createAccount() throws SQLException {
        System.out.println();
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        String cardNumber = NumberGenerator.generate();
        String pin = NumberGenerator.generatePin();
        System.out.println(cardNumber);
        System.out.println("Your card PIN:");
        System.out.println(pin);
        System.out.println();
        String dbQuery = "INSERT INTO card (number, pin, balance) VALUES ( '" + cardNumber + "', '" + pin + "', " + 0 + ");";
        DatabaseConnect.connectDatabase(Main.dbUrl, dbQuery);
        MainMenu.printMenu();
    }

    static void logAccount() throws SQLException {
        String pin;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your card number:");
        String loginCardNumber = scanner.next();
        System.out.println(loginCardNumber);
        System.out.println("Enter your PIN:");
        String loginPin = scanner.next();

        try {
            String queryStringPin = "SELECT pin FROM card WHERE number = " + loginCardNumber + ";";
            pin = DatabaseConnect.readDatabaseString(Main.dbUrl, queryStringPin, "pin");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            pin = null;
        }


        if (loginPin.equals(pin)) {
            System.out.println("You have successfully logged in!");
            System.out.println();
            LoginMenu.loginMenu(loginCardNumber);
        } else {
            System.out.println("Wrong card number or PIN!");
            MainMenu.printMenu();
        }

    }

    public static void addIncome(String cardNumber) throws SQLException {
        System.out.println("Enter income:");
        Scanner scanner = new Scanner(System.in);
        int addToBalance = scanner.nextInt();
        String dbQuery = "SELECT balance FROM card WHERE number = " + cardNumber + " ;";
        int balance = DatabaseConnect.readDatabaseInteger(Main.dbUrl, dbQuery, "balance");
        addToBalance += balance;
        String adIncome = "UPDATE card SET balance = " + addToBalance + " WHERE number = " + cardNumber + " ;";
        DatabaseConnect.connectDatabase(Main.dbUrl, adIncome);
        System.out.println("Income was added!");
        LoginMenu.loginMenu(cardNumber);

    }

    public static void doTransfer(String cardNumber) throws SQLException {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        Scanner scanner = new Scanner(System.in);
        String cardToTransfer = scanner.next();
        String dbQuery2 = "SELECT EXISTS(SELECT number FROM card WHERE number= '" + cardToTransfer + "') ;";
        boolean cardToTransferExists = DatabaseConnect.readDatabaseBoolean(Main.dbUrl, dbQuery2);
        System.out.println(cardToTransferExists);
        if (!NumberGenerator.verifyNumber(cardToTransfer)) {

            System.out.println("Probably you made a mistake in the card number. Please try again!");
            LoginMenu.loginMenu(cardNumber);

        } else if (cardToTransferExists) {

            System.out.println("Enter how much money you want to transfer:");
            int amountToTransfer = scanner.nextInt();
            String dbQuery = "SELECT balance FROM card WHERE number = " + cardNumber + ";";
            int balance = DatabaseConnect.readDatabaseInteger(Main.dbUrl, dbQuery, "balance");

            if (amountToTransfer > balance) {
                System.out.println("Not enough money!");
                LoginMenu.loginMenu(cardNumber);
            } else {
                String dbQuery3 = "SELECT balance FROM card WHERE number = '" + cardToTransfer + "' ;";
                int balanceToTransfer = DatabaseConnect.readDatabaseInteger(Main.dbUrl, dbQuery3, "balance");
                int amountSubtracted = balance - amountToTransfer;
                int amountAdded = amountToTransfer + balanceToTransfer;

                String subtract = "UPDATE card SET balance = " + amountSubtracted + " WHERE number = " + cardNumber + " ;";
                String addAmount = "UPDATE card SET balance = " + amountAdded + " WHERE number = " + cardToTransfer + " ;";

                DatabaseConnect.connectDatabase(Main.dbUrl, subtract);
                DatabaseConnect.connectDatabase(Main.dbUrl, addAmount);

                System.out.println("Success!");
                LoginMenu.loginMenu(cardNumber);
            }

        } else {

            System.out.println("Such a card does not exist.");
            LoginMenu.loginMenu(cardNumber);
        }

    }

    public static void closeAccount(String cardNumber) throws SQLException {
        String delAccount = "DELETE FROM card WHERE number = " + cardNumber + " ;";
        DatabaseConnect.connectDatabase(Main.dbUrl, delAccount);
        MainMenu.printMenu();
    }

    public static void getBalance(String cardNumber) throws SQLException {
        String dbQuery = "SELECT balance FROM card WHERE number = " + cardNumber + ";";
        int balance = DatabaseConnect.readDatabaseInteger(Main.dbUrl, dbQuery, "balance");
        System.out.println("Balance: " + balance);
        LoginMenu.loginMenu(cardNumber);
    }
}

