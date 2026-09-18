/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sefolopart1;

/**
 *
 * @author user
 */
public class Login { 
     String fName;
     String lName;
     String userName;
     String passwords;
     String phoneNumber;
    
    public Login(){
        
    }
    
    
    // login to capture
    
    public Login (String firstName, String lastName, String usrName, String password, String phoneNum){
        
       fName = firstName;
       lName = lastName;
       userName = usrName;
       passwords = password;
       phoneNumber = phoneNum;
       
    }
    
    
    // method to validate username
    public boolean checkUserName (String usrName){
    
        if (userName.length()<=5 && userName.contains("_"))
        {
            return true;
        }
        return false;
       
    }
    
    
    // method to validate password
    public boolean checkPasswordComplexity (String password){
        
        if( passwords.length()< 8)
        {
            return false;
        }
        // declarations
        boolean hasUppercase =false;
        boolean hasNumber = false;
        boolean hasSpecialCharc = false;
        
        // loop to check every character
        for (int i = 0; i < passwords.length(); i++)
        {
          // https://www.w3schools.com/java/ref_string_charat.asp w3schools
          
          char ch = passwords.charAt(i);
          
          //https://www.tutorialspoint.com/java/character_isuppercase.htm TutorialsPoint 
          if (Character.isUpperCase(ch))
          {
              hasUppercase = true;
          }
          else if (Character.isDigit(ch))
          {
              hasNumber = true;
          }
          else if (!Character.isLetter(ch))
          {
              hasSpecialCharc = true;
          }
          
          if (hasUppercase && hasNumber && hasSpecialCharc)
          {
              return true;
          }
            
        }
        return false;
    }
    
    
    public boolean checkCellPhoneNumber(String phoneNum){
        
        if ( phoneNumber.startsWith("+27") && phoneNumber.length() == 12)
        {
            return true;
        }
        return false;
    }
    
    
    // method to register the user
    public String registerUser(){
      
        if (!checkUserName (userName))
        {
            return " Username is not correctly formatted; Please ensure your username "
                    +" contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity (passwords))
        {
            return " Password is not correctly formatted; Please ensure your password  contains at least eigth characters, a capital letter, a number, and a special character ";
        }
        
        return " Username successfully captured. Pasword successfully captured. ";
    }
    
    
    // method to check entered login with the registered
    public boolean loginUser (String entUsername, String entPassword){
        
        if ( userName.equals(entUsername) && passwords.equals(entPassword))
        {
            return true;
        }
        return false;
    }
    
    
    // method to return login message
    public String returnLoginStatus (String entUsername, String entPassword){
        
        if (loginUser(entUsername,entPassword ))
        {
           return " Welcome " + fName + ", " + lName + " it is great to see you again. "; 
        }
        return " Username or password incorrect, please try again. ";
    }
    
}
