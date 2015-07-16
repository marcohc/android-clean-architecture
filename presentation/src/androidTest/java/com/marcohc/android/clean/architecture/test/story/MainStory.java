package com.marcohc.android.clean.architecture.test.story;

import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.marcohc.android.clean.architecture.data.util.PreferencesManager;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.LogInActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.MainActivity;
import com.marcohc.android.clean.architecture.test.util.Utils;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsAnything.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainStory extends ActivityInstrumentationTestCase2<MainActivity> {

    private Given given;
    private When when;
    private Then then;

    private MainActivity mActivity;

    public MainStory() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {

        // Basic initialization stuff
        super.setUp();
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        injectInstrumentation(instrumentation);
        mActivity = getActivity();

        // Delete previous preferences
        SharedPreferences preferences = instrumentation.getTargetContext().getSharedPreferences(PreferencesManager.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
        given = new Given();
        when = new When();
        then = new Then();
    }

    @Test
    public void checkPreconditions() {
        assertThat(mActivity, notNullValue());
        assertThat(getInstrumentation(), notNullValue());
    }

    @Test
    public void test1TheListIsLoaded() {

        Utils.showMessage(mActivity, "testTheListIsLoaded");

        Utils.waitForIt(1000);

        then.theListIsLoaded();
    }

    @Test
    public void test2TapOnListAndFillData() {

        Utils.showMessage(mActivity, "test2TapOnListAndFillData");

        Utils.waitForIt(1000);

        when.theUserTapOnTheList();

        then.theListIsLoaded();

        then.dataIsFilled();
    }

    @Test
    public void testLogOutCancel() {

        Utils.showMessage(mActivity, "testLogOutCancel");

        Utils.waitForIt(1000);

        when.userTapOnMenu();

        when.userTapOnLogOut();

        when.userCancelDialog();

        then.userStaysInScreen();

    }

    @Test
    public void testLogOutOk() {

        Utils.showMessage(mActivity, "testLogOutCancel");

        Utils.waitForIt(1000);

        when.userTapOnMenu();

        when.userTapOnLogOut();

        when.userAcceptDialog();

        then.userGoesToLogin();

    }

    public class Given {

    }

    public class When {

        public void theUserTapOnTheList() {
            onView(withId(R.id.listView)).perform(click());
        }

        public void userTapOnMenu() {
            onView(withId(R.id.drawerLayout)).perform(Utils.actionOpenDrawer());
        }

        public void userTapOnLogOut() {
            onView(withId(R.id.logOutContainer)).perform(click());
        }

        public void userCancelDialog() {
            onView(withId(R.id.buttonDefaultNegative)).perform(click());
        }

        public void userAcceptDialog() {
            onView(withId(R.id.buttonDefaultPositive)).perform(click());
        }
    }

    public class Then {

        public void theListIsLoaded() {
            onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));
        }

        public void dataIsFilled() {
            onView(withId(R.id.addressText)).check(matches(not(withText(""))));
        }

        public void userStaysInScreen() {
            Utils.assertThatIsOnActivity(MainActivity.class);
        }

        public void userGoesToLogin() {
            Utils.assertThatIsOnActivity(LogInActivity.class);
        }
    }
}
