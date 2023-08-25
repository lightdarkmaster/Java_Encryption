import java.util.HashMap;
import java.util.Map;

public class MonoalphabeticCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String KEY = "QWERTYUIOPASDFGHJKLZXCVBNM";

    public static void main(String[] args) {
        String plaintext = "HELLO";
        String encryptedText = encrypt(plaintext);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }

    public static String encrypt(String plaintext) {
        plaintext = plaintext.toUpperCase();
        StringBuilder encryptedText = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = ALPHABET.indexOf(c);
                char encryptedChar = KEY.charAt(index);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText) {
        encryptedText = encryptedText.toUpperCase();
        StringBuilder decryptedText = new StringBuilder();

        for (char c : encryptedText.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = KEY.indexOf(c);
                char decryptedChar = ALPHABET.charAt(index);
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
    }
}
