package com.marcohc.architecture.test.story;

import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.marcohc.architecture.presentation.util.PreferencesConstants;
import com.marcohc.architecture.presentation.view.impl.activity.MainActivity;
import com.marcohc.architecture.sample.R;
import com.marcohc.architecture.test.util.Utils;

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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsAnything.anything;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestC_MainStory extends ActivityInstrumentationTestCase2<MainActivity> {

    private Given given;
    private When when;
    private Then then;

    private MainActivity mActivity;
    private IdlingResource.ResourceCallback callback;

    public TestC_MainStory() {
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
        SharedPreferences preferences = instrumentation.getTargetContext().getSharedPreferences(PreferencesConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
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

        Utils.waitSleeping(1000);

        then.theListIsLoaded();
    }

    @Test
    public void test2TapOnListAndFillData() {

        Utils.showMessage(mActivity, "test2TapOnListAndFillData");

        Utils.waitSleeping(1000);

        when.theUserTapOnTheList();

        then.theListIsLoaded();

        then.dataIsFilled();
    }

    public class Given {

    }

    public class When {

        public void theUserTapOnTheList() {
            onView(withId(R.id.listView)).perform(click());
        }

        public void tapOnMenu() {
            onView(withId(R.id.drawerLayout)).perform(Utils.actionOpenDrawer());
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

    }
}
