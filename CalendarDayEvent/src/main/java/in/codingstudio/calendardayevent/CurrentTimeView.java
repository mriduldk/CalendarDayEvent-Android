package in.codingstudio.calendardayevent;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class CurrentTimeView extends FrameLayout {

    private LinearLayout linearLayoutParent;

    public CurrentTimeView(Context context) {
        super(context);
        init(null);
    }

    public CurrentTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CurrentTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_current_time, this, true);
        linearLayoutParent = (LinearLayout) findViewById(R.id.linearLayoutParent);

    }


    public void setPosition(Rect rect, int topMargin, int bottomMargin) {
        LayoutParams params =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = rect.top + topMargin;
        params.leftMargin = rect.left;
        linearLayoutParent.getLayoutParams().height = rect.height() + bottomMargin;
        setLayoutParams(params);
    }



    public float getSeparateWidth() {
        return linearLayoutParent.getLayoutParams().width;
    }

    public float getSeparateHeight() {
        return linearLayoutParent.getLayoutParams().height;
    }

    public void setHourSeparatorAsInvisible() {
        linearLayoutParent.setVisibility(INVISIBLE);

    }

    public void setHourSeparatorAsVisible() {
        linearLayoutParent.setVisibility(VISIBLE);
    }

    public void setHourSeparatorIsVisible(boolean b) {
        if (b) {
            setHourSeparatorAsVisible();
        } else {
            setHourSeparatorAsInvisible();
        }

    }

}
