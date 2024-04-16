package us.master.entregable1;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import us.master.entregable1.MainActivity;
import us.master.entregable1.R;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureCardViewsArePresent() throws Exception {
        // Check if the CardViews are displayed on the screen
        Espresso.onView(ViewMatchers.withId(R.id.cardView1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.cardView2))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void ensureCardViewClickOpensCorrectActivity() throws Exception {
        Trip.generateTripList(123456789);

        // Click on the first CardView and check if the correct activity is opened
        Espresso.onView(ViewMatchers.withId(R.id.cardView1))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(5)));

        // Go back to MainActivity
        Espresso.pressBack();

        // Click on the second CardView and check if the correct activity is opened
        Espresso.onView(ViewMatchers.withId(R.id.cardView2))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.hasChildCount(0)));
    }
}