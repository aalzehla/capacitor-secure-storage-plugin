package com.aalzehla.capacitor_secure_storage;

public class PreferencesConfiguration implements Cloneable {

  static final PreferencesConfiguration DEFAULTS;

  static {
    DEFAULTS = new PreferencesConfiguration();
    DEFAULTS.group = "MPayStorage";
  }

  String group;

  @Override
  public PreferencesConfiguration clone() throws CloneNotSupportedException {
    return (PreferencesConfiguration) super.clone();
  }
}
