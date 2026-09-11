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
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        Login obj = new Login();
        String username;
        String password;
        String cellNumber;
        
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
            }else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscire and is no more than five characters in length.");
            }
        }
        
        while (true) {
            System.out.println("Enter a password (at least 8 characyers; a capital letter, a number and a special character)");
            password = input.nextLine();
            
            if (obj.checkPasswordComplexity(password)){
                System.out.println("Password successfully captured");
                break;
            }else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special character.");
            }
        }
        
        while (true) {
            System.out.println("Enter your cell phone number (with international code, e.g. +27838968976)");
            cellNumber = input.nextLine();
            
            if (obj.checkCellPhoneNumber(cellNumber)) {
                System.out.println("Cell phone number successfully added.");
                break;
            }else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }
        
        //register 
        String registerationMessage = obj.registerUser(username, password, cellNumber, firstName , lastName);
        System.out.println(registerationMessage);
        
        //login 
        if (registerationMessage.equals("Username successfully captured.")) {
            System.out.println();
            System.out.println("=== QuickChat Login ===");

            System.out.print("Enter your username: ");
            String strLoginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String strLoginPassword = input.nextLine();

            boolean blnSuccess = obj.loginUser(strLoginUsername, strLoginPassword);
            System.out.println(obj.returnLoginStatus(blnSuccess));
        }
        input.close();
    }
    
}
