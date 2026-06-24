/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.login1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User
 */
public class TestLogin1 {
    
    
  //Declare the object at the class level so all methods can see it
        Login1 user = new Login1();
        //Register Success
       @Test
 void testRegisterSuccss(){
   String result = user.registerUser("kyl_1", "Password@1","+27838968976");
        assertEquals("User successfully registered.",result);
    }
     //Username fail
 
     @Test
     void testUserNameFail(){
        String result = user.registerUser("kyle!!!!","Password@1","+27838968976");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",result);
     }    
   //Password Fail
   @Test
   void testPasswordFail(){
    String result = user.registerUser("kyl_1","password","+27838968976");
assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",result);
    }
//Phone fail
@Test
void testPhoneFail(){
   String result = user.registerUser("kyl_1","Password@1","08966553");
   assertEquals("Cell number is not correctly formatted or does not contain an international code; please correct the number and try again.",result);
       }
//Login success 
@Test
void testLoginSuccess(){
  user.registerUser("kyl_1","Password@1","+27838968976");
  assertTrue(user.loginUser("kyl_1","Password@1"));
    }
//Login Fail
@Test 
void testLogiFail(){
  user.registerUser("kyl_1","Password@1","+27838968976");
  assertFalse(user.loginUser("wrong","wrong"));
    }
    }   
    
    

