/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
  /*

 */
package com.mycompany.login1;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
//POE PART 3 added : StoredMessages and Display a report 
//Updated from Part 2 to add:
// Menu option 4: Stored Messages
public static void main(String[] args) {
//Create a scanner object
Scanner input = new Scanner(System.in);
Login1  auth  = new Login1();

// PART 1 — REGISTER
System.out.println(" ; Register ;");
System.out.println("Enter username");
String username = input.nextLine();
System.out.println("Enter password");
String password = input.nextLine();
System.out.println("Enter cell phone number(+27838968976)");
String phone = input.nextLine();
String result = auth.registerUser(username, password, phone);
System.out.println(result);

if (!result.equals("User successfully registered.")) {
System.out.println("Registration failed. Please restart.");
input.close();
return;
}

// PART 1 — LOGIN
System.out.println("\n ;Login;");
System.out.println("Enter username:");
String loginUser = input.nextLine();
System.out.println("Enter password: ");
String loginPass = input.nextLine();
String loginResult = auth.returnLoginStatus(loginUser, loginPass);
System.out.println(loginResult);
if (!auth.loginUser(loginUser, loginPass)) {
System.out.println("Login failed. Exiting.");
input.close();
return;
}

// PART 3: Load any previously stored messages from
// the JSON file into the storedList array.
// This runs once at startup so stored messages are
// available in the "Stored Messages" menu.
MessageManager.loadStoredFromJSON();
// PART 2 — WELCOME + MESSAGE COUNT
System.out.println("\nWelcome to QuickChat.");
System.out.print("How many messages would you like to send? ");
int numMessages = 0;
try {
numMessages = Integer.parseInt(input.nextLine().trim());
} catch (NumberFormatException e) {
System.out.println("Invalid number. Exiting.");
input.close();
return;
}
// MAIN MENU LOOP
// PART 3: Added option 4 — Stored Messages
boolean running = true;
    
while (running) {
System.out.println("\n--- MENU ---");
System.out.println("1) Send Messages");
System.out.println("2) Show recently sent messages");
System.out.println("3) Quit");
System.out.println("4) Stored Messages");   // PART 3 — new option
System.out.print("Choose an option: ");
String choice = input.nextLine().trim();

switch (choice) {
case "1":
sendMessages(input, numMessages);
break;
case "2":// UPDATED: now reads from sentList object list directly
showSentMessages();
break;
case "3":
running = false;
System.out.println("Goodbye!");
break;
case "4":
// PART 3 — open the stored messages sub-menu
storedMessagesMenu(input);
break;
default:
System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
}
}
input.close();
}

// PART 2 — showSentMessages()
// Reads from sentList object list directly using getSentList()
// Replaced "Coming Soon" with actual sent message display
public static void showSentMessages() {
ArrayList<Messages> sentList = Messages.getSentList();
if (sentList.isEmpty()) {
System.out.println("No messages sent yet.");
return;
}

System.out.println("\n--- Recently Sent Messages ---");
for (int i = 0; i < sentList.size(); i++) {
Messages msg = sentList.get(i);
System.out.println("---- Message " + (i + 1) + " ----");
System.out.println("ID       : " + msg.getMessageID());
System.out.println("Hash     : " + msg.getMessageHash());
System.out.println("Recipient: " + msg.getRecipient());
System.out.println("Message  : " + msg.getMessageText());
}
System.out.println("\nTotal sent: " + sentList.size());
}

// PART 3 — storedMessagesMenu()
public static void storedMessagesMenu(Scanner input) {
boolean inMenu = true;
while (inMenu) {
System.out.println("\n--- STORED MESSAGES MENU ---");
System.out.println("a) Display sender and recipient of all stored messages");
System.out.println("b) Display the longest stored message");
System.out.println("c) Search for a message by ID");
System.out.println("d) Search all messages for a recipient");
System.out.println("e) Delete a message using the message hash");
System.out.println("f) Display full report of all messages");
System.out.println("x) Back to main menu");
System.out.print("Choose an option: ");
String option = input.nextLine().trim().toLowerCase();

switch (option) {

case "a":      // a. Display sender and recipient of all stored messages
ArrayList<Messages> storedList = Messages.getStoredList();
if (storedList.isEmpty()) {
System.out.println("No stored messages.");
} else {
System.out.println("\n--- Stored Message Contacts ---");
for (int i = 0; i < storedList.size(); i++) {
Messages msg = storedList.get(i);
System.out.println("Message " + (i + 1) + ":");
System.out.println("Recipient: " + msg.getRecipient());
System.out.println("Message: " + msg.getMessageText());
}
}
break;

case "b":       // b. Display the longest stored message
ArrayList<Messages> stored = Messages.getStoredList();
if (stored.isEmpty()) {
System.out.println("No stored messages.");
} else {
Messages longest = stored.get(0);
for (Messages msg : stored) {
if (msg.getMessageText().length() > longest.getMessageText().length()) {
longest = msg;
}
}
System.out.println("\n--- Longest Stored Message ---");
System.out.println("Recipient: " + longest.getRecipient());
System.out.println("Message  : " + longest.getMessageText());
}
break;

case "c":         // c. Search for a message ID and display the corresponding recipient and message.
System.out.print("Enter message ID to search: ");
String searchID = input.nextLine().trim();

ArrayList<Messages> allC = new ArrayList<>();
allC.addAll(Messages.getSentList());
allC.addAll(Messages.getStoredList());
allC.addAll(Messages.getDisregardedList());
                
boolean foundC = false;
System.out.println("\n--- Search Results ---");
for (Messages msg : allC) {
if (msg.getMessageID().equals(searchID)) {
System.out.println("ID       : " + msg.getMessageID());
System.out.println("Hash     : " + msg.getMessageHash());
System.out.println("Recipient: " + msg.getRecipient());
System.out.println("Message  : " + msg.getMessageText());
System.out.println("-----");
foundC = true;
}
}
if (!foundC) {
System.out.println("No message found for ID: " + searchID);
}
break;

case "d":          // d. Search all messages stored for a particular recipient
System.out.print("Enter recipient number to search: ");
String searchRecipient = input.nextLine().trim();

ArrayList<Messages> allD = new ArrayList<>();
allD.addAll(Messages.getSentList());
allD.addAll(Messages.getStoredList());
allD.addAll(Messages.getDisregardedList());

boolean foundD = false;
System.out.println("\n--- Messages for " + searchRecipient + " ---");
for (Messages msg : allD) {
if (msg.getRecipient().equals(searchRecipient)) {
System.out.println("Message: " + msg.getMessageText());
System.out.println("Hash: " + msg.getMessageHash());
System.out.println("-----");
foundD = true;
}
}
if (!foundD) {
System.out.println("No messages found for: " + searchRecipient);
}
break;

case "e":
// e. Delete a message using the message hash
System.out.print("Enter message hash to delete: ");
String hash = input.nextLine().trim().toUpperCase();
ArrayList<Messages> storedE = Messages.getStoredList();
Messages toRemove = null;

for (Messages msg : storedE) {
if (msg.getMessageHash().equals(hash)) {
toRemove = msg;
break;
}
}
                
if (toRemove != null) {
String deletedText = toRemove.getMessageText();
storedE.remove(toRemove);
System.out.println("\n\"" + deletedText + "\" successfully deleted.");
} else {
System.out.println("Hash not found. No message deleted.");
}
break;

case "f":                                               // f. Display a report that lists the full details of ALL THE STORED messages.
ArrayList<Messages> allF = Messages.getStoredList();    // uses ONLY Messages.getStoredList().
if (allF.isEmpty()) {
System.out.println("No stored messages to report.");
} else {
System.out.println("\n--- Full Stored Message Report ---");
System.out.printf("%-25s %-20s %s%n","Message Hash", "Recipient", "Message");
System.out.println("---------");
for (Messages msg : allF) {
System.out.printf("%-25s %-20s %s%n",
msg.getMessageHash(), msg.getRecipient(), msg.getMessageText());
}
}
break;

case "x":       // go back to main menu
inMenu = false; 
System.out.println("Returning to main menu...");
break;

default:
System.out.println("Invalid option. Please choose a, b, c, d, e, f, or x.");
}
}
}

// sendMessages() , updated from Part 2 to use getMessageID() safely
public static void sendMessages(Scanner input, int numMessages) {
int messageNumber = 0;
for (int i = 0; i < numMessages; i++) {
messageNumber++;
System.out.println("\n--- Message " + messageNumber + " of " + numMessages + " ---");

//Get valid recipient
String recipient = "";
while (true) {
System.out.print("Enter recipient cell number (e.g. +27718693002): ");
recipient = input.nextLine().trim();
Messages tempMsg = new Messages(recipient, "placeholder", messageNumber);
String cellCheck = tempMsg.checkRecipientsCell();
System.out.println(cellCheck);
if (cellCheck.equals("Cell phone number successfully captured.")) break;
}

//Get valid message text
String messageText = "";
while (true) {
System.out.print("Enter your message (max 250 characters): ");
messageText = input.nextLine().trim();
if (messageText.length() <= 250) {
System.out.println("Message ready to send.");
break;
} else {
int excess = messageText.length() - 250;
System.out.println("Message exceeds the limit of 250 characters by "+ excess + "; Please minimize the size.");
}
}

//Create Messages object
Messages msg = new Messages(recipient, messageText, messageNumber);

//Show ID
if (msg.checkMessageID()) {
System.out.println("Message ID generated: " + msg.getMessageID());
}

//Show Hash
System.out.println("Message Hash: " + msg.getMessageHash());

//Ask what to do and there are the options 
System.out.println("\nWhat would you like to do?");
System.out.println("1) Send Message");
System.out.println("2) Disregard Message");
System.out.println("3) Store Message to send later");
System.out.print("Choose an option: ");
int action = 0;
try {
action = Integer.parseInt(input.nextLine().trim());
} catch (NumberFormatException e) {
action = 2;
}

System.out.println(msg.SentMessage(action));

//If sent — display details
if (action == 1) {
System.out.println("\n--- Message Details ---");
System.out.println("Message ID:   " + msg.getMessageID());
System.out.println("Message Hash: " + msg.getMessageHash());
System.out.println("Recipient:    " + msg.getRecipient());
System.out.println("Message:      " + msg.getMessageText());
}
}

System.out.println("\n====");
System.out.println("Total messages sent: " + Messages.totalMessagesSent());
System.out.println("====");
System.out.println(Messages.printMessages());
}
}
