package com.mycompany.sefolopart1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
        login.fName = "Kyle";
        login.lName = "Smith";
    }

    // ---------- Username tests ----------

    @Test
    public void testUsername_CorrectlyFormatted() {
        login.userName = "kyl_1";
        assertTrue(login.checkUserName(login.userName));
    }

    @Test
    public void testUsername_IncorrectlyFormatted() {
        login.userName = "kyle!!!!!!";
        assertFalse(login.checkUserName(login.userName));
    }

    // ---------- Password tests ----------

    @Test
    public void testPassword_MeetsComplexity() {
        login.passwords = "Ch&&sec@ke99!";
        assertTrue(login.checkPasswordComplexity(login.passwords));
    }

    @Test
    public void testPassword_DoesNotMeetComplexity() {
        login.passwords = "password";
        assertFalse(login.checkPasswordComplexity(login.passwords));
    }

    // ---------- Cell phone tests ----------

    @Test
    public void testCellPhone_CorrectlyFormatted() {
        login.phoneNumber = "+27838968976";
        assertTrue(login.checkCellPhoneNumber(login.phoneNumber));
    }

    @Test
    public void testCellPhone_IncorrectlyFormatted() {
        login.phoneNumber = "08966553";
        assertFalse(login.checkCellPhoneNumber(login.phoneNumber));
    }

    // ---------- registerUser() message tests ----------

    @Test
    public void testRegisterUser_UsernameFail() {
        login.userName = "kyle!!!!!!";
        assertEquals("Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.",
                login.registerUser());
    }

    @Test
    public void testRegisterUser_PasswordFail() {
        login.userName = "kyl_1";          // must pass username check first
        login.passwords = "password";      // then fails on password
        assertEquals("Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, "
                + "and a special character.",
                login.registerUser());
    }

    @Test
    public void testRegisterUser_Success() {
        login.userName = "kyl_1";
        login.passwords = "Ch&&sec@ke99!";
        login.phoneNumber = "+27838968976";
        assertEquals("Username successfully captured. Password successfully captured. "
                + "Cell phone number successfully added. Registration successful.",
                login.registerUser());
    }

    // ---------- Login tests ----------

    @Test
    public void testLogin_Successful() {
        login.userName = "kyl_1";
        login.passwords = "Ch&&sec@ke99!";
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLogin_Failed() {
        login.userName = "kyl_1";
        login.passwords = "Ch&&sec@ke99!";
        assertFalse(login.loginUser("kyl_1", "wrongPassword"));
    }

    @Test
    public void testReturnLoginStatus_Success() {
        login.userName = "kyl_1";
        login.passwords = "Ch&&sec@ke99!";
        assertEquals("Welcome Kyle, Smith it is great to see you again.",
                login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatus_Failed() {
        login.userName = "kyl_1";
        login.passwords = "Ch&&sec@ke99!";
        assertEquals("Username or password incorrect, please try again.",
                login.returnLoginStatus("kyl_1", "wrongPassword"));
    }
}