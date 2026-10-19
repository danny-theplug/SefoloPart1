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
        
        //prompt user how many messages they want to send
        System.out.println(" How many messages do you wish to enter ");
        int maxMessages = Message.readNum(input);
        int usedMessages = 0;
        boolean totalShow = false;
        
        // menu of the options
        System.out.println(" Enter 1) Send messages ");
        System.out.println(" Enter 2) Show recently sent messages 'COMING SOON'. ");
        System.out.println(" Enter 3) Quit ");
        
        // prompt the userto enter a number
        System.out.println(" Enter a number between 1-3 ");
        int option = Message.readNum(input);
        
        
        while ( option != 3 && option < 4)
        {
          switch (option)
          {
              case 1: 
                  if (usedMessages > maxMessages)
                  {
                      System.out.println(" You have already all " + maxMessages + " messages. ");
                  }
                  else 
                  {
                      // asks for recipient phone number and the message
                      Message message = new Message (input);
                      
                      // send, disregard, store
                      String result = message.sentMessage(input);
                      
                      // the disregard message is already shown while waiting for 0
                            if (!result.equals("Press 0 to delete the message"))
                            {
                                System.out.println(" " + result + " ");
                            }
                            
                            if (result.equals("Message successfully sent"))
                            {
                                System.out.println(message.printMessage());
                            }
                            
                            usedMessages++;
                            
                            // total is shown once all the messages have been entered
                            if (usedMessages == maxMessages)
                            {
                                System.out.println(" Total messages sent: " + Message.returnTotalMessages());
                                totalShow = true;
                            }
                        }
                        break;
                        
              case 2:
                  System.out.println(" Coming Soon ");
                  
              case 3: 
                  if (!totalShow)
                  {
                      System.out.println(" Total messages sent: " + Message.returnTotalMessages());
                  }
                  
              default:
                  System.out.println(" Enter a number between 1-3 ");
            }
                 
          
          }
        }
    }
    
    
    

    

