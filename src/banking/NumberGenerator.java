package banking;

import java.util.Random;

public class NumberGenerator {
    final static int cardNumLength = 16;
    final static int pinLength = 4;


    static String generate() {

        Random random = new Random();
        int[] cardNumbers = new int[cardNumLength];

        //Card No 400000 and further random
        cardNumbers[0] = 4;
        for (int j = 1; j < 6; j++) {
            cardNumbers[j] = 0;
        }
        for (int i = 6; i < cardNumLength; i++) {
            cardNumbers[i] = random.nextInt(10);
        }
        //Luhn algorithm and checksum
        int checksum = 0;
        int tempCalculation;
        for (int i = 0; i < cardNumLength - 1; i++) {
            //Drop last digit:
            tempCalculation = cardNumbers[i];
            //Multiply odd digits by 2:
            if (i % 2 == 0) {
                tempCalculation *= 2;
            }
            //Subtract 9 to numbers over 9:
            if (tempCalculation > 9) {
                tempCalculation -= 9;
            }
            //Add all numbers:
            checksum += tempCalculation;
        }

        checksum %= 10;
        if (checksum != 0) {
            checksum = 10 - checksum;
        }

        cardNumbers[cardNumLength - 1] = checksum;

        StringBuilder cardNumberBuilder = new StringBuilder();
        for (int i = 0; i < cardNumLength; i++) {
            cardNumberBuilder.append(cardNumbers[i]);
        }

        return cardNumberBuilder.toString();


    }

    static String generatePin() {
        StringBuilder pinBuilder = new StringBuilder();
        Random random = new Random();

        for (int j = 0; j < pinLength; j++) {
            pinBuilder.append(random.nextInt(10));
        }
        return pinBuilder.toString();
    }

    static boolean verifyNumber(String cardNumber) {
        int[] cardNumbers = new int[cardNumLength];

        for (int j = 0; j < cardNumLength; j++) {
            char tempChar = cardNumber.charAt(j);
            cardNumbers[j] = Character.getNumericValue(tempChar);
        }

        int checksum = 0;
        int tempCalculation;
        for (int i = 0; i < cardNumLength - 1; i++) {
            //Drop last digit:
            tempCalculation = cardNumbers[i];
            //Multiply odd digits by 2:
            if (i % 2 == 0) {
                tempCalculation *= 2;
            }
            //Subtract 9 to numbers over 9:
            if (tempCalculation > 9) {
                tempCalculation -= 9;
            }
            //Add all numbers:
            checksum += tempCalculation;
        }

        checksum %= 10;
        if (checksum != 0) {
            checksum = 10 - checksum;
        }

        return cardNumbers[cardNumLength - 1] == checksum;

    }


}
