import java.util.Base64;

public class StreamCipherDemo {

    public void demonstrateStreamCipher() {
        byte[] key = "exampleKey".getBytes(); // Sample key for demonstration

        // Test messages of various lengths
        String[] testMessages = {"Simple", "Simple Test Message", "Even more simple test message for stream cipher."};

        for (String message : testMessages) {
            String encrypted = encryptStreamCipher(message, key);
            String decrypted = decryptStreamCipher(encrypted, key);

            System.out.println("Original: " + message);
            System.out.println("Encrypted: " + encrypted);
            System.out.println("Decrypted: " + decrypted);
            System.out.println();
        }
    }

    private String encryptStreamCipher(String data, byte[] key) {
        byte[] dataBytes = data.getBytes();
        byte[] result = new byte[dataBytes.length];

        for (int i = 0; i < dataBytes.length; i++) {
            result[i] = (byte) (dataBytes[i] ^ key[i % key.length]);
        }

        return Base64.getEncoder().encodeToString(result);
    }

    private String decryptStreamCipher(String encryptedData, byte[] key) {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] result = new byte[encryptedBytes.length];

        for (int i = 0; i < encryptedBytes.length; i++) {
            result[i] = (byte) (encryptedBytes[i] ^ key[i % key.length]);
        }

        return new String(result);
    }
}