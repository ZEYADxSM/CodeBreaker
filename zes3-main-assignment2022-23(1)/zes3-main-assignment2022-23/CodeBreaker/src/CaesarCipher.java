public class CaesarCipher {
    private int shift;

    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    public String encrypt(String plainText) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            if (Character.isLetter(ch)) {
                char encryptedChar = (char) (((ch - 'A' + shift) % 26) + 'A');
                cipherText.append(encryptedChar);
            } else {
                cipherText.append(ch);
            }
        }
        return cipherText.toString();
    }

    public String decrypt(String cipherText) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            if (Character.isLetter(ch)) {
                char decryptedChar = (char) (((ch - 'A' - shift + 26) % 26) + 'A');
                plainText.append(decryptedChar);
            } else {
                plainText.append(ch);
            }
        }
        return plainText.toString();
    }
}