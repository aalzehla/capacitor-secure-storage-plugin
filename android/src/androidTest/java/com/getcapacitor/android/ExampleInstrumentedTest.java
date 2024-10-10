package com.getcapacitor.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.aalzehla.capacitor_secure_storage.SecureStoragePlugin;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

  @Test
  public void useAppContext() throws Exception {
    Context appContext = InstrumentationRegistry.getInstrumentation()
      .getTargetContext();
    assertEquals(
      "com.aalzehla.capacitor_secure_storage.test",
      appContext.getPackageName()
    );
  }
}
