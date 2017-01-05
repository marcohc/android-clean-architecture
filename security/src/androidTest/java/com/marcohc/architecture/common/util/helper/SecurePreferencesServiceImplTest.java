package com.marcohc.architecture.common.util.helper;

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
public class SecurePreferencesServiceImplTest {

    Context context;

    private SecurePreferencesServiceImpl mSecurePreferencesServiceImpl;

    @Before
    public void before() {
        context = InstrumentationRegistry.getTargetContext();
        mSecurePreferencesServiceImpl = new SecurePreferencesServiceImpl(context);
    }

    @Test
    public void testGetStringCorrectValue() {
        mSecurePreferencesServiceImpl.putString("KEY", "VALUE");
        assertEquals(mSecurePreferencesServiceImpl.getString("KEY", null), "VALUE");
    }

    @Test
    public void testGetStringWrongValue() {
        mSecurePreferencesServiceImpl.putString("KEY", "VALUE");
        assertNotEquals(mSecurePreferencesServiceImpl.getString("KEY", null), "ANOTHER_VALUE");
    }

}