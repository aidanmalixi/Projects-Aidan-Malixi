import java.math.BigInteger;
import java.security.*;
import javax.crypto.spec.DHParameterSpec;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;

public class KeyExchangeService {
    private SecureRandom secureRandom;
    private final BigInteger prime;
    private final BigInteger root;

    public KeyExchangeService() {
        this.secureRandom = new SecureRandom();
        this.prime = new BigInteger("100149999534532491790598334835182887872406107303936094855823798431436613444740594113225880523447963414297889965620986765404916834694114286237030775620735063795860978107018854722996225721191554252857963267257214772192894331024884016741379303173608192996053591713049458404161110007313668377787523013710540799821");
        this.root = BigInteger.valueOf(2);
    }


    public BigInteger[] generatePrimeAndRoot() {

        return new BigInteger[]{prime, root};
    }

    public KeyPair diffieHellmanKeyExchange() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        DHParameterSpec dhSpec = new DHParameterSpec(this.prime, this.root);
        keyPairGenerator.initialize(dhSpec, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }


    public byte[] lfsrKeyGeneration(int seed, int[] taps, int length) {
        int lfsr = seed;
        byte[] key = new byte[length / 8];

        for (int i = 0; i < length; i++) {
            int lsb = lfsr & 1;
            lfsr >>= 1;
            if (lsb == 1) {
                for (int tap : taps) {
                    lfsr ^= 1 << (tap - 1);
                }
            }
            if (i % 8 == 0) {
                key[i / 8] = (byte) (lsb << 7);
            } else {
                key[i / 8] |= lsb << (7 - i % 8);
            }
        }
        return key;
    }

    public KeyPair rsaKeyExchange(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    public BigInteger findPrimitiveRoot(BigInteger p) {
        BigInteger phi = p.subtract(BigInteger.ONE);
        Set<BigInteger> factors = primeFactors(phi);
        for (BigInteger g = BigInteger.valueOf(2); g.compareTo(p) < 0; g = g.add(BigInteger.ONE)) {
            boolean isGenerator = true;
            for (BigInteger factor : factors) {
                if (g.modPow(phi.divide(factor), p).equals(BigInteger.ONE)) {
                    isGenerator = false;
                    break;
                }
            }
            if (isGenerator) {
                return g;
            }
        }
        return BigInteger.valueOf(-1);
    }
public byte[] encryptWithRSA(PublicKey publicKey, byte[] data) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return cipher.doFinal(data);
}

public byte[] decryptWithRSA(PrivateKey privateKey, byte[] encryptedData) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return cipher.doFinal(encryptedData);
}
    private Set<BigInteger> primeFactors(BigInteger n) {
        Set<BigInteger> factors = new HashSet<>();
        while (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            factors.add(BigInteger.valueOf(2));
            n = n.divide(BigInteger.valueOf(2));
        }
        for (BigInteger i = BigInteger.valueOf(3); i.multiply(i).compareTo(n) <= 0; i = i.add(BigInteger.valueOf(2))) {
            while (n.mod(i).equals(BigInteger.ZERO)) {
                factors.add(i);
                n = n.divide(i);
            }
        }
        if (n.compareTo(BigInteger.valueOf(2)) > 0) {
            factors.add(n);
        }
        return factors;
    }
    public byte[] computeSharedSecret(PrivateKey ownPrivateKey, PublicKey otherUserPublicKey) throws Exception {
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(ownPrivateKey);
        keyAgreement.doPhase(otherUserPublicKey, true);
        return keyAgreement.generateSecret();
    }
}