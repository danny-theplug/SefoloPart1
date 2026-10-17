/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sefolopart1;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class SefoloPart1 {

    public static void main(String[] args) {
        
      
        
        Scanner input = new Scanner (System.in);
        Login login = new Login();
        
        // Declaration
        String usrName;
        String password;
        String phoneNum;
        String firstName;
        String lastName;
        
        // prompt the user first and last names
        System.out.println(" Enter your firstName: ");
        firstName = input.nextLine();
        login.fName = firstName;
        
        System.out.println(" Enter your lastName: ");
        lastName = input.nextLine();
        login.lName = lastName;
        
        // prompt the user
        System.out.println(" Enter your username: ");
        usrName = input.nextLine();
        login.userName = usrName;
        
        
        // check username condtions
        while ( !login.checkUserName(usrName) )
        {
            System.out.println(" Username is not correctly formatted ");
            System.out.println(" Please ensure your user name contains an underscore (_) ");
            System.out.println(" Username must be no more than 5 characters in length ");
           
            System.out.println(" Enter your username: ");
            usrName = input.nextLine();
            login.userName = usrName;
            break;
        }
        
        
            System.out.println(" Username succesfully captured ");
            
        
        // prompt user for password
        System.out.println(" Enter your password: ");
        password = input.nextLine();
        login.passwords = password;
        
        
        // check password conditions
        while (!login.checkPasswordComplexity(password))
        {
            System.out.println(" Password is not correctly formatted ");
            System.out.println(" Ensure that the password contains at least 8 characters ");
            System.out.println(" Password must contain a capital letter ");
            System.out.println(" Password must contain a number ");
            System.out.println(" Password must contain a special character ");
            
            System.out.println(" Enter your password: ");
            password = input.nextLine();
            login.passwords = password;
            break;
        }
        
            System.out.println(" password successfully captured ");
        
        // cell phone number prompt
        System.out.println(" Enter your cellphone number: +27 ");
        phoneNum = input.nextLine();
        login.phoneNumber = phoneNum;
        
        // cell phone conditions
        while (!login.checkCellPhoneNumber (phoneNum))
        {
            System.out.println(" Cell phone number incorrectly formatted ");
            System.out.println(" does not contain international code ");
            
            System.out.println(" Enter your cellphone number: +27 ");
            phoneNum = input.nextLine();
            login.phoneNumber = phoneNum;
        }
        
        System.out.println (" Cell phone number successfully added ") ;
            
            
        // registration conformation
        
        System.out.println(login.registerUser());
        
        
        // login
        System.out.println(" ------------Login------------ ");
        
        System.out.println(" Enter your username to login: ");
        String loginUsername = input.nextLine();
        
        System.out.println(" Enter your password to login: ");
        String loginPassword = input.nextLine();
        
        System.out.println(login.returnLoginStatus(loginUsername, loginPassword));
        
        
        // QuickChat
        System.out.println(" ----------------- QuickChat---------------");
        
        System.out.println(" Welcome to QuickChat. ");
        
        // menu of the options
        System.out.println(" Enter 1) Send messages ");
        System.out.println(" Enter 2) Show recently sent messages 'COMING SOON'. ");
        System.out.println(" Enter 3) Quit ");
        
        // prompt the userto enter a number
        System.out.println(" Enter a number between 1-3 ");
        int option;
        
        
        while ( option != 3 && option < 4)
        {
          switch (option)
          {
              case 1: 
                  if (messageUsed > maxMessages)
                  {
                      System.out.println(" You have already all " + maxMessages + " messages. ");
                  }
                  else
                  {
                      System.out.println(" Enter the recipient cell phone number: +27 ");
                      String recipient = input.nextLine();
                      
                      Message check = new Message ( recipient, " check ");
                      while (!check.checkRecipientCell())
                      {
                          System.out.println(" Cell phone number not correctly formatted,");
                          System.out.println(" Ensure that number has no more than 10 characters, ");
                          System.out.println(" Does not contain international code. ");
                          
                          System.out.println(" Enter the recipient cell phone number: +27 ");
                          recipient = input.nextLine();
                          check = new Message (recipient, "check");
                      }
                      
                  }
          }
        }
    }
    
    
    
}
    

