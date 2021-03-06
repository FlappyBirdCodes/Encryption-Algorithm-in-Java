// Name: Oscar Law
// Date: December 1st, 2020
// Description: A program that allows users to encrypt and decrypt messages in files

import java.util.*;
import java.io.*;

public class EncryptionProject {
    public static String encrypt_decrypt(String letter, String toEncryptDecrypt) {

        // Arrays that indicate the key-value pairs between decrypted and encrypted letters/symbols
        String[] decrypted = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", " ", ".", ",", "-"};
        String[] encrypted = {"b", "d", "r", "p", "o", "v", "z", "m", "x", "h", "s", "l", "u", "y", "t", "f", "n", "k", "g", "j", "q", "c", "a", "i", "w", "e", "3", "0", "5", "2", "9", "4", "7", "6", "1", "8", ",", "-", ".", " "};
        
        // Execeutes if user chooses to encrypt a message
        if (toEncryptDecrypt.equals("1")) {
            int letter_index = Arrays.asList(decrypted).indexOf(letter);
            
            // Returns the same letter/symbol if it is not found in the array
            if (letter_index == -1)
                return letter;
            return encrypted[letter_index];
        }

        // Execeutes if user chooses to decrypt a message
        else {
            int letter_index = Arrays.asList(encrypted).indexOf(letter);
            if (letter_index == -1)
                return letter;
            return decrypted[letter_index];
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Encryption System.");
        System.out.println("What would you like to do?");
        System.out.println("Enter 1 for Encrypt, 2 for Decrypt:");

        String toEncryptDecrypt = new Scanner(System.in).nextLine();
        // Reasks for input if input is not either 1 or 2
        while (!toEncryptDecrypt.equals("1") && !toEncryptDecrypt.equals("2")) {
            System.out.println("Invalid input. Please try again and enter either 1 or 2.");
            toEncryptDecrypt = new Scanner(System.in).nextLine();
        }

        // Finds the text file given the name
        System.out.println("Enter the file name:");
        String file_name = new Scanner(System.in).nextLine();
        File text_file = new File(file_name + ".txt");
        boolean file_exists = text_file.exists();

        // Reasks for input if file name is not found
        while (file_exists == false) {
            System.out.println("Sorry, this file name does not exist. Please try again:");
            file_name = new Scanner(System.in).nextLine();
            text_file = new File(file_name + ".txt");
            file_exists = text_file.exists();
        }

        // Sets filename depending on whether user decides to encrypt or decrypt
        FileWriter new_file;
        String action;
        if (toEncryptDecrypt.equals("1")) {
            new_file = new FileWriter("enc.txt");
            action = "Encryption";
        }
        else {
            new_file = new FileWriter("dec.txt");
            action = "Decryption";
        }

        Scanner read_file = new Scanner(text_file);
        while (read_file.hasNextLine()) {

            // Reads each line of the text file
            String each_line = read_file.nextLine();
            each_line = each_line.toLowerCase();
            String[] arr_line = each_line.split("");

            // Encrypts each letter in the string and adds to new string
            String new_string = "";
            for (int i=0; i<arr_line.length; i++) {
                String letter = arr_line[i];
                new_string += encrypt_decrypt(letter, toEncryptDecrypt);
            }

            new_file.write(new_string + "\n");
        }
        System.out.println(action + " completed");
        new_file.close();
        read_file.close();
    }
}
