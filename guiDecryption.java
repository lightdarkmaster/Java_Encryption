import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class guiDecryption extends JFrame {

    private String encryptedFilePath;
    private String decryptedFilePath;
    private String secretKey = "MySecretKey12345";

    private JTextField encryptedField;
    private JTextField decryptedField;

    public static void main(String[] args) {
        guiDecryption app = new guiDecryption();
        app.setVisible(true);
    }

    public guiDecryption() {
        setTitle("File Decryption UI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel titleLabel = new JLabel("File Decryption", SwingConstants.CENTER);
        JLabel encryptedLabel = new JLabel("Encrypted File:");
        JLabel decryptedLabel = new JLabel("Decrypted File:");

        encryptedField = new JTextField();
        decryptedField = new JTextField();

        JButton chooseEncryptedButton = new JButton("Choose Encrypted File");
        JButton chooseDecryptedButton = new JButton("Choose Decrypted File");
        JButton decryptButton = new JButton("Decrypt");

        chooseEncryptedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(guiDecryption.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    encryptedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    encryptedField.setText(encryptedFilePath);
                }
            }
        });

        chooseDecryptedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(guiDecryption.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    decryptedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    decryptedField.setText(decryptedFilePath);
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    decryptFile(encryptedFilePath, decryptedFilePath, secretKey);
                    JOptionPane.showMessageDialog(guiDecryption.this,
                            "File decrypted successfully.", "Decryption", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(guiDecryption.this,
                            "An error occurred during decryption.", "Decryption Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.add(titleLabel);
        mainPanel.add(new JLabel());
        mainPanel.add(encryptedLabel);
        mainPanel.add(encryptedField);
        mainPanel.add(chooseEncryptedButton);
        mainPanel.add(new JLabel());
        mainPanel.add(decryptedLabel);
        mainPanel.add(decryptedField);
        mainPanel.add(chooseDecryptedButton);
        mainPanel.add(decryptButton);

        add(mainPanel);
    }

    public void decryptFile(String encryptedFilePath, String decryptedFilePath, String secretKey)
            throws Exception {
        // Similar decryption logic as in the previous example
        // ... (Code from previous decryption example)
    }
}
