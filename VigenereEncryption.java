public class VigenereEncryption {
    public static void main(String[] args) {
        String plaintext = "HELLO";
        String keyword = "KEY";

        String encryptedText = encrypt(plaintext, keyword);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, keyword);
        System.out.println("Decrypted: " + decryptedText);
    }

    public static String encrypt(String plaintext, String keyword) {
        StringBuilder encryptedText = new StringBuilder();
        plaintext = plaintext.toUpperCase();
        keyword = keyword.toUpperCase();

        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int shift = keyword.charAt(j) - 'A';
                char encryptedChar = (char) (((c - 'A' + shift) % 26) + 'A');
                encryptedText.append(encryptedChar);
                j = (j + 1) % keyword.length();
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, String keyword) {
        StringBuilder decryptedText = new StringBuilder();
        encryptedText = encryptedText.toUpperCase();
        keyword = keyword.toUpperCase();

        for (int i = 0, j = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int shift = keyword.charAt(j) - 'A';
                char decryptedChar = (char) (((c - 'A' - shift + 26) % 26) + 'A');
                decryptedText.append(decryptedChar);
                j = (j + 1) % keyword.length();
            } else {
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
    }
}
