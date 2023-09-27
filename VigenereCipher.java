package Java_Encryption;
import java.util.Scanner;

public class VigenereCipher {

    public static void main(String arg[]){
        Scanner input = new Scanner(System.in);
        String plaintext;
        String keyword;

        System.out.println("Enter plaintext: ");
        plaintext = input.nextLine();

        System.out.println("Enter Key: ");
        keyword = input.next();

        encrypt(plaintext, keyword);
    }

    public static void encrypt(String plaintext, String keyword) {
        // Converting plaintext to char array
        char msg[] = plaintext.toCharArray();
        int msgLen = msg.length;
        int i, j;

        // Creating new char arrays
        char key[] = new char[msgLen];
        char encryptedMsg[] = new char[msgLen];

        // Generate key, using the keyword in cyclic manner equal to the length of the original message
        for (i = 0, j = 0; i < msgLen; ++i, ++j) {
            if (j == keyword.length()) {
                j = 0;
            }
            key[i] = keyword.charAt(j);
        }

        // Encryption code
        for (i = 0; i < msgLen; ++i) {
            char plaintextChar = msg[i];
            char keyChar = key[i];
            int index = (plaintextChar - 'A' + keyChar - 'A') % 26; // Encrypt each character
            if (index < 0) {
                index += 26; // Ensure positive value
            }
            encryptedMsg[i] = (char) (index + 'A'); // Convert back to character
        }

        System.out.println("Original Message: " + plaintext);
        System.out.println("Keyword: " + keyword);
        System.out.println("Encrypted Message: " + String.valueOf(encryptedMsg));
    }
}
