package com.edu.hcmuaf.fit.webbanvemaybay.services.core;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAHelper {

    // Hàm kiểm tra chữ ký số có hợp lệ hay không
    public static boolean verifySignature(String dataGoc, String signatureBase64, String publicKeyBase64) {
        try {
            // Chuyển Public Key từ chuỗi Base64 về đối tượng PublicKey trong Java
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64.trim());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = kf.generatePublic(spec);

            // Cấu hình giải thuật SHA256withRSA để xác thực
            Signature publicSignature = Signature.getInstance("SHA256withRSA");
            publicSignature.initVerify(publicKey);
            publicSignature.update(dataGoc.getBytes("UTF-8"));

            // Giải mã chuỗi chữ ký số từ Base64
            byte[] signatureBytes = Base64.getDecoder().decode(signatureBase64.trim());
            return publicSignature.verify(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}