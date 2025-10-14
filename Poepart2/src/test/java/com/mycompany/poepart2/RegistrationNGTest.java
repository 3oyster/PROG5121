/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package com.mycompany.poepart2;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_Lab
 */
public class RegistrationNGTest {
    
    public RegistrationNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of userNameCheck method, of class Registration.
     */
    @Test
    public void testUserNameCheck() {
        System.out.println("userNameCheck");
        String username = "";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.userNameCheck(username);
        assertEquals(result, expResult);
        
    }

    /**
     * Test of passwordCheck method, of class Registration.
     */
    @Test
    public void testPasswordCheck() {
        System.out.println("passwordCheck");
        String password = "";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.passwordCheck(password);
        assertEquals(result, expResult);
       
    }

    /**
     * Test of cellNumberCheck method, of class Registration.
     */
    @Test
    public void testCellNumberCheck() {
        System.out.println("cellNumberCheck");
        String cellphone = "";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.cellNumberCheck(cellphone);
        assertEquals(result, expResult);
      
    }

    /**
     * Test of signUp method, of class Registration.
     */
    @Test
    public void testSignUp() {
        System.out.println("signUp");
        Registration instance = new Registration();
        instance.signUp();
      
    }

    /**
     * Test of signIn method, of class Registration.
     */
    @Test
    public void testSignIn() {
        System.out.println("signIn");
        Registration instance = new Registration();
        instance.signIn();
       
    }

    /**
     * Test of sendMessage method, of class Registration.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        Registration instance = new Registration();
        instance.sendMessage();
       
    }

    /**
     * Test of checkMessageId method, of class Registration.
     */
    @Test
    public void testCheckMessageId() {
        System.out.println("checkMessageId");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.checkMessageId();
        assertEquals(result, expResult);
       
    }

    /**
     * Test of createMessageHash method, of class Registration.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        String messageId = "";
        int messageNum = 0;
        String messageContent = "";
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.createMessageHash(messageId, messageNum, messageContent);
        assertEquals(result, expResult);
      
    }

    /**
     * Test of returnTotalMessages method, of class Registration.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Registration instance = new Registration();
        int expResult = 0;
        int result = instance.returnTotalMessages();
        assertEquals(result, expResult);
      
    }

    /**
     * Test of printMessages method, of class Registration.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.printMessages();
        assertEquals(result, expResult);
      
    }
    
}
