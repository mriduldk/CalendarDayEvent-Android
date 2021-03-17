package in.codingstudio.calendardayevent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.text.SimpleDateFormat;

import in.codingstudio.calendardayevent.data.ToDo_With_Topic;

public class ToDoView_With_Topic extends FrameLayout {

    protected ToDo_With_Topic mToDo;
    protected OnEventToDoClickListener mToDoClickListener;
    protected TextView mDescription, mTopic, mStartTime, mEndTime;
    protected CardView mCardView;
    protected int mShowDuration = 3000;
    protected LinearLayout mLinearLayoutParent, mLinearLayoutChild;
    protected AlphaAnimation mFadeOutAnim = new AlphaAnimation(1.0f, 0.0f);
    protected Animation.AnimationListener mFadeOutListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            setAlpha(1);
            setVisibility(View.INVISIBLE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private Runnable mHidePopup = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    public ToDoView_With_Topic(@NonNull Context context) {
        super(context);
        init(null);
    }

    public ToDoView_With_Topic(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ToDoView_With_Topic(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    protected void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_event_popup_updated, this, true);

        mDescription        = findViewById(R.id.textViewDescription);
        mCardView           =  findViewById(R.id.cardViewParent);
        mEndTime            = findViewById(R.id.textViewEndTime);
        mStartTime          = findViewById(R.id.textViewStartTime);
        mTopic              = findViewById(R.id.textViewTopic);
        mLinearLayoutParent = findViewById(R.id.linearLayoutParent);
        mLinearLayoutChild  = findViewById(R.id.linearLayoutChild);


        mCardView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mToDoClickListener != null) {
                    mToDoClickListener.onToDoClick(ToDoView_With_Topic.this, mToDo);
                }
            }
        });
    }

    public void show() {
        setVisibility(View.VISIBLE);
        if (this.mToDo.isAutohide()) {
            removeCallbacks(mHidePopup);
            postDelayed(mHidePopup, mShowDuration);
        }
    }

    public void hide() {
        if (mFadeOutAnim.hasStarted() && !mFadeOutAnim.hasEnded()) {
            return;
        }
        mFadeOutAnim = new AlphaAnimation(getAlpha(), 0.0f);
        setVisibility(View.VISIBLE);
        mFadeOutAnim.setAnimationListener(mFadeOutListener);
        mFadeOutAnim.setDuration(200);
        startAnimation(mFadeOutAnim);
    }

    public void setPosition(Rect rect, int topMargin, int bottomMargin) {
        LayoutParams params =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = rect.top + topMargin;
        params.leftMargin = rect.left;
        mCardView.getLayoutParams().height = rect.height() + bottomMargin;
        setLayoutParams(params);
    }


    public void setToDo(ToDo_With_Topic todo) {
        this.mToDo = todo;
        mDescription.setText(String.valueOf(todo.getDescription()));

        if (todo.getTopic().equals("")){
            mTopic.setVisibility(GONE);
        }else{
            mTopic.setVisibility(VISIBLE);
            mTopic.setText(String.valueOf(todo.getTopic()));
        }

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm a");
        String startTime = sdf1.format(todo.getStartTime().getTime());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm a");
        String endTime = sdf2.format(todo.getEndTime().getTime());

        mStartTime.setText(startTime);
        mEndTime.setText(endTime);


        // TODO Manage color
        mLinearLayoutParent.setBackgroundColor(todo.getColor());
        mTopic.setTextColor(todo.getColor());

        if(mToDoClickListener != null){
            mToDoClickListener.onLoadData(ToDoView_With_Topic.this, todo);
        }

    }


    @Override
    public void setOnClickListener(final OnClickListener l) {
        throw new UnsupportedOperationException("you should use setOnToDoClickListener()");
    }


    public void setOnToDoClickListener(OnEventToDoClickListener listener) {
        this.mToDoClickListener = listener;
    }

    public interface OnEventToDoClickListener {
        void onToDoClick(ToDoView_With_Topic view, ToDo_With_Topic data);

        void onLoadData(ToDoView_With_Topic view, ToDo_With_Topic data);
    }

}
