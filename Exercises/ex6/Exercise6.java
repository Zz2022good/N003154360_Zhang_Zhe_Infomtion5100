package Exercises.ex6;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SecureRandom;


public class Exercise6 {

    static class Person {
        private String name;
        private KeyPair rsaKeyPair;

        public Person(String name) {
            this.name = name;
        }

        public void generateRSAKeys() throws Exception {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            rsaKeyPair = keyPairGenerator.generateKeyPair();
        }

        public PublicKey getPublicKey() {
            return rsaKeyPair.getPublic();
        }

        public PrivateKey getPrivateKey() {
            return rsaKeyPair.getPrivate();
        }
    }
    // IV
    public static byte[] generateIV() {
        byte[] iv = new byte[12];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        return iv;
    }

    // Symmetric encryption with AES/GCM/NoPadding
    public static byte[] encryptAES(String message, SecretKey secretKey, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    // Symmetric decryption with AES/GCM/NoPadding
    public static String decryptAES(byte[] encryptedMessage, SecretKey secretKey, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // Asymmetric encryption with RSA/ECB/PKCS1Padding
    public static byte[] encryptRSA(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    // Asymmetric decryption with RSA/ECB/PKCS1Padding
    public static String decryptRSA(byte[] encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // Sign message using RSA private key
    public static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes(StandardCharsets.UTF_8));
        return signature.sign();
    }

    // Verify signature using RSA public key
    public static boolean verifySignature(String message, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes(StandardCharsets.UTF_8));
        return signature.verify(signatureBytes);
    }

    public static void main(String[] args) throws Exception {

            // Create Alice and Bob
            Person alice = new Person("Alice");
            Person bob = new Person("Bob");

            // Generate RSA-2048 keys for Alice and Bob
            alice.generateRSAKeys();
            bob.generateRSAKeys();

            // Generate AES-256 symmetric key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey aesKey = keyGenerator.generateKey();

            System.out.println("cipher Demo\n");

            // Symmetric Encryption / Decryption
            String symmetricMessage = "Hello Bob, this is a secret symmetric message from Alice.";
            byte[] iv = generateIV();

            System.out.println(" Symmetric Encryption and Decryption using AES-256 GCM ");
            System.out.println("Original Message: " + symmetricMessage);

            byte[] encryptedAES = encryptAES(symmetricMessage, aesKey, iv);
            System.out.println("Encrypted Message: " + encryptedAES);

            String decryptedAES = decryptAES(encryptedAES, aesKey, iv);
            System.out.println("Decrypted Message: " + decryptedAES);
            System.out.println();


            // Asymmetric Encryption / Decryption
            String asymmetricMessage = "Hello Bob, this is a secret RSA message from Alice.";

            System.out.println("Asymmetric Encryption / Decryption (RSA-2048) -----");
            System.out.println("Original Message: " + asymmetricMessage);

            // Alice encrypts using Bob's public key
            byte[] encryptedRSA = encryptRSA(asymmetricMessage, bob.getPublicKey());
            System.out.println("Encrypted Message: " + encryptedRSA);

            // Bob decrypts using Bob's private key
            String decryptedRSA = decryptRSA(encryptedRSA, bob.getPrivateKey());
            System.out.println("Decrypted Message: " + decryptedRSA);
            System.out.println();


            // validating signature
            String signedMessage = "Hello Bob, please verify that this message really came from Alice.";

            System.out.println("----- Digital Signature / Verification -----");
            System.out.println("Message to Sign: " + signedMessage);


            byte[] signatureBytes = signMessage(signedMessage, alice.getPrivateKey());
            System.out.println("Signature: " + signatureBytes);


            boolean isVerified = verifySignature(signedMessage, signatureBytes, alice.getPublicKey());
            System.out.println("Signature Valid: " + isVerified);
        }
    }
