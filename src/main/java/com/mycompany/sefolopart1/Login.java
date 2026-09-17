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
    
        if (usrName.length()<=5 && usrName.contains("_"))
        {
            return true;
        }
        return false;
       
    }
    
    
    // method to validate password
    public boolean checkPasswordComplexity (String password){
        
        if( password.length()< 8)
        {
            return false;
        }
        // declarations
        boolean hasUppercase =false;
        boolean hasNumber = false;
        boolean hasSpecialCharc = false;
        
        // loop to check every character
        for (int i = 0; i < password.length(); i++)
        {
          // https://www.w3schools.com/java/ref_string_charat.asp w3schools
          
          char ch = password.charAt(i);
          
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
        
        if ( phoneNum.startsWith("+27") && phoneNum.length() == 12)
        {
            return true;
        }
        return false;
    }
    
    
    
}
