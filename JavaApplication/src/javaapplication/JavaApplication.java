/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication;

import java.util.Scanner;

/**
 *
 * @author matet
 */
public class JavaApplication {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login obj = new Login();
        String username;
        String password;
        String cellNumber;

        // ---------- Registration ----------
        System.out.println("=========Registration ===============");
        System.out.println("Enter your first name:");
        String firstName = input.nextLine();

        System.out.println("Enter your last name");
        String lastName = input.nextLine();

        while (true) {
            System.out.println("Enter a username (must contain an underscore) and be no more than 5 characters");
            username = input.nextLine();
            if (obj.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        while (true) {
            System.out.println("Enter a password (at least 8 characters; a capital letter, a number and a special character)");
            password = input.nextLine();
            if (obj.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special character.");
            }
        }

        while (true) {
            System.out.println("Enter your cell phone number (with international code, e.g. +27838968976)");
            cellNumber = input.nextLine();
            if (obj.checkCellPhoneNumber(cellNumber)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        String registrationMessage = obj.registerUser(username, password, cellNumber, firstName, lastName);
        System.out.println(registrationMessage);

        // ---------- Login ----------
        if (registrationMessage.equals("Username successfully captured.")) {
            System.out.println();
            System.out.println("=== QuickChat Login ===");

            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            boolean loginSuccess = obj.loginUser(loginUsername, loginPassword);
            String loginStatusMessage = obj.returnLoginStatus(loginSuccess);
            System.out.println(loginStatusMessage);   // printed once only

            // ---------- Part 2 ----------
            if (loginSuccess) {
                runQuickChat(input);
            } else {
                System.out.println("Login failed. You cannot use QuickChat.");
            }
        }

        input.close();
    }

    // Keeps asking until the user types a whole number
    public static int readNumber(Scanner input, String prompt) {
        int number = -1;
        boolean valid = false;
        while (!valid) {
            System.out.println(prompt);
            String text = input.nextLine();
            try {
                number = Integer.parseInt(text.trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return number;
    }

    public static void runQuickChat(Scanner input) {
        System.out.println("Welcome to QuickChat.");

        int numMessages = readNumber(input, "How many messages do you wish to enter?");
        boolean messagesEntered = false;
        Messages lastMessage = null;

        boolean running = true;
        while (running) {
            System.out.println("\n--- QuickChat Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            int option = readNumber(input, "Choose an option:");

            switch (option) {
                case 1:
                    if (messagesEntered) {
                        System.out.println("You have already entered your " + numMessages + " messages.");
                        break;
                    }

                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");

                        // Recipient: repeat until correctly formatted
                        String recipient;
                        Messages message;
                        while (true) {
                            System.out.println("Enter recipient cell number:");
                            recipient = input.nextLine();
                            message = new Messages(i, recipient, "placeholder");
                            String recipientResult = message.checkRecipientCell();
                            System.out.println(recipientResult);
                            // contains() so small wording differences don't break the loop
                            if (recipientResult.contains("successfully captured")) {
                                break;
                            }
                        }

                        // Message text: repeat until 250 characters or fewer
                        String messageText;
                        while (true) {
                            System.out.println("Enter your message (max 250 characters):");
                            messageText = input.nextLine();
                            message = new Messages(i, recipient, messageText);
                            System.out.println(message.checkMessageLength());
                            if (messageText.length() <= 250) {
                                break;
                            }
                        }

                        // Send / Disregard / Store
                        System.out.println("\nWhat would you like to do with this message?");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message to send later");
                        int choice = readNumber(input, "Choose an option:");
                        System.out.println(message.SentMesssage(choice));

                        if (choice == 1) {
                            System.out.println("\n" + message.getFullDetails());
                        } else if (choice == 2) {
                            String deleteInput = input.nextLine();
                            if (deleteInput.trim().equals("0")) {
                                System.out.println("Message deleted.");
                            }
                        }

                        lastMessage = message;
                    }
                    messagesEntered = true;

                    int total = (lastMessage != null) ? lastMessage.returnTotalMessages() : 0;
                    System.out.println("\nTotal messages sent: " + total);
                    break;

                case 2:
                    String recentMessages = Messages.printMessages();
                    if (recentMessages.equals("")) {
                        System.out.println("No messages have been sent yet.");
                    } else {
                        System.out.println("\n--- Recently Sent Messages ---");
                        System.out.println(recentMessages);
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2 or 3.");
                    break;
            }
        }
    }
}