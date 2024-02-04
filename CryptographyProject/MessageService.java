import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class MessageService {
    private final EncryptionService encryptionService;
    private final DigitalSignatureService digitalSignatureService;
    private final KeyPair keyPair;

    public MessageService() throws Exception {
        this.encryptionService = new EncryptionService();
        this.digitalSignatureService = new DigitalSignatureService();
        this.keyPair = digitalSignatureService.generateKeyPair(2048);
    }

    /**
     * Sends an encrypted and signed message.
     *
     * @param message The message to send.
     * @param encryptionKey The encryption key.
     * @return The encrypted message.
     * @throws Exception If encryption or signing fails.
     */
    public String sendMessage(String message, byte[] encryptionKey) throws Exception {
        // Encrypt the message
        String encryptedMessage = encryptionService.encryptStreamCipher(message, encryptionKey);

        // Sign the encrypted message
        PrivateKey privateKey = keyPair.getPrivate();
        String signature = digitalSignatureService.generateSignature(encryptedMessage.getBytes(), privateKey);

        // Combine the encrypted message with the signature
        return encryptedMessage + ":" + signature;
    }

    /**
     * Receives an encrypted and signed message, verifies the signature, and decrypts the message.
     *
     * @param encryptedMessageWithSignature The encrypted message with a signature.
     * @param encryptionKey The decryption key.
     * @return The decrypted message if the signature is valid, null otherwise.
     * @throws Exception If decryption or signature verification fails.
     */
    public String receiveMessage(String encryptedMessageWithSignature, byte[] encryptionKey) throws Exception {
        // Split the encrypted message and the signature
        String[] parts = encryptedMessageWithSignature.split(":");
        if (parts.length != 2) throw new IllegalArgumentException("Invalid message format");

        String encryptedMessage = parts[0];
        String signature = parts[1];

        // Verify the signature
        PublicKey publicKey = keyPair.getPublic();
        if (!digitalSignatureService.verifySignature(encryptedMessage.getBytes(), signature, publicKey)) {
            throw new SecurityException("Signature verification failed");
        }

        // Decrypt the message
        return encryptionService.decryptStreamCipher(encryptedMessage, encryptionKey);
    }

    /**
     * Demonstrates the encryption and decryption process using the services.
     *
     * @param message The message to encrypt and decrypt.
     * @param encryptionKey The encryption key.
     * @throws Exception If there is a cryptographic failure.
     */
    public void demoEncryptionDecryption(String message, byte[] encryptionKey) throws Exception {
        String sentMessage = sendMessage(message, encryptionKey);
        System.out.println("Sent message: " + sentMessage);

        String receivedMessage = receiveMessage(sentMessage, encryptionKey);
        System.out.println("Received message: " + receivedMessage);
    }
}