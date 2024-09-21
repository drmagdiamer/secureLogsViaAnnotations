package com.drmagdiamer.logAnnotation.secureLogAnnotation.core;

import com.drmagdiamer.logAnnotation.secureLogAnnotation.definition.LogSecurityMasker;

public class DefaultMasker implements LogSecurityMasker {
  @Override
  public String mask(String input) {
    if (input == null) return null;
    if (input.trim().isEmpty()) return "<EMPTY>";
    return "***[" + input.length() + "]";
  }
}
