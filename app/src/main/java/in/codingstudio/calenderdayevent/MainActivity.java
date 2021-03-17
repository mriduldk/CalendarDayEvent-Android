package in.codingstudio.calenderdayevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Calendar;

import in.codingstudio.calendardayevent.CalendarDayEvent;
import in.codingstudio.calendardayevent.ToDo;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ToDo> todos;
    private CalendarDayEvent dayEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayEvent = findViewById(R.id.dayView);

        dayEvent.setLimitTime(0, 24);

        todos = new ArrayList<>();

        Calendar timeStart = Calendar.getInstance();
        timeStart.set(Calendar.HOUR_OF_DAY, 12);
        timeStart.set(Calendar.MINUTE, 0);
        Calendar timeEnd = (Calendar) timeStart.clone();
        timeEnd.set(Calendar.HOUR_OF_DAY, 13);
        timeEnd.set(Calendar.MINUTE, 0);

        ToDo to = new ToDo();
        to.setTopic("message");
        to.setDescription("Desc");
        to.setStartTime(timeStart);
        to.setEndTime(timeEnd);
        to.setColor(getResources().getColor(R.color.orange));

        todos.add(to);


        Calendar timeStart2 = Calendar.getInstance();
        timeStart2.set(Calendar.HOUR_OF_DAY, 16);
        timeStart2.set(Calendar.MINUTE, 0);
        Calendar timeEnd2 = (Calendar) timeStart.clone();
        timeEnd2.set(Calendar.HOUR_OF_DAY, 19);
        timeEnd2.set(Calendar.MINUTE, 30);

        ToDo to2 = new ToDo();
        to2.setTopic("trip");
        to2.setDescription("Want to go to Goa");
        to2.setStartTime(timeStart2);
        to2.setEndTime(timeEnd2);
        to2.setColor(getResources().getColor(R.color.baby_blue));

        todos.add(to2);


        dayEvent.setTodos(todos);


        //scrollView.scrollTo(5,6);

    }
}