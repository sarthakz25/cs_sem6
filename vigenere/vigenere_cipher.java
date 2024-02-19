package lab4;

import java.util.Scanner;

public class vigenere_cipher {

    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        System.out.println("enter the plaintext:");
        String plaintext = sc.nextLine();
        System.out.println("enter the keyword:");
        String keyword = sc.nextLine();

        encryptDecrypt(plaintext, keyword);
    }

    public static void encryptDecrypt(String plaintext, String keyword) {
        String upperCasePlaintext = plaintext.toUpperCase();
        String upperCaseKeyword = keyword.toUpperCase();

        char[] msg = upperCasePlaintext.toCharArray();
        char[] key = new char[msg.length];
        char[] encryptedMsg = new char[msg.length];
        char[] decryptedMsg = new char[msg.length];

        // to generate key in cyclic manner equal to length of plaintext
        for (int i = 0, j = 0; i < msg.length; ++i) {
            if (j == upperCaseKeyword.length()) {
                j = 0;
            }
            // to only operate on letters and ignore other characters
            if (Character.isLetter(msg[i])) {
                key[i] = upperCaseKeyword.charAt(j);
                j++; // to only increment j for letters
            } else {
                key[i] = msg[i]; // if non letter character just copy to key
            }
        }

        // encryption
        for (int i = 0; i < msg.length; ++i) {
            if (Character.isLetter(msg[i])) {
                int offset = (msg[i] - 'A');
                encryptedMsg[i] = (char) (((key[i] - 'A' + offset) % 26) + 'A');
            } else {
                encryptedMsg[i] = msg[i]; // for non letter characters just copy directly
            }
        }

        // decryption
        for (int i = 0; i < msg.length; ++i) {
            if (Character.isLetter(encryptedMsg[i])) {
                int offset = (encryptedMsg[i] - key[i] + 26) % 26;
                decryptedMsg[i] = (char) (offset + 'A');
            } else {
                decryptedMsg[i] = encryptedMsg[i];
            }
        }

        System.out.println("plaintext == " + plaintext);
        System.out.println("keyword == " + keyword);
        System.out.println("\ngenerated key == " + String.valueOf(key));
        System.out.println("\nencrypted message == " + String.valueOf(encryptedMsg));
        System.out.println("decrypted message == " + String.valueOf(decryptedMsg));
    }
}
