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
        
        if (login.loginUser(loginUsername, loginPassword))
        {
            // QuickChat
            System.out.println(" ----------------- QuickChat---------------");

            System.out.println(" Welcome to QuickChat. ");

            // reads the stored messages from StoredMessages.json into the Stored Messages array
            StoredMessages.loadStoredMessages();

            //prompt user how many messages they want to send
            System.out.println(" How many messages do you wish to enter ");
            int maxMessages = Message.readNum(input);
            int usedMessages = 0;
            boolean totalShow = false;

            int option = 0;

            while (option != 3)
            {
                // menu of the options
                System.out.println(" Enter 1) Send messages ");
                System.out.println(" Enter 2) Show recently sent messages ");
                System.out.println(" Enter 3) Quit ");
                System.out.println(" Enter 4) Stored Messages ");

                // prompt the user to enter a number
                System.out.println(" Enter a number between 1-4 ");
                option = Message.readNum(input);

                switch (option)
                {
                    case 1:
                        if (usedMessages >= maxMessages)
                        {
                            System.out.println(" You have already entered all " + maxMessages + " messages. ");
                        }
                        else
                        {
                            // asks for recipient phone number and the message
                            Message message = new Message (input);

                            // send, disregard, store
                            String result = message.sentMessage(input);

                            // the disregard message is already shown while waiting for 0
                            if (!result.equals("Press 0 to delete the message."))
                            {
                                System.out.println(" " + result + " ");
                            }

                            if (result.equals("Message successfully sent."))
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
                        // shows all the messages sent while the program is running
                        Message recent = new Message();
                        System.out.println(recent.printMessages());
                        break;

                    case 3:
                        if (!totalShow)
                        {
                            System.out.println(" Total messages sent: " + Message.returnTotalMessages());
                        }
                        break;

                    case 4:
                        storedMessagesMenu(input, login);
                        break;

                    default:
                        System.out.println(" Enter a number between 1-4 ");
                }
            }
        }
    }


    // the Stored Messages menu - uses the arrays in the Message class
    private static void storedMessagesMenu(Scanner input, Login login)
    {
        int choice = 0;

        while (choice != 7)
        {
            System.out.println(" ---------- Stored Messages ---------- ");
            System.out.println(" 1) Display the sender and recipient of all stored messages ");
            System.out.println(" 2) Display the longest stored message ");
            System.out.println(" 3) Search for a message ID ");
            System.out.println(" 4) Search for all the messages to a recipient ");
            System.out.println(" 5) Delete a message using the message hash ");
            System.out.println(" 6) Display a report of all the messages ");
            System.out.println(" 7) Back to the main menu ");
            System.out.println(" Enter a number between 1-7 ");
            choice = Message.readNum(input);

            switch (choice)
            {
                case 1:
                    System.out.println(StoredMessages.displaySenderAndRecipient(login.userName));
                    break;

                case 2:
                    System.out.println(" Longest message: " + StoredMessages.displayLongestMessage());
                    break;

                case 3:
                    System.out.println(" Enter the message ID: ");
                    String id = input.nextLine().trim();
                    System.out.println(" Recipient: " + StoredMessages.searchRecipientByID(id));
                    System.out.println(" Message: " + StoredMessages.searchMessageID(id));
                    break;

                case 4:
                    System.out.println(" Enter the recipient cell number: ");
                    String recipient = input.nextLine().trim();
                    System.out.println(StoredMessages.searchByRecipient(recipient));
                    break;

                case 5:
                    System.out.println(" Enter the message hash: ");
                    String hash = input.nextLine().trim();
                    System.out.println(StoredMessages.deleteMessageByHash(hash));
                    break;

                case 6:
                    System.out.println(StoredMessages.displayReport());
                    break;

                case 7:
                    break;

                default:
                    System.out.println(" Enter a number between 1-7 ");
            }
        }
    }
}

