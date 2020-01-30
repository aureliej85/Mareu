package fr.aureliejosephine.mareu;

import org.junit.Rule;
import org.junit.Test;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static fr.aureliejosephine.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;

public class filteredMeetingListTest {

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityTestRule = new ActivityTestRule<>(ListMeetingActivity.class);

    @Test
    public void filteredMeetingListShouldShowOnlyOneItem(){
        onView(allOf(withId(R.id.my_recycler_view), isDisplayed()));
        onView(ViewMatchers.withId(R.id.searchView)).perform(click()).perform(typeText("29/7/2020"));
        onView(allOf(withId(R.id.my_recycler_view), isDisplayed())).check(withItemCount(1));
    }
}
