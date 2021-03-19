package com.github.pulsebeat02.backdoor;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class CryptoHelper {

  private final Cipher cipher;
  private final Key key;

  public CryptoHelper(final String key, final String algo) throws Exception {
    this.key = new SecretKeySpec(key.getBytes(), algo);
    cipher = Cipher.getInstance(algo);
  }

  public String encrypt(final String plaintext) throws Exception {
    if (plaintext == null) {
      return null;
    }
    cipher.init(Cipher.ENCRYPT_MODE, key);
    final byte[] encrypted = cipher.doFinal(plaintext.getBytes());
    return Base64.getEncoder().encodeToString(encrypted);
  }

  public String decrypt(final String encrypted) throws Exception {
    if (encrypted == null) {
      return null;
    }
    cipher.init(Cipher.DECRYPT_MODE, key);
    final byte[] decordedValue = Base64.getDecoder().decode(encrypted);
    final byte[] decrypted = cipher.doFinal(decordedValue);
    return new String(decrypted);
  }
}
