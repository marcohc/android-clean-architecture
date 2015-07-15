package com.marcohc.android.clean.architecture.test.story;

import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.marcohc.android.clean.architecture.data.util.PreferencesManager;
import com.marcohc.android.clean.architecture.domain.interactor.impl.IsUserLoggedInUseCase;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.LogInActivity;
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
public class LoginStory extends ActivityInstrumentationTestCase2<LogInActivity> {

    private Given given;
    private When when;
    private Then then;

    private LogInActivity mActivity;

    public LoginStory() {
        super(LogInActivity.class);
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

        given.theUserIsNotLoggedIn();

        when.theUserWaitUntilListIsLoaded();

        then.theListIsLoaded();
    }

    @Test
    public void test2CredentialsAreFilledWithDataWhenUserTapOnTheList() {

        Utils.showMessage(mActivity, "testCredentialsAreFilledWithDataWhenUserTapOnTheList");

        Utils.waitForIt(1000);

        given.theUserIsNotLoggedIn();

        when.theUserWaitUntilListIsLoaded();

        when.theUserTapOnTheList();

        then.theListIsLoaded();

        then.credentialsAreFilled();
    }

    @Test
    public void test3LogIn() {

        Utils.showMessage(mActivity, "testLogIn");

        Utils.waitForIt(1000);

        given.theUserIsNotLoggedIn();

        when.theUserWaitUntilListIsLoaded();

        when.theUserTapOnTheList();

        when.userClickOnAction();

        then.theUserIsLoggedIn();
    }

    public class Given {

        public void theUserIsNotLoggedIn() {
            assertFalse(new IsUserLoggedInUseCase().execute());
        }

    }

    public class When {

        public void theUserWaitUntilListIsLoaded() {
            onView(withId(R.id.listView)).perform(Utils.waitId(R.id.listView, 3000));
        }

        public void theUserTapOnTheList() {
            onView(withId(R.id.listView)).perform(click());
        }

        public void userClickOnAction() {
            onView(withId(R.id.item_2)).perform(click());
        }
    }

    public class Then {

        public void theListIsLoaded() {
            onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));
        }

        public void credentialsAreFilled() {
            onView(withId(R.id.usernameEditText)).check(matches(not(withText(""))));
            onView(withId(R.id.passwordEditText)).check(matches(not(withText(""))));
        }

        public void theUserIsLoggedIn() {
            assertTrue(new IsUserLoggedInUseCase().execute());
        }
    }
}
