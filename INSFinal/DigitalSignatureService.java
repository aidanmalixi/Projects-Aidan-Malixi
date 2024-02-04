import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.*;
import java.util.Base64;

public class DigitalSignatureService {
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";


    public String generateSignature(byte[] data, PrivateKey privateKey)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);

signature.initSign(privateKey);


signature.update(data);

byte[] digitalSignature = signature.sign();
return Base64.getEncoder().encodeToString(digitalSignature);
}





public boolean verifySignature(byte[] data, String signatureToVerify, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
signature.initVerify(publicKey);
signature.update(data);
byte[] signatureBytes = Base64.getDecoder().decode(signatureToVerify);
return signature.verify(signatureBytes);
}


public String generateSignatureWithTimestamp(byte[] data, PrivateKey privateKey)
throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {
Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
signature.initSign(privateKey);

long timestamp = System.currentTimeMillis();
ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
byteArrayOutputStream.write(data);
byteArrayOutputStream.write(ByteBuffer.allocate(Long.BYTES).putLong(timestamp).array());

byte[] dataWithTimestamp = byteArrayOutputStream.toByteArray();
signature.update(dataWithTimestamp);

byte[] digitalSignature = signature.sign();
return Base64.getEncoder().encodeToString(digitalSignature);
}


    public boolean verifySignatureWithTimestamp(byte[] data, String signatureToVerify, PublicKey publicKey, long acceptableTimeRangeMillis)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {
Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
signature.initVerify(publicKey);

byte[] signatureBytes = Base64.getDecoder().decode(signatureToVerify);


ByteBuffer buffer = ByteBuffer.wrap(signatureBytes);
long timestamp = buffer.getLong();

long currentTimestamp = System.currentTimeMillis();
if (Math.abs(currentTimestamp - timestamp) > acceptableTimeRangeMillis) {
    return false;
}

signature.update(data);
return signature.verify(signatureBytes);
}


    public KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.generateKeyPair();
    }
}