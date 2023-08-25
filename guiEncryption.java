import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Base64;

public class guiEncryption extends JFrame {

    private JTextField inputFileField;
    private JTextField outputFileField;
    private JTextField secretKeyField;

    public guiEncryption() {
        setTitle("File Encryption");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel inputFileLabel = new JLabel("Input File:");
        JLabel outputFileLabel = new JLabel("Output File:");
        JLabel secretKeyLabel = new JLabel("Secret Key:");

        inputFileField = new JTextField();
        outputFileField = new JTextField();
        secretKeyField = new JTextField();

        JButton chooseInputButton = new JButton("Choose Input File");
        JButton chooseOutputButton = new JButton("Choose Output File");
        JButton encryptButton = new JButton("Encrypt");

        chooseInputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(guiEncryption.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    inputFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        chooseOutputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(guiEncryption.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    outputFileField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputFilePath = inputFileField.getText();
                    String outputFilePath = outputFileField.getText();
                    String secretKey = secretKeyField.getText();

                    encryptFile(inputFilePath, outputFilePath);
                    JOptionPane.showMessageDialog(guiEncryption.this,
                            "File encrypted successfully.", "Encryption", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(guiEncryption.this,
                            "An error occurred during encryption.", "Encryption Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.add(inputFileLabel);
        mainPanel.add(inputFileField);
        mainPanel.add(chooseInputButton);
        mainPanel.add(new JLabel());
        mainPanel.add(outputFileLabel);
        mainPanel.add(outputFileField);
        mainPanel.add(chooseOutputButton);
        mainPanel.add(new JLabel());
        mainPanel.add(secretKeyLabel);
        mainPanel.add(secretKeyField);
        mainPanel.add(new JLabel());
        mainPanel.add(encryptButton);

        add(mainPanel);
    }

    public static void main(String[] args) {
        guiEncryption app = new guiEncryption();
        app.setVisible(true);
    }

    public static String encryptFile(String plainText, String secretKeyString) throws Exception {
        byte[] key = secretKeyString.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[cipher.getBlockSize()];
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
