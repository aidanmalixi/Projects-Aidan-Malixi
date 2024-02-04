import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.math.BigInteger;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.util.Arrays;
import java.util.Base64;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        try {
  
            KeyExchangeService keyExchangeService = new KeyExchangeService();
            EncryptionService encryptionService = new EncryptionService();
            DigitalSignatureService digitalSignatureService = new DigitalSignatureService();
            MessageService messageService = new MessageService();


            KeyPair user1KeyPair = keyExchangeService.diffieHellmanKeyExchange(); // No arguments
            PrivateKey user1PrivateKey = user1KeyPair.getPrivate();
            PublicKey user1PublicKey = user1KeyPair.getPublic();

  
            KeyPair user2KeyPair = keyExchangeService.diffieHellmanKeyExchange(); // No arguments
            PrivateKey user2PrivateKey = user2KeyPair.getPrivate();
            PublicKey user2PublicKey = user2KeyPair.getPublic();

     
            KeyAgreement keyAgreementUser1 = KeyAgreement.getInstance("DH");
            keyAgreementUser1.init(user1PrivateKey);
            keyAgreementUser1.doPhase(user2PublicKey, true);
            byte[] user1SharedSecret = keyAgreementUser1.generateSecret();

            KeyAgreement keyAgreementUser2 = KeyAgreement.getInstance("DH");
            keyAgreementUser2.init(user2PrivateKey);
            keyAgreementUser2.doPhase(user1PublicKey, true);
            byte[] user2SharedSecret = keyAgreementUser2.generateSecret();

            if (java.util.Arrays.equals(user1SharedSecret, user2SharedSecret)) {
                System.out.println("Shared secret keys match.");
            } else {
                System.out.println("Shared secret keys do not match.");
            }

            SecretKey desKey = encryptionService.generateKey("DES", 56); 
            IvParameterSpec iv = new IvParameterSpec(new byte[8]); 

          String[] messages = {
                "Short Message",
                "A Message that is a little bit longer",
                "A Message that is the longest to demonstrate the code application of encryption and decryption using DES"
            };
            

            for (String message : messages) {

                byte[] messageBytes = message.getBytes();

                byte[] encryptedDataBytes = encryptionService.encryptDES(messageBytes, desKey, "ECB", iv);
                byte[] decryptedDataBytes = encryptionService.decryptDES(encryptedDataBytes, desKey, "ECB", iv);

                String encryptedData = Base64.getEncoder().encodeToString(encryptedDataBytes);
                String decryptedData = new String(decryptedDataBytes);

                System.out.println("Original: " + message);
                System.out.println("Encrypted: " + encryptedData);
                System.out.println("Decrypted: " + decryptedData);
                System.out.println();
            
            }

            byte[] symmetricKey = encryptionService.generateKey("AES", 128).getEncoded();

            String originalMessage = "Text message te";


            String encryptedMessage = messageService.sendMessage(originalMessage, symmetricKey);
            System.out.println("Encrypted Message: " + encryptedMessage);


            String decryptedMessage = messageService.receiveMessage(encryptedMessage, symmetricKey);
            System.out.println("Decrypted Message: " + decryptedMessage);

            KeyPair signatureKeyPair = digitalSignatureService.generateKeyPair(2048);
            String signature = digitalSignatureService.generateSignature(originalMessage.getBytes(), signatureKeyPair.getPrivate());
            System.out.println("Digital Signature: " + signature);

            boolean isSignatureValid = digitalSignatureService.verifySignature(originalMessage.getBytes(), signature, signatureKeyPair.getPublic());
            System.out.println("Is Signature Valid: " + isSignatureValid);

            StreamCipherDemo demo = new StreamCipherDemo();
            demo.demonstrateStreamCipher();
            demoDigitalSignature();
            demoDigitalSignatureWithTimestamp(digitalSignatureService, signatureKeyPair);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void demoDigitalSignature() throws Exception {
        DigitalSignatureService digitalSignatureService = new DigitalSignatureService();
        KeyPair keyPair = digitalSignatureService.generateKeyPair(2048);


        String originalData = "This is the original document.";
        byte[] originalDataBytes = originalData.getBytes();


        String signature = digitalSignatureService.generateSignature(originalDataBytes, keyPair.getPrivate());

        boolean isOriginalValid = digitalSignatureService.verifySignature(originalDataBytes, signature, keyPair.getPublic());
        System.out.println("Original signature valid: " + isOriginalValid);

        String alteredData = "This is the altered document.";
        byte[] alteredDataBytes = alteredData.getBytes();

 
        boolean isAlteredValid = digitalSignatureService.verifySignature(alteredDataBytes, signature, keyPair.getPublic());
        System.out.println("Altered signature valid: " + isAlteredValid);
    }

 
    private static void demoDigitalSignatureWithTimestamp(DigitalSignatureService digitalSignatureService, KeyPair keyPair) throws Exception {
        String originalMessage = "This is a test message.";
    
   
        byte[] messageBytes = originalMessage.getBytes();
    
  
        String signatureWithTimestamp = digitalSignatureService.generateSignatureWithTimestamp(messageBytes, keyPair.getPrivate());
        System.out.println("Signature with Timestamp: " + signatureWithTimestamp);
    

        boolean isSignatureValid = digitalSignatureService.verifySignatureWithTimestamp(messageBytes, signatureWithTimestamp, keyPair.getPublic(), 60000); // 60 seconds tolerance
        System.out.println("Is signature with timestamp valid? " + isSignatureValid);
 
        String alteredMessage = "This is an altered message.";
        boolean isAlteredSignatureValid = digitalSignatureService.verifySignatureWithTimestamp(alteredMessage.getBytes(), signatureWithTimestamp, keyPair.getPublic(), 60000);
        System.out.println("Is altered message signature valid? " + isAlteredSignatureValid);
    }
}
