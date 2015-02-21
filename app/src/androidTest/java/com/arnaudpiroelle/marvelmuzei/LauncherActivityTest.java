package com.arnaudpiroelle.marvelmuzei;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import com.arnaudpiroelle.marvelmuzei.ui.launcher.launcher.LauncherActivity;
import com.squareup.spoon.Spoon;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class LauncherActivityTest extends ActivityInstrumentationTestCase2<LauncherActivity> {

    private Activity activity;

    public LauncherActivityTest() {
        super(LauncherActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
    }

    public void testDisplayExtensionDescriptionText() {
        Spoon.screenshot(activity, "LauncherActivity");
        onView(withId(R.id.extension_description)).check(matches(withText(R.string.extension_description)));
    }

}
