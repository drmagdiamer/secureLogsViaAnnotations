package com.drmagdiamer.logAnnotation.secureLogAnnotation.core;

import com.drmagdiamer.logAnnotation.secureLogAnnotation.definition.LogSecurityEncoder;
import com.drmagdiamer.logAnnotation.util.EncoderHelper;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DefaultEncryptor implements LogSecurityEncoder {
  @Override
  public String encrypt(String input) {
    try {
      return EncoderHelper.encrypt(input);
    } catch (Exception e) {
      log.error("Error while encrypting the input", e);
      return "EXCEPTION";
    }
  }

  @Override
  public String decrypt(String input) {
    try {
      return EncoderHelper.decrypt(input);
    } catch (Exception e) {
      log.error("Error while encrypting the input", e);
      return "EXCEPTION";
    }
  }
}
