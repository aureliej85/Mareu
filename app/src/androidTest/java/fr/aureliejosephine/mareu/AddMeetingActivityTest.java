package fr.aureliejosephine.mareu;

import org.junit.Rule;
import org.junit.Test;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

public class AddMeetingActivityTest {


    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityTestRule = new ActivityTestRule<>(ListMeetingActivity.class);


    @Test
    public void fromMeetingListToAddMeeting() {
        onView(allOf(ViewMatchers.withId(R.id.my_recycler_view), isDisplayed()));
        onView(withId(R.id.fab)).perform(click());
        onView(allOf(ViewMatchers.withId(R.id.subjectEdit),isDisplayed()));
    }
}
