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
import org.json.simple.parser.ParserException;

public class Messages {
    //variables 
    private String messageID;
    private String message;
    private String recipient;
    private String messageHash;
    private int  messageNo;
    private static ArrayList<String>sentMessages= new ArrayList<String>();
    private static int totalNoMessagesSent = 0;
    
    //constructor
    public Messages(String message, int messageNo, String messageHash, String messageID, String recipient){
        this.message = message;
        this.messageNo = messageNo;
        this.recipient = recipient;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
        
    }
    
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
    
        boolean result = false;
        
        if (messageID.length() <= 10) {
            result = true;
        }
        return result;
    }
    
    public String checkRecipientCell() {
        String result = "Cell phone number is incorrectly formatted, Please reenter the phone numeber.";
        
        if (recipient.matches("^\\+\\d{10,11}$")) {
            result = "Cell phone number has been successfully captured.";
        }
        return result;
    }
    
    public String checkMessageLength()  {
        String result ;
        
        if (message.length() <= 250 ) {
            result = "Message is ready to send";
        }else {
            int x = message.length() - 250;
            result = "Message exceeds 250 characters by " + x + "; Please reduce the size.";
        }
        return result;
    }
    
    //message hash task
    public String createMessageHash() {
        String firstTwo = messageID.substring(0,2);
        String[] words = message.trim().split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        String hash;
        
        firstWord = firstWord.replaceAll("[^A-Za-z0-9]", " ");
        lastWord = lastWord.replaceAll("[^A-Za-z0-9]", " ");
        hash = firstTwo + ";" + messageNo+ ":" + firstWord + lastWord;
        
        return hash.toUpperCase();
       
    }
    
    public String SentMesssage(int choice) { 
        String result;
        
        switch (choice) {
            case 1: sentMessages.getFullDetails();
                    totalNoMessagesSent++;
                    break;
            case 2: 
                    result ="Press 0 to delete the message.";
                    break;
            case 3: storeMessage();
                    result = "Message successfully stored.";
                    break;
            default: result = "Invalid selection choice";
                     break;
        }
        return result;
        
    }
    
    public String getFullDetails(){
            return "MessageID:" + messageID 
                    + "\nMessageHash:" + messageHash +
                    "\nRecipient:" + recipient + 
                    "\nMessage: " + message;
        }
        
        public static String printMessages(){
            String allMessages = "";
            
            int i =0;
            
            while (i < sentMessages.size()) {
                allMessages = allMessages + sentMessages.get(i) + "\n";
                i++;
            }
            return allMessages;
        }
        
        public int returnTotalMessages() {
            return totalNoMessagesSent;
        }
}
