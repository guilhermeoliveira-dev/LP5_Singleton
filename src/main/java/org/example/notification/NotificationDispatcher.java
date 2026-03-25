package org.example.notification;

import org.example.entity.User;

public class NotificationDispatcher {

    private final String smtpServer;
    private final String smsGateway;

    private static NotificationDispatcher instance;

    private NotificationDispatcher(){
        // on a real application, this would be loaded from a resource file or something like that
        smtpServer = "smtp.gmail.com";
        smsGateway = "api.provider.com";
    }

    public static NotificationDispatcher getInstance(){
        if(instance == null){
            instance = new NotificationDispatcher();
        }
        return instance;
    }

    public void sendEmailNotification(User user, String body, String subject){

        if(user == null){
            throw new IllegalArgumentException("Null user");
        }
        if(body == null || body.isEmpty()){
            throw new IllegalArgumentException("Empty email body");
        }
        if(subject == null){
            subject = "";
        }

        String receiver = user.getEmail();

        String builtEmail = "From: "+ NotificationConfiguration.getInstance().getDefaultEmail();
        builtEmail += "\nTo: "+receiver;
        builtEmail += "\nSubject: "+subject;
        builtEmail += "\n\n"+body;

        String output = "Email sent using: \""+smtpServer+"\" \n\n"+builtEmail;
        System.out.println(output);

    }

    public void sendPushNotification(User user, String title, String message){

        if(user == null){
            throw new IllegalArgumentException("Null user");
        }
        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("Empty push title");
        }
        if(message == null || message.isEmpty()){
            throw new IllegalArgumentException("Empty push message");
        }

        String userId = user.getId();

        String builtPush = title+"\n"+message;

        String output = "Push sent to user of ID \""+userId+"\":\n"+builtPush;
        System.out.println(output);

    }

    public void sendSMSNotification(User user, String message){

        if(user == null){
            throw new IllegalArgumentException("Null user");
        }
        if(message == null || message.isEmpty()){
            throw new IllegalArgumentException("Empty message");
        }

        String userNumber = user.getNumber();

        String sms = NotificationConfiguration.getInstance().getDefaultNumber()+":\n"+message;

        String output = "SMS sent to the number \""+userNumber+"\" using \""+smsGateway+"\"\n\n"+sms;
        System.out.println(output);
    }

}
