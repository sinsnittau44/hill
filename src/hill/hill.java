package hill;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class hill extends JFrame {
	private static int[] plaintextFrequency = new int[26];
	private static int[] ciphertextFrequency = new int[26];

	private JLabel label;
	private JTextField textField;
	private JButton button;

	public hill() {
		setTitle("Hill Cipher");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		label = new JLabel("输入明文：");
		add(label);

		textField = new JTextField(20);
		add(textField);

		button = new JButton("加密");
		add(button);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String plaintext = textField.getText().toUpperCase();
				String ciphertext = hill.encrypt(plaintext);
				JOptionPane.showMessageDialog(null, "密文：" + ciphertext);

				updateFrequency(plaintext, plaintextFrequency);
				updateFrequency(ciphertext, ciphertextFrequency);
			}
		});
	}

	public static int[] getPlaintextFrequency() {
		return plaintextFrequency;
	}

	public static int[] getCiphertextFrequency() {
		return ciphertextFrequency;
	}

	private void updateFrequency(String text, int[] frequency) {
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				frequency[c - 'A']++;
			}
		}
	}

	public static void main(String[] args) {
		hill frame = new hill();
		frame.setVisible(true);
	}

	private static final int[][] KEY = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

	public static String encrypt(String plaintext) {
		int len = plaintext.length();
		int[][] matrix = new int[3][len / 3 + (len % 3 == 0 ? 0 : 1)];
		int k = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (k < len) {
					matrix[i][j] = plaintext.charAt(k++) - 'A';
				} else {
					matrix[i][j] = 0;
				}
			}
		}

		int[][] result = new int[3][matrix[0].length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				for (int m = 0; m < 3; m++) {
					result[i][j] += KEY[i][m] * matrix[m][j];
				}
				result[i][j] %= 26;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				sb.append((char) (result[i][j] + 'A'));
			}
		}

		return sb.toString();
	}
}
