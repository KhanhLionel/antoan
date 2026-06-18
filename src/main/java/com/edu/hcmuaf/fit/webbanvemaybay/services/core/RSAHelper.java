package com.edu.hcmuaf.fit.webbanvemaybay.services.core;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAHelper {

    public static boolean verifySignature(String data, String signatureBase64, String publicKeyPemOrBase64) {
        try {
            PublicKey publicKey = loadPublicKey(publicKeyPemOrBase64);

            Signature publicSignature = Signature.getInstance("SHA256withRSA");
            publicSignature.initVerify(publicKey);
            publicSignature.update(data.getBytes(StandardCharsets.UTF_8));

            byte[] signatureBytes = Base64.getDecoder().decode(signatureBase64.trim());
            return publicSignature.verify(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static PublicKey loadPublicKey(String publicKeyPemOrBase64) throws Exception {
        String publicKeyContent = publicKeyPemOrBase64
                .replace("begin public key", "")
                .replace("end public key", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(publicKeyContent);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePublic(spec);
    }

    public static String sha256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) {
                hex.append(String.format("%02x", b));
            }

            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("Không thể tạo SHA-256 hash", e);
        }
    }

    public static String createPublicKeyFingerprint(String publicKeyPemOrBase64) {
        try {
            String publicKeyContent = publicKeyPemOrBase64
                    .replace("begin public key", "")
                    .replace("end public key", "")
                    .replaceAll("\\s", "");

            return sha256(publicKeyContent);
        } catch (Exception e) {
            throw new RuntimeException("Không thể tạo fingerprint cho public key", e);
        }
    }
}