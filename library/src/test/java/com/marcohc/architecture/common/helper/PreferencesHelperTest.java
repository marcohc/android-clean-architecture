package com.marcohc.architecture.common.helper;

import android.content.Context;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreferencesHelperTest {

    @Mock
    Context mMockContext;

    @Test(expected = PreferencesHelper.PreferencesHelperException.class)
    public void testSetUpThrowsException() {
        PreferencesHelper.setUp(null);
    }

    @Test
    public void testSetUpDoesNotThrowsException() {
        PreferencesHelper.setUp(mMockContext);
    }

    @Test(expected = PreferencesHelper.PreferencesHelperException.class)
    public void testGetInstanceThrowsException() {
        PreferencesHelper.clearInstance();
        PreferencesHelper.getInstance();
    }

    @Test
    public void testGetInstanceDoesNotThrowsException() {
        PreferencesHelper.setUp(mMockContext);
        assertNotEquals(PreferencesHelper.getInstance(), null);
    }

}