package com.marcohc.architecture.common.helper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppInfoHelperTest {

    @Before
    public void before() {
        Context context = InstrumentationRegistry.getTargetContext();
        PreferencesHelper.setUp(context);
        AppInfoHelper.setUp(PreferencesHelper.getInstance());
        PreferencesHelper.getInstance().clear();
    }

    @Test
    public void testIsFirstAppExecutionTrue() {
        assertTrue(AppInfoHelper.getInstance().isFirstAppExecution());
    }

    @Test
    public void testIsFirstAppExecutionFalse() {
        assertTrue(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
    }

    @Test
    public void testIsFirstAppExecutionTricky() {
        assertTrue(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
        AppInfoHelper.getInstance().forceFirstAppExecution();
        assertTrue(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
    }

    @Test
    public void testForceFirstAppExecution() {
        assertTrue(AppInfoHelper.getInstance().isFirstAppExecution());
        assertFalse(AppInfoHelper.getInstance().isFirstAppExecution());
        AppInfoHelper.getInstance().forceFirstAppExecution();
        assertTrue(AppInfoHelper.getInstance().isFirstAppExecution());
    }

    @Test
    public void testIsFirstUseTodayTrue() {
        assertTrue(AppInfoHelper.getInstance().isFirstUseToday());
    }

    @Test
    public void testIsFirstUseTodayFalse() {
        AppInfoHelper.getInstance().trackLastAppExecution();
        assertFalse(AppInfoHelper.getInstance().isFirstUseToday());
    }

    @Test
    public void testIsFirstUseTodayMix() {
        assertTrue(AppInfoHelper.getInstance().isFirstUseToday());
        AppInfoHelper.getInstance().trackLastAppExecution();
        assertFalse(AppInfoHelper.getInstance().isFirstUseToday());
        PreferencesHelper.getInstance().clear();
        assertTrue(AppInfoHelper.getInstance().isFirstUseToday());
    }

    @Test
    public void testIsFirstUseTodayTrickyFalse() {
        PreferencesHelper.getInstance().putLong("last_app_execution", System.currentTimeMillis() - 3600 * 1000);
        assertFalse(AppInfoHelper.getInstance().isFirstUseToday());
    }

    @Test
    public void testIsFirstUseTodayTrickyTrue() {
        PreferencesHelper.getInstance().putLong("last_app_execution", System.currentTimeMillis() - 3600 * 25 * 1000);
        assertTrue(AppInfoHelper.getInstance().isFirstUseToday());
    }

    @Test
    public void testIsFirstUseLast24HoursTrue() {
        assertTrue(AppInfoHelper.getInstance().isFirstUseLast24Hours());
    }

    @Test
    public void testIsFirstUseLast24HoursFalse() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -3);
        PreferencesHelper.getInstance().putLong("last_app_execution", calendar.getTimeInMillis());
        assertFalse(AppInfoHelper.getInstance().isFirstUseLast24Hours());
    }

    @Test
    public void testIsFirstUseLast24HoursMix() {
        assertTrue(AppInfoHelper.getInstance().isFirstUseLast24Hours());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -15);
        PreferencesHelper.getInstance().putLong("last_app_execution", calendar.getTimeInMillis());
        assertFalse(AppInfoHelper.getInstance().isFirstUseLast24Hours());
        PreferencesHelper.getInstance().clear();
        assertTrue(AppInfoHelper.getInstance().isFirstUseLast24Hours());
    }

    @Test
    public void testIsFirstUseLast24HoursTrickyTrue1() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        calendar.add(Calendar.MINUTE, -1);
        PreferencesHelper.getInstance().putLong("last_app_execution", calendar.getTimeInMillis());
        assertTrue(AppInfoHelper.getInstance().isFirstUseLast24Hours());
    }

    @Test
    public void testIsFirstUseLast24HoursTrickyTrue2() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -36);
        PreferencesHelper.getInstance().putLong("last_app_execution", calendar.getTimeInMillis());
        assertTrue(AppInfoHelper.getInstance().isFirstUseLast24Hours());
    }

    @Test
    public void testIsFirstUseLast24HoursTrickyFalse() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        PreferencesHelper.getInstance().putLong("last_app_execution", calendar.getTimeInMillis());
        assertTrue(AppInfoHelper.getInstance().isFirstUseLast24Hours());
    }

    @Test
    public void testGetUniqueIdNotEmpty() {
        assertNotNull(AppInfoHelper.getInstance().getUniqueId());
        assertFalse(StringHelper.isEmpty(AppInfoHelper.getInstance().getUniqueId()));
    }

    @Test
    public void testGetUniqueIdEqual() {
        assertEquals(AppInfoHelper.getInstance().getUniqueId(), AppInfoHelper.getInstance().getUniqueId());
    }

}