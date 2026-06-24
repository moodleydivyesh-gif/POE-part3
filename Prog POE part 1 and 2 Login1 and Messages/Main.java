/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login1;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class Main {
 

    public static void main(String[] args) {

        // Create the Scanner this will read what the user will type 
        Scanner input = new Scanner(System.in);

        // Create a Login1 object to handle register and login
        Login1 auth = new Login1();

        
        // PART 1 — REGISTER and Login
        
        System.out.println(" ; Register ;");

        // Get userName
        System.out.println("Enter username");
        String username = input.nextLine();

        // Get password
        System.out.println("Enter password");
        String password = input.nextLine();

        // Get number
        System.out.println("Enter cell phone number(+27838968976)");
        String phone = input.nextLine();

        // Call Register method
        String result = auth.registerUser(username, password, phone);
        System.out.println(result);

        // If registration failed — stop the program here
        if (!result.equals("User successfully registered.")) {
            System.out.println("Registration failed. Please restart and try again.");
            input.close();
            return;
        }
        
        System.out.println("\n ;Login;");

        // Get login userName
        System.out.println("Enter username:");
        String loginUser = input.nextLine();

        // Get login password
        System.out.println("Enter password: ");
        String loginPass = input.nextLine();

        // Display login status
        String loginResult = auth.returnLoginStatus(loginUser, loginPass);
        System.out.println(loginResult);

        // If login failed — stop the program here
        if (!auth.loginUser(loginUser, loginPass)) {
            System.out.println("Login failed. Exiting.");
            input.close();
            return;
        }

     
        // POE PART 2: QUICKCHAT
        

//part 2: Welcome to QuickChat
    System.out.println("\nWelcome to QuickChat.");
    System.out.println(" How many do you want to send ");
    int numMessages = Integer.parseInt(input.nextLine().trim());
    boolean running = true;
    // Main menu will loop will control the main menu loop util the user chooses to quit
    while(running){
System.out.println("\nQuickChat menu");
System.out.println("Choose an option");
System.out.println("1) send messages");
System.out.println("2) show recently sent messages");
System.out.println("3) Quit");
String choice = input.nextLine().trim();

if (choice.equals("1")){
 //This sends the messages
 for(int i = 0; i < numMessages;i++){
int messageNumber = i + 1;
System.out.println("\nMessages"+ messageNumber + "of" + numMessages);

// vallidate recipients cell number
String recipient = "";
boolean validRecipient = false;
while (!validRecipient){
 System.out.println("Enter recipients cell number:") ;
 recipient = input.nextLine().trim();
 Messages message = new Messages(recipient,"PlaceHolder",messageNumber);
 String recipientCheck = message.checkRecipientsCell();
 System.out.println(recipientCheck);
 if (recipientCheck.equals("Cell number successfully captured")){
validRecipient = true; //This will exit the loop if valid
}
}
 // Vallidate Message length
 String messageText = "";
 boolean validMessage = false;
 while(!validMessage){
  System.out.println("Enter your message(musn't exceed 250 characters )");
  messageText = input.nextLine();
 Messages temp = new Messages(recipient, messageText, messageNumber);
 String lengthCheck = temp.checkMessageLength();
 System.out.println(lengthCheck);
 if(lengthCheck.equals("Message ready to send.")){
validMessage = true; // Exits the loop if valid
     
 }
 }
 //Final message object vallidated
 Messages messages = new Messages(recipient,messageText,messageNumber);
 //Diplay the full message details
 System.out.println("\nMessageID:"+ messages.checkMessageID());
 System.out.println("Message Hash" + messages.getMessageHash());
 System.out.println("Recipient" + messages.getRecipient());
 System.out.println("Message" + messages.getMessageText());

 // Ask the user what to do with the message
 int sendChoice = 0;
 while(sendChoice< 1 || sendChoice> 3){
     System.out.println("\n1) Send Message");
     System.out.println("\n2) Disregard Message");
     System.out.println("\n3) Store Message");
     System.out.println("Choose: ");
     sendChoice = Integer.parseInt(input.nextLine().trim());
     break;
   }
 }
 if(choice.equals("1")){
System.out.println(Messages.sendMessage(sentChoice));
System.out.println("Message" );
}
    //Display total messages sent after the messages were proccessed during the session 
    System.out.println("\nTotal Messages sent: " + Messages.totalMessagesSent());
    }
    else if(choice.equals("2")){
       System.out.println("Comming soon.");
            }
    else if(choice.equals("3")){
            running = false;
            System.out.println("Goodbye!");
             }
            else
            //Handel invalid menu input
            System.out.println("Invalid option, Please choose 1, 2, or 3.");
            }
    }
    }
       