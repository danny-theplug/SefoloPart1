/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.sefolopart1;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoredMessagesTest {

    // ---------- Test data from the assignment ----------

    // Message 1 - Sent
    private Message msg1;
    // Message 2 - Stored
    private Message msg2;
    // Message 3 - Disregard
    private Message msg3;
    // Message 4 - Sent (the message ID is the developer ID)
    private Message msg4;
    // Message 5 - Stored
    private Message msg5;


    // runs before every test: clears the arrays and loads the test data
    @BeforeEach
    public void setUp() {

        StoredMessages.sentMessages.clear();
        StoredMessages.disregardedMessages.clear();
        StoredMessages.storedMessages.clear();
        StoredMessages.messageHashes.clear();
        StoredMessages.messageIDs.clear();
        Message.totalMessagesSent = 0;

        msg1 = new Message("0000000001", 0, "+27834557896", "Did you get the cake?");
        msg1.sentMessage(1);

        msg2 = new Message("0000000002", 1, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg2.sentMessage(3);

        msg3 = new Message("0000000003", 2, "+27834484567", "Yohoooo, I am at your gate.");
        msg3.sentMessage(2);

        msg4 = new Message("0838884567", 3, "+27838884567", "It is dinner time!");
        msg4.sentMessage(1);

        msg5 = new Message("0000000005", 4, "+27838884567", "Ok, I am leaving without you.");
        msg5.sentMessage(3);
    }


    // runs after every test: removes the JSON file the tests created
    @AfterEach
    public void tearDown() {

        new File("StoredMessages.json").delete();
    }


    // ---------- Sent Messages array ----------

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {

        assertEquals(2, StoredMessages.returnSentMessages().size());
        assertEquals("Did you get the cake?", StoredMessages.returnSentMessages().get(0));
        assertEquals("It is dinner time!", StoredMessages.returnSentMessages().get(1));
    }


    // ---------- The other arrays ----------

    @Test
    public void testDisregardedMessagesArray() {

        assertEquals(1, StoredMessages.returnDisregardedMessages().size());
        assertEquals("Yohoooo, I am at your gate.", StoredMessages.returnDisregardedMessages().get(0));
    }

    @Test
    public void testStoredMessagesArray() {

        assertEquals(2, StoredMessages.returnStoredMessages().size());
    }

    @Test
    public void testMessageHashAndIDArrays() {

        assertEquals(5, StoredMessages.returnMessageHashes().size());
        assertEquals(5, StoredMessages.returnMessageIDs().size());
        assertTrue(StoredMessages.returnMessageIDs().contains("0838884567"));
        assertTrue(StoredMessages.returnMessageHashes().contains(msg2.messageHash));
    }


    // ---------- Display the longest message ----------

    @Test
    public void testDisplayLongestMessage() {

        assertEquals("Where are you? You are late! I have asked you to be on time.",
                StoredMessages.displayLongestMessage());
    }


    // ---------- Search for a message ID ----------

    @Test
    public void testSearchForMessageID() {

        assertEquals("It is dinner time!", StoredMessages.searchMessageID("0838884567"));
        assertEquals("+27838884567", StoredMessages.searchRecipientByID("0838884567"));
    }

    @Test
    public void testSearchForMessageID_NotFound() {

        assertEquals("Message ID not found.", StoredMessages.searchMessageID("1111111111"));
    }


    // ---------- Search for all the messages to a recipient ----------

    @Test
    public void testSearchMessagesForRecipient() {

        String result = StoredMessages.searchByRecipient("+27838884567");

        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testSearchMessagesForRecipient_NoneFound() {

        assertEquals("No messages were found for +27830000000.",
                StoredMessages.searchByRecipient("+27830000000"));
    }


    // ---------- Delete a message using the message hash ----------

    @Test
    public void testDeleteMessageUsingHash() {

        String result = StoredMessages.deleteMessageByHash(msg2.messageHash);

        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", result);
        assertEquals(1, StoredMessages.returnStoredMessages().size());
        assertEquals(4, StoredMessages.returnMessageHashes().size());
    }

    @Test
    public void testDeleteMessage_HashNotFound() {

        assertEquals("Message hash not found.", StoredMessages.deleteMessageByHash("99:9:NOTHERE"));
    }


    // ---------- Display report ----------

    @Test
    public void testDisplayReport() {

        String report = StoredMessages.displayReport();

        assertTrue(report.contains("Message Hash: " + msg1.messageHash));
        assertTrue(report.contains("Recipient: +27834557896"));
        assertTrue(report.contains("Message: Did you get the cake?"));
        assertTrue(report.contains("Message: It is dinner time!"));
        assertTrue(report.contains("Message: Ok, I am leaving without you."));
    }


    // ---------- Stored messages are read back from the JSON file ----------

    @Test
    public void testLoadStoredMessagesFromJSON() {

        // the stored messages were written to StoredMessages.json in setUp
        StoredMessages.storedMessages.clear();
        StoredMessages.messageHashes.clear();
        StoredMessages.messageIDs.clear();

        StoredMessages.loadStoredMessages();

        assertEquals(2, StoredMessages.returnStoredMessages().size());
        assertEquals("Ok, I am leaving without you.", StoredMessages.returnStoredMessages().get(1));
    }
}