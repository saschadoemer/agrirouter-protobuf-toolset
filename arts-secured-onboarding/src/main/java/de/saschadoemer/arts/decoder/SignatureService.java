package de.saschadoemer.arts.decoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SignatureService {

    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    private byte[] decodeHex(String encodedSignature) {
        try {
            return Hex.decodeHex(encodedSignature.toCharArray());
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    public String createSignature(String privateKey, String publicKey, String jsonBody) {
        byte[] signature = this.createSignature(jsonBody, privateKey);
        this.verifySignature(jsonBody, signature, publicKey);
        String encodedSignature = Hex.encodeHexString(signature);
        this.verifySignature(jsonBody, decodeHex(encodedSignature), publicKey);
        return encodedSignature;
    }

    private byte[] createSignature(String requestBody, String privateKey) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(this.createPrivateKey(privateKey));
            signature.update(requestBody.getBytes(UTF_8));
            return signature.sign();
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private void verifySignature(String requestBody, byte[] signedBytes, String publicKey) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(this.createPublicKey(publicKey));
            signature.update(requestBody.getBytes(UTF_8));
            if (!signature.verify(signedBytes)) {
                throw new RuntimeException("Invalid signature.");
            }
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private PrivateKey createPrivateKey(String privateKey) {
        try {
            String pkcs8Pem = privateKey.replace("-----BEGIN PRIVATE KEY-----", "");
            pkcs8Pem = pkcs8Pem.replace("-----END PRIVATE KEY-----", "");
            pkcs8Pem = pkcs8Pem.replaceAll("\\s+", "");
            byte[] pkcs8EncodedBytes = Base64.getDecoder().decode(pkcs8Pem);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (IllegalArgumentException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private PublicKey createPublicKey(String publicKey) {
        try {
            String pkcs8Pem = publicKey.replace("-----BEGIN PUBLIC KEY-----", "");
            pkcs8Pem = pkcs8Pem.replace("-----END PUBLIC KEY-----", "");
            pkcs8Pem = pkcs8Pem.replaceAll("\\s+", "");
            byte[] pkcs8EncodedBytes = Base64.getDecoder().decode(pkcs8Pem);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pkcs8EncodedBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (IllegalArgumentException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
