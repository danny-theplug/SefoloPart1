/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sefolopart1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class StoredMessages {
    
  
    // the arrays
    static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<Message> disregardedMessages = new ArrayList<>();
    static ArrayList<Message> storedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();

    // puts a message's hash and ID into the Message Hash array and the Message ID array
    static void addHashAndID(Message message) {

        messageHashes.add(message.messageHash);
        messageIDs.add(message.messageID);
    }


    // ---------- the arrays ----------

    // Sent Messages array - the text of all the messages sent
    public static ArrayList<String> returnSentMessages() {

        ArrayList<String> texts = new ArrayList<>();
        for (int i = 0; i < sentMessages.size(); i++)
        {
            texts.add(sentMessages.get(i).messageText);
        }
        return texts;
    }

    // Disregarded Messages array - the text of all the messages disregarded
    public static ArrayList<String> returnDisregardedMessages() {

        ArrayList<String> texts = new ArrayList<>();
        for (int i = 0; i < disregardedMessages.size(); i++)
        {
            texts.add(disregardedMessages.get(i).messageText);
        }
        return texts;
    }

    // Stored Messages array - the text of all the messages stored
    public static ArrayList<String> returnStoredMessages() {

        ArrayList<String> texts = new ArrayList<>();
        for (int i = 0; i < storedMessages.size(); i++)
        {
            texts.add(storedMessages.get(i).messageText);
        }
        return texts;
    }

    // Message Hash array - all the message hashes
    public static ArrayList<String> returnMessageHashes() {

        return messageHashes;
    }

    // Message ID array - all the message IDs
    public static ArrayList<String> returnMessageIDs() {

        return messageIDs;
    }


    // reads StoredMessages.json back into the Stored Messages array (call this once when the program starts)
    public static void loadStoredMessages() {

        File file = new File("StoredMessages.json");
        if (!file.exists())
        {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            String id = "";
            String hash = "";
            String recipient = "";

            while ((line = reader.readLine()) != null)
            {
                line = line.trim();

                if (line.startsWith("\"messageID\""))
                {
                    id = readJsonValue(line);
                }
                else if (line.startsWith("\"messageHash\""))
                {
                    hash = readJsonValue(line);
                }
                else if (line.startsWith("\"recipient\""))
                {
                    recipient = readJsonValue(line);
                }
                else if (line.startsWith("\"message\""))
                {
                    // the message is the last item of each stored message, so the message can be built now
                    String text = readJsonValue(line);

                    // the message number is the middle part of the hash (e.g. 00:1:HIPAYMENT)
                    int number = 0;
                    String[] parts = hash.split(":");
                    if (parts.length > 1)
                    {
                        try
                        {
                            number = Integer.parseInt(parts[1]);
                        }
                        catch (NumberFormatException e)
                        {
                            number = 0;
                        }
                    }

                    Message loaded = new Message(id, number, recipient, text);
                    storedMessages.add(loaded);
                    addHashAndID(loaded);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(" Could not read the JSON file: " + e.getMessage());
        }
    }


    // takes the value out of one JSON line, e.g.   "recipient": "+27838884567",   gives   +27838884567
    private static String readJsonValue(String line) {

        int start = line.indexOf(": \"") + 3;
        int end = line.lastIndexOf("\"");

        return unescapeJson(line.substring(start, end));
    }


    // the opposite of escapeJson
    private static String unescapeJson(String text) {

        String result = "";
        for (int i = 0; i < text.length(); i++)
        {
            char ch = text.charAt(i);

            if (ch == '\\' && i + 1 < text.length())
            {
                i++;
                char next = text.charAt(i);

                if (next == 'n')
                {
                    result = result + "\n";
                }
                else if (next == 'r')
                {
                    result = result + "\r";
                }
                else if (next == 't')
                {
                    result = result + "\t";
                }
                else
                {
                    result = result + next;
                }
            }
            else
            {
                result = result + ch;
            }
        }
        return result;
    }


    // ---------- the Stored Messages menu ----------

    // a. the sender and recipient of all the stored messages
    public static String displaySenderAndRecipient(String sender) {

        if (storedMessages.isEmpty())
        {
            return "No messages have been stored yet.";
        }

        String all = "";
        for (int i = 0; i < storedMessages.size(); i++)
        {
            all = all + "Sender: " + sender + " | Recipient: " + storedMessages.get(i).recipientCell + "\n";
        }
        return all;
    }


    // b. the longest stored message
    public static String displayLongestMessage() {

        if (storedMessages.isEmpty())
        {
            return "No messages have been stored yet.";
        }

        Message longest = storedMessages.get(0);
        for (int i = 1; i < storedMessages.size(); i++)
        {
            if (storedMessages.get(i).messageText.length() > longest.messageText.length())
            {
                longest = storedMessages.get(i);
            }
        }
        return longest.messageText;
    }


    // c. search for a message ID (looks in the sent, disregarded and stored arrays) and return its message
    public static String searchMessageID(String id) {

        Message found = findByMessageID(id);
        if (found == null)
        {
            return "Message ID not found.";
        }
        return found.messageText;
    }


    // c. the recipient of the message with that ID
    public static String searchRecipientByID(String id) {

        Message found = findByMessageID(id);
        if (found == null)
        {
            return "Message ID not found.";
        }
        return found.recipientCell;
    }


    // looks for a message ID in the three message arrays
    private static Message findByMessageID(String id) {

        for (int i = 0; i < sentMessages.size(); i++)
        {
            if (sentMessages.get(i).messageID.equals(id))
            {
                return sentMessages.get(i);
            }
        }
        for (int i = 0; i < disregardedMessages.size(); i++)
        {
            if (disregardedMessages.get(i).messageID.equals(id))
            {
                return disregardedMessages.get(i);
            }
        }
        for (int i = 0; i < storedMessages.size(); i++)
        {
            if (storedMessages.get(i).messageID.equals(id))
            {
                return storedMessages.get(i);
            }
        }
        return null;
    }


    // d. all the messages sent or stored for one recipient
    public static String searchByRecipient(String recipient) {

        String found = "";

        for (int i = 0; i < sentMessages.size(); i++)
        {
            if (sentMessages.get(i).recipientCell.equals(recipient))
            {
                found = found + "\"" + sentMessages.get(i).messageText + "\" ";
            }
        }
        for (int i = 0; i < storedMessages.size(); i++)
        {
            if (storedMessages.get(i).recipientCell.equals(recipient))
            {
                found = found + "\"" + storedMessages.get(i).messageText + "\" ";
            }
        }

        if (found.isEmpty())
        {
            return "No messages were found for " + recipient + ".";
        }
        return found.trim();
    }


    // e. deletes a message using its message hash
    public static String deleteMessageByHash(String hash) {

        // stored messages
        for (int i = 0; i < storedMessages.size(); i++)
        {
            if (storedMessages.get(i).messageHash.equalsIgnoreCase(hash))
            {
                Message removed = storedMessages.remove(i);
                removeHashAndID(removed);

                // the JSON file is written again, so the deleted message is gone from the file too
                removed.storeMessage();
                return "Message: \"" + removed.messageText + "\" successfully deleted.";
            }
        }

        // sent messages
        for (int i = 0; i < sentMessages.size(); i++)
        {
            if (sentMessages.get(i).messageHash.equalsIgnoreCase(hash))
            {
                Message removed = sentMessages.remove(i);
                removeHashAndID(removed);
                Message.totalMessagesSent--;
                return "Message: \"" + removed.messageText + "\" successfully deleted.";
            }
        }

        // disregarded messages
        for (int i = 0; i < disregardedMessages.size(); i++)
        {
            if (disregardedMessages.get(i).messageHash.equalsIgnoreCase(hash))
            {
                Message removed = disregardedMessages.remove(i);
                removeHashAndID(removed);
                return "Message: \"" + removed.messageText + "\" successfully deleted.";
            }
        }

        return "Message hash not found.";
    }


    // takes this message's hash and ID out of the two arrays
    private static void removeHashAndID(Message message) {

        messageHashes.remove(message.messageHash);
        messageIDs.remove(message.messageID);
    }


    // f. a report with the full details of all the messages sent and stored
    public static String displayReport() {

        String report = "------ SENT MESSAGES ------\n";
        if (sentMessages.isEmpty())
        {
            report = report + "No messages have been sent yet.\n";
        }
        for (int i = 0; i < sentMessages.size(); i++)
        {
            report = report + sentMessages.get(i).printMessage() + "\n\n";
        }

        report = report + "------ STORED MESSAGES ------\n";
        if (storedMessages.isEmpty())
        {
            report = report + "No messages have been stored yet.\n";
        }
        for (int i = 0; i < storedMessages.size(); i++)
        {
            report = report + storedMessages.get(i).printMessage() + "\n\n";
        }
        return report;
    }

    
}


