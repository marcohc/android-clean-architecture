package com.marcohc.architecture.common.helper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreferencesHelperTest {

    private Context context;
    private PreferencesHelper preferencesHelper;

    @Before
    public void before() {
        context = InstrumentationRegistry.getTargetContext();
        preferencesHelper = new PreferencesHelper(context);
    }

    @Test
    public void testGetStringCorrectValue() {
        preferencesHelper.putString("KEY", "VALUE");
        assertEquals(preferencesHelper.getString("KEY", null), "VALUE");
    }

    @Test
    public void testGetStringWrongValue() {
        preferencesHelper.putString("KEY", "VALUE");
        assertNotEquals(preferencesHelper.getString("KEY", null), "ANOTHER_VALUE");
    }

}