package com.marcohc.android.clean.architecture.test.story;

import android.widget.Toast;

import com.marcohc.android.clean.architecture.domain.interactor.impl.IsUserLoggedInUseCase;
import com.marcohc.android.clean.architecture.presentation.BuildConfig;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.StartActivity;
import com.marcohc.android.clean.architecture.test.util.Utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

public class UsersListStory {

    private StartActivity mActivity;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Test
    public void testTheListIsLoaded() {

        showMessage("testTheListIsLoaded");

        Given.theUserIsNotLoggedIn();

        When.theUserWaitUntilListIsLoaded();

        Then.theListIsLoaded();
    }

    @Test
    public void testCredentialsAreFilledWithData() {

        showMessage("testCredentialsAreFilledWithData");

        Given.theUserIsNotLoggedIn();

        When.theUserWaitUntilListIsLoaded();

        Then.theListIsLoaded();

        Then.credentialsAreFilled();
    }

    public static class Given {

        public static void theUserIsNotLoggedIn() {
            assertFalse(new IsUserLoggedInUseCase().execute());
        }

        public static void theUserIsLoggedIn() {
            assertTrue(new IsUserLoggedInUseCase().execute());
        }
    }

    public static class When {

        public static void theUserWaitUntilListIsLoaded() {
            onView(isRoot()).perform(Utils.waitId(R.id.listView, 10000));
//            Utils.onViewWithId(R.id.listView).perform(Utils.waitId(R.id.listView, 10000));
        }
    }

    public static class Then {

        public static void theListIsLoaded() {
            onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));
        }

        public static void credentialsAreFilled() {
            onView(withId(R.id.usernameEditText)).check(matches(not(withText(""))));
            onView(withId(R.id.passwordEditText)).check(matches(not(withText(""))));
        }

    }

    // ************************************************************************************************************************************************************************
    // * Util methods --> To Library
    // ************************************************************************************************************************************************************************

    public void showMessage(final String message) {
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
