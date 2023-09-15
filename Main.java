package Java_Encryption;

public class Main {
	public static void main (String[] args) {
		String plaintext = "hello";
		plaintext = plaintext.toUpperCase();
		int key = 15;
		String ciphertext = "";

		for (int i = 0; i < plaintext.length(); i++) {
			int index = plaintext.charAt(i) - 65;
			int ci = index + key;

			if (ci >= 26) {
				ci = ci - 26;
			}

			Character ch = (char) (ci + 65);

			ciphertext = ciphertext + ch.toString();

		}

		System.out.print("Cipher: ");
		System.out.println(ciphertext);

		plaintext = "";


		for (int i = 0; i < ciphertext.length(); i++) {
			int index = ciphertext.charAt(i) - 65;
			int ci = index - key;

			if (ci < 0) {
				ci = ci + 26;
			}

			Character ch = (char) (ci + 65);

			plaintext = plaintext + ch.toString();

		}

		System.out.print("Decipher: ");
		System.out.println(plaintext);


	}
}
//add it to the GUI
