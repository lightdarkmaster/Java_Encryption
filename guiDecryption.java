import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.*
import java.io.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class guiDecryption extends Application {

    private String encryptedFilePath;
    private String decryptedFilePath;
    private String secretKey = "MySecretKey12345";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(10);

        Label titleLabel = new Label("File Decryption");
        Label encryptedLabel = new Label("Encrypted File:");
        Label decryptedLabel = new Label("Decrypted File:");

        TextField encryptedField = new TextField();
        TextField decryptedField = new TextField();

        Button chooseEncryptedButton = new Button("Choose Encrypted File");
        Button chooseDecryptedButton = new Button("Choose Decrypted File");
        Button decryptButton = new Button("Decrypt");

        chooseEncryptedButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Encrypted File");
            encryptedFilePath = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
            encryptedField.setText(encryptedFilePath);
        });

        chooseDecryptedButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Decrypted File");
            decryptedFilePath = fileChooser.showSaveDialog(primaryStage).getAbsolutePath();
            decryptedField.setText(decryptedFilePath);
        });

        decryptButton.setOnAction(e -> {
            try {
                decryptFile(encryptedFilePath, decryptedFilePath, secretKey);
                System.out.println("File decrypted successfully.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(
            titleLabel,
            encryptedLabel, encryptedField, chooseEncryptedButton,
            decryptedLabel, decryptedField, chooseDecryptedButton,
            decryptButton
        );

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("File Decryption UI");
        primaryStage.show();
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
