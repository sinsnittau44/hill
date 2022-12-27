package hill;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class caesar extends JFrame {
	private static int[] plaintextFrequency = new int[26];
	private static int[] ciphertextFrequency = new int[26];

	private JLabel label;
	private JTextField textField;
	private JButton button;
	
	public static String count(String args) {
	    String input = args;

	    //使用HashMap存储字符出现的次数
	    Map<Character, Integer> characterCounts = new HashMap<>();
	    for (char c : input.toCharArray()) {
	      characterCounts.put(c, characterCounts.getOrDefault(c, 0) + 1);
	    }

	    //将字符出现的次数转换为字符串
	    StringBuilder sb = new StringBuilder();
	    for (Map.Entry<Character, Integer> entry : characterCounts.entrySet()) {
	      sb.append(entry.getKey() + ": " + entry.getValue() + "; ");
	    }
	    
	    return sb.toString();
	  }

	public caesar() {
		setTitle("Caesar Cipher");
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
				JOptionPane.showMessageDialog(null, "密文：" + ciphertext + "\n加密前\n" + count(plaintext) + "\n加密后\n" + count(ciphertext));

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
		caesar frame = new caesar();
		frame.setVisible(true);
	}

	private static final int[][] KEY = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

	public static String encrypt(String plaintext) {
	    String message = plaintext;
	    int key = 123;
	    String encryptedMessage = "";
	    for (char c : message.toCharArray()) {
	      encryptedMessage += (char) (c + key);
	    }

		return encryptedMessage;
	}
}
