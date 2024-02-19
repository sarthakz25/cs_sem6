package lab3;

import java.util.Scanner;

public class hill_cipher {

    static Scanner userInput = new Scanner(System.in);

    static void encrypt(String message) {
        message = message.toUpperCase();
        // key matrix
        System.out.println("enter key matrix size -");
        int matrixSize = userInput.nextInt();
        System.out.println("enter the key matrix -");
        int[][] keyMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                keyMatrix[i][j] = userInput.nextInt();
            }
        }
        // to check if det = 0
        validateDeterminant(keyMatrix, matrixSize);

        int[][] messageVector = new int[matrixSize][1];
        String CipherText = "";
        int[][] cipherMatrix = new int[matrixSize][1];
        int j = 0;
        while (j < message.length()) {
            for (int i = 0; i < matrixSize; i++) {
                if (j >= message.length()) {
                    messageVector[i][0] = 23;
                } else {
                    messageVector[i][0] = (message.charAt(j)) % 65;
                }
                System.out.println(messageVector[i][0]);
                j++;
            }
            int x, i;
            for (i = 0; i < matrixSize; i++) {
                cipherMatrix[i][0] = 0;

                for (x = 0; x < matrixSize; x++) {
                    cipherMatrix[i][0] += keyMatrix[i][x] * messageVector[x][0];
                }
                System.out.println(cipherMatrix[i][0]);
                cipherMatrix[i][0] = cipherMatrix[i][0] % 26;
            }
            for (i = 0; i < matrixSize; i++) {
                CipherText += (char) (cipherMatrix[i][0] + 65);
            }
        }
        System.out.println("Ciphertext => " + CipherText);
    }

    static void decrypt(String message) {
        message = message.toUpperCase();
        // key matrix
        System.out.println("enter key matrix size -");
        int n = userInput.nextInt();
        System.out.println("enter inverse key matrix -");
        int[][] keyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                keyMatrix[i][j] = userInput.nextInt();
            }
        }
        validateDeterminant(keyMatrix, n);

        // solving for required plaintext message
        int[][] messageVector = new int[n][1];
        String PlainText = "";
        int[][] plainMatrix = new int[n][1];
        int j = 0;
        while (j < message.length()) {
            for (int i = 0; i < n; i++) {
                if (j >= message.length()) {
                    messageVector[i][0] = 23;
                } else {
                    messageVector[i][0] = (message.charAt(j)) % 65;
                }
                System.out.println(messageVector[i][0]);
                j++;
            }
            int x, i;
            for (i = 0; i < n; i++) {
                plainMatrix[i][0] = 0;

                for (x = 0; x < n; x++) {
                    plainMatrix[i][0] += keyMatrix[i][x] * messageVector[x][0];
                }

                plainMatrix[i][0] = plainMatrix[i][0] % 26;
            }
            for (i = 0; i < n; i++) {
                PlainText += (char) (plainMatrix[i][0] + 65);
            }
        }
        System.out.println("plaintext => " + PlainText);
    }

    public static int determinant(int[][] a, int n) {
        int det = 0, sign = 1, p = 0, q = 0;

        if (n == 1) {
            det = a[0][0];
        } else {
            int[][] b = new int[n - 1][n - 1];
            for (int x = 0; x < n; x++) {
                p = 0;
                q = 0;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (j != x) {
                            b[p][q++] = a[i][j];
                            if (q % (n - 1) == 0) {
                                p++;
                                q = 0;
                            }
                        }
                    }
                }
                det = det + a[0][x] * determinant(b, n - 1) * sign;
                sign = -sign;
            }
        }
        return det;
    }

    static void hillCipher(String message) {
        message = message.toUpperCase().replaceAll("\\s", "");
        System.out.println("choose an option:");
        System.out.println("1: encrypt");
        System.out.println("2: decrypt");
        int choice = userInput.nextInt();
        userInput.nextLine();

        switch (choice) {
            case 1:
                encrypt(message);
                break;
            case 2:
                decrypt(message);
                break;
            default:
                System.out.println("invalid choice!");
        }
    }

    static void validateDeterminant(int[][] keyMatrix, int n) {
        if (determinant(keyMatrix, n) % 26 == 0) {
            System.out.println("invalid key determinant cannot be 0");
        }
    }

    public static void main(String[] args) {
        System.out.println("enter the message: ");
        String message = userInput.nextLine();
        hillCipher(message);
    }
}
