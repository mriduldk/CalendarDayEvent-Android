package in.codingstudio.calendardayevent;

import java.util.Calendar;

import in.codingstudio.calendardayevent.data.ToDo_With_Topic;

public class ToDo implements ToDo_With_Topic {

    private Calendar startTime;
    private Calendar endTime;
    private String topic = "";
    private String description;
    private int color;
    private boolean isAutoHide;

    public ToDo() {

    }


    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public Boolean isAutohide() {
        return isAutoHide;
    }

    @Override
    public Calendar getStartTime() {
        return startTime;
    }

    @Override
    public Calendar getEndTime() {
        return endTime;
    }

    ///


    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAutoHide(boolean autoHide) {
        isAutoHide = autoHide;
    }
}
