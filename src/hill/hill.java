package hill;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public static void main(String[] args) {
		// 创建窗口
		JFrame frame = new JFrame("加密");
		frame.setSize(400, 140);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 创建标签和文本框
		JLabel keyLabel = new JLabel("加密内容:");
		JTextField keyField = new JTextField(10);

		// 创建按钮
		JButton submitButton = new JButton("Submit");

		// 为按钮添加事件监听器
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 读取用户输入的内容
				String keyString = keyField.getText();

				// 将密钥转换为字节
				byte key = (byte) Integer.parseInt(keyString, 16);

			}
		});

		// 将标签、文本框和按钮添加到窗口中
		frame.add(keyLabel);
		frame.add(keyField);
		frame.add(submitButton);
		
		// 设置窗口布局
		frame.setLayout(new FlowLayout());

		// 显示窗口
		frame.setVisible(true);
	}
}
