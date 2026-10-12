/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javaapplication;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matet
 */
public class MessagesIT {

    // Test data from the brief
    private static final String RECIPIENT_1 = "+27718693002";
    private static final String MESSAGE_1 = "Hi Mike, can you join us for dinner tonight?";
    private static final String RECIPIENT_2 = "08575975889";
    private static final String MESSAGE_2 = "Hi Keegan, did you receive the payment?";

    // A known ID so the hash can be predicted (the real ID is random)
    private static final String KNOWN_ID = "0012345678";

    public MessagesIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // ---------- Message length (250 characters) ----------

    /**
     * Test of checkMessageLength method, of class Message. Success case.
     */
    @Test
    public void testCheckMessageLengthSuccess() {
        System.out.println("checkMessageLength - success");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        String expResult = "Message ready to send.";
        String result = instance.checkMessageLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkMessageLength method, of class Message. Failure case:
     * a 260 character message is 10 characters too long.
     */
    @Test
    public void testCheckMessageLengthFailure() {
        System.out.println("checkMessageLength - failure");
        String longText = "";
        int count = 0;
        while (count < 260) {
            longText = longText + "a";
            count++;
        }
        Messages instance = new Messages(0, RECIPIENT_1, longText);
        String expResult = "Message exceeds 250 characters by 10; please reduce the size.";
        String result = instance.checkMessageLength();
        assertEquals(expResult, result);
    }

    // ---------- Recipient cell number ----------

    /**
     * Test of checkRecipientCell method, of class Message. Success case.
     */
    @Test
    public void testCheckRecipientCellSuccess() {
        System.out.println("checkRecipientCell - success");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        String expResult = "Cell phone number successfully captured.";
        String result = instance.checkRecipientCell();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkRecipientCell method, of class Message. Failure case.
     */
    @Test
    public void testCheckRecipientCellFailure() {
        System.out.println("checkRecipientCell - failure");
        Messages instance = new Messages(1, RECIPIENT_2, MESSAGE_2);
        String expResult = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String result = instance.checkRecipientCell();
        assertEquals(expResult, result);
    }

    // ---------- Message hash ----------

    /**
     * Test of createMessageHash method, of class Message. Test case 1.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        instance.setMessageID(KNOWN_ID);
        String expResult = "00:0:HITONIGHT";
        String result = instance.createMessageHash();
        assertEquals(expResult, result);
    }

    /**
     * Test of createMessageHash method, of class Message.
     * Tests the remainder of the hashes in a loop.
     */
    @Test
    public void testCreateMessageHashLoop() {
        System.out.println("createMessageHash - loop");
        String[] messages = {MESSAGE_1, MESSAGE_2};
        String[] expectedHashes = {"00:0:HITONIGHT", "00:1:HIPAYMENT"};

        for (int i = 0; i < messages.length; i++) {
            Messages instance = new Messages(i, RECIPIENT_1, messages[i]);
            instance.setMessageID(KNOWN_ID);
            assertEquals(expectedHashes[i], instance.createMessageHash());
        }
    }

    // ---------- Message ID ----------

    /**
     * Test of checkMessageID method, of class Message.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        boolean expResult = true;
        boolean result = instance.checkMessageID();
        assertEquals(expResult, result);
    }

    /**
     * Test that the generated Message ID is exactly ten digits.
     */
    @Test
    public void testMessageIDGenerated() {
        System.out.println("getMessageID - generated");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        String messageID = instance.getMessageID();
        System.out.println("Message ID generated: " + messageID);
        assertEquals(10, messageID.length());
        assertTrue(messageID.matches("[0-9]{10}"));
    }

    /**
     * Test checkMessageID fails when the ID is longer than ten characters.
     */
    @Test
    public void testCheckMessageIDTooLong() {
        System.out.println("checkMessageID - too long");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        instance.setMessageID("12345678901");
        boolean expResult = false;
        boolean result = instance.checkMessageID();
        assertEquals(expResult, result);
    }

    // ---------- Send / Disregard / Store ----------

    /**
     * Test of SentMessage method, of class Message. Option 1: Send.
     */
    @Test
    public void testSentMessageSend() {
        System.out.println("SentMessage - send");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        String expResult = "Message successfully sent.";
        String result = instance.SentMesssage(1);
        assertEquals(expResult, result);
    }

    /**
     * Test of SentMessage method, of class Message. Option 2: Disregard.
     */
    @Test
    public void testSentMessageDisregard() {
        System.out.println("SentMessage - disregard");
        Messages instance = new Messages(1, RECIPIENT_2, MESSAGE_2);
        String expResult = "Press 0 to delete the message.";
        String result = instance.SentMesssage(2);
        assertEquals(expResult, result);
    }

    /**
     * Test of SentMessage method, of class Message. Option 3: Store.
     * Note: this writes to messages.json in the project folder.
     */
    @Test
    public void testSentMessageStore() {
        System.out.println("SentMessage - store");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        String expResult = "Message successfully stored.";
        String result = instance.SentMesssage(3);
        assertEquals(expResult, result);
    }

    // ---------- Totals and printing ----------

    /**
     * Test of returnTotalMessagess method, of class Message.
     * The total is shared by all tests, so we compare before and after.
     */
    @Test
    public void testReturnTotalMessagess() {
        System.out.println("returnTotalMessagess");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        int totalBefore = instance.returnTotalMessages();
        instance.SentMesssage(1);
        int expResult = totalBefore + 1;
        int result = instance.returnTotalMessages();
        assertEquals(expResult, result);
    }

    /**
     * Test that a discarded message does not increase the total.
     */
    @Test
    public void testDiscardDoesNotIncreaseTotal() {
        System.out.println("returnTotalMessagess - discard");
        Messages instance = new Messages(1, RECIPIENT_2, MESSAGE_2);
        int totalBefore = instance.returnTotalMessages();
        instance.SentMesssage(2);
        int result = instance.returnTotalMessages();
        assertEquals(totalBefore, result);
    }

    /**
     * Test of getFullDetails method, of class Message.
     * Order must be: Message ID, Message Hash, Recipient, Message.
     */
    @Test
    public void testGetFullDetails() {
        System.out.println("getFullDetails");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        instance.setMessageID(KNOWN_ID);
        String expResult = "Message ID: 0012345678"
                + "\nMessage Hash: 00:0:HITONIGHT"
                + "\nRecipient: +27718693002"
                + "\nMessage: Hi Mike, can you join us for dinner tonight?\n";
        String result = instance.getFullDetails();
        assertEquals(expResult, result);
    }

    /**
     * Test of printMessages method, of class Message.
     * A sent message must appear in the printed list.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        instance.SentMesssage(1);
        String result = Messages.printMessages();
        assertTrue(result.contains(MESSAGE_1));
    }

    // ---------- JSON storage ----------

    /**
     * Test of storeMessage method, of class Message.
     */
    @Test
    public void testStoreMessage() {
        System.out.println("storeMessage");
        Messages instance = new Messages(0, RECIPIENT_1, MESSAGE_1);
        boolean expResult = true;
        boolean result = instance.storeMessages();
        assertEquals(expResult, result);
    }

}