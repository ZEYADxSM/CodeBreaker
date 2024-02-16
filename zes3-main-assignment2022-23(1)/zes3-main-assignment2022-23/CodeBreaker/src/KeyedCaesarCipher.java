public class KeyedCaesarCipher {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encrypt(String text, String key) {
        // Convert the text to uppercase.
        text = text.toUpperCase();

        // Create a new string to store the encrypted text.
        String encryptedText = "";

        // Iterate through each character in the text.
        for (int i = 0; i < text.length(); i++) {
            // Get the position of the current character in the alphabet.
            int position = ALPHABET.indexOf(text.charAt(i));

            // Encrypt the character by shifting it by the key.
            int encryptedPosition = (position + key.length()) % 26;

            // Check if the encrypted character is in the key.
            if (key.indexOf(ALPHABET.charAt(encryptedPosition)) != -1) {
                // If it is, then skip it.
                continue;
            }

            // Append the encrypted character to the encrypted text.
            encryptedText += Character.toUpperCase(ALPHABET.charAt(encryptedPosition));
        }

        // Return the encrypted text.
        return encryptedText;
    }

    public static String decrypt(String text, String key) {
        // Convert the text to uppercase.
        text = text.toUpperCase();

        // Create a new string to store the decrypted text.
        StringBuilder decryptedText = new StringBuilder();

        // Iterate through each character in the text.
        for (int i = 0; i < text.length(); i++) {
            // Get the position of the current character in the alphabet.
            int position = ALPHABET.indexOf(text.charAt(i));

            // Decrypt the character by shifting it by the key.
            int decryptedPosition = (position - key.length() + 26) % 26;

            // Append the decrypted character to the decrypted text.
            decryptedText.append(Character.toUpperCase(ALPHABET.charAt(decryptedPosition)));
        }

        // Return the decrypted text.
        return decryptedText.toString();
    }
}