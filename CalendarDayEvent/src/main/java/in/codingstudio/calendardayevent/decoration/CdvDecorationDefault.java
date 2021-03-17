package in.codingstudio.calendardayevent.decoration;

import android.content.Context;
import android.graphics.Rect;

import in.codingstudio.calendardayevent.CurrentTimeView;
import in.codingstudio.calendardayevent.DayView;
import in.codingstudio.calendardayevent.ToDoView_With_Topic;
import in.codingstudio.calendardayevent.data.ToDo_With_Topic;


public class CdvDecorationDefault implements CdvDecoration {

    protected Context mContext;

    protected ToDoView_With_Topic.OnEventToDoClickListener mOnTodoClickListener;

    public CdvDecorationDefault(Context context) {
        this.mContext = context;
    }

    @Override
    public ToDoView_With_Topic getToDoView_With_Topic(ToDo_With_Topic todo, Rect eventBound, int hourHeight, int separateHeight) {
        ToDoView_With_Topic view = new ToDoView_With_Topic(mContext);
        view.setOnToDoClickListener(mOnTodoClickListener);
        view.setToDo(todo);
        view.setPosition(eventBound, -hourHeight / 4 + separateHeight, hourHeight / 2 - separateHeight * 2);
        return view;
    }


    @Override
    public CurrentTimeView getCurrentTimeView(Rect eventBound, int separateHeight) {
        CurrentTimeView view = new CurrentTimeView(mContext);
        view.setPosition(eventBound, -separateHeight / 4 + separateHeight, separateHeight / 2 - separateHeight * 2);
        return view;
    }


    @Override
    public DayView getDayView(int hour) {
        DayView dayView = new DayView(mContext);
        dayView.setText(String.format("%1$2s:00", hour)); //TODO Change into am and Pm
        return dayView;
    }

    public void setOnToDoClickListener(ToDoView_With_Topic.OnEventToDoClickListener listener){
        this.mOnTodoClickListener = listener;
    }
}
