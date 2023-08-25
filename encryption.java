package Java_Encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
//import java.util.Base64;

public class encryption {
    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String outputFilePath = "encrypted.enc";
        String secretKey = "MySecretKey12345";

        try {
            encryptFile(inputFilePath, outputFilePath, secretKey);
            System.out.println("File encrypted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encryptFile(String inputFilePath, String outputFilePath, String secretKey)
            throws Exception {
        byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[cipher.getBlockSize()];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        try (FileInputStream inputFileStream = new FileInputStream(inputFilePath);
             FileOutputStream outputFileStream = new FileOutputStream(outputFilePath)) {
            byte[] inputBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputFileStream.read(inputBuffer)) != -1) {
                byte[] outputBuffer = cipher.update(inputBuffer, 0, bytesRead);
                if (outputBuffer != null) {
                    outputFileStream.write(outputBuffer);
                }
            }
            byte[] finalOutputBuffer = cipher.doFinal();
            if (finalOutputBuffer != null) {
                outputFileStream.write(finalOutputBuffer);
            }
        }
    }
}
