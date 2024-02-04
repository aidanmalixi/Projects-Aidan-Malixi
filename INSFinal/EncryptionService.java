import javax.crypto.*;
import javax.crypto.spec.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;
public class EncryptionService {
    private static final String shS = "AES";
    private static final String DES = "DES";

    public byte[] readImageFile(String filePath) throws IOException {
    File file = new File(filePath);
    try (FileInputStream fis = new FileInputStream(file)) {
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        return data;
    }
}

public void writeImageFile(byte[] data, String filePath) throws IOException {
    try (FileOutputStream fos = new FileOutputStream(filePath)) {
        fos.write(data);
    }
}
 
    public byte[] encryptAES(byte[] data, SecretKey key, String mode, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(shS + "/" + mode + "/PKCS5Padding");
        if ("ECB".equals(mode)) {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        }
        return cipher.doFinal(data);
    }
    
    public byte[] decryptAES(byte[] encryptedData, SecretKey key, String mode, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(shS + "/" + mode + "/PKCS5Padding");
        if ("ECB".equals(mode)) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
        }
        return cipher.doFinal(encryptedData);
    }

    public byte[] encryptDES(byte[] data, SecretKey key, String mode, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/" + mode + "/PKCS5Padding");
        if ("ECB".equals(mode)) {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        }
        return cipher.doFinal(data);
    }
    
    public byte[] decryptDES(byte[] encryptedData, SecretKey key, String mode, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/" + mode + "/PKCS5Padding");
        if ("ECB".equals(mode)) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
        }
        return cipher.doFinal(encryptedData);
    }

    public String encryptStreamCipher(String data, byte[] key) {
        byte[] dataBytes = data.getBytes();
        byte[] result = new byte[dataBytes.length];

        for (int i = 0; i < dataBytes.length; i++) {
            result[i] = (byte) (dataBytes[i] ^ key[i % key.length]);
        }

        return Base64.getEncoder().encodeToString(result);
    }

    public String decryptStreamCipher(String encryptedData, byte[] key) {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] result = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            result[i] = (byte) (encryptedBytes[i] ^ key[i % key.length]); 
        }
        return new String(result); 
    }

  
    public SecretKey generateKey(String algorithm, int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }
}

