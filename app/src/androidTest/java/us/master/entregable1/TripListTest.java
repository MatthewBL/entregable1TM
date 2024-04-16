package us.master.entregable1;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.DatePicker;

import java.time.LocalDate;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TripListTest {

    @Rule
    public ActivityTestRule<TripList> mActivityRule = new ActivityTestRule<>(TripList.class);

    @Test
    public void ensureColumnToggleWorks() throws Exception {
        Trip.generateTripList(123456789);

        // Check if the RecyclerView's layout manager has a span count of 1
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(withLayoutManagerSpanCount(1)));

        // Find the toggle button and perform a click action on it
        Espresso.onView(ViewMatchers.withId(R.id.columnasSwitch))
                .perform(ViewActions.click());

        // Check if the RecyclerView's layout manager has a span count of 2
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(withLayoutManagerSpanCount(2)));

        // Perform another click action on the toggle button
        Espresso.onView(ViewMatchers.withId(R.id.columnasSwitch))
                .perform(ViewActions.click());

        // Check if the RecyclerView's layout manager has a span count of 1
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(withLayoutManagerSpanCount(1)));
    }

    private static Matcher<View> withLayoutManagerSpanCount(final int spanCount) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("RecyclerView with layout manager span count: " + spanCount);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager) {
                    return ((GridLayoutManager) layoutManager).getSpanCount() == spanCount;
                }
                return false;
            }
        };
    }

    @Test
    public void testFilterActivityStartDate() throws Exception {
        // Generate the trip list
        Trip.generateTripList(123456789);
        LocalDate dayAfterTomorrow = LocalDate.now().plusMonths(1);

        // Find the filter button and perform a click action on it
        Espresso.onView(ViewMatchers.withId(R.id.filtrarTextView))
                .perform(ViewActions.click());

        // Check if the Filter activity is displayed
        Espresso.onView(ViewMatchers.withId(R.id.filterActivityRootLayout))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Perform a click action on the filter option
        Espresso.onView(ViewMatchers.withId(R.id.startDateImageView))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.isAssignableFrom(DatePicker.class))
                .perform(PickerActions.setDate(dayAfterTomorrow.getYear(), dayAfterTomorrow.getMonthValue(), dayAfterTomorrow.getDayOfMonth()));
        Espresso.onView(ViewMatchers.withText("Aceptar"))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.button3))
                .perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.hasChildCount(12)));
    }
}