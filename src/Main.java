public class Main {

    public static void main(String[] args) {
        String plaintext = "HELLO WORLD";
        String key = "KEY";
        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Key: " + key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
    public static String encrypt(String plaintext, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyIndex = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char encryptedChar = shiftChar(currentChar, key.charAt(keyIndex), true);
                encryptedText.append(encryptedChar);

                keyIndex = (keyIndex + 1) % key.length();
            } else {
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char currentChar = ciphertext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char decryptedChar = shiftChar(currentChar, key.charAt(keyIndex), false);
                decryptedText.append(decryptedChar);

                keyIndex = (keyIndex + 1) % key.length();
            } else {
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    private static char shiftChar(char textChar, char keyChar, boolean encrypt) {
        int textPos = textChar - 'A';
        int keyPos = keyChar - 'A';
        int shift;

        if (encrypt) {
            shift = (textPos + keyPos) % 26;
        } else {
            shift = (textPos - keyPos + 26) % 26;
        }

        return (char) (shift + 'A');
    }
}
