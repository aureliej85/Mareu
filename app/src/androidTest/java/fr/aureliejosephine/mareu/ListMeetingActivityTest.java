package fr.aureliejosephine.mareu;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import fr.aureliejosephine.mareu.utils.DeleteViewAction;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static fr.aureliejosephine.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;


public class ListMeetingActivityTest {

    private ListMeetingActivity mActivity;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule = new ActivityTestRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }


    @Test
    public void meetingListShouldNotBeEmpty() {
        onView(allOf(withId(R.id.my_recycler_view), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void meetingList_deleteAction_shouldRemoveItem() {
        onView(allOf(withId(R.id.my_recycler_view), isDisplayed())).check(withItemCount(4)).perform(actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withText("OUI")).perform(click());
        onView(allOf(withId(R.id.my_recycler_view), isDisplayed())).check(withItemCount(4 - 1));
    }

}
