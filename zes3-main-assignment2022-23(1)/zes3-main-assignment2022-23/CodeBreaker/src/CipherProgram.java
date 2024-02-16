import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CipherProgram {
    private static final String CAESAR_KEY_FILE = "caesar-key.txt";
    private static final String KEYED_CAESAR_KEY_FILE = "keyed-caesar-key.txt";
    private static final String VIGENERE_KEY_FILE = "vigenere-key.txt";

    static String preparedPlainText = "";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 11) {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    performCaesarCipherShift();
                    break;
                case 2:
                    performKeyedCaesarCipherShift();
                    break;
                case 3:
                    performVigenereCipher();
                    break;
                case 4:
                    editKey(scanner);
                    break;
                case 5:
                    performUserFileEncryption(scanner);
                    break;
                case 6:
                    displayPreparedPlainText();
                    break;
                case 7:
                    Decryptor.decryptMenu(scanner);
                    break;
                case 11:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void displayPreparedPlainText() {
        System.out.println("Prepared Plain Text: " + preparedPlainText);
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

    private static void performUserFileEncryption(Scanner scanner) {
        System.out.print("Enter the path of the plain text file: ");
        String filePath = scanner.nextLine();

        try {
            String plainText = loadPlainTextFromFile(filePath);
            if (plainText != null) {
                preparedPlainText = Prepare.prepareText(plainText);
                System.out.println("Prepared Plain Text: " + preparedPlainText);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the plain text file: " + e.getMessage());
        }
    }

    private static void displayKeys() {
        System.out.println("Caesar Cipher Key: " + readCaesarKey());
        System.out.println("Keyed Caesar Cipher Key: " + readKeyedCaesarKey());
        System.out.println("Vigenere Cipher Key: " + readVigenereKey());
    }
    private static void saveCipherTextToFile(String cipherText) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file name:");
        String filePath = sc.nextLine();
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(cipherText);
            fileWriter.close();
            System.out.println("Cipher text saved to file: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the cipher text: " + e.getMessage());
        }
    }


    static void displayMenu() {
        System.out.println("=== Cipher Program Menu ===");
        displayKeys();
        System.out.println("-----------");
        System.out.println("1. Caesar Cipher Shift");
        System.out.println("2. Keyed Caesar Cipher Shift");
        System.out.println("3. Vigenère Cipher");
        System.out.println("4. Edit the Key");
        System.out.println("5. Input a user-specified plain text file");
        System.out.println("6. Display the plain text file");
        System.out.println("7. Input the cipher text file");
        System.out.println("11. Exit Program");
        System.out.print("Please enter your choice (1-11): ");
    }

    private static void performCaesarCipherShift() {
        int shift = readCaesarKey();

        CaesarCipher caesarCipher = new CaesarCipher(shift);
        String cipherText = caesarCipher.encrypt(preparedPlainText);
        System.out.println("Cipher Text: " + cipherText);
        saveCipherTextToFile(cipherText);
        String decryptedText = caesarCipher.decrypt(cipherText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    static int readCaesarKey() {
        int shift = 0;
        try {
            String keyData = new String(Files.readAllBytes(Paths.get(CAESAR_KEY_FILE)));
            shift = Integer.parseInt(keyData.trim());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the Caesar key file: " + e.getMessage());
        }
        return shift;
    }

    private static void performKeyedCaesarCipherShift() {
        String key = readKeyedCaesarKey();

        KeyedCaesarCipher keyedCaesarCipher = new KeyedCaesarCipher();
        String cipherText = keyedCaesarCipher.encrypt(preparedPlainText, key);
        System.out.println("Cipher Text: " + cipherText);
        saveCipherTextToFile(cipherText);
        String decryptedText = keyedCaesarCipher.decrypt(cipherText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    static String readKeyedCaesarKey() {
        String key = "";
        try {
            key = new String(Files.readAllBytes(Paths.get(KEYED_CAESAR_KEY_FILE))).trim();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the Keyed Caesar key file: " + e.getMessage());
        }
        return key;
    }

    private static void performVigenereCipher() {
        String key = readVigenereKey();

        VigenereCipher vigenereCipher = new VigenereCipher(key);
        String cipherText = vigenereCipher.encrypt(preparedPlainText);
        System.out.println("Cipher Text: " + cipherText);
        saveCipherTextToFile(cipherText);
        String decryptedText = vigenereCipher.decrypt(cipherText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    static String readVigenereKey() {
        String key = "";
        try {
            key = new String(Files.readAllBytes(Paths.get(VIGENERE_KEY_FILE))).trim();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the Vigenere key file: " + e.getMessage());
        }
        return key;
    }

    private static void editKey(Scanner scanner) {
        System.out.println("=== Edit Key ===");
        System.out.println("1. Edit Caesar Key");
        System.out.println("2. Edit Keyed Caesar Key");
        System.out.println("3. Edit Vigenere Key");
        System.out.print("Please enter your choice (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                editCaesarKey(scanner);
                break;
            case 2:
                editKeyedCaesarKey(scanner);
                break;
            case 3:
                editVigenereKey(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void editCaesarKey(Scanner scanner) {
        System.out.print("Enter the new Caesar key: ");
        int newKey = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        saveKeyToFile(CAESAR_KEY_FILE, String.valueOf(newKey));
    }

    private static void editKeyedCaesarKey(Scanner scanner) {
        System.out.print("Enter the new Keyed Caesar key: ");
        String newKey = scanner.nextLine();

        saveKeyToFile(KEYED_CAESAR_KEY_FILE, newKey);
    }

    private static void editVigenereKey(Scanner scanner) {
        System.out.print("Enter the new Vigenere key: ");
        String newKey = scanner.nextLine();

        saveKeyToFile(VIGENERE_KEY_FILE, newKey);
    }

    private static void saveKeyToFile(String fileName, String key) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(key);
            System.out.println("Key saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the key to file: " + e.getMessage());
        }
    }
}