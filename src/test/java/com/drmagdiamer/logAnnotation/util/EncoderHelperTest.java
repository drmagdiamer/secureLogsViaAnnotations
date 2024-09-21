package com.drmagdiamer.logAnnotation.util;

import static org.junit.jupiter.api.Assertions.*;

class EncoderHelperTest {

  @org.junit.jupiter.api.Test
  void encrypt() {
    try {
      String originalString = "SensitiveData123!";
      System.out.println("Original String: " + originalString);

      // Encrypt the string
      String encryptedString = EncoderHelper.encrypt(originalString);
      System.out.println("Encrypted String: " + encryptedString);
      assertNotNull(encryptedString);

      // Decrypt the string
      String decryptedString = EncoderHelper.decrypt(encryptedString);
      System.out.println("Decrypted String: " + decryptedString);
      assertEquals(originalString, decryptedString);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
