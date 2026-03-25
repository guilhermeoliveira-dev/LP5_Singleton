package org.example.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    public void newUser_normalParameters_runsOK(){
        User user;
        try{
            user = new User("1", "usertest@example.com", "+55 (32) 91234-5678");
        } catch(Exception e){
            Assertions.fail("exception creating user: "+e.getMessage());
            return;
        }
        Assertions.assertEquals("1", user.getId());
        Assertions.assertEquals("usertest@example.com", user.getEmail());
        Assertions.assertEquals("+55 (32) 91234-5678", user.getNumber());

    }

    @Test
    public void newUser_emptyId_throwsException(){

        try{
            new User("", "usertest@example.com", "+55 (32) 91234-5678");
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Invalid user ID", e.getMessage());
        }
    }

    @Test
    public void newUser_nullId_throwsException(){

        try{
            new User(null, "usertest@example.com", "+55 (32) 91234-5678");
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Invalid user ID", e.getMessage());
        }
    }

    @Test
    public void newUser_emptyEmail_throwsException(){
        try{
            new User("1", "", "+55 (32) 91234-5678");
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Invalid user email", e.getMessage());
        }
    }

    @Test
    public void newUser_nullEmail_throwsException(){
        try{
            new User("1", null, "+55 (32) 91234-5678");
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Invalid user email", e.getMessage());
        }
    }

    @Test
    public void newUser_emptyNumber_throwsException(){
        try{
            new User("1", "usertest@example.com", "");
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Invalid user number", e.getMessage());
        }
    }

    @Test
    public void newUser_nullNumber_throwsException(){
        try{
            new User("1", "usertest@example.com", null);
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertEquals("Invalid user number", e.getMessage());
        }
    }

}