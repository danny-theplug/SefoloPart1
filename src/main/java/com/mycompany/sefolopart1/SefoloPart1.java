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
        
        System.out.println(" Enter your lastName: ");
        lastName = input.nextLine();
        
        // prompt the user
        System.out.println(" Enter your username: ");
        usrName = input.nextLine();
       
        // check username condtions
        while ( !checkUserName(usrName) )
        {
            System.out.println(" Username is not correctly formatted ");
            System.out.println(" Please ensure your user name contains an underscore (_) ");
            System.out.println(" Username must be no more than 5 characters in length ");
           
            System.out.println(" Enter your username: ");
            usrName = input.nextLine();
            break;
        }
        
        
            System.out.println(" Username succesfully captured ");
            
        
        // prompt user for password
        System.out.println(" Enter your password: ");
        password = input.nextLine();
        
        // check password conditions
        while (!checkPasswordComplexity(password))
        {
            System.out.println(" Password is not correctly formatted ");
            System.out.println(" Ensure that the password contains at least 8 characters ");
            System.out.println(" Password must contain a capital letter ");
            System.out.println(" Password must contain a number ");
            System.out.println(" Password must contain a special character ");
            
            System.out.println(" Enter your password: ");
            password = input.nextLine();
            break;
        }
        
            System.out.println(" password successfully captured ");
        
        // cell phone number prompt
        System.out.println(" Enter your cellphone number: +27 ");
        phoneNum = input.nextLine();
        
        // cell phone conditions
        while (!checkCellPhoneNumber (phoneNum))
        {
            System.out.println(" Cell phone number incorrectly formatted ");
            System.out.println(" does not contain international code ");
            
            System.out.println(" Enter your cellphone number: +27 ");
            phoneNum = input.nextLine();
        }
        
        System.out.println (" Cell phone number successfully added ") ;
            
            
        // registration conformation
        
        String registerUser();
        
        
        
    }
    
    
    
}
    

