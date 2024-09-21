package com.drmagdiamer.logAnnotation.secureLogAnnotation.definition;

public interface LogSecurityEncoder {
    String encrypt(String input);
    String decrypt(String input);
}
