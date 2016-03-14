package com.marcohc.architecture.common.helper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SecurePreferencesHelperTest {

    Context context;

    @Before
    public void before() {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test(expected = PreferencesMethods.PreferencesException.class)
    public void testSetUpThrowsException() {
        SecurePreferencesHelper.setUp(null);
    }

    @Test
    public void testSetUpDoesNotThrowsException() {
        SecurePreferencesHelper.setUp(context);
    }

    @Test(expected = PreferencesMethods.PreferencesException.class)
    public void testGetInstanceThrowsException() {
        SecurePreferencesHelper.clearInstance();
        SecurePreferencesHelper.getInstance();
    }

    @Test
    public void testGetInstanceDoesNotThrowsException() {
        SecurePreferencesHelper.setUp(context);
        assertNotEquals(SecurePreferencesHelper.getInstance(), null);
    }

    @Test
    public void testGetStringCorrectValue() {
        SecurePreferencesHelper.setUp(context);
        SecurePreferencesHelper.getInstance().putString("KEY", "VALUE");
        assertEquals(SecurePreferencesHelper.getInstance().getString("KEY", null), "VALUE");
    }

    @Test
    public void testGetStringWrongValue() {
        SecurePreferencesHelper.setUp(context);
        SecurePreferencesHelper.getInstance().putString("KEY", "VALUE");
        assertNotEquals(SecurePreferencesHelper.getInstance().getString("KEY", null), "ANOTHER_VALUE");
    }

}