package in.codingstudio.calendardayevent.data;

public interface ToDo_With_Topic extends ITimeDuration {

    String getTopic();

    String getDescription();

    int getColor();

    Boolean isAutohide();

}
