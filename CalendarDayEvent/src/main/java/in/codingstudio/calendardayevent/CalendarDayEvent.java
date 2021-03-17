package in.codingstudio.calendardayevent;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.codingstudio.calendardayevent.data.ITimeDuration;
import in.codingstudio.calendardayevent.data.ToDo_With_Topic;
import in.codingstudio.calendardayevent.decoration.CdvDecoration;
import in.codingstudio.calendardayevent.decoration.CdvDecorationDefault;

public class CalendarDayEvent extends FrameLayout {

    private int mDayHeight = 0;

    private int mEventMarginLeft = 0;

    private int mHourWidth = 120;

    private int mTimeHeight = 120;

    private int mSeparateHourHeight = 0;

    private int mStartHour = 0;

    private int mEndHour = 24;

    private LinearLayout mLayoutDayView;

    private FrameLayout mLayoutTodo;

    private FrameLayout mLayoutTime;

    private CdvDecoration mDecoration;

    private ScrollView mScrollView;


    private List<? extends ToDo_With_Topic> mTodo;

    public CalendarDayEvent(Context context) {
        super(context);
        init(null);
    }

    public CalendarDayEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CalendarDayEvent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_day_calendar, this, true);

        mLayoutDayView = (LinearLayout) findViewById(R.id.dayview_container);
        mLayoutTodo = (FrameLayout) findViewById(R.id.todo_container);
        mLayoutTime = (FrameLayout) findViewById(R.id.time_container);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);

        mDayHeight = getResources().getDimensionPixelSize(R.dimen.dayHeight);

        if (attrs != null) {
            @SuppressLint("CustomViewStyleable") TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarDayView);
            try {
                mEventMarginLeft =
                    a.getDimensionPixelSize(R.styleable.CalendarDayView_eventMarginLeft,
                        mEventMarginLeft);
                mDayHeight =
                    a.getDimensionPixelSize(R.styleable.CalendarDayView_dayHeight, mDayHeight);
                mStartHour = a.getInt(R.styleable.CalendarDayView_startHour, mStartHour);
                mEndHour = a.getInt(R.styleable.CalendarDayView_endHour, mEndHour);
            } finally {
                a.recycle();
            }
        }

        mTodo = new ArrayList<>();
        mDecoration = new CdvDecorationDefault(getContext());
        mScrollView.setSmoothScrollingEnabled(true);

        refresh();
    }

    public void refresh() {
        drawDayViews();

        drawToDos();

        drawCurrentTime();
    }

    private void drawDayViews() {
        mLayoutDayView.removeAllViews();
        DayView dayView = null;
        for (int i = mStartHour; i <= mEndHour; i++) {
            dayView = getDecoration().getDayView(i);
            mLayoutDayView.addView(dayView);
        }
        mHourWidth = (int) dayView.getHourTextWidth();
        mTimeHeight = (int) dayView.getHourTextHeight();
        mSeparateHourHeight = (int) dayView.getSeparateHeight();
    }

    @SuppressLint("SimpleDateFormat")
    private void drawCurrentTime(){

        ToDo t = new ToDo();
        t.setStartTime(Calendar.getInstance());
        t.setEndTime(Calendar.getInstance());
        Rect rect = getTimeBound(t);

        // add Current Time views
        CurrentTimeView view =
                getDecoration().getCurrentTimeView(rect, mSeparateHourHeight);
        if (t != null) {
            mLayoutTime.addView(view, view.getLayoutParams());

            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) getContext()).getWindowManager()
                    .getDefaultDisplay()
                    .getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;

            mScrollView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mScrollView, "scrollY", 0, rect.bottom -  (height / 2)).setDuration(900);
                    objectAnimator.start();
                }
            }, 800);
        }

    }

    private void drawToDos() {
        mLayoutTodo.removeAllViews();

        for (ToDo_With_Topic todo : mTodo) {
            Rect rect = getTimeBound(todo);

            // add To-Do views
            ToDoView_With_Topic view =
                    getDecoration().getToDoView_With_Topic(todo, rect, mTimeHeight, mSeparateHourHeight);
            if (todo != null) {
                mLayoutTodo.addView(view, view.getLayoutParams());


            }
        }
    }


    private Rect getTimeBound(ITimeDuration event) {
        Rect rect = new Rect();
        rect.top = getPositionOfTime(event.getStartTime()) + mTimeHeight / 2 + mSeparateHourHeight;
        rect.bottom = getPositionOfTime(event.getEndTime()) + mTimeHeight / 2 + mSeparateHourHeight;
        rect.left = mHourWidth + mEventMarginLeft;
        rect.right = getWidth();
        return rect;
    }

    private int getPositionOfTime(Calendar calendar) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY) - mStartHour;
        int minute = calendar.get(Calendar.MINUTE);
        return hour * mDayHeight + minute * mDayHeight / 60;
    }


    public void setTodos(List<? extends ToDo_With_Topic> todos) {
        this.mTodo = todos;
        refresh();
    }

    public void setLimitTime(int startHour, int endHour) {
        if (startHour >= endHour) {
            throw new IllegalArgumentException("start hour must before end hour");
        }
        mStartHour = startHour;
        mEndHour = endHour;
        refresh();
    }

    public void setDecorator(@NonNull CdvDecoration decorator) {
        this.mDecoration = decorator;
        refresh();
    }

    public CdvDecoration getDecoration() {
        return mDecoration;
    }
}
