package fr.aureliejosephine.mareu;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static fr.aureliejosephine.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;

public class filteredMeetingListTest {

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityTestRule = new ActivityTestRule<>(ListMeetingActivity.class);
    Context context = InstrumentationRegistry.getTargetContext();

    @Test
    public void filteredMeetingListShouldShowOnlyOneItem(){
        onView(allOf(withId(R.id.my_recycler_view), isDisplayed()));
        onView(ViewMatchers.withId(R.id.searchView)).perform(click()).perform(typeText(context.getString(R.string.test_input_date)));
        //onView(allOf(withId(R.id.my_recycler_view), isDisplayed())).check(withItemCount(1));
        onView(withId(R.id.roomHourSubjTv)).check(matches(withText(context.getString(R.string.test_subject_input2))));
    }
}
