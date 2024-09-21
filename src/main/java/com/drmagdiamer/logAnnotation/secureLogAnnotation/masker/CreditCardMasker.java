package com.drmagdiamer.logAnnotation.secureLogAnnotation.masker;

import com.drmagdiamer.logAnnotation.secureLogAnnotation.definition.LogSecurityMasker;

public class CreditCardMasker implements LogSecurityMasker {
  @Override
  public String mask(String input) {
    if (input == null) return null;
    if (input.trim().isEmpty()) return "<EMPTY>";
    if (input.length() > 4) return input.substring(0, 4) + "****";
    else return "***";
  }
}
