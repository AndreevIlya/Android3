package com.example.android3;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.android3", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureProgressViewIsShowing() {
        MainActivity activity = rule.getActivity();
        View progress = activity.findViewById(R.id.loadingView);
        View content = activity.findViewById(R.id.contentView);
        View empty = activity.findViewById(R.id.emptyView);
        assertThat(progress, notNullValue());
        assertThat(progress, instanceOf(ProgressBar.class));
        assertThat(content, notNullValue());
        assertThat(empty, notNullValue());
        activity.showLoading();
        assertEquals(progress.getVisibility(), View.VISIBLE);
        assertEquals(content.getVisibility(), View.GONE);
        assertEquals(empty.getVisibility(), View.GONE);
    }

    @Test
    public void ensureProgressViewIsHidden() {
        MainActivity activity = rule.getActivity();
        View progress = activity.findViewById(R.id.loadingView);
        assertThat(progress, notNullValue());
        assertThat(progress, instanceOf(ProgressBar.class));
        Log.i("sdagfa", "regfrewg");
        activity.hideLoading();
        assertEquals(progress.getVisibility(), View.GONE);
    }

    @Test
    public void ensureContentIsEmpty() {
        MainActivity activity = rule.getActivity();
        View content = activity.findViewById(R.id.contentView);
        View empty = activity.findViewById(R.id.emptyView);
        assertThat(content, notNullValue());
        assertThat(empty, notNullValue());
        activity.showEmptyState();
        assertEquals(content.getVisibility(), View.GONE);
        assertEquals(empty.getVisibility(), View.VISIBLE);
    }

    @Test
    public void ensureContentIsShowing() {
        MainActivity activity = rule.getActivity();
        View content = activity.findViewById(R.id.contentView);
        View empty = activity.findViewById(R.id.emptyView);
        assertThat(content, notNullValue());
        assertThat(empty, notNullValue());
        activity.showRepoList(new ArrayList<>());
        assertEquals(content.getVisibility(), View.VISIBLE);
        assertEquals(empty.getVisibility(), View.GONE);
    }
}
