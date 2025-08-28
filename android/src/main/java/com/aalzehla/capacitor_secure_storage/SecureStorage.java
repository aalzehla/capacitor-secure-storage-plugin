package com.aalzehla.capacitor_secure_storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class SecureStorage {

  private SharedPreferences preferences;

  private interface PreferencesOperation {
    void execute(SharedPreferences.Editor editor);
  }

  SecureStorage(Context context, SecureStorageConfiguration configuration) {
//    this.preferences = context.getSharedPreferences(configuration.group, Activity.MODE_PRIVATE);
    this.preferences = this.getEncryptedPreferences(context, configuration);
  }
  public void init() {
    set("auth", "false");
  }
  private SharedPreferences getEncryptedPreferences(Context context, SecureStorageConfiguration configuration){
    try{
      String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
      return EncryptedSharedPreferences.create(
        configuration.group,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
      );
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public String get(String key) {
    return preferences.getString(key, null);
  }

  public Map<String, String> getValues(List<String> keys) {
    Map<String, String> resultMap = new HashMap<>();
    for (String key : keys) {
      String value = preferences.getString(key, "");
      resultMap.put(key, value);
    }
    return resultMap;
  }

  public void set(String key, String value) {
    executeOperation(editor -> editor.putString(key, value));
  }

  public void remove(String key) {
    executeOperation(editor -> editor.remove(key));
  }

  public Set<String> keys() {
    return preferences.getAll().keySet();
  }

  public Map<String, ?> getAll() {
    return preferences.getAll();
  }

  public void clear() {
    executeOperation(SharedPreferences.Editor::clear);
  }

  private void executeOperation(PreferencesOperation op) {
    SharedPreferences.Editor editor = preferences.edit();
    op.execute(editor);
    editor.apply();
  }
}
