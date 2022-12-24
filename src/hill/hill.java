package hill;

public class hill {

	public static String encrypt(String plaintext, int shift) {
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
	
}
