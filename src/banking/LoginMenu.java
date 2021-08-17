package banking;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginMenu {

    static void loginMenu(String cardNumber) throws SQLException {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");


        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch ( choice ) {
            case 1 ->                         //  Balance
                    Account.getBalance(cardNumber);
            case 2 ->                         //  Add income
                    Account.addIncome(cardNumber);
            case 3 ->                         //  Do transfer
                    Account.doTransfer(cardNumber);
            case 4 ->                         //  Close account
                    Account.closeAccount(cardNumber);
            case 5 ->                         //  Log out
                    MainMenu.printMenu();
            case 0 ->                         //  Exit
                    System.out.println("Bye!");
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}
