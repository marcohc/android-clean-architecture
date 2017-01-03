package com.marcohc.architecture.common.helper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.marcohc.architecture.common.service.AppInfoService;
import com.marcohc.architecture.common.service.AppInfoServiceImpl;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Calendar;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppInfoServiceTest {

    private AppInfoService appInfoService;
    private PreferencesMethods preferenceMethods;

    @Before
    public void before() {
        Context context = InstrumentationRegistry.getTargetContext();
        appInfoService = new AppInfoServiceImpl(context);
        preferenceMethods = new PreferencesMethods(context);
        preferenceMethods.clear();
    }

    @Test
    public void testIsFirstAppExecutionTrue() {
        assertTrue(appInfoService.isFirstAppExecution());
    }

    @Test
    public void testIsFirstAppExecutionFalse() {
        assertTrue(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
    }

    @Test
    public void testIsFirstAppExecutionTricky() {
        assertTrue(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
        appInfoService.forceFirstAppExecution();
        assertTrue(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
    }

    @Test
    public void testForceFirstAppExecution() {
        assertTrue(appInfoService.isFirstAppExecution());
        assertFalse(appInfoService.isFirstAppExecution());
        appInfoService.forceFirstAppExecution();
        assertTrue(appInfoService.isFirstAppExecution());
    }

    @Test
    public void testIsFirstUseTodayTrue() {
        assertTrue(appInfoService.isFirstUseToday());
    }

    @Test
    public void testIsFirstUseTodayFalse() {
        appInfoService.trackLastAppExecution();
        assertFalse(appInfoService.isFirstUseToday());
    }

    @Test
    public void testIsFirstUseTodayMix() {
        assertTrue(appInfoService.isFirstUseToday());
        appInfoService.trackLastAppExecution();
        assertFalse(appInfoService.isFirstUseToday());
        preferenceMethods.clear();
        assertTrue(appInfoService.isFirstUseToday());
    }

    @Test
    public void testIsFirstUseTodayTrickyFalse() {
        preferenceMethods.putLong("last_app_execution", System.currentTimeMillis() - 3600 * 1000);
        assertFalse(appInfoService.isFirstUseToday());
    }

    @Test
    public void testIsFirstUseTodayTrickyTrue() {
        preferenceMethods.putLong("last_app_execution", System.currentTimeMillis() - 3600 * 25 * 1000);
        assertTrue(appInfoService.isFirstUseToday());
    }

    @Test
    public void testIsFirstUseLast24HoursTrue() {
        assertTrue(appInfoService.isFirstUseLast24Hours());
    }

    @Test
    public void testIsFirstUseLast24HoursFalse() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -3);
        preferenceMethods.putLong("last_app_execution", calendar.getTimeInMillis());
        assertFalse(appInfoService.isFirstUseLast24Hours());
    }

    @Test
    public void testIsFirstUseLast24HoursMix() {
        assertTrue(appInfoService.isFirstUseLast24Hours());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, -15);
        preferenceMethods.putLong("last_app_execution", calendar.getTimeInMillis());
        assertFalse(appInfoService.isFirstUseLast24Hours());
        preferenceMethods.clear();
        assertTrue(appInfoService.isFirstUseLast24Hours());
    }

    @Test
    public void testIsFirstUseLast24HoursTrickyTrue1() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        calendar.add(Calendar.MINUTE, -1);
        preferenceMethods.putLong("last_app_execution", calendar.getTimeInMillis());
        assertTrue(appInfoService.isFirstUseLast24Hours());
    }

    @Test
    public void testIsFirstUseLast24HoursTrickyTrue2() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -36);
        preferenceMethods.putLong("last_app_execution", calendar.getTimeInMillis());
        assertTrue(appInfoService.isFirstUseLast24Hours());
    }

    @Test
    public void testIsFirstUseLast24HoursTrickyFalse() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        preferenceMethods.putLong("last_app_execution", calendar.getTimeInMillis());
        assertTrue(appInfoService.isFirstUseLast24Hours());
    }

}