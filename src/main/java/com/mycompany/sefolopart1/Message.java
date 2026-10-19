/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sefolopart1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Message {
    

    // details of ONE message
    private String messageID;
    private int numMessagesSent;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    
    
     // number given to the next message (starts at 0)
    private static int messageCounter = 0;
    
    // how many messages were actually sent
    private static int totalMessagesSent = 0;   
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    

    // constructor - builds a message and gives it an ID, a number and a hash
    public Message(String recipientCell, String messageText) {

        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.numMessagesSent = messageCounter;
        this.messageHash = createMessageHash();
    }
    
    
  // constructor that uses the Scanner to ask the user for the recipient and the message
    public Message(Scanner input) {

        // recipient
        System.out.println(" Enter the recipient cell number: ");
        recipientCell = input.nextLine();

        while (checkRecipientCell(recipientCell).contains(" incorrectly formattedcelby"))
        {
            System.out.println(" Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again. ");
            System.out.println(" Enter the recipient cell number: ");
            recipientCell = input.nextLine();
        }

        // message
        System.out.println(" Enter your message: ");
        messageText = input.nextLine();

        while (!checkMessageLength(messageText))
        {
            System.out.println(" Please enter a message of less than 250 characters. ");
            System.out.println(" Enter your message: ");
            messageText = input.nextLine();
        }
        System.out.println(" Message sent ");

        // the system creates the ID, the message number and the hash
        messageID = generateMessageID();
        numMessagesSent = messageCounter;
        messageHash = createMessageHash();
    }

    // empty constructor - used to get a Message object that can run the checks
    public Message() {

    }

    // generates a random ten-digit number
    private String generateMessageID() {

        // Math.random() gives a number from 0 to 1, so this gives a number from 1000000000 to 9999999999
        long number = (long) (Math.random() * 9000000000L) + 1000000000L;

        return String.valueOf(number);
    }


    // checks the message ID is not more than ten characters
    public boolean checkMessageID() {

        if (messageID != null && messageID.length() < 10)
        {
            return true;
        }
        return false;
    }


    // checks the recipient starts with the international code (+27) and is 12 characters long
    public String checkRecipientCell(String recipient) {

        if (recipient.startsWith("+27") && recipient.length() == 12)
        {
            return " Cell phone number successfully captured ";
        }
        return " Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again. ";
        
    }


    // checks the message is not longer than 250 characters
    public boolean checkMessageLength(String text) {

        if (text.length() <= 250)
        {
            return true;
        }
        return false;
    }


    // hash = first two digits of ID : message number : first word + last word (ALL CAPS)
    public String createMessageHash() {

        String fstTwo = messageID.substring(0, 2);

        String[] words = messageText.trim().split("\\s+");
        String fstWord = words[0];
        String lstWord = words[words.length - 1];

        return (fstTwo + ":" + numMessagesSent + ":" + fstWord + lstWord).toUpperCase();
    }


    // lets the user choose to send, store, or disregard the message
    public String sentMessage(Scanner input) {

        System.out.println(" 1) Send Message ");
        System.out.println(" 2) Disregard Message ");
        System.out.println(" 3) Store Message to send later ");
        System.out.println(" Choose an option: ");
        int option = readNum(input);

        while (option < 1 || option > 3)
        {
            System.out.println(" Please choose 1, 2 or 3. ");
            option = readNum(input);
        }

        String result = sentMessage(option);
        
        // disregarding: the message is only deleted once the user presses 0
        if (option == 2)
        {
            System.out.println(" " + result + " ");
            String answer = input.nextLine();

            while (!answer.equals("0"))
            {
                System.out.println(" Press 0 to delete the message ");
                answer = input.nextLine();
            }
        }

        return result;
    }


    // does the option they chose: 1 = send, 2 = disregard, 3 = store
    public String sentMessage(int option) {

        switch (option)
        {
            case 1:
                sentMessages.add(this);
                totalMessagesSent++;
                messageCounter++;
                return "Message successfully sent";

            case 2:
                return "Press 0 to delete the message";

            case 3:
                storedMessages.add(this);
                messageCounter++;
                storeMessage();
                return "Message successfully stored";

            default:
                return "Invalid option";
        }
    }


    // returns all the messages sent while the program is running
    public String printMessages() {

        if (sentMessages.isEmpty())
        {
            return "No messages have been sent yet.";
        }

        String all = "";
        for (int i = 0; i < sentMessages.size(); i++)
        {
            all = all + sentMessages.get(i).printMessage() + "\n\n";
        }
        return all;
    }


    // full details in the required order: ID, Hash, Recipient, Message
    public String printMessage() {

        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipientCell + "\n"
                + "Message: " + messageText;
    }


    // total of all messages sent
    public static int returnTotalMessages() {

        return totalMessagesSent;
    }


    // writes ALL stored messages to StoredMessages.json
    public void storeMessage() {

        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < storedMessages.size(); i++)
        {
            Message m = storedMessages.get(i);

            json.append("  {\n");
            json.append("    \"messageID\": \"").append(escapeJson(m.messageID)).append("\",\n");
            json.append("    \"messageHash\": \"").append(escapeJson(m.messageHash)).append("\",\n");
            json.append("    \"recipient\": \"").append(escapeJson(m.recipientCell)).append("\",\n");
            json.append("    \"message\": \"").append(escapeJson(m.messageText)).append("\"\n");
            json.append("  }");

            if (i < storedMessages.size() - 1)
            {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("]");

        try (FileWriter writer = new FileWriter("StoredMessages.json"))
        {
            writer.write(json.toString());
        }
        catch (IOException e)
        {
            System.out.println(" Could not write the JSON file: " + e.getMessage());
        }
    }


    // makes text safe to place inside JSON quotes
    private static String escapeJson(String text) {

        return text.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
    
    // keeps asking until the types a whole number 
    public static int readNum(Scanner input){
        int number = 0;
        boolean isValid = false;
        
        while (!isValid)
        {
            try
            {
                number = Integer.parseInt(input.nextLine().trim());
                isValid = true; 
            }
            catch (NumberFormatException e)
            {
                System.out.println(" Please enter a number. ");
            }
        }
        return number;
    }
}