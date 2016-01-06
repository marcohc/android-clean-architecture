package com.marcohc.android.clean.architecture.test.story;

import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.marcohc.android.clean.architecture.domain.interactor.IsUserLoggedInUseCase;
import com.marcohc.android.clean.architecture.sample.R;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesConstants;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.LogInActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.MainActivity;
import com.marcohc.android.clean.architecture.test.util.Utils;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestB_LoginStory extends ActivityInstrumentationTestCase2<LogInActivity> {

    private Given given;
    private When when;
    private Then then;

    private LogInActivity mActivity;

    public TestB_LoginStory() {
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
    public void test1UsernameValidation() {

        Utils.showMessage(mActivity, "test1UsernameValidation");

        Utils.waitSleeping(1000);

        given.theUserIsNotLoggedIn();

        when.userWritesOnPassword();

        when.userClickOnAction();

        then.theUserStaysOnScreen();
    }

    @Test
    public void test2PasswordValidation() {

        Utils.showMessage(mActivity, "test2PasswordValidation");

        Utils.waitSleeping(1000);

        given.theUserIsNotLoggedIn();

        when.userWritesOnUsername();

        when.userClickOnAction();

        then.theUserStaysOnScreen();
    }

    @Test
    public void test3LogInWrong() {

        Utils.showMessage(mActivity, "test3LogInWrong");

        Utils.waitSleeping(1000);

        given.theUserIsNotLoggedIn();

        when.userWritesOnUsernameWrongly();

        when.userWritesOnPassword();

        when.userClickOnAction();

        then.theUserStaysOnScreen();
    }

    @Test
    public void test4LogInOk() {

        Utils.showMessage(mActivity, "test4LogInOk");

        Utils.waitSleeping(1000);

        given.theUserIsNotLoggedIn();

        when.userWritesOnUsername();

        when.userWritesOnPassword();

        when.userClickOnAction();

        then.theUserGoesToMain();
    }

    public class Given {

        public void theUserIsNotLoggedIn() {
            assertFalse(new IsUserLoggedInUseCase().execute());
        }

    }

    public class When {

        public void userClickOnAction() {
            onView(withId(R.id.item_2)).perform(click());
        }

        public void userWritesOnUsername() {
            onView(withId(R.id.usernameEditText)).perform(typeText("admin"));
        }

        public void userWritesOnPassword() {
            onView(withId(R.id.passwordEditText)).perform(typeText("admin"));
        }

        public void userWritesOnUsernameWrongly() {
            onView(withId(R.id.usernameEditText)).perform(typeText("BadUsername"));
        }
    }

    public class Then {

        public void theUserStaysOnScreen() {
            Utils.assertThatIsOnActivity(LogInActivity.class);
        }

        public void theUserGoesToMain() {
            Utils.assertThatIsOnActivity(MainActivity.class);
        }

    }
}
