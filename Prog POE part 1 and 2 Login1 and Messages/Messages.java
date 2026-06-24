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
    
    // Static variables shared across all message objects 
    private static int totalMessagesSent = 0;
    private static ArrayList <String> sentMessages = new ArrayList<>();

//Contructor will automate when the new message object is created 

    public Messages(String recipient, String messageText,int messagesNumber){
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messagesNumber;
        this.messageID = getMessageID();
        this.messageHash = createMessageHash();
    }
//Generatae a 10 digit message ID 
    public String getMessageID(){
        Random random = new Random();
        long id = (long)(random.nextDouble()* 9000000000L)+ 1000000000L;
        return String.valueOf(id);
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
        return "Message ready to send.";
    }
    
int excess = messageText.length() - 250; // This will calculate the words exceeding the 250 word message limit per text
return "Message exceeds the limit of 250 characters by " + excess + "; Please minimize the size.";
        }

//Allows users to send or disregard  or keep messages 
public String sendMessage(int sentChoice){
    switch(sentChoice){
      case 1: // ---- SEND ----
StringBuilder entry = new StringBuilder();
entry.append("Message ID: ").append(messageID);
entry.append(" | Hash: ").append(messageHash);
entry.append(" | Recipient: ").append(recipient);
entry.append(" | Message: ").append(messageText);
 //Add to the shared  Arraylist 
 sentMessages.add(entry.toString());
 //This will track the ttal number of messages sent throughout the scession 
 totalMessagesSent++;
return "Message successfully sent.";
case 2: // ---- DISREGARD ----
return "Press 0 to delete the message.";
 case 3: // ---- STORE ----
storeMessages();
return "Message successfully stored.";
 
default:
return "Invalid option. Please choose 1, 2, or 3.";
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
entry.append(sentMessages.get(1)).append("\n");
}
return entry.toString();
}
//Return the amout number of messages sent during the scession
public static int totalMessagesSent(){
return totalMessagesSent;
}
//Add Jon file to the Message.java filr to store and retrieve the message 

public void storeMessages(){
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
//Reset Data message scession ,used in the JUnit test to ensure that the each test starts fresh
public static void resetSession(){
    totalMessagesSent = 0;
    sentMessages.clear();
}
} 

