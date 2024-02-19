package lab8;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;

public class rsadsign {
    public static void main(String... argv) throws Exception {
//        to generate a public private key pair
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

//        private key can be used to sign a message
//        public keyholder to verify the message

        String message = "To Be or not To Be";

        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(pair.getPrivate());
        privateSignature.update(message.getBytes(StandardCharsets.UTF_8));

        byte[] signature = privateSignature.sign();

        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(pair.getPublic());
        publicSignature.update(message.getBytes(StandardCharsets.UTF_8));
        boolean isCorrect = publicSignature.verify(signature);

        System.out.println("Signature correct: " + isCorrect);

//        public key can be used to encrypt a message,  private key can be used to decrypt it.
//        encrypt
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());

        byte[] cipherText = encryptCipher.doFinal(message.getBytes());

//        decrypt
        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());

        String decipheredMessage = new String(decriptCipher.doFinal(cipherText), StandardCharsets.UTF_8);

        System.out.println(decipheredMessage);
    }
}