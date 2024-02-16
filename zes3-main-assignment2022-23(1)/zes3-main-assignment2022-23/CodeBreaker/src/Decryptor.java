import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Decryptor{

    static Scanner scanner = new Scanner(System.in);
    public static void decryptMenu(Scanner scanner) throws IOException {
        int choice = 0;

        while (choice != 4) {
            displayDecryptionOptions();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    performCaesarDecryption();
                    break;
                case 2:
                    performKeyedCaesarDecryption();
                    break;
                case 3:
                    performVigenereDecryption();
                    break;
                case 4:
                    showCipher();
                    break;
                case 5:
                    System.out.println("Returning to the main menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void showCipher() throws IOException {
        System.out.println("Enter the file name: ");
        String filepath = scanner.nextLine();
        String cipherText = loadPlainTextFromFile(filepath);
        System.out.println(cipherText);
    }

    private static void displayDecryptionOptions() {
        System.out.println("=== Decryption Options ===");
        System.out.println("1. Caesar Decryption");
        System.out.println("2. Keyed Caesar Decryption");
        System.out.println("3. Vigenère Decryption");
        System.out.println("4. Show Cipher");
        System.out.println("5. Return to Main Menu");
        System.out.print("Please enter your choice (1-4): ");
    }

    private static void performCaesarDecryption() throws IOException {
        int shift = CipherProgram.readCaesarKey();
        System.out.println("Enter the file name: ");
        String filepath = scanner.nextLine();
        String cipherText = loadPlainTextFromFile(filepath);
        CaesarCipher caesarCipher = new CaesarCipher(shift);
        String decryptedText = caesarCipher.decrypt(cipherText);
        System.out.println("Decrypted Text: " + decryptedText);

    }
    private static String loadPlainTextFromFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        }
        return text.toString();
    }

    private static void performKeyedCaesarDecryption() throws IOException {
        String key = CipherProgram.readKeyedCaesarKey();
        System.out.println("Enter the file name: ");
        String filepath = scanner.nextLine();
        String cipherText = loadPlainTextFromFile(filepath);
        KeyedCaesarCipher keyedCaesarCipher = new KeyedCaesarCipher();
        String decryptedText = keyedCaesarCipher.decrypt(cipherText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    private static void performVigenereDecryption() throws IOException {
        String key = CipherProgram.readVigenereKey();
        System.out.println("Enter the file name: ");
        String filepath = scanner.nextLine();
        String cipherText = loadPlainTextFromFile(filepath);
        VigenereCipher vigenereCipher = new VigenereCipher(key);
        String decryptedText = vigenereCipher.decrypt(cipherText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

}