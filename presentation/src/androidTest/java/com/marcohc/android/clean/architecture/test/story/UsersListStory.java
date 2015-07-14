package com.marcohc.android.clean.architecture.test.story;

import android.support.test.InstrumentationRegistry;
import android.widget.Toast;

import com.marcohc.android.clean.architecture.domain.interactor.impl.IsUserLoggedInUseCase;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.StartActivity;
import com.marcohc.android.clean.architecture.test.util.Utils;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.IsAnything.anything;

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

        // ASDASD ASD ASD asd asd asd
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
            onView(withId(R.id.usernameEditText)).check(matches(withText("")));
            onView(withId(R.id.passwordEditText)).check(matches(withText("")));
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
