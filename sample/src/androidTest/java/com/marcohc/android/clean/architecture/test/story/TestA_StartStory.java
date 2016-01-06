package com.marcohc.android.clean.architecture.test.story;

import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.marcohc.android.clean.architecture.domain.interactor.IsUserLoggedInUseCase;
import com.marcohc.android.clean.architecture.domain.interactor.LogInUseCase;
import com.marcohc.android.clean.architecture.sample.R;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesConstants;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.LogInActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.MainActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.StartActivity;
import com.marcohc.android.clean.architecture.test.util.Utils;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestA_StartStory extends ActivityInstrumentationTestCase2<StartActivity> {

    private static int testCounter = 0;
    private Given given;
    private When when;
    private Then then;
    private StartActivity mActivity;

    public TestA_StartStory() {
        super(StartActivity.class);
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

        if (testCounter == 2) {
            given.theUserLogsIn();
            Utils.waitSleeping(3000);
        } else {
            testCounter++;
        }
    }

    @Test
    public void checkPreconditions() {
        assertThat(mActivity, notNullValue());
        assertThat(getInstrumentation(), notNullValue());
    }

    @Test
    public void test1TheUserIsNotLoggedInAndGoesToLogin() {

        Utils.showMessage(mActivity, "test2TheUserIsNotLoggedInAndGoesToLogin");

        given.theUserIsNotLoggedIn();

        Utils.waitSleeping(3000);

        then.theUserGoesToLogin();
    }

    @Test
    public void test2TheUserIsLoggedInAndGoesToMain() {

        Utils.showMessage(mActivity, "test3TheUserIsLoggedInAndGoesToMain");

        given.theUserIsLoggedIn();

        then.theUserGoesToMain();
    }

    public class Given {

        public void theUserIsNotLoggedIn() {
            assertFalse(new IsUserLoggedInUseCase().execute());
        }

        public void theUserIsLoggedIn() {
            assertTrue(new IsUserLoggedInUseCase().execute());
        }

        public void theUserLogsIn() {
            new LogInUseCase("admin", "admin").execute();
        }
    }

    public class When {
    }

    public class Then {

        public void theSplashScreenIsShown() {
            onView(withId(R.id.splashImage)).check(matches(isDisplayed()));
        }

        public void theUserGoesToLogin() {
            Utils.assertThatIsOnActivity(LogInActivity.class);
        }

        public void theUserGoesToMain() {
            Utils.assertThatIsOnActivity(MainActivity.class);
        }
    }
}
