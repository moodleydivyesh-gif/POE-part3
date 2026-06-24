/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.login1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
/**
 *
 * @author User
 */

public class TestMessages {

private Messages messages;
private final String TEST_RECIPIENT = "+27718693002";
private final String TEST_MESSAGE = "Hi Mike can you join us for dinner tonight";
private final int TEST_NUM = 2;


    @BeforeEach
public void resetSession() {
messages = new Messages(TEST_RECIPIENT, TEST_MESSAGE,TEST_NUM);
 Messages.resetSession();
}
    
// Message ID Tests
    

@Test
void generateMessageID() {
 String id = messages.getMessage();
assertNotNull( id,"Message ID should be generated." );
    }



    @Test
void checkMessageID() {
assertTrue(messages.checkMessageID(),"Message ID must contain 10 digits.");
    }
    
    // Message Length Tests
    

@Test
void messageLengthSuccess() {
String result =messages.checkMessageLength();
assertEquals("Message ready to send",result);
    }



    @Test
void messageLengthFailure() {
String longMessage ="A".repeat(260);
Messages longMsg = new Messages(TEST_RECIPIENT,longMessage,TEST_NUM);
String result = longMsg.checkMessageLength();
 assertEquals("Message exceeds the limit of 250 characters by 10; Please minimize the size.",result);
    }
    
    // Recipient Tests
  

    @Test
void checkRecipientCell() {
String result = messages.checkRecipientsCell();
assertEquals("Cell phone number successfully captured.", result );
    }



    @Test
void recipientCellNoInternationalCode() {
Messages invalid =new Messages("0718693002", TEST_MESSAGE, TEST_NUM);
String result = invalid.checkRecipientsCell();
assertEquals("Cell number is not correctly formatted or does not contain an international code; please correct the number and try again.",result);
    }



    @Test
    void recipientCellTooLong() {
Messages invalid =new Messages("+2771869300299",TEST_MESSAGE,TEST_NUM );
String result = invalid.checkRecipientsCell();
assertEquals( "Cell number is not correctly formatted or does not contain an international code; please correct the number and try again.",result);
    }

    // Message Hash Tests
   

    @Test
    void messageHashNotNull() {
assertNotNull(messages.getMessageHash());
    }



    @Test
    void messageHashUppercase() {
String hash =messages.getMessageHash();
assertEquals( hash.toUpperCase(),hash);
    }



    @Test
    void messageHashContainsColon() {
 String hash =messages.getMessageHash();
assertTrue(hash.contains(":"));
    }



    @Test
    void messageHashEndsCorrectly() {
String hash =messages.getMessageHash();
 assertTrue(hash.endsWith("HITONIGHT")
        );
    }
    // Sent Message Tests
    

    @Test
    void sendMessage() {
String result =messages.SentMessage(1);
assertEquals("Message successfully sent.", result);
    }



    @Test
    void disregardMessage() {
String result =messages.SentMessage(2);
assertEquals("Press 0 to delete the message.",result);
    }



@Test
void storeMessage() {
String result = messages.SentMessage(3);
assertEquals("Message successfully stored.",result);
    }

// Total Messages Tests
   

@Test
void totalMessagesAfterSend() {
int before =Messages.totalMessagesSent();
messages.SentMessage(1);
int after = Messages.totalMessagesSent();
assertEquals(before + 1,after);
    }



@Test
void totalMessagesDisregard() {
int before =Messages.totalMessagesSent();
messages.SentMessage(2);
int after = Messages.totalMessagesSent();
assertEquals(before,after );
       
    }



@Test
void totalMessagesStore() {
int before =  Messages.totalMessagesSent();
messages.SentMessage(3);
int after =Messages.totalMessagesSent();
assertEquals( before,after );
               
                
       
    }
}

 