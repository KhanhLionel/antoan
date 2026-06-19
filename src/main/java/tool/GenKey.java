package tool;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class GenKey {
    public  KeyPair genkey() throws NoSuchAlgorithmException {
        KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
        key.initialize(2048);

        return key.generateKeyPair();
    }
    public  void savePrivateKey(PrivateKey pri, File f) throws IOException {
        String key = Base64.getEncoder().encodeToString(pri.getEncoded());
        Files.writeString(f.toPath(), key);
    }
    public  void savePublicKey(PublicKey k , File f) throws IOException {
        String key = Base64.getEncoder().encodeToString(k.getEncoded());
        Files.writeString(f.toPath(), key,StandardCharsets.UTF_8);
    }


    public  PrivateKey importPrivateKey(String path) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        String key = Files.readString(Path.of(path));
        byte[] b =Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec =  new PKCS8EncodedKeySpec(b);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }
    public  String signFile(String f,PrivateKey k ) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String content = Files.readString(Path.of(f), StandardCharsets.UTF_8);
        byte[] data = content.getBytes(StandardCharsets.UTF_8);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(k);
        signature.update(data);
        byte[] signed = signature.sign();
        return Base64.getEncoder().encodeToString(signed);
    }
    public  void exportSign(String path, String sig) throws IOException {
        Files.writeString(
                Path.of(path),
                sig,StandardCharsets.UTF_8
        );
    }



}

