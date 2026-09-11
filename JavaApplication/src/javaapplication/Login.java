/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author matet
 */
public class Login {
    
    private String strStoredUsername;
    private String strStoredPassword;
    private String strStoredCellPhone;
    private String strFirstName;
    private String strLastName;
    
    
    //username check method
    public boolean checkUserName(String username) {
        boolean blnContainsUnderscore = username.contains("_");
        boolean blnWithinLength = username.length() <= 5;

        boolean blnResult;
        if (blnContainsUnderscore == true && blnWithinLength == true) {
            blnResult = true;
        } else {
            blnResult = false;
        }

        return blnResult;
    } 
    
    
    //password check method
    public boolean checkPasswordComplexity(String password) {
        int intCapitalCount = 0;
        int intNumberCount = 0;
        int intSpecialCount = 0;

        int intIndex = 0;
        while (intIndex < password.length()) {
            char chrCurrent = password.charAt(intIndex);
            String strType = classifyCharacter(chrCurrent);

            switch (strType) {
                case "CAPITAL":
                    intCapitalCount = intCapitalCount + 1;
                    break;
                case "NUMBER":
                    intNumberCount = intNumberCount + 1;
                    break;
                case "SPECIAL":
                    intSpecialCount = intSpecialCount + 1;
                    break;
                default:
                    // lowercase letter - doesn't count toward any rule
                    break;
            }

            intIndex = intIndex + 1;
        }

        boolean blnResult;
        if (password.length() >= 8 && intCapitalCount > 0 && intNumberCount > 0 && intSpecialCount > 0) {
            blnResult = true;
        } else {
            blnResult = false;
        }

        return blnResult;
    }
    
    
    //act as support to checkPasswordComplexity
    private String classifyCharacter(char chrLetter) {
        String strCategory;

        if (Character.isUpperCase(chrLetter)) {
            strCategory = "CAPITAL";
        } else if (Character.isDigit(chrLetter)) {
            strCategory = "NUMBER";
        } else if (Character.isLowerCase(chrLetter)) {
            strCategory = "LOWERCASE";
        } else {
            strCategory = "SPECIAL";
        }

        return strCategory;
    }
    
    //cellphone check method
    public boolean checkCellPhoneNumber(String cellNumber) {
        String regexPattern = "^\\+\\d{10,11}$";
        
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(cellNumber);
        
        return matcher.matches();
    }
    
    //register user method
    public String registerUser(String username, String password, String cellNumber,
                                String firstName, String lastName) {

        String strMessage;

        if (checkUserName(username) == false) {
            strMessage = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        } else if (checkPasswordComplexity(password) == false) {
            strMessage = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        } else if (checkCellPhoneNumber(cellNumber) == false) {
            strMessage = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        } else {
            strStoredUsername = username;
            strStoredPassword = password;
            strStoredCellPhone = cellNumber;
            strFirstName = firstName;
            strLastName = lastName;
            strMessage = "Username successfully captured.";
        }

        return strMessage;
    }
    
    
    //login user method
    public boolean loginUser(String strLoginUsername, String strLoginPassword) {
        boolean blnResult = false;

        if (strStoredUsername == null) {
            blnResult = false;
        } else if (strStoredUsername.equals(strLoginUsername) && strStoredPassword.equals(strLoginPassword)) {
            blnResult = true;
        } else {
            blnResult = false;
        }

        return blnResult;
    }
    
    //login status message
    public String returnLoginStatus(boolean blnLoginSuccess) {
        String strMessage;

        if (blnLoginSuccess == true) {
            strMessage = "Welcome " + strFirstName + ", " + strLastName + " it is great to see you again.";
        } else {
            strMessage = "Username or password incorrect, please try again.";
        }

        return strMessage;
    }
}
