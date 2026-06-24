/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.login1;

   
/**
 *
 * @author User
 */
public class Login1 {

//Storing details 
    private String userName;
    private String  password ;
    private final String firstName = "Divyesh";
    private final String lastName = "Moodley";
    private String cellPhoneNumber;

    // Username validation
    public boolean checkUserName(){
        return userName.contains("_") && userName.length()<= 5;
    }
    //password validation
public boolean checkPasswordComplexity(){
 boolean hasUpperCase= false;
 boolean hasNumber = false ;
 boolean hasSpecial = false ;

for(char c : password.toCharArray()){
    
    if(Character.isUpperCase(c))
        hasUpperCase = true;
    
    if(Character.isDigit(c))
        hasNumber =true;
    
    if(Character.isLetterOrDigit(c))
         hasSpecial = true;
}    

return password.length( )>= 8 && hasUpperCase && hasNumber && hasSpecial;
}

// phone Validation (REGEX)
 //South African format : +27 followed by 9 digits 
public boolean checkCellPhoneNumber(){
    return cellPhoneNumber != null && cellPhoneNumber.matches("^\\+27\\d{9}$");
}
//Register user
public String registerUser(String userName ,String password ,String cellPhoneNumber){
this.userName = userName;
this.password = password;
this.cellPhoneNumber = cellPhoneNumber;

//Username check
if(!this.checkUserName()){
return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";   
}
if(!this.checkPasswordComplexity()){
    return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
}
if(!this.checkCellPhoneNumber()){
  return "Cell number is not correctly formatted or does not contain an international code; please correct the number and try again."  ;
}
return "User successfully registered.";
}
 //Validate login 
public boolean loginUser(String enteredUserName,String enteredPassword){
if(enteredUserName == null|| enteredPassword == null){
  return false;  
}
if (this.userName == null || this.password == null){    
return false;
}
return enteredUserName.equals(this.userName) && enteredPassword.equals(this.password);
}
// Return login message
public String returnLoginStatus(String enteredUserName,String enteredPassword){
    if(this.loginUser(enteredUserName, enteredPassword)){
        return " Welcome " + this.firstName + "," + this.lastName + " It is great to see you again ";
        
}else{
        return " Username or pasword incorrect , please try again. " ;
  
}
}

}

