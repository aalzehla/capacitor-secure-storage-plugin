package com.aalzehla.securestorage;

public class SecureStorageConfiguration implements Cloneable {

  static final SecureStorageConfiguration DEFAULTS;

  static {
    DEFAULTS = new SecureStorageConfiguration();
    DEFAULTS.group = "MPayStorage";
  }

  String group;

  @Override
  public SecureStorageConfiguration clone() throws CloneNotSupportedException {
    return (SecureStorageConfiguration) super.clone();
  }
}
