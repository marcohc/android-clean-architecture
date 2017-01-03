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
public class SecurePreferencesTest {

    Context context;

    private SecurePreferences securePreferences;

    @Before
    public void before() {
        context = InstrumentationRegistry.getTargetContext();
        securePreferences = new SecurePreferences(context);
        
    }

    @Test
    public void testGetStringCorrectValue() {
        securePreferences.putString("KEY", "VALUE");
        assertEquals(securePreferences.getString("KEY", null), "VALUE");
    }

    @Test
    public void testGetStringWrongValue() {
        securePreferences.putString("KEY", "VALUE");
        assertNotEquals(securePreferences.getString("KEY", null), "ANOTHER_VALUE");
    }

}