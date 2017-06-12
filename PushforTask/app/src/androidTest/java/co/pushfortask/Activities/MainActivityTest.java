package co.pushfortask.Activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.pushfortask.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view_posts), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        pressBack();

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_view_posts), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(2, click()));

        pressBack();

        ViewInteraction textView = onView(
                allOf(withId(R.id.title), withText("Sunt aut facere repellat provident occaecati excepturi optio reprehenderit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.card_view),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Sunt aut facere repellat provident occaecati excepturi optio reprehenderit")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.title), withText("Sunt aut facere repellat provident occaecati excepturi optio reprehenderit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.card_view),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Sunt aut facere repellat provident occaecati excepturi optio reprehenderit")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
