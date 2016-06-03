package edu.uw.tacoma.zanderp.tcss450drumproject;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import edu.uw.tacoma.zanderp.tcss450drumproject.Authenticate.SignInActivity;

/**
 * Tests the login and logout functionality of the application.
 */
public class LoginTest extends ActivityInstrumentationTestCase2<SignInActivity> {
    private Solo solo;

    public LoginTest(){
        super(SignInActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();

    }
    public void testLogin() {
        solo.enterText(1, "test@gmail.com");
        solo.enterText(2, "teststepan");
        solo.clickOnButton("Sign In");
        boolean textFound = solo.searchText("Customize");
        assertTrue("Sign in worked!", textFound);
    }

    public void testLogout() {
        solo.clickOnMenuItem("Log Out");
        boolean worked = solo.searchButton("Sign In");
        assertTrue("Logout worked!", worked);
    }



}
