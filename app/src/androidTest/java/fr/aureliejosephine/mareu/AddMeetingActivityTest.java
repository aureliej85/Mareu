package fr.aureliejosephine.mareu;

import org.junit.Rule;
import org.junit.Test;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static fr.aureliejosephine.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
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

    @Test
    public void clickOnSaveButtonShouldAddMeeting(){
        onView(allOf(ViewMatchers.withId(R.id.my_recycler_view), isDisplayed()));
        onView(withId(R.id.fab)).perform(click());
        onView(ViewMatchers.withId(R.id.subjectEdit)).perform(replaceText("New meeting added"));
        onView(allOf(withId(R.id.saveButton), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.my_recycler_view), isDisplayed())).check(withItemCount(4));
    }

}
