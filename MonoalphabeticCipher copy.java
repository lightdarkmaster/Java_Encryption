import java.util.HashMap;
import java.util.Map;

public class MonoalphabeticCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String CIPHER_ALPHABET = "xyzabcdefghijklmnopqrstuvw"; // Example substitution

    private Map<Character, Character> encryptionMap;
    private Map<Character, Character> decryptionMap;

    public MonoalphabeticCipher() {
        encryptionMap = new HashMap<>();
        decryptionMap = new HashMap<>();

        // Create the encryption and decryption maps
        for (int i = 0; i < ALPHABET.length(); i++) {
            char plainChar = ALPHABET.charAt(i);
            char cipherChar = CIPHER_ALPHABET.charAt(i);

            encryptionMap.put(plainChar, cipherChar);
            decryptionMap.put(cipherChar, plainChar);
        }
    }

    public String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                char encryptedChar = encryptionMap.get(Character.toLowerCase(c));
                if (Character.isUpperCase(c)) {
                    encryptedChar = Character.toUpperCase(encryptedChar);
                }
                ciphertext.append(encryptedChar);
            } else {
                ciphertext.append(c);
            }
        }

        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                char decryptedChar = decryptionMap.get(Character.toLowerCase(c));
                if (Character.isUpperCase(c)) {
                    decryptedChar = Character.toUpperCase(decryptedChar);
                }
                plaintext.append(decryptedChar);
            } else {
                plaintext.append(c);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        MonoalphabeticCipher cipher = new MonoalphabeticCipher();
        String plaintext = "ABCDEFG";
        String encryptedText = cipher.encrypt(plaintext);
        String decryptedText = cipher.decrypt(encryptedText);

        System.out.println("Original: " + plaintext);
        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decryptedText);
    }
}

//need to ask for the User the input
//need to display the output
//Monoalphabetic cipher
//Poly Alphabetic Cipher
//Vigenere Ciphers
