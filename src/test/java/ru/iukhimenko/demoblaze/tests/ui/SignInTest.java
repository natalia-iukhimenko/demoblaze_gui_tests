package ru.iukhimenko.demoblaze.tests.ui;

import ru.iukhimenko.demoblaze.ui.base.GuiTest;
import ru.iukhimenko.demoblaze.ui.elements.SignInForm;
import ru.iukhimenko.demoblaze.ui.pages.HomePage;
import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static ru.iukhimenko.demoblaze.ui.base.DataGenerator.getUniqueUsername;
import static ru.iukhimenko.demoblaze.ui.base.DataGenerator.getValidPassword;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignInTest extends GuiTest {
    private SignInForm signInForm;
    private String username, password;

    @BeforeTest
    public void registerAsTestUser() {
        username = getUniqueUsername();
        password = getValidPassword();
        openHomePage().openSignUpForm().registerAs(username, password).accept();
    }

    @BeforeClass
    public void openSignInForm() {
        signInForm = openHomePage().openSignInForm();
    }

    @Test
    public void usernameIsDisplayedIfSignedInSuccessfully() {
        HomePage home = signInForm.signInWith(username, password);
        String expectedMessageText = "Welcome " + username;
        assertEquals(home.getWelcomeMessageText(), expectedMessageText, "Welcome text on home page doesn't match the expected value");
    }

    @Test
    public void signInWithWrongUsername() {
        Alert userDoesNotExistAlert = signInForm.failSignInWith(getUniqueUsername(), password);
        assertEquals(userDoesNotExistAlert.getText(), "User does not exist.", "Error message for signing in with a wrong username doesn't match the expected value");
        signInForm.accept(userDoesNotExistAlert);
    }

    @Test
    public void signInWithWrongPassword() {
        Alert wrongPasswordAlert = signInForm.failSignInWith(username, username);
        assertEquals(wrongPasswordAlert.getText(), "Wrong password.", "Error message for signing in with a wrong password doesn't match the expected value");
        signInForm.accept(wrongPasswordAlert);
    }

    @Test(dataProvider = "emptyUsernameOrPasswordProvider")
    public void signWithEmptyCredentials(String username, String password) {
        Alert fillDataAlert = signInForm.failSignInWith(username, password);
        assertEquals(fillDataAlert.getText(), "Please fill out Username and Password.", "Error message for signing in with empty credentials doesn't match the expected value");
        signInForm.accept(fillDataAlert);
    }

    @Test
    public void passwordFieldIsMaskedTest() {
        signInForm.setPassword(getValidPassword());
        assertTrue(signInForm.isPasswordFieldMasked(), "Password field is not masked on 'Sign In' form");
    }

    @DataProvider
    private Object[][] emptyUsernameOrPasswordProvider() {
        return new Object[][] {
                {"", getValidPassword()},
                {getUniqueUsername(), ""}
        };
    }
}
