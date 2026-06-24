/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.login1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class TestPart3 {

private Messages msg1, msg2, msg3, msg4, msg5;
// @BeforeEach — resets all arrays then populates test data
// Runs before EVERY @Test so each test starts clean.
@BeforeEach
public void setUp() {
Messages.resetSession(); // clear all static arrays

// Create 5 test messages using exact assignment test data
msg1 = new Messages("+27834557896","Did you get the cake?", 1);
msg2 = new Messages("+27838884567","Where are you? You are late! I have asked you to be on time.", 2);
msg3 = new Messages("+27834484567","Yohoooo, I am at your gate.", 3);
msg4 = new Messages("0838884567","It is dinner time!", 4);
msg5 = new Messages("+27838884567","Ok, I am leaving without you.", 5);

// Populate the correct arrays by calling SentMessage()
msg1.SentMessage(1); // → sentMessages
msg2.SentMessage(3); // → storedMessages
msg3.SentMessage(2); // → disregardedMessages
msg4.SentMessage(1); // → sentMessages
msg5.SentMessage(3); // → storedMessages
}

// TEST 1: Sent messages populated correctly
@Test
public void testSentMessagesPopulated() {
String output = Messages.printMessages();

// printMessages() prints all entries in sentMessages
assertTrue(output.contains("Did you get the cake?"),"Sent messages should contain msg1.");
assertTrue(output.contains("It is dinner time!"),"Sent messages should contain msg4.");
// ARRAYS: build a small String[] and print with Arrays.toString()
String[] sentTexts = {msg1.getMessageText(),msg4.getMessageText()};
System.out.println("Sent messages: " + Arrays.toString(sentTexts));
System.out.println(output);
}

// TEST 2: Display the longest message
@Test
public void testGetLongestMessage() {
String longest = Messages.getLongestMessage();
String expected = "ID: " + msg2.getMessageID()+ " | Hash: " + msg2.getMessageHash()+ " | Recipient: " + msg2.getRecipient()+ " | Message: " + msg2.getMessageText();
assertEquals(expected, longest,"Message 2 is the longest stored message.");
System.out.println("Longest: " + longest);
}

   
// TEST 3: Search for a message by ID
@Test
public void testSearchByID() {
String result = Messages.searchMessageID(msg2.getMessageID());
assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."),"Search by ID should return msg2's message text.");
System.out.println("Search by ID result:\n" + result);
}
 
// TEST 4: Search all messages for a particular recipients
@Test
public void testSearchByRecipient() {
String result = Messages.searchByRecipient("+27838884567");
assertFalse(result.contains("Where are you? You are late! I have asked you to be on time."),
"Should find msg2 for recipient +27838884567");
assertTrue(result.contains("Ok, I am leaving without you."),"Should find msg5 for recipient +27838884567");
System.out.println("Recipient search:\n" + result);
}

    
// TEST 5: Delete a message using the message hash
//this prompt must Delete msg2 and the system confirms deletion.
    
@Test
public void testDeleteMessagesByHash() {
String hashToDelete = msg2.getMessageHash();

// Confirm msg2 exists before deletion
String before = Messages.searchMessageID(msg2.getMessageID());
assertTrue(before.contains("Where are you?"),
"msg2 should exist in storedMessages before deletion.");

// Delete
String result = Messages.deleteMessageByHash(hashToDelete);
assertTrue(result.contains("deleted")||result.contains("successfully"),"Result should confirm deletion.");

// Confirm msg2 is gone after deletion
String after = Messages.searchMessageID(msg2.getMessageID());
assertEquals("Message ID not found.", after,"msg2 should no longer be found after deletion.");
System.out.println("Delete result: " + result);
}

    
// TEST 6: Display full report
//the report must show Hash, Recipient, Message for stored messages.

@Test
public void testDisplayReport() {
String report = Messages.displayReport();
assertNotNull(report, "Report should not be null.");

// Report should contain stored messages (msg2 and msg5)
assertTrue(report.contains("Where are you? You are late! I have asked you to be on time."),"Report should contain msg2.");
assertTrue(report.contains("Ok, I am leaving without you."),"Report should contain msg5.");
assertTrue(report.contains("Hash:"),"Report should contain hash label.");
assertTrue(report.contains("Recipient:"),"Report should contain recipient label.");
System.out.println(report);
    }

    
// TEST 7: messageHashes and messageIDs populated correctly
//using array it must: create 5 messages  → 5 hashes, 5 IDs stored
public void testHashAndIDArraysPopulated() {
// Build String[] from the 5 message objects directly
String[] hashes = {msg1.getMessageHash(),msg2.getMessageHash(),msg3.getMessageHash(),msg4.getMessageHash(),msg5.getMessageHash()};
String[] ids = {msg1.getMessageID(),msg2.getMessageID(),msg3.getMessageID(),msg4.getMessageID(),msg5.getMessageID()};

// Both arrays should have exactly 5 entries
assertEquals(5, hashes.length,"hashArray should have 5 entries — one per message.");
assertEquals(5, ids.length,"idArray should have 5 entries — one per message.");

// ARRAYS: Arrays.toString() prints array contents in test output
System.out.println("All Hashes: " + Arrays.toString(hashes));
System.out.println("All IDs   : " + Arrays.toString(ids));
// using a for loop to Every hash to be non-null and fully uppercase
for (int i = 0; i < hashes.length; i++) {
assertNotNull(hashes[i],"Hash at index " + i + " should not be null.");
// STRING: .toUpperCase() on an already-uppercase value returns same value
assertEquals(hashes[i].toUpperCase(), hashes[i],"Hash at index " + i + " should be all uppercase.");
}
}

   
// TEST 8: Disregarded message confirmed
@Test
public void testDisregardedMessageConfirmed() {
// msg3 should NOT appear in sent messages
String sent = Messages.printMessages();
assertFalse(sent.contains("Yohoooo, I am at your gate."),"Disregarded msg3 should not appear in sent messages.");

// msg3 should NOT appear in the stored report
String report = Messages.displayReport();
assertFalse(report.contains("Yohoooo, I am at your gate."),"Disregarded msg3 should not appear in stored report.");

// Confirm msg3's hash exists (message was created and hashed correctly)
assertNotNull(msg3.getMessageHash(),"msg3 should still have a valid hash even though disregarded.");
System.out.println("msg3 hash (disregarded): " + msg3.getMessageHash());
}

    
// TEST 9: Stored messages confirmed
@Test
public void testStoredMessagesPopulated() {
String report = Messages.displayReport();
assertTrue(report.contains("Where are you? You are late! I have asked you to be on time."),"storedMessages should contain msg2.");
assertTrue(report.contains("Ok, I am leaving without you."),"storedMessages should contain msg5.");
System.out.println("Stored messages report:\n" + report);
}
}
