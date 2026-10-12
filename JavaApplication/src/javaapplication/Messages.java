/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;

/**
 *
 * @author matet
 */
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Messages {

    // ---------- Variables ----------
    private String messageID;
    private String message;
    private String recipient;
    private String messageHash;
    private int messageNo;
    private static ArrayList<String> sentMessages = new ArrayList<String>();
    private static int totalNoMessagesSent = 0;

    // ---------- Constructor ----------
    public Messages(int messageNo, String recipient, String message) {
        this.messageNo = messageNo;
        this.recipient = recipient;
        this.message = message;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    // ---------- Message ID ----------
    public void setMessageID(String messageID) {
        this.messageID = messageID;
        this.messageHash = createMessageHash();
    }

    private String generateID() {
        Random random = new Random();
        String id = "";
        int count = 0;

        while (count < 10) {
            id = id + random.nextInt(10);
            count++;
        }
        return id;
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // ---------- Validation ----------
    public String checkRecipientCell() {
        String result = "Cell phone number is incorrectly formatted or does not contain an international code. "
                + "Please correct the number and try again.";

        if (recipient.matches("^\\+\\d{10,11}$")) {
            result = "Cell phone number successfully captured.";
        }
        return result;
    }

    public String checkMessageLength() {
        String result;

        if (message.length() <= 250) {
            result = "Message ready to send.";
        } else {
            int x = message.length() - 250;
            result = "Message exceeds 250 characters by " + x + "; please reduce the size.";
        }
        return result;
    }

    // ---------- Message hash ----------
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = message.trim().split(" ");
        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");

        String hash = firstTwo + ":" + messageNo + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // ---------- Details / send / print ----------
    public String getFullDetails() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message + "\n";
    }

    public String SentMesssage(int choice) {
        String result = "";

        switch (choice) {
            case 1:
                sentMessages.add(getFullDetails());
                totalNoMessagesSent++;
                result = "Message successfully sent.";
                break;
            case 2:
                result = "Press 0 to delete the message.";
                break;
            case 3:
                storeMessages();
                result = "Message successfully stored.";
                break;
            default:
                result = "Invalid selection choice";
                break;
        }
        return result;
    }

    public static String printMessages() {
        String allMessages = "";
        int i = 0;

        while (i < sentMessages.size()) {
            allMessages = allMessages + sentMessages.get(i) + "\n";
            i++;
        }
        return allMessages;
    }

    public int returnTotalMessages() {
        return totalNoMessagesSent;
    }

    // ---------- JSON storage ----------
    @SuppressWarnings("unchecked")
    public boolean storeMessages() {
        boolean stored = false;
        String filePath = "messages.json";
        JSONArray messageArray = new JSONArray();

        File file = new File(filePath);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(reader);
                if (obj instanceof JSONArray) {
                    messageArray = (JSONArray) obj;
                }
            } catch (IOException | ParseException e) {
                System.out.println("Could not read existing file, starting fresh.");
            }
        }

        JSONObject messageObj = new JSONObject();
        messageObj.put("messageID", messageID);
        messageObj.put("messageHash", messageHash);
        messageObj.put("recipient", recipient);
        messageObj.put("message", message);
        messageArray.add(messageObj);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(messageArray.toJSONString());
            writer.flush();
            stored = true;
        } catch (IOException e) {
            System.out.println("Error writing JSON file: " + e.getMessage());
        }

        return stored;
    }

    // ---------- Getters ----------
    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }
}