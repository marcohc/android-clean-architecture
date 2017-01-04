package com.marcohc.architecture.common.helper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.marcohc.architecture.common.service.preference.PreferencesService;
import com.marcohc.architecture.common.service.preferences.PreferencesServiceImpl;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreferencesServiceImplTest {

    private static final float FLOAT_VALUE = 1.5f;
    private static final float DEFAULT_FLOAT_VALUE = -1.0f;
    private static final long LONG_VALUE = 123;
    private static final long DEFAULT_LONG_VALUE = -1;
    private static final int INT_VALUE = 20;
    private static final int DEFAULT_INT_VALUE = -1;
    private static final String KEY = "key";
    private static final String STRING_VALUE = "value";
    private static final String DEFAULT_STRING_VALUE = "default_value";

    private Context context;
    private PreferencesService mPreferencesService;

    @Before
    public void before() {
        context = InstrumentationRegistry.getTargetContext();
        mPreferencesService = new PreferencesServiceImpl(context);
    }

    @Test
    public void testPutAndGetBoolean() {
        mPreferencesService.putBoolean(KEY, true);
        assertEquals(mPreferencesService.getBoolean(KEY, false), true);
    }

    @Test
    public void testPutAndGetFloat() {
        mPreferencesService.putFloat(KEY, FLOAT_VALUE);
        assertEquals(mPreferencesService.getFloat(KEY, DEFAULT_FLOAT_VALUE), FLOAT_VALUE);
    }

    @Test
    public void testPutAndGetInt() {
        mPreferencesService.putInt(KEY, INT_VALUE);
        assertEquals(mPreferencesService.getInt(KEY, DEFAULT_INT_VALUE), INT_VALUE);
    }

    @Test
    public void testPutAndGetLong() {
        mPreferencesService.putLong(KEY, LONG_VALUE);
        assertEquals(mPreferencesService.getLong(KEY, DEFAULT_LONG_VALUE), LONG_VALUE);
    }

    @Test
    public void testPutAndGetString() {
        mPreferencesService.putString(KEY, STRING_VALUE);
        assertEquals(mPreferencesService.getString(KEY, DEFAULT_STRING_VALUE), STRING_VALUE);
    }

    @Test
    public void testRemoveItem() {
        mPreferencesService.putString(KEY, STRING_VALUE);
        mPreferencesService.remove(KEY);
        assertEquals(mPreferencesService.getString(KEY, DEFAULT_STRING_VALUE), DEFAULT_STRING_VALUE);
    }

    @Test
    public void testClear() {
        mPreferencesService.putString(KEY, STRING_VALUE);
        mPreferencesService.clear();
        assertEquals(mPreferencesService.getString(KEY, DEFAULT_STRING_VALUE), DEFAULT_STRING_VALUE);
    }

}