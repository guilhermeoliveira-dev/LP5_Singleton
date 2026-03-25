package org.example.notification;

import org.example.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class NotificationDispatcherTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final User goodUser = new User("1", "usertest@example.com", "+55 (32) 94321-8765");

    private final String goodEmailBody = "good morning, how are you doing on this fine day?";
    private final String goodEmailSubject = "good morning";

    private final String goodPushTitle = "Welcome";
    private final String goodPushMessage = "welcome to Example!";

    private final String goodSMSMessage = "welcome to Example!";

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        outContent.reset();
        System.setOut(originalOut);
    }

    @Test
    public void getInstance_isUnique(){
        NotificationDispatcher dispatcher = NotificationDispatcher.getInstance();
        NotificationDispatcher dispatcher2 = NotificationDispatcher.getInstance();

        Assertions.assertSame(dispatcher, dispatcher2, "singleton returned two different instances");
    }

    @Test
    public void sendEmailNotification_validParameters_runsOK(){
        String expectedOutput = """
                Email sent using: "smtp.gmail.com"\s
                
                From: noreply@example.com
                To: usertest@example.com
                Subject: good morning
                
                good morning, how are you doing on this fine day?
                """;

        NotificationDispatcher.getInstance().sendEmailNotification(goodUser, goodEmailBody, goodEmailSubject);

        Assertions.assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    public void sendEmailNotification_emptyUser_throwsException(){
        try{
            NotificationDispatcher.getInstance().sendEmailNotification(null, goodEmailBody, goodEmailSubject);
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Null user", e.getMessage());
        }
    }

    @Test
    public void sendEmailNotification_emptyEmailBody_throwsException() {
        try {
            NotificationDispatcher.getInstance().sendEmailNotification(goodUser, "", goodEmailSubject);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Empty email body", e.getMessage());
        }
    }

    @Test
    public void sendEmailNotification_nullEmailBody_throwsException() {
        try {
            NotificationDispatcher.getInstance().sendEmailNotification(goodUser, null, goodEmailSubject);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Empty email body", e.getMessage());
        }
    }

    @Test
    public void sendEmailNotification_nullEmailSubject_runsWithEmptySubject(){
        String expectedOutput = """
               Email sent using: "smtp.gmail.com"\s
               
               From: noreply@example.com
               To: usertest@example.com
               Subject:\s
               
               good morning, how are you doing on this fine day?
               """;

        NotificationDispatcher.getInstance().sendEmailNotification(goodUser, goodEmailBody, null);

        Assertions.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void sendPushNotification_validParameters_runsOK(){
        String expectedOutput = """
               Push sent to user of ID "1":
               Welcome
               welcome to Example!
               """;
        NotificationDispatcher.getInstance().sendPushNotification(goodUser, goodPushTitle, goodPushMessage);

        Assertions.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void sendPushNotification_nullUser_throwsException(){
        try{
            NotificationDispatcher.getInstance().sendPushNotification(null, goodPushTitle, goodPushMessage);
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Null user", e.getMessage());
        }
    }

    @Test
    public void sendPushNotification_nullTitle_throwsException(){
        try{
            NotificationDispatcher.getInstance().sendPushNotification(goodUser, null, goodPushMessage);
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Empty push title", e.getMessage());
        }
    }

    @Test
    public void sendPushNotification_emptyTitle_throwsException(){
        try{
            NotificationDispatcher.getInstance().sendPushNotification(goodUser, "", goodPushMessage);
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Empty push title", e.getMessage());
        }
    }

    @Test
    public void sendPushNotification_nullMessage_throwsException(){
        try{
            NotificationDispatcher.getInstance().sendPushNotification(goodUser, goodPushTitle, null);
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Empty push message", e.getMessage());
        }
    }

    @Test
    public void sendPushNotification_emptyMessage_throwsException(){
        try{
            NotificationDispatcher.getInstance().sendPushNotification(goodUser, goodPushTitle, "");
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Empty push message", e.getMessage());
        }
    }

    @Test
    public void sendSMSNotification_validParameters_runsOK(){
        String expectedOutput = """
               SMS sent to the number "+55 (32) 94321-8765" using "api.provider.com"
               
               +55 (32) 91234-5678:
               welcome to Example!
               """;
        NotificationDispatcher.getInstance().sendSMSNotification(goodUser, goodSMSMessage);

        Assertions.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void sendSMSNotification_emptyMessage_throwsException(){
        try{
            NotificationDispatcher.getInstance().sendSMSNotification(goodUser, "");
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Empty message", e.getMessage());
        }
    }

    @Test
    public void sendSMSNotification_nullMessage_throwsException(){
        try{
            NotificationDispatcher.getInstance().sendSMSNotification(goodUser, null);
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Empty message", e.getMessage());
        }
    }

    @Test
    public void sendSMSNotification_nullUser_throwsException(){
        try{
            NotificationDispatcher.getInstance().sendSMSNotification(null, goodSMSMessage);
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Null user", e.getMessage());
        }
    }

}