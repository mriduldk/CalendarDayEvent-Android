package in.codingstudio.calendardayevent.decoration;

import android.graphics.Rect;

import in.codingstudio.calendardayevent.CurrentTimeView;
import in.codingstudio.calendardayevent.DayView;
import in.codingstudio.calendardayevent.ToDoView_With_Topic;
import in.codingstudio.calendardayevent.data.ToDo_With_Topic;


/**
 * Created by FRAMGIA\pham.van.khac on 22/07/2016.
 */
public interface CdvDecoration {

    ToDoView_With_Topic getToDoView_With_Topic(ToDo_With_Topic todo, Rect eventBound, int hourHeight, int separateHeight);

    CurrentTimeView getCurrentTimeView(Rect eventBound, int separateHeight);

    DayView getDayView(int hour);
}
