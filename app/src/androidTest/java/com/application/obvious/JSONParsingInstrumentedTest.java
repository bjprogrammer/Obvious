package com.application.obvious;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.application.obvious.model.ImageList;
import com.application.obvious.utils.HelperFunctions;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class JSONParsingInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        ImageList imageList = new Gson().fromJson(HelperFunctions.fetchData(appContext), ImageList.class);
        assertEquals(26, imageList.getData().size());
    }
}
