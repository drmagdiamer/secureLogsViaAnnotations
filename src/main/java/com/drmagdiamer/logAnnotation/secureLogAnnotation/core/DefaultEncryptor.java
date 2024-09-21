package com.drmagdiamer.logAnnotation.secureLogAnnotation.core;

import com.drmagdiamer.logAnnotation.secureLogAnnotation.definition.LogSecurityEncoder;
import com.drmagdiamer.logAnnotation.util.EncoderHelper;
import lombok.extern.log4j.Log4j2;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.spec.PBEParameterSpec;

@Log4j2
public class DefaultEncryptor implements LogSecurityEncoder {
    @Override
    public String encrypt(String input) {
        try{
        return EncoderHelper.encrypt(input);
        }catch(Exception e) {
            log.error("Error while encrypting the input", e);
            return "EXCEPTION";
        }
    }

    @Override
    public String decrypt(String input) {
        try{
            return EncoderHelper.decrypt(input);
        }catch(Exception e) {
            log.error("Error while encrypting the input", e);
            return "EXCEPTION";
        }
    }


}
