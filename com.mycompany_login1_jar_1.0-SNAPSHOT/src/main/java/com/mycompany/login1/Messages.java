/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login1;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author User
 */

public class Messages {

    //Private Fields Storing each messages details
private String messageID;
private int messageNumber;
private String recipient;
private String messageText;
private String messageHash;
    
// part 3 of POE Static variables shared across all message objects using  fully arrays and manipulating Strings 
private static int totalMessagesSent = 0;
private static ArrayList<String> sentMessages = new ArrayList<>();
private static ArrayList<String> disregardedMessages = new ArrayList<>();
private static ArrayList<String> storedMessages = new ArrayList<>();
private static ArrayList<String> messageHashes = new ArrayList<>();
private static ArrayList<String> messageIDs = new ArrayList<>();
private static ArrayList<String> recipients = new ArrayList<>();
private static ArrayList<Messages>sentList = new ArrayList<>();
private static ArrayList<Messages>storedList = new ArrayList<>();
private static ArrayList<Messages>disregardedList = new ArrayList<>();
//Contructor will automate when the new message object is created 

public Messages(String recipient, String messageText,int messagesNumber){
this.recipient = recipient;
this.messageText = messageText;
this.messageNumber = messagesNumber;
this.messageID = generateMessageID();
this.messageHash = createMessageHash();

// part 3 added arrays to store the messagesID and hash via arrays 
messageIDs.add(this.messageID);
messageHashes.add(this.messageHash);
recipients.add(this.recipient);
}
//Generatae a 10 digit message ID 
private String generateMessageID(){
Random random = new Random();
long id = (long)(random.nextDouble()* 9000000000L)+ 1000000000L;
return String.valueOf(id);
}
public String getMessageID() {
return messageID;
}
//ID message checker ensures that the ID is exactly 10 digits
public boolean  checkMessageID(){
return messageID != null && messageID.length()== 10;
}
// Validates the recipients cell number  
// The cell number must start with + and then 13 digits or less after 
public String checkRecipientsCell(){
if(recipient != null && recipient.startsWith("+")&& recipient.length()<= 13){
return "Cell phone number successfully captured.";
}
return "Cell number is not correctly formatted or does not contain an international code; please correct the number and try again.";
} 
//Create Message Hash  using the first two digits of the ID , Message Number , First and Last word 
public String  createMessageHash(){
String idPrefix = messageID.substring(0,2) ;// First Id characters
String[] words = messageText.trim().split("\\s+");
String firstWord = words[0];
String lastWord = words[words.length-1];
lastWord = lastWord.replaceAll("[^a-zA-Z0-9]","");
//combine all the parts and change them to upper case 
String messageHash = (idPrefix + ":" +messageNumber+ ":" + firstWord + lastWord).toUpperCase();
return messageHash;
}

//Checking and making sure that the message doesnt exceed 250 characters
public String checkMessageLength(){
if (messageText.length()<= 250 ){
return "Message ready to send";
}
    
int excess = messageText.length() - 250; // This will calculate the words exceeding the 250 word message limit per text
return "Message exceeds the limit of 250 characters by " + excess + "; Please minimize the size.";
}

//Allows users to send or disregard  or keep messages 
public String SentMessage(int choice){
String entry = "ID: " + messageID + " | Hash: " + messageHash + " | Recipient: " + recipient + " | Message: " + messageText;
switch(choice){

case 1:
sentMessages.add(entry);
sentList.add(this);
totalMessagesSent++;
return "Message successfully sent.";

case 2:
disregardedMessages.add(entry);
disregardedList.add(this);
return "Press 0 to delete the message.";

case 3:
storedMessages.add(entry);
storedList.add(this);
storedMessages();
return "Message successfully stored.";
default:
return "Invalid option.";
}
}
//Return all messages sent during the scession 
public static String printMessages(){
if(sentMessages.isEmpty()){
return "No message sent yet.";  
    }
StringBuilder entry = new StringBuilder();
for (int i = 0; i < sentMessages.size(); i++){
entry.append("----Message----").append(i+1).append("----\n");
entry.append(sentMessages.get(i)).append("\n\n");
}
return entry.toString();
}
//Return the amout number of messages sent during the scession
public static int totalMessagesSent(){
return totalMessagesSent;
}
//Add Json file to the Message.java filr to store and retrieve the message 
public void storedMessages(){
try(FileWriter file  = new FileWriter("message.Json",true)){  // this will apppend the file 
StringBuilder json = new StringBuilder();
json.append("{\n");
json.append("  \"messageID\": \""   ).append(messageID    ).append("\",\n");
json.append("  \"messageNumber\": " ).append(messageNumber ).append(",\n");
json.append("  \"messageHash\": \"" ).append(messageHash   ).append("\",\n");
json.append("  \"recipient\": \""   ).append(recipient     ).append("\",\n");
json.append("  \"message\": \""     ).append(messageText   ).append("\"\n");
json.append("},\n");
file.write(json.toString());
  
file.write(System.lineSeparator()); // new message will have a separate line 
}
catch(IOException e){
System.out.println(" Error  storing message:" + e.getMessage());
}
}


// LONGEST STORED MESSAGE
public static String getLongestMessage() {
if (storedMessages.isEmpty()) {
return "No stored messages.";
}

String longest =  (String) storedMessages.get(0);
for (String msg : storedMessages) {
if (msg.length()> longest.length()) {
longest = msg;
}
}
return longest;
}

// check MESSAGE ID
public static String searchMessageID(String id) {
for (String entry : storedMessages) {
if (entry.contains("ID: " + id + " |")) {
return entry;
}
}
return "Message ID not found.";
}


// Find the RECIPIENT
public static String searchByRecipient(String recipientNumber) {
String result ="";
for(int i = 0; i< recipients.size(); i++){
String cleanStored = recipients.get(i).trim().replace("", "");
String cleanSearch = recipientNumber.trim().replace("", "");
if(cleanStored.equalsIgnoreCase(cleanSearch)){
if(i< storedMessages.size()) {
 result += storedMessages.get(i)+ "\n";
}  
}
}
if(result.length()==0){
 return "No message found.";   
}
return result;
}
public static String findRecipient(String recipient){
return searchByRecipient(recipient);
}

// DELETE MESSAGE USING HASH
public static String deleteMessageByHash(String hash) {
if (hash == null || hash.trim().isEmpty()) {
return "Error: Message hash not found. Deletion failed.";
}
boolean deletedAny = false;
 
// Safe to use the same index i here — these 3 arrays grow
// together in the constructor for EVERY message created.
for (int i = messageHashes.size() - 1; i >= 0; i--) {
if (messageHashes.get(i).trim().equalsIgnoreCase(hash.trim())) {
messageHashes.remove(i);
messageIDs.remove(i);
recipients.remove(i);
deletedAny = true;
}
}
// storedMessages only contains messages where SentMessage(3) was
// called — it is a DIFFERENT length/order than messageHashes.
// for the hash.
for (int i = storedMessages.size() - 1; i >= 0; i--) {
if (storedMessages.get(i).toUpperCase().contains("HASH: " + hash.trim().toUpperCase())) {
storedMessages.remove(i);
}
}
 
if (deletedAny) {
return "Message deleted successfully.";
} else {
return "Error: Message hash not found. Deletion failed.";
}
}
 
public static String DeleteMessagesByHash(String hash) {
return deleteMessageByHash(hash);
}
 
// DISPLAY REPORT
public static String displayReport() {
// Guard: nothing stored yet
if (storedMessages.isEmpty()) {
return "No stored messages to report.";
}
 
StringBuilder report = new StringBuilder();   // creates the object we build text on
report.append("\n===== STORED MESSAGE REPORT =====\n");
 for (int i = 0; i < storedMessages.size(); i++) {
String entry = storedMessages.get(i);     // pull the ONE correct entry
String[] parts = entry.split(" \\| ");    // break it into its 4 labeled fields
 // Print each labeled part on its own line
for (String part : parts) {
report.append(part).append("\n");
}
report.append("-----\n");
}
 
return report.toString();   // .toString() called on the StringBuilder object
}
 
// LOAD POE TEST DATA
 
public static void loadPOETestData() {
Messages m1 = new Messages("+27834557896","Did you get the cake?",1);
m1.SentMessage(1);

Messages m2 = new Messages("+27838884567","Where are you? You are late! I have asked you to be on time.",2);
m2.SentMessage(3);

Messages m3 = new Messages("+27834484567","Yohoooo, I am at your gate.",3);
m3.SentMessage(2);

Messages m4 = new Messages("0838884567","It is dinner time!",4);
m4.SentMessage(1);

Messages m5 = new Messages("+27838884567","Ok, I am leaving without you.",5);
m5.SentMessage(3);

}

//Getters 
public String getMessage(){
    return messageID;
}
public String getMessageHash(){
return messageHash;
}
public String getRecipient(){
    return recipient;
}
public  int getMessageNumber(){
    return messageNumber;
}
public String getMessageText(){
    return messageText;
}  
public static ArrayList<Messages> getSentList() {
    return sentList;
}

public static ArrayList<Messages> getStoredList() {
    return storedList;
}

public static ArrayList<Messages> getDisregardedList() {
    return disregardedList;
}

public static int returnTotalMessages() {
    return totalMessagesSent;
}

//Reset Data message scession ,used in the JUnit test to ensure that the each test starts fresh
public static void resetSession(){
totalMessagesSent = 0;
sentMessages.clear();
storedMessages.clear();       
disregardedMessages.clear();  
messageIDs.clear();           
messageHashes.clear();        
recipients.clear();
sentList.clear();
storedList.clear();
disregardedList.clear();
}
}
 
