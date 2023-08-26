import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class SecureEncryption {
    public static void main(String[] args) throws Exception {
        String plaintext = "This is a secret message.";
        
        SecretKey secretKey = generateAESKey();
        
        byte[] encryptedBytes = encryptAES(plaintext, secretKey);
        String decryptedText = decryptAES(encryptedBytes, secretKey);
        
        System.out.println("Encryption File Starts Here....");
        System.out.println("Original: " + plaintext);
        System.out.println("Encrypted: " + bytesToHex(encryptedBytes));
        System.out.println("Decrypted: " + decryptedText);
        System.out.println("Encryption File Ends Here....");
        System.out.println("<------ END ------->");
    }

    public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    public static byte[] encryptAES(String plaintext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
    }

    public static String decryptAES(byte[] encryptedBytes, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}
