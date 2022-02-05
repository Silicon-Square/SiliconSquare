package it.siliconsquare.common;

import java.util.Random;

public class PasswordGenerator {

    /**
     * Generate a random password including symbols, capital letters, small letters, numbers.
     *
     * @param length the number of characters of the password
     * @return plain random password (NOT encrypted)
     */
    public static String generatePassword(int length) {
        String symbols = "-/.^&*_!@%=+>)";
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String finalString = capitalLetters + smallLetters + numbers + symbols;

        Random random = new Random(System.currentTimeMillis());
        char[] password = new char[length];

        for (int i = 0; i < length; i++)
            password[i] = finalString.charAt(random.nextInt(finalString.length()));

        return String.valueOf(password);
    }

}
