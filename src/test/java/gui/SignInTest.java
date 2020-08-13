package gui;

import api.SignUpService;
import gui.base.GuiTest;
import gui.elements.SignInForm;
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
    public void openSignInForm() {
        signInForm = openHomePage()
                .openSignInForm();
    }

    @BeforeClass
    public void createTestUser() {
        username = getUniqueUsername();
        password = getValidPassword();
        SignUpService.signUpAs(username, password);
    }

    @Test
    public void usernameIsDisplayedIfSignInSuccessfully() {
        HomePage home = signInForm.signInWith(username, password);
        String expectedMessageText = new StringBuilder("Welcome ").append(username).toString();
        assertEquals(home.getWelcomeMessage(), expectedMessageText);
    }

    @Test
    public void signWithWrongUsername() {
        // User does not exist.
    }

    @Test
    public void signWithWrongPassword() {
        // Wrong password.
    }

    @Test(dataProvider = "emptyUsernameOrPasswordProvider")
    public void signWithEmptyCredentials(String username, String password) {
        Alert fillDataAlert = signInForm.failSignInWith(username, password);
        assertEquals(fillDataAlert.getText(), "Please fill out Username and Password.");
        signInForm.accept(fillDataAlert);
    }

    @Test
    public void allFieldsAreClearedOnFormClose() {

    }

    @Test
    public void signUpFormIsHiddenOnCloseButtonClick() {

    }

    @Test
    public void signUpFormIsHiddenOnCrossButtonClick() {

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
