package com.marcohc.android.clean.architecture.test.story;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.marcohc.android.clean.architecture.data.util.PreferencesManager;
import com.marcohc.android.clean.architecture.domain.interactor.impl.IsUserLoggedInUseCase;
import com.marcohc.android.clean.architecture.domain.interactor.impl.LogInUseCase;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.LogInActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.MainActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.StartActivity;
import com.marcohc.android.clean.architecture.test.util.Utils;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Collection;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import static android.support.test.runner.lifecycle.Stage.RESUMED;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StartStory extends ActivityInstrumentationTestCase2<StartActivity> {

    private Given given;
    private When when;
    private Then then;
    private StartActivity mActivity;

    public StartStory() {
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
    public void test1TheSplashIsShown() {

        Utils.showMessage(mActivity, "test1TheSplashIsShown");

        given.theUserIsNotLoggedIn();

        then.theSplashScreenIsShown();
    }

    @Test
    public void test2TheUserIsNotLoggedInAndGoesToLogin() {

        Utils.showMessage(mActivity, "test2TheUserIsNotLoggedInAndGoesToLogin");

        given.theUserIsNotLoggedIn();

        // TODO REMOVE THIS AND DO IT WITH ESPRESSO
        Utils.waitForIt(1000);

        then.theUserGoesToLogin();
    }

    @Test
    public void test3TheUserIsLoggedInAndGoesToMain() {

        Utils.showMessage(mActivity, "test3TheUserIsLoggedInAndGoesToMain");

        // TODO: CHECK THIS, IS WRONG
        given.theUserIsLoggedIn();

        // TODO REMOVE THIS AND DO IT WITH ESPRESSO
        Utils.waitForIt(1000);

        then.theUserGoesToMain();
    }

    public class Given {

        public void theUserIsNotLoggedIn() {
            assertFalse(new IsUserLoggedInUseCase().execute());
        }

        public void theUserIsLoggedIn() {
            new LogInUseCase("MyUsername", "MyFakePassword").execute();
        }
    }

    public class When {
    }

    public class Then {

        public void theSplashScreenIsShown() {
            onView(withId(R.id.splashImage)).check(matches(isDisplayed()));
        }

        public void theUserGoesToLogin() {
            assertThatIsOnActivity(LogInActivity.class);
        }

        public void theUserGoesToMain() {
            assertThatIsOnActivity(MainActivity.class);
        }
    }

    private void assertThatIsOnActivity(Class clazz) {
        Activity currentActivity = getActivityInstance();
        assertTrue(currentActivity != null);
        assertTrue(currentActivity.getClass().equals(clazz));
    }

    public Activity getActivityInstance() {
        final Activity[] currentActivity = new Activity[1];
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity[0] = (Activity) resumedActivities.iterator().next();
                }
            }
        });

        return currentActivity[0];
    }
}
