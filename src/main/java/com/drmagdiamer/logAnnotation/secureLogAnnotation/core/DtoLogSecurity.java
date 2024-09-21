package com.drmagdiamer.logAnnotation.secureLogAnnotation.core;

import com.drmagdiamer.logAnnotation.secureLogAnnotation.annotaion.Encrypted;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.annotaion.Masked;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.annotaion.Unrestricted;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.definition.LogSecurityEncoder;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.definition.LogSecurityMasker;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.encryptor.DefaultEncryptor;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.masker.DefaultMasker;

import java.lang.reflect.Field;
import java.util.*;

public abstract class DtoLogSecurity {

  private static LogSecurityMasker defaultMasker = new DefaultMasker();
  private static LogSecurityEncoder encryptor = new DefaultEncryptor();

  private static Map<String, LogSecurityMasker> maskLibrary = new HashMap<>();

  public static void addToMapLibrary(String name, LogSecurityMasker masker) {
    maskLibrary.put(name, masker);
  }

  public static void setEncryptor(LogSecurityEncoder encrypterObj) {
    DtoLogSecurity.encryptor = encrypterObj;
  }

  public static void setDefaultMasker(LogSecurityMasker maskerObj) {
    DtoLogSecurity.defaultMasker = maskerObj;
  }

  public String get(String fieldNameName) {
    try {
      Class<?> clazz = this.getClass();
      final Field field = clazz.getDeclaredField(fieldNameName);
      field.setAccessible(true);
      String value;
      if (field.get(this) != null) value = field.get(this).toString();
      else value = null;

      String maskeName;
      LogSecurityMasker masker;

      if (field.isAnnotationPresent(Masked.class)) {
        Masked r = field.getDeclaredAnnotation(Masked.class);
        maskeName = r.maskName();
        if (maskeName == null || maskeName.isEmpty()) {
          masker = defaultMasker;
        } else {
          masker = maskLibrary.get(maskeName);
          if (masker == null) throw new RuntimeException("No such masker : " + maskeName);
        }
        return (value == null) ? null : "\"" + masker.mask(value) + "\"";
      } else if (field.isAnnotationPresent(Encrypted.class)) {
        return (value == null) ? null : "\"" + encryptor.encrypt(value) + "\"";
      } else if (field.isAnnotationPresent(Unrestricted.class)) {
        return getValue(field, value);
      } else {
        return getValue(field, value);
      }

    } catch (Exception ex) {
      throw new RuntimeException(
          "HipaaComplianceDataModel::get() -  Error in attribute name " + fieldNameName);
    }
  }

  @Override
  public String toString() {
    Class<?> clazz = this.getClass();
    TreeMap<Integer, Map<String, String>> fieldList = new TreeMap<>();
    int order;
    String value;
    String maskeName;
    LogSecurityMasker masker;
    for (Field field : clazz.getDeclaredFields()) {
      value = null;
      field.setAccessible(true);
      try {
        if (field.get(this) != null) value = field.get(this).toString();
        else value = null;
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
      if (field.isAnnotationPresent(Masked.class)) {
        Masked r = field.getDeclaredAnnotation(Masked.class);
        order = r.order();
        maskeName = r.maskName();
        if (maskeName == null || maskeName.length() == 0) {
          masker = defaultMasker;
        } else {
          masker = maskLibrary.get(maskeName);
          if (masker == null) throw new RuntimeException("No such masker : " + maskeName);
        }
        Map<String, String> fields = getFieldList(fieldList, order);
        fields.put(field.getName(), (value == null) ? null : "\"" + masker.mask(value) + "\"");
      } else if (field.isAnnotationPresent(Encrypted.class)) {
        Encrypted s = field.getDeclaredAnnotation(Encrypted.class);
        order = s.order();
        Map<String, String> fields = getFieldList(fieldList, order);
        fields.put(
            field.getName(), (value == null) ? null : "\"" + encryptor.encrypt(value) + "\"");
      } else if (field.isAnnotationPresent(Unrestricted.class)) {
        Unrestricted u = field.getDeclaredAnnotation(Unrestricted.class);
        order = u.order();
        Map<String, String> fields = getFieldList(fieldList, order);
        fields.put(field.getName(), getValue(field, value));
      } else {
        Map<String, String> fields = getFieldList(fieldList, Integer.MAX_VALUE);
        fields.put(field.getName(), getValue(field, value));
      }
    }
    StringBuffer result = new StringBuffer();
    result.append("{");
    Set<Integer> keys = fieldList.keySet();
    ArrayList<Integer> sortedKeys = new ArrayList<>(keys);
    Collections.sort(sortedKeys);
    boolean first = true;
    for (Integer orderedKey : sortedKeys) {
      Map<String, String> fields = fieldList.get(orderedKey);
      Set<String> names = fields.keySet();
      for (String name : names) {
        if (!first) result.append(",");
        result.append("\"").append(name).append("\"").append(":").append(fields.get(name));
        first = false;
      }
    }

    result.append("}");
    return result.toString();
  }

  String getValue(Field field, String value) {
    if (value == null) return null;
    if (field.getType() == String.class) {
      return "\"" + value + "\"";
    }
    if (field.getClass().isPrimitive()) {
      return value;
    }

    return value;
  }

  private Map<String, String> getFieldList(
      TreeMap<Integer, Map<String, String>> fieldList, int order) {
    if (!fieldList.containsKey(order)) {
      Map<String, String> map = new TreeMap<>();
      fieldList.put(order, map);
    }
    return fieldList.get(order);
  }
}
