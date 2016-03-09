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
public class PreferencesHelperTest {

    Context context;

    @Before
    public void before() {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test(expected = PreferencesHelper.PreferencesHelperException.class)
    public void testSetUpThrowsException() {
        PreferencesHelper.setUp(null);
    }

    @Test
    public void testSetUpDoesNotThrowsException() {
        PreferencesHelper.setUp(context);
    }

    @Test(expected = PreferencesHelper.PreferencesHelperException.class)
    public void testGetInstanceThrowsException() {
        PreferencesHelper.clearInstance();
        PreferencesHelper.getInstance();
    }

    @Test
    public void testGetInstanceDoesNotThrowsException() {
        PreferencesHelper.setUp(context);
        assertNotEquals(PreferencesHelper.getInstance(), null);
    }

    @Test
    public void testGetStringCorrectValue() {
        PreferencesHelper.setUp(context);
        PreferencesHelper.getInstance().putString("KEY", "VALUE");
        assertEquals(PreferencesHelper.getInstance().getString("KEY", null), "VALUE");
    }

    @Test
    public void testGetStringWrongValue() {
        PreferencesHelper.setUp(context);
        PreferencesHelper.getInstance().putString("KEY", "VALUE");
        assertNotEquals(PreferencesHelper.getInstance().getString("KEY", null), "ANOTHER_VALUE");
    }

}