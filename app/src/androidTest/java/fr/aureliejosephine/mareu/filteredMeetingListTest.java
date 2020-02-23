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
        onView(ViewMatchers.withId(R.id.filter)).perform(click());
        onView(ViewMatchers.withText("Filtrer par salle")).perform(click());
        onView(ViewMatchers.withText("Réunion A")).perform(click());
        onView(allOf(withId(R.id.my_recycler_view), isDisplayed()));
        onView(allOf(withText("Réunion A - 11h00 - Sujet 3"), isDisplayed()));
    }
}
