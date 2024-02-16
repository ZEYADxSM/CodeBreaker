Java Cipher Program
Overview

The Java Cipher Program is a console-based application that provides encryption and decryption functionalities using various cipher algorithms, including Caesar Cipher, Keyed Caesar Cipher, and Vigenère Cipher.
Table of Contents

    Features
    How to Use
    File Structure
    Dependencies
    Build and Run
    License

Features

    Cipher Algorithms:
        Caesar Cipher Shift
        Keyed Caesar Cipher Shift
        Vigenère Cipher

    Key Management:
        Edit Caesar Key
        Edit Keyed Caesar Key
        Edit Vigenere Key

    File Operations:
        Input a user-specified plain text file
        Display the plain text file
        Input the cipher text file
            Display Cipher Text
            Decrypt using Caesar Cipher
            Decrypt using Keyed Caesar Cipher
            Decrypt using Vigenère Cipher

How to Use

    Compilation:
        Compile the Java files using a Java compiler.

    Run:
        Execute the compiled Java class containing the main method (CipherProgram).

    Menu Navigation:
        Use the displayed menu to choose options (1-11) based on the desired operation.

File Structure

    CipherProgram.java: Main class containing the program logic.
    Decryptor.java: Class handling decryption options and methods.
    CaesarCipher.java: Class implementing the Caesar Cipher algorithm.
    KeyedCaesarCipher.java: Class implementing the Keyed Caesar Cipher algorithm.
    VigenereCipher.java: Class implementing the Vigenère Cipher algorithm.

Dependencies

    Java Development Kit (JDK) installed on your machine.

Build and Run

    Compile:

    bash

javac CipherProgram.java

Run:

bash

java CipherProgram
