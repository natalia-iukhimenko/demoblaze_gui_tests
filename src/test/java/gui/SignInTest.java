package gui;

import api.SignUpService;
import gui.base.GuiTest;
import gui.elements.SignInForm;
import gui.elements.SignUpForm;
import gui.pages.HomePage;
import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static gui.base.DataGenerator.getUniqueUsername;
import static gui.base.DataGenerator.getValidPassword;
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
    public void usernameIsDisplayedIfSignInSuccessfully() {
        HomePage home = signInForm.signInWith(username, password);
        String expectedMessageText = new StringBuilder("Welcome ").append(username).toString();
        assertEquals(home.getWelcomeMessage(), expectedMessageText);
    }

    @Test
    public void signInWithWrongUsername() {
        Alert userDoesNotExistAlert = signInForm.failSignInWith(getUniqueUsername(), password);
        assertEquals(userDoesNotExistAlert.getText(), "User does not exist.");
        signInForm.accept(userDoesNotExistAlert);
    }

    @Test
    public void signInWithWrongPassword() {
        Alert wrongPasswordAlert = signInForm.failSignInWith(username, username);
        assertEquals(wrongPasswordAlert.getText(), "Wrong password.");
        signInForm.accept(wrongPasswordAlert);
    }

    @Test(dataProvider = "emptyUsernameOrPasswordProvider")
    public void signWithEmptyCredentials(String username, String password) {
        Alert fillDataAlert = signInForm.failSignInWith(username, password);
        assertEquals(fillDataAlert.getText(), "Please fill out Username and Password.");
        signInForm.accept(fillDataAlert);
    }

    @Test
    public void passwordFieldIsMaskedTest() {
        signInForm.setPassword(getValidPassword());
        assertTrue(signInForm.isPasswordFieldMasked());
    }

    @DataProvider
    private Object[][] emptyUsernameOrPasswordProvider() {
        return new Object[][] {
                {"", getValidPassword()},
                {getUniqueUsername(), ""}
        };
    }
}
