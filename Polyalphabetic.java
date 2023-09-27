import java.util.*;

public class Polyalphabetic {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter plaintext: ");
        String msg = input.nextLine();
        msg = msg.toUpperCase();

        System.out.println("Enter key: ");
        String key = input.nextLine();
        key = key.toUpperCase();

        String enc;
        if (isInteger(key)) {
            int numericKey = Integer.parseInt(key);
            key = "" + alphabet.charAt(numericKey % 26);
            enc = autoEncryption(msg, key);
        } else {
            enc = autoEncryption(msg, key);
        }

        System.out.println("Plaintext : " + msg);
        System.out.println("Key : " + key);
        System.out.println("Encrypted : " + enc);
        System.out.println("Decrypted : " + autoDecryption(enc, key));
        System.out.println("Poly-Alphabetic Cipher");
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String autoEncryption(String msg, String key){
        int len = msg.length();

        // generating the key-stream
        String newKey = key.concat(msg);
        newKey = newKey.substring(0, newKey.length() - key.length());
        String encryptMsg = "";

        // applying encryption algorithm
        for (int x = 0; x < len; x++) {
            int first = alphabet.indexOf(msg.charAt(x));
            int second = alphabet.indexOf(newKey.charAt(x));
            int total = (first + second) % 26;
            encryptMsg += alphabet.charAt(total);
        }
        return encryptMsg;
    }

    public static String autoDecryption(String msg, String key){
        String currentKey = key;
        String decryptMsg = "";

        // applying decryption algorithm
        for (int x = 0; x < msg.length(); x++) {
            int get1 = alphabet.indexOf(msg.charAt(x));
            int get2 = alphabet.indexOf(currentKey.charAt(x));
            int total = (get1 - get2) % 26;
            total = (total < 0) ? total + 26 : total;
            decryptMsg += alphabet.charAt(total);
            currentKey += alphabet.charAt(total);
        }
        return decryptMsg;
    }
}
//fix ko na an input na dapat na accept hin integer tas ig coconvert into char or string --chan-chan..