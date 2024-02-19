package lab2;

import java.util.*;

// caesar cipher algorithm
public class caesar_cipher {
    public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encrypt(String input) {
        input = input.toUpperCase();

        StringBuilder encrypt = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                encrypt.append(" ");
                continue;
            }

            int pos = alphabet.indexOf(input.charAt(i));

            int encryptPos = (3 + pos) % 26;
            char encryptChar = alphabet.charAt(encryptPos);

            encrypt.append(encryptChar);
        }

        return encrypt.toString();
    }

    public static String decrypt(String input) {

        input = input.toUpperCase();

        StringBuilder decrypt = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                decrypt.append(" ");
                continue;
            }

            int pos = alphabet.indexOf(input.charAt(i));

//            int decryptPos = (pos - 3) % 26;
//
//            if (decryptPos < 0) {
//                decryptPos = alphabet.length() + decryptPos;
//            }

            // to handle wrap around automatically no need for explicit check if decryptPos < 0
            int decryptPos = (pos - 3 + 26) % 26;

            char decryptChar = alphabet.charAt(decryptPos);

            decrypt.append(decryptChar);
        }

        return decrypt.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("enter a string: ");
        String inputStr = sc.nextLine();

        System.out.println("encrypted string => " + encrypt(inputStr));
        System.out.println("decrypted string => " + decrypt(encrypt(inputStr)));

        sc.close();
    }
}
