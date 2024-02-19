package lab2;

import java.util.Scanner;

public class railFence_cipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = sc.nextLine();

        System.out.print("Enter the key value: ");
        int key = sc.nextInt();

        encryption(text, key);
    }

    private static void encryption(String text, int key) {
        StringBuilder encrypted_text = new StringBuilder();

//        to change direction to down or up of zigzag pattern to put the values
        boolean direction = false;
//        variable to iterate over matrix
        int j = 0;
//        value of key which is length of text of zigzag
        int column = text.length();
//        char array to store zigzag formation
        char[][] a = new char[key][column];


        for (int i = 0; i < column; i++) {
//            if value of j reaches bottom or j == 0 change the direction
            if (j == 0 || j == key - 1) {
                direction = !direction;
            }
//            add characters of message in rail fence form of diagonal
            a[j][i] = text.charAt(i);
            if (direction) {
                j++;
            } else {
                j--;
            }
        }

//        adding characters of message to diagonals to form rail fence form
        for (int i = 0; i < key; i++) {
            for (int k = 0; k < column; k++) {
                if (a[i][k] != 0) {
                    encrypted_text.append(a[i][k]);
                }
            }
        }

        System.out.println("Encrypted text => " + encrypted_text);

        decryption(encrypted_text.toString(), key);
    }

    private static void decryption(String encrypted_text, int key) {
        StringBuilder decrypted_text = new StringBuilder();

        boolean direction = false;
        int column = encrypted_text.length();
        int j = 0;
        char[][] a = new char[key][column];

        for (int i = 0; i < column; i++) {
            if (j == 0 || j == key - 1) {
                direction = !direction;
            }
//            putting symbol of asterisk in rail fence order
            a[j][i] = '*';
            if (direction) {
                j++;
            } else {
                j--;
            }
        }

//        as direction variable is changed set it to false
        direction = false;

        int index = 0;

//        to put encrypted text in rail fence form in place of asterisk symbol
        for (int i = 0; i < key; i++) {
            for (int k = 0; k < column; k++) {
                if (a[i][k] == '*' && index < column) {
                    a[i][k] = encrypted_text.charAt(index++);
                }
            }
        }

//        as variable j is changed set it to 0
        j = 0;

        for (int i = 0; i < column; i++) {
            if (j == 0 || j == key - 1) {
                direction = !direction;
            }
            decrypted_text.append(a[j][i]);
//            changing direction variable when it is not in place of text
            if (direction) {
                j++;
            } else {
                j--;
            }
        }

        System.out.println("Decrypted text => " + decrypted_text);
    }
}
