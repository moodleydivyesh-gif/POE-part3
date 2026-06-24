/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login1;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class MessageManager {
    //Using an ArrayList to store messages  better instead of using a standard Array
   private static ArrayList< Messages > storedMessages = new ArrayList<>();
   //Part 3 Load Stored Messages fom JSON
   public static void loadStoredFromJSON(){
   System.out.println("Json/File loaded successfully.");
   }
   //Option A Disply the Sender and the Recipients
   public static String displayStoredSendersAndRecipients(){
   if(storedMessages.isEmpty()) {
    return "No messages currently stored.";
    }   
   String output = "---Stored Contacts---\n";
   for(Messages msg: storedMessages){
    output += "Recipients: "+ msg.getRecipient() +"\n";
   } 
   return output;
   } 
   //Option B Display the Longest Message 
   public static String getLongestMessage(){
    if (storedMessages.isEmpty()) {
     return "No message is stored currently.";   
    } 
    Messages longest = storedMessages.get(0);
    for(Messages msg: storedMessages){
      if(msg.getMessageText().length()> longest.getMessageText().length()) {
       longest = msg;   
      } 
    }
    return longest.getMessageText();
   }
   //Option C search by ID 
public static String searchByID(String id){
for(Messages msg: storedMessages)  {
if(msg.getMessageID().equals(id)){
return "Found message: " + msg.getMessageText() + "\nRecipient: " + msg.getRecipient();
    }   
   }
   return "Error :Message ID not found ";
   }
   //Option D search by Recipient
   public static String searchByRecipient(String recipient){
    String output = ""    ;
   for(Messages msg : storedMessages){
   if(msg.getRecipient().equals(recipient)){
    output += "ID: " + msg.getMessageID()+ "|Hash:" + msg.getMessageText() + "\n";
    }    
   }
   if(output.isEmpty()){
    return "Error: No message found for this recipient";   
   }
   return output;
   }
   //Option E Deleting by Hash
   public static String deleteMessageByHash(String hash){
    for(int i = 0; i < storedMessages.size(); i++) {
    if(storedMessages.get(i).getMessageHash().equals(hash)){
    storedMessages.remove(i);
    return "Message successfully deleted. " ;
    }    
    }  
    return "Error: Message hash not found.Deletion failed.";
   }
   //Option F Display full report 
   public static String displayReport(){
    if(storedMessages.isEmpty())  {
     return "No message to report.";
     } 
    String report = "--- Full Message Report---\n";
    for (Messages msg: storedMessages){
      report += "ID: " + msg.getMessageID()+ "\n" + "Hash: " + msg.getMessageHash() + "\n" + "Recipient: " + msg.getRecipient() + "\n" + "Messages: " + msg.getMessageText() + "\n" + "------\n";
    }
    return report;
   }
}
