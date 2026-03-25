package org.example.notification;

public class NotificationConfiguration {

    private final String defaultEmail;
    private final String defaultNumber;

    private static NotificationConfiguration instance;

    private NotificationConfiguration(){
        // on a real application, this would be loaded from a resource file or something like that
        defaultEmail = "noreply@example.com";
        defaultNumber = "+55 (32) 91234-5678";
    }

    public static NotificationConfiguration getInstance(){
        if(instance == null){
            instance = new NotificationConfiguration();
        }
        return instance;
    }

    public String getDefaultEmail() {
        return defaultEmail;
    }

    public String getDefaultNumber() {
        return defaultNumber;
    }
}
