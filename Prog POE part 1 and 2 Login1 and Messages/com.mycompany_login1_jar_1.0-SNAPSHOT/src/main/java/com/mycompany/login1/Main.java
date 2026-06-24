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
        
// ==========================
// QUICKCHAT
// ==========================

System.out.println("\nWelcome to QuickChat.");
System.out.print("How many messages would you like to send? ");
int numMessages =Integer.parseInt(input.nextLine());

boolean running =true;


while(running){
System.out.println("\n--- MENU ---");
System.out.println("1 Send Messages");
System.out.println("2 Show Sent Messages");
System.out.println("3 Quit");
System.out.print("Choose option: ");
String choice =input.nextLine();

switch(choice){


case "1)":sendMessages(input,numMessages);
break;
case "2)": System.out.println(Messages.printMessages());
break;
case "3)":running = false;System.out.println("Goodbye");
break;
default:
System.out.println("Invalid option.");
}
}
input.close();
}

// ==========================
// SEND MESSAGES
// ==========================
public static void sendMessages(
Scanner input,
   int numMessages)
{
for(int i=0;i<numMessages; i++)
{
System.out.println("\n--- Message "+(i+1)+" of "+numMessages+" ---");


// =====================
// Recipient
// =====================
String recipients = "";
while(true){
System.out.print("Enter recipient (+27718693002): " );
recipients = input.nextLine();

Messages temp = new Messages(recipients,"placeholder",0);
String check =temp.checkRecipientsCell();
System.out.println(check);

if(check.equals("Cell phone number successfully captured.")){
break;
}

}
// =====================
// Message text
// =====================
String messageText = "";
while(true){
System.out.print("Enter your message (max 250 chars): ");

messageText = input.nextLine();
if(messageText.length()<=250){
System.out.println("Message ready to send.");
break;

}
else{
int excess = messageText.length()-250;
System.out.println("Message exceeds limit by " + excess);
}
}
// =====================
// Create message
// =====================
Messages temp = new Messages(recipients,messageText,0);
String validationResult = temp.checkMessageLength();
if(validationResult.equals("Message ready to send")){ 
Messages Msg = new Messages(recipients,messageText,1); 
System.out.println("Message ID: "+Msg.checkMessageID() );
System.out.println("Message Hash: "+Msg.getMessageHash());
System.out.println("\n1) Send");
System.out.println( "2) Disregard");
 System.out.println("3) Store");
System.out.print("Choose:");
int action=Integer.parseInt(input.nextLine());
String result = Msg.SentMessage(action);
System.out.println(result);

if(action == 1){
System.out.println("\n--- Message Details ---");
System.out.println("Message ID: "+Msg.checkMessageID());
System.out.println("Message Hash: "+Msg.getMessageHash());
System.out.println("Recipient: "+Msg.getRecipient());
System.out.println("Message: "+Msg.getMessageText());    
}
}else{
System.out.print(validationResult);
System.out.println("This message could not be processed.");
}
System.out.println("\n====================");
System.out.println("Total messages sent: "+ Messages.totalMessagesSent());
System.out.println("====================");
}
}
}
