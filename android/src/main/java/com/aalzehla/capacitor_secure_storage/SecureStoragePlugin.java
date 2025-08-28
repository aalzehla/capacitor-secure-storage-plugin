package com.aalzehla.capacitor_secure_storage;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;

@CapacitorPlugin(name = "SecureStoragePlugin")
public class SecureStoragePlugin extends Plugin {

  private SecureStorage preferences;

  @Override
  public void load() {
    preferences = new SecureStorage(getContext(), SecureStorageConfiguration.DEFAULTS);
    preferences.init();
  }

  @PluginMethod
  public void get(PluginCall call) {
    String key = call.getString("key");
    if (key == null) {
      call.reject("Must provide key");
      return;
    }

    String value = preferences.get(key);

    JSObject ret = new JSObject();
    ret.put("value", value == null ? JSObject.NULL : value);
    call.resolve(ret);
  }

  @PluginMethod
  public void getValues(PluginCall call) {
    // Get the keys array from JS
    List<String> keys = null;
    try {
      keys = Arrays.asList(call.getArray("keys").toList().toArray(new String[0]));
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    // Get map from SharedPreferences
    Map<String, String> valuesMap = preferences.getValues(keys);

    // Convert map to JSObject
    JSObject result = new JSObject();
    for (Map.Entry<String, String> entry : valuesMap.entrySet()) {
        result.put(entry.getKey(), entry.getValue());
    }

    // Send result back to JS
    call.resolve(result);
  }

  @PluginMethod
  public void getAll(PluginCall call) {

    Map<String, ?> value = preferences.getAll();

    JSObject result = new JSObject();
    for (Map.Entry<String, ?> entry : value.entrySet()) {
        result.put(entry.getKey(), entry.getValue());
    }
    call.resolve(result);
  }

  @PluginMethod
  public void set(PluginCall call) {
    String key = call.getString("key");
    if (key == null) {
      call.reject("Must provide key");
      return;
    }

    String value = call.getString("value");
    preferences.set(key, value);

    call.resolve();
  }

  @PluginMethod
  public void remove(PluginCall call) {
    String key = call.getString("key");
    if (key == null) {
      call.reject("Must provide key");
      return;
    }

    preferences.remove(key);

    call.resolve();
  }

  @PluginMethod
  public void keys(PluginCall call) {
    Set<String> keySet = preferences.keys();
    String[] keys = keySet.toArray(new String[0]);

    JSObject ret = new JSObject();
    try {
      ret.put("keys", new JSArray(keys));
    } catch (JSONException ex) {
      call.reject("Unable to serialize response.", ex);
      return;
    }
    call.resolve(ret);
  }

  @PluginMethod
  public void clear(PluginCall call) {
    preferences.clear();
    call.resolve();
  }

  @PluginMethod
  public void migrate(PluginCall call) {
    List<String> migrated = new ArrayList<>();
    List<String> existing = new ArrayList<>();
    SecureStorage oldPreferences = new SecureStorage(getContext(), SecureStorageConfiguration.DEFAULTS);

    for (String key : oldPreferences.keys()) {
      String value = oldPreferences.get(key);
      String currentValue = preferences.get(key);

      if (currentValue == null) {
        preferences.set(key, value);
        migrated.add(key);
      } else {
        existing.add(key);
      }
    }

    JSObject ret = new JSObject();
    ret.put("migrated", new JSArray(migrated));
    ret.put("existing", new JSArray(existing));
    call.resolve(ret);
  }

  @PluginMethod
  public void removeOld(PluginCall call) {
    call.resolve();
  }
}
