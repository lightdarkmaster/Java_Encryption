package Java_Encryption;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
//import java.util.Base64;

public class decryption {
    public static void main(String[] args) {
        String encryptedFilePath = "encrypted.enc";
        String decryptedFilePath = "decrypted.txt";
        String secretKey = "MySecretKey12345";

        try {
            decryptFile(encryptedFilePath, decryptedFilePath, secretKey);
            System.out.println("File decrypted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptFile(String encryptedFilePath, String decryptedFilePath, String secretKey)
            throws Exception {
        byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[cipher.getBlockSize()];
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        try (FileInputStream encryptedFileStream = new FileInputStream(encryptedFilePath);
             FileOutputStream decryptedFileStream = new FileOutputStream(decryptedFilePath)) {
            byte[] inputBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = encryptedFileStream.read(inputBuffer)) != -1) {
                byte[] outputBuffer = cipher.update(inputBuffer, 0, bytesRead);
                if (outputBuffer != null) {
                    decryptedFileStream.write(outputBuffer);
                }
            }
            byte[] finalOutputBuffer = cipher.doFinal();
            if (finalOutputBuffer != null) {
                decryptedFileStream.write(finalOutputBuffer);
            }
        }
    }
}
