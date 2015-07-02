package com.xs2mobile.buurapp;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Toast;

import com.xs2mobile.buurapp.presentation.view.impl.activity.StartActivity;

import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
public class Test extends ActivityInstrumentationTestCase2<StartActivity> {

    private StartActivity mActivity;

    public Test() {
        super(StartActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

//    public void testLogInOk() {
//
//        showMessage("Log in OK");
//
//        waitForIt(1000);
//
//        clickOnWithShortPause(R.id.logInButton);
//
//        waitForIt(1000);
//
//    }
//
//    public void testLogInError() {
//
//        showMessage("Log in ERROR");
//
//        waitForIt(1000);
//
//        clickOnWithShortPause(R.id.logInButton);
//
//        waitForIt(1000);
//
//    }

    public void testSignUpOk() {

        showMessage("Sign up OK");

        waitForIt(1000);

        clickOnWithShortPause(R.id.signUpButton);
//
//        typeOnWithShortPause(R.id.usernameEditText, "MyUsername");
//
//        typeOnWithShortPause(R.id.firstNameEditText, "MyFirstName");
//
//        typeOnWithShortPause(R.id.lastNameEditText, "MyLastName");
//
//        typeOnWithShortPause(R.id.streetEditText, "Paasheuvelweg 9F");
//
//        typeOnWithShortPause(R.id.numberEditText, "9");
//
//        typeOnWithShortPause(R.id.zipCodeEditText, "1105 BE");
//
//        typeOnWithShortPause(R.id.cityEditText, "Amsterdam");
//
//        typeOnWithShortPause(R.id.countryEditText, "Netherlands");
//
//        typeOnWithShortPause(R.id.birthdayEditText, "29/02/1950");
//
//        typeOnWithShortPause(R.id.emailEditText, "my_ultra_fake_email_adress@gmail.com");
//
//        typeOnWithShortPause(R.id.passwordEditText, "MyPassword");
//
//        typeOnWithShortPause(R.id.confirmPasswordEditText, "MyPassword");

        clickOnWithShortPause(R.id.item_2);

        waitForIt(1000);

    }

//    public void testSignUpError() {
//        // TODO
//    }
//
//    public void testForgotPassword() {
//        // TODO
//    }

    // ************************************************************************************************************************************************************************
    // * Util methods --> To Library
    // ************************************************************************************************************************************************************************

    private void typeOnWithShortPause(int viewId, String text) {
        onView(withId(viewId)).perform(typeText(text));
    }

    private void showMessage(final String message) {
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickOnWithShortPause(int viewId) {
        onView(withId(viewId)).perform(click());
        waitForIt(750);
    }

    private void clickOnWithLongPause(int viewId) {
        onView(withId(viewId)).perform(click());
        waitForIt(3000);
    }

    private void waitForIt(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
