package hill;

import java.util.ArrayList;
import java.util.List;

public class hill {

	public static String encryptCaesarCipher(String plaintext, int shift) {
		StringBuilder ciphertext = new StringBuilder();
		for (char c : plaintext.toCharArray()) {
			if (Character.isLetter(c)) {
				if (Character.isUpperCase(c)) {
					int asciiValue = (int) c;
					int newAsciiValue = ((asciiValue - 65 + shift) % 26) + 65;
					char newChar = (char) newAsciiValue;
					ciphertext.append(newChar);
				} else {
					int asciiValue = (int) c;
					int newAsciiValue = ((asciiValue - 97 + shift) % 26) + 97;
					char newChar = (char) newAsciiValue;
					ciphertext.append(newChar);
				}
			} else {
				ciphertext.append(c);
			}
		}
		return ciphertext.toString();
	}

	private static final int BLOCK_SIZE = 3;
	private static final int[][] KEY_MATRIX = { { 8, 3 }, { 2, 7 } };

	public static String encryptHillCipher(String plaintext) {
		List<Integer> plaintextVector = new ArrayList<>();
		for (char c : plaintext.toCharArray()) {
			plaintextVector.add((int) c - 97);
		}

		StringBuilder ciphertext = new StringBuilder();
		while (plaintextVector.size() % BLOCK_SIZE != 0) {
			plaintextVector.add(0);
		}

		for (int i = 0; i < plaintextVector.size(); i += BLOCK_SIZE) {
			int[] block = new int[BLOCK_SIZE];
			for (int j = 0; j < BLOCK_SIZE; j++) {
				block[j] = plaintextVector.get(i + j);
			}

			int[] encryptedBlock = new int[BLOCK_SIZE];
			for (int j = 0; j < BLOCK_SIZE; j++) {
				for (int k = 0; k < BLOCK_SIZE; k++) {
					encryptedBlock[j] += KEY_MATRIX[j][k] * block[k];
				}
			}

			for (int j = 0; j < BLOCK_SIZE; j++) {
				ciphertext.append((char) (encryptedBlock[j] % 26 + 97));
			}
		}

		return ciphertext.toString();
	}

	
}
