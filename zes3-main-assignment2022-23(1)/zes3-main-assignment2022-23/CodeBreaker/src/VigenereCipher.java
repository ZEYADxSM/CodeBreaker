public class VigenereCipher {
    private String key;

    public VigenereCipher(String key) {
        this.key = key;
    }

    public String encrypt(String plainText) {
        StringBuilder cipherText = new StringBuilder();
        int keyLength = key.length();
        int plainTextLength = plainText.length();

        for (int i = 0; i < plainTextLength; i++) {
            char ch = plainText.charAt(i);
            if (Character.isLetter(ch)) {
                char encryptedChar = (char) (((ch - 'A' + getKeyShift(i)) % 26) + 'A');
                cipherText.append(encryptedChar);
            } else {
                cipherText.append(ch);
            }
        }
        return cipherText.toString();
    }

    public String decrypt(String cipherText) {
        StringBuilder plainText = new StringBuilder();
        int keyLength = key.length();
        int cipherTextLength = cipherText.length();

        for (int i = 0; i < cipherTextLength; i++) {
            char ch = cipherText.charAt(i);
            if (Character.isLetter(ch)) {
                char decryptedChar = (char) (((ch - 'A' - getKeyShift(i) + 26) % 26) + 'A');
                plainText.append(decryptedChar);
            } else {
                plainText.append(ch);
            }
        }
        return plainText.toString();
    }

    private int getKeyShift(int index) {
        char keyChar = key.charAt(index % key.length());
        return Character.toUpperCase(keyChar) - 'A';
    }
}