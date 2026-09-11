/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package javaapplication;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;


public class LoginTest {


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

    /**
     * Test of checkUserName method, of class LoginConsole - correctly formatted.
     */
    @Test
    public void testCheckUserName_CorrectlyFormatted() {
        System.out.println("checkUserName");
        String strUsername = "kyl_1";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkUserName(strUsername);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkUserName method, of class LoginConsole - incorrectly formatted.
     */
    @Test
    public void testCheckUserName_IncorrectlyFormatted() {
        System.out.println("checkUserName");
        String strUsername = "kyle!!!!!!!";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkUserName(strUsername);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPasswordComplexity method, of class LoginConsole - meets complexity.
     */
    @Test
    public void testCheckPasswordComplexity_Correct() {
        System.out.println("checkPasswordComplexity");
        String strPassword = "Ch&&sec@ke99!";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(strPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPasswordComplexity method, of class LoginConsole - does not meet complexity.
     */
    @Test
    public void testCheckPasswordComplexity_Incorrect() {
        System.out.println("checkPasswordComplexity");
        String strPassword = "password";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(strPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkCellPhoneNumber method, of class LoginConsole - correctly formatted.
     */
    @Test
    public void testCheckCellPhoneNumber_Correct() {
        System.out.println("checkCellPhoneNumber");
        String strCellNumber = "+27838968976";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(strCellNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkCellPhoneNumber method, of class LoginConsole - incorrectly formatted.
     */
    @Test
    public void testCheckCellPhoneNumber_Incorrect() {
        System.out.println("checkCellPhoneNumber");
        String strCellNumber = "08966553";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(strCellNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerUser method, of class LoginConsole - all details valid.
     */
    @Test
    public void testRegisterUser_Success() {
        System.out.println("registerUser");
        String strUsername = "kyl_1";
        String strPassword = "Ch&&sec@ke99!";
        String strCellNumber = "+27838968976";
        String strFirst = "Kyle";
        String strLast = "Smith";
        Login instance = new Login();
        String expResult = "Username successfully captured.";
        String result = instance.registerUser(strUsername, strPassword, strCellNumber, strFirst, strLast);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerUser method, of class LoginConsole - invalid username.
     */
    @Test
    public void testRegisterUser_BadUsername() {
        System.out.println("registerUser");
        String strUsername = "kyle!!!!!!!";
        String strPassword = "Ch&&sec@ke99!";
        String strCellNumber = "+27838968976";
        String strFirst = "Kyle";
        String strLast = "Smith";
        Login instance = new Login();
        String expResult = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String result = instance.registerUser(strUsername, strPassword, strCellNumber, strFirst, strLast);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUser method, of class LoginConsole - successful login.
     */
    @Test
    public void testLoginUser_Successful() {
        System.out.println("loginUser");
        Login instance = new Login();
        instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String strUsername = "kyl_1";
        String strPassword = "Ch&&sec@ke99!";
        boolean expResult = true;
        boolean result = instance.loginUser(strUsername, strPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of loginUser method, of class LoginConsole - failed login.
     */
    @Test
    public void testLoginUser_Failed() {
        System.out.println("loginUser");
        Login instance = new Login();
        instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String strUsername = "kyl_1";
        String strPassword = "WrongPassword1!";
        boolean expResult = false;
        boolean result = instance.loginUser(strUsername, strPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnLoginStatus method, of class LoginConsole - success message.
     */
    @Test
    public void testReturnLoginStatus_Success() {
        System.out.println("returnLoginStatus");
        Login instance = new Login();
        instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean blnLoginSuccess = true;
        String expResult = "Welcome Kyle, Smith it is great to see you again.";
        String result = instance.returnLoginStatus(blnLoginSuccess);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnLoginStatus method, of class LoginConsole - failure message.
     */
    @Test
    public void testReturnLoginStatus_Failure() {
        System.out.println("returnLoginStatus");
        Login instance = new Login();
        boolean blnLoginSuccess = false;
        String expResult = "Username or password incorrect, please try again.";
        String result = instance.returnLoginStatus(blnLoginSuccess);
        assertEquals(expResult, result);
    }

}