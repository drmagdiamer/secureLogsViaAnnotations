package com.drmagdiamer.logAnnotation.secureLogAnnotation.core;

import com.drmagdiamer.logAnnotation.secureLogAnnotation.definition.LogSecurityMasker;

public class SSNMasker implements LogSecurityMasker {
  public String mask(String input) {
    if (input == null) return null;
    if (input.trim().isEmpty()) return "<EMPTY>";
    if (input.length() > 3) return "***-" + input.substring(input.length() - 3);
    else return "***";
  }
}
